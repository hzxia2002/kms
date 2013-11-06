package com.article.controller;

import com.article.daoservice.*;
import com.article.domain.*;
import com.article.util.Constants;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date: 13-10-30
 * Time: 上午10:28
 */
@Controller
public class PageController extends BaseCRUDActionController {
    @Autowired
    private CmsArticleService articleService;

    @Autowired
    private CmsCatalogService cmsCatalogService;

    @Autowired
    private CmsCollectCatagoryService cmsCollectCatagoryService;

    @Autowired
    private CmsCollectArticleService cmsCollectArticleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CmsStudyPlanService cmsStudyPlanService;

    @Autowired
    private DocAttachmentsService docAttachmentsService;


    @RequestMapping
    public String view(Model model,Long id) throws Exception {
        try {
            CmsArticle cmsArticle = articleService.get(id);
            CmsCatalog path = cmsArticle.getPath();
            String paths = cmsArticle.getTitle();
            while (path!=null) {
                paths = path.getName()+ ">>" + paths;
                if(path.getParent()==null){
                    if(path.getPath().equals(Constants.LAW_PROGRAM_KMS)){
                        model.addAttribute("type","2");
                    }else if (path.getPath().equals(Constants.INVESTIGATION_KMS)){
                        model.addAttribute("type","3");
                    }else if (path.getPath().equals(Constants.CASE_KMS)){
                        model.addAttribute("type","4");
                    }
                }
                path = path.getParent();
            }
            //点击次数增加1
            Long visitTimes = cmsArticle.getVisitTimes();
            if(visitTimes==null){
                visitTimes = 1L;
            }else{
                visitTimes += 1L;
            }
            cmsArticle.setVisitTimes(visitTimes);
            Long docId = cmsArticle.getDocId();
            if(docId!=null){
                List<DocAttachments> docAttachmentses = docAttachmentsService.find("from DocAttachments where doc.id=" + docId);
                model.addAttribute("docAttachmentses", docAttachmentses);
            }
            model.addAttribute("bean", cmsArticle);
            model.addAttribute("paths",paths);


            //文章保存
            articleService.save(cmsArticle);

        } catch (Exception e) {
            log.error("error", e);
        }

        return "pages/view";
    }

    @RequestMapping
    public String preView(Model model,Long id) throws Exception {
        try {
            CmsArticle cmsArticle = articleService.get(id);
            CmsCatalog path = cmsArticle.getPath();
            String paths = cmsArticle.getTitle();
            while (path!=null) {
                paths = path.getName()+ ">>" + paths;
                if(path.getParent()==null){
                    if(path.getPath().equals(Constants.LAW_PROGRAM_KMS)){
                        model.addAttribute("type","2");
                    }else if (path.getPath().equals(Constants.INVESTIGATION_KMS)){
                        model.addAttribute("type","3");
                    }else if (path.getPath().equals(Constants.CASE_KMS)){
                        model.addAttribute("type","4");
                    }
                }
                path = path.getParent();
            }

//            Long docId = cmsArticle.getDocId();
            //点击次数增加1
            Long visitTimes = cmsArticle.getVisitTimes();
            if(visitTimes==null){
                visitTimes = 0L;
            }
            cmsArticle.setVisitTimes(visitTimes);
            model.addAttribute("bean", cmsArticle);
            model.addAttribute("paths",paths);


            //文章保存
            articleService.save(cmsArticle);

        } catch (Exception e) {
            log.error("error", e);
        }

        return "pages/preView";
    }



    @RequestMapping
    public String index(Model model) throws Exception {
        //取推荐主题
        String hql = "from CmsArticle t where t.isTop=1 order by t.publishTime desc";
        Page page = new Page();
        page = articleService.findByPage(page, hql);
        List rows = page.getRows();
        if(rows.size()>3){
            rows = rows.subList(0, 3);
        }
        model.addAttribute("recommends",rows);

        //取最新知识
        hql = "from CmsArticle t  order by t.publishDate desc";
        page = articleService.findByPage(page, hql);
        List newKmsRows = page.getRows();
        model.addAttribute("newKmsRows",newKmsRows);

        //取热点知识
        hql = "from CmsArticle t  order by t.visitTimes desc";
        page = articleService.findByPage(page, hql);
        List hotKmsRows = page.getRows();
        model.addAttribute("hotKmsRows",hotKmsRows);

        //首页样式控制
        model.addAttribute("type","1");
        return "pages/index";
    }

    @RequestMapping
    public String lawProgramIndex(Model model) throws Exception {

        //样式控制
        model.addAttribute("type","2");
        return "pages/lawProgramIndex";
    }

    @RequestMapping
    public String kmsIndex(Model model) throws Exception {

        //样式控制
        model.addAttribute("type","3");
        return "pages/kmsIndex";
    }

    @RequestMapping
    public String caseIndex(Model model) throws Exception {

        //样式控制
        model.addAttribute("type","4");
        return "pages/caseIndex";
    }

    @RequestMapping
    public String studyIndex(Model model) throws Exception {

        //样式控制
        model.addAttribute("type","5");
        return "pages/studyIndex";
    }

    @RequestMapping
    public void collect(HttpServletResponse response,HttpServletRequest request,Model model,String title,Long dictory_val,Long articleId) throws Exception {
        if(cmsCollectArticleService.find("from CmsCollectArticle where article.id=" + articleId).size()>0){
            sendSuccessJSON(response, "该文章已经收藏");
        }else{
            CmsCollectArticle cmsCollectArticle = new CmsCollectArticle();
            cmsCollectArticle.setCatagory(cmsCollectCatagoryService.get(dictory_val));
            cmsCollectArticle.setRemark(title);
            cmsCollectArticle.setUrl("/page/view.html?id="+articleId);
            Long id = SpringSecurityUtils.getCurrentUser().getId();
            cmsCollectArticle.setUser(sysUserService.get(id));
            cmsCollectArticle.setArticle(articleService.get(articleId));
            cmsCollectArticleService.save(cmsCollectArticle);
            sendSuccessJSON(response, "添加收藏成功");
        }
    }

    @RequestMapping
    public String collectInit(HttpServletResponse response,Model model,String title,Long articleId) throws Exception {
        model.addAttribute("title", new String(title.getBytes("iso-8859-1"),"utf-8"));
        model.addAttribute("articleId", articleId);
        return "pages/collectInit";
    }

    /**
     * 取得树数据
     *
     * @param type
     *            节点类型
     * @param uid
     *            节点ID
     * @throws Exception
     *             异常
     */
    @RequestMapping
    @ResponseBody
    public List<Node> dictoryTree(String type,String uid, String id) throws Exception {
        TreeBranch treeBranch = new TreeBranch();
        type = StringUtils.defaultString(type, "");

        String hql = "from CmsCollectCatagory where parent.id is null order by treeId";
        if(StringUtils.isNotEmpty(uid)) {
            hql = "from CmsCollectCatagory where parent.id = " + uid + " order by treeId";
        }

        List<CmsCollectCatagory> cmsCollectCatagorys = cmsCollectCatagoryService.findByQuery(hql);

        for (CmsCollectCatagory cmsCollectCatagory : cmsCollectCatagorys) {
            ZTreeNode treeNode = new ZTreeNode();
            treeNode.setId(cmsCollectCatagory.getClass().getSimpleName() + "_" + cmsCollectCatagory.getId());
            if(!cmsCollectCatagory.getIsLeaf()){
                treeNode.setIsParent(true);
                treeNode.setOpen(false);
            }else{
                treeNode.setIsLeaf(true);
            }
            treeNode.setName(cmsCollectCatagory.getName());
            treeNode.setText(cmsCollectCatagory.getName());
            treeNode.setUid(cmsCollectCatagory.getId().toString());
            treeNode.setType(cmsCollectCatagory.getClass().getSimpleName());
            treeNode.setTreeId(cmsCollectCatagory.getTreeId());
            treeBranch.addTreeNode(treeNode);
        }

        return treeBranch.getTreeNodeList();
    }

    @RequestMapping
    @ResponseBody
    public List<Map> cagalogTree(String type,String uid, String id,String treeType) throws Exception {
        List<Map> treeDataList = new ArrayList<Map>();

        String hql = "from CmsCatalog where parent.id is null and path='"+treeType+"'";
        List<CmsCatalog> cmsCatalogs = cmsCatalogService.findByQuery(hql);
        if(cmsCatalogs.size()>0){
            String treeId = cmsCatalogs.get(0).getTreeId();


            HashMap rootMap = new HashMap();
            rootMap.put("text","导航");
            rootMap.put("id",-1L);
            rootMap.put("isLoaded", true);

            treeDataList.add(rootMap);

            hql = "from CmsCatalog where treeId like '" + treeId + "%' order by treeId";
            cmsCatalogs = cmsCatalogService.findByQuery(hql);
            for (CmsCatalog cmsCatalog : cmsCatalogs) {
                HashMap map = new HashMap();
                map.put("text",cmsCatalog.getName());
                map.put("id",cmsCatalog.getId());
                if(cmsCatalog.getParent()!=null){
                    map.put("pid",cmsCatalog.getParent().getId());
                }else{
                    map.put("pid",-1L);
                }
                treeDataList.add(map);
            }
        }


        return treeDataList;
    }

    @RequestMapping
    @ResponseBody
    public Map getContent(Long id,Integer pageNo,String treeType,Integer pageSize) throws Exception {
        HashMap dataMap = new HashMap();
        List<Map> dataList = new ArrayList<Map>();
        String treeId ;
        if(id==null){
            String hql = "from CmsCatalog where parent.id is null and path='"+treeType+"'";
            List<CmsCatalog> cmsCatalogs = cmsCatalogService.findByQuery(hql);
            treeId = cmsCatalogs.get(0).getTreeId();
        }else{
            CmsCatalog cmsCatalog = cmsCatalogService.get(id);
            treeId = cmsCatalog.getTreeId();
        }

        String hql = "from CmsArticle c where c.path.treeId like'"+treeId+"' order by publishDate desc";
        if(pageNo==null){
            pageNo = 1;
        }
        if(pageSize==null){
            pageSize = 10;
        }
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page.setPage(pageNo);
        Page<CmsArticle> cmsArticlePage = articleService.findByPage(page, hql);
        List<CmsArticle> rows = cmsArticlePage.getRows();

        for (CmsArticle row : rows) {
            HashMap map = new HashMap();
            map.put("id",row.getId());
            map.put("title",row.getTitle());
            map.put("publishDate",row.getPublishDate());
            dataList.add(map);
        }
        dataMap.put("total",cmsArticlePage.getTotal());
        dataMap.put("page",pageNo);
        dataMap.put("records",cmsArticlePage.getRecords());
        dataMap.put("data",dataList);
        return dataMap;
    }


    @RequestMapping
    @ResponseBody
    public List<Map> studyTree(String type,String uid, String id) throws Exception {
        List<Map> treeDataList = new ArrayList<Map>();

        String hql = "from CmsCollectCatagory";
        List<CmsCollectCatagory> cmsCollectCatagories = cmsCollectCatagoryService.findByQuery(hql);


        HashMap rootMap = new HashMap();
        rootMap.put("text","收藏夹");
        rootMap.put("id",-1L);
        rootMap.put("isLoaded", true);

        treeDataList.add(rootMap);


        for (CmsCollectCatagory cmsCollectCatagory : cmsCollectCatagories) {
            HashMap map = new HashMap();
            map.put("text",cmsCollectCatagory.getName());
            map.put("id",cmsCollectCatagory.getId());
            if(cmsCollectCatagory.getParent()!=null){
                map.put("pid",cmsCollectCatagory.getParent().getId());
            }else{
                map.put("pid",-1L);
            }
            map.put("isLoaded",true);
            treeDataList.add(map);
        }

        rootMap = new HashMap();
        rootMap.put("text","我的学习");
        rootMap.put("id",-2L);
        rootMap.put("isLoaded", true);

        treeDataList.add(rootMap);

        rootMap = new HashMap();
        rootMap.put("text","历史学习任务");
        rootMap.put("id",-3L);
        rootMap.put("isLoaded", true);

        treeDataList.add(rootMap);

        return treeDataList;
    }

    @RequestMapping
    public String myStudy(Long id,Integer pageNo,Integer pageSize,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currendTimeStr = format.format(new Date());
        Long userId = SpringSecurityUtils.getCurrentUser().getId();
        String hql = "from CmsStudyPlan p left join fetch p.course where p.course.startTime<='"+currendTimeStr+"' and p.course.endTime>='"+currendTimeStr+"' and p.user.id="+userId;
        String countHql = "select * from Cms_Study_Plan p  left join Cms_Study_Course c on c.id=p.course_id where c.start_Time<='"+currendTimeStr+"' and c.end_Time>='"+currendTimeStr+"' and p.user_id="+userId;
        Page<CmsStudyPlan> byPage = cmsStudyPlanService.findByPage(page,countHql ,hql);
        model.addAttribute("page",byPage);
        return "pages/myStudy";
    }

    @RequestMapping
    public String studyHistory(Long id,Integer pageNo,Integer pageSize,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currendTimeStr = format.format(new Date());
        Long userId = SpringSecurityUtils.getCurrentUser().getId();
        String hql = "from CmsStudyPlan p left join fetch p.course where  p.course.endTime<'"+currendTimeStr+"' and p.user.id="+userId;
        String countHql = "select * from Cms_Study_Plan p  left join Cms_Study_Course c on c.id=p.course_id where c.end_Time<'"+currendTimeStr+"' and p.user_id="+userId;
        Page<CmsStudyPlan> byPage = cmsStudyPlanService.findByPage(page,countHql ,hql);
        model.addAttribute("page",byPage);
        return "pages/studyHistory";
    }

    /**
     * 创建分页对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page getPage(Integer pageNo, Integer pageSize) {
        if(pageNo==null){
            pageNo = 1;
        }
        if(pageSize==null){
            pageSize = 10;
        }
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page.setPage(pageNo);
        return page;
    }

    @RequestMapping
    public String myCollect(Long id,Integer pageNo,Integer pageSize,String articleName,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);

        Long userId = SpringSecurityUtils.getCurrentUser().getId();

        String hql = "from CmsCollectArticle c left join fetch c.catagory where c.user.id="+ userId;
        String countSql = "select * from Cms_Collect_Article c where c.user_id="+ userId;
        if(id!=null){
            hql = "from CmsCollectArticle c where c.user.id="+ userId+" and c.catagory.id="+id;
            countSql = "select * from Cms_Collect_Article c   where c.user_id="+ userId +" and c.catagory_id="+id;
        }
        if(StringUtils.isNotEmpty(articleName)){
            hql += " and c.remark like '%"+articleName.trim()+"%'";
            countSql +=  " and c.remark like '%"+articleName.trim()+"%'";
        }
        Page<CmsCollectArticle> byPage = cmsCollectArticleService.findByPage(page ,countSql,hql);
        model.addAttribute("page",byPage);
        model.addAttribute("id",id);
        model.addAttribute("articleName",articleName);
        return "pages/collect";
    }

    /**
     *  删除收藏
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void deleteCollect(HttpServletResponse response,Long id) throws Exception {
        cmsCollectArticleService.delete(id) ;
        sendSuccessJSON(response, "成功删除收藏");
    }



}