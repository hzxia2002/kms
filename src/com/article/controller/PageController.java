package com.article.controller;

import com.article.daoservice.*;
import com.article.domain.*;
import com.article.manager.ExaPaperManager;
import com.article.util.Constants;
import com.comet.core.config.CustomizedPropertyPlaceholderConfigurer;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.security.user.BaseUser;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.tree.Node;
import com.comet.system.tree.TreeBranch;
import com.comet.system.tree.ZTreeNode;
import com.comet.system.utils.PrivilegeUtils;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.field.RtfPageNumber;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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

    @Autowired
    private CmsCommentService cmsCommentService;

    @Autowired
    private ExaPaperSectionService exaPaperSectionService;

    @Autowired
    private ExaPaperService exaPaperService;

    @Autowired
    private ConQuestionService conQuestionService;

    @Autowired
    private HashMap systemMap;

    @Autowired
    private ExaPaperManager exaPaperManager;




    @RequestMapping
    public String view(HttpServletRequest request,Model model,Long id) throws Exception {
        try {
            CmsArticle cmsArticle = articleService.get(id);
            CmsCatalog path = cmsArticle.getPath();
            String pathName = path.getPath();
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

                        // 微课程去学习页面，技战法使用普通页面浏览
                        if(StringUtils.equals(pathName, "weikecheng")) {
                            Object pptPrefix = systemMap.get("pptPrefix");
                            Object pptSuffix = systemMap.get("pptSuffix");
                            model.addAttribute("pptPrefix", pptPrefix);
                            model.addAttribute("pptSuffix", pptSuffix);
                            return showCase(request,model,id);
                        }
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

            //取评论
            String commentHql = "from CmsComment where knowledge.id="+id+" order by replyTime desc";
            List<CmsComment> cmsComments = cmsCommentService.find(commentHql);
            model.addAttribute("comments", cmsComments);
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
    public String preView(HttpServletRequest request,Model model,Long id) throws Exception {
        try {
            CmsArticle cmsArticle = articleService.get(id);
            CmsCatalog path = cmsArticle.getPath();
            String paths = cmsArticle.getTitle();

            Object pptPrefix = systemMap.get("pptPrefix");
            Object pptSuffix = systemMap.get("pptSuffix");
            model.addAttribute("pptPrefix", pptPrefix);
            model.addAttribute("pptSuffix", pptSuffix);

            while (path!=null) {
                paths = path.getName()+ ">>" + paths;
                if(path.getParent()==null){
                    if(path.getPath().equals(Constants.LAW_PROGRAM_KMS)){
                        model.addAttribute("type","2");
                    }else if (path.getPath().equals(Constants.INVESTIGATION_KMS)){
                        model.addAttribute("type","3");
                    }else if (path.getPath().equals(Constants.CASE_KMS)){
                        model.addAttribute("type","4");
                        return showCase(request,model,id);
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
    public String printView(HttpServletRequest request,Model model,Long articleId) throws Exception {
        try {
            CmsArticle cmsArticle = articleService.get(articleId);
            CmsCatalog path = cmsArticle.getPath();
            String paths = cmsArticle.getTitle();

            model.addAttribute("bean", cmsArticle);
            model.addAttribute("paths",paths);


        } catch (Exception e) {
            log.error("error", e);
        }

        return "pages/printView";
    }


    @RequestMapping
    public String index(Model model) throws Exception {
        //取推荐主题
        String hql = "from CmsArticle t where t.path.path='"+Constants.TJZT_KMS+"' and t.isPublished=1 and t.isValid=1 and t.path.isValid=1 order by t.publishTime desc";
        Page page = new Page();
        page.setPage(1);
        page.setPageSize(3);
        page = articleService.findByPage(page, hql);

        List rows = page.getRows();

        model.addAttribute("recommends",rows);

        //取最新知识
        hql = "from CmsArticle t  where t.isPublished=1 and t.isValid=1 and t.path.isValid=1 order by t.publishDate desc";
        page.setPageSize(10);
        page = articleService.findByPage(page, hql);
        List newKmsRows = page.getRows();
        model.addAttribute("newKmsRows",newKmsRows);

        //取热点知识
        hql = "from CmsArticle t where t.isPublished=1 and t.isValid=1 and t.path.isValid=1 order by t.visitTimes desc";
        page.setPageSize(10);
        page = articleService.findByPage(page, hql);
        List hotKmsRows = page.getRows();
        model.addAttribute("hotKmsRows",hotKmsRows);

        //首页样式控制
        model.addAttribute("type","1");
        return "pages/index";
    }

    @RequestMapping
    public String more(Long id,Integer pageNo,Integer pageSize,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);
        page.setAutoCount(true);
        //取推荐主题
        String hql = "from CmsArticle t where t.path.path='"+Constants.TJZT_KMS+"' and t.isPublished=1 and t.isValid=1 and t.path.isValid=1 order by t.publishTime desc";
        page = articleService.findByPage(page, hql);

        model.addAttribute("page",page);
        return "pages/more";
    }


    @RequestMapping
    public String showCase(HttpServletRequest request,Model model,Long id) throws Exception {
        try {
            CmsArticle cmsArticle = articleService.get(id);
            CmsCatalog path = cmsArticle.getPath();
            model.addAttribute("type","4");
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
                ArrayList attachments = new ArrayList();
                boolean hasPpt = false;
                boolean hasAVI = false;
                for (DocAttachments docAttachmentse : docAttachmentses) {
                    HashMap<String, Object> attachment = new HashMap<String, Object>();
                    attachment.put("id",docAttachmentse.getId());
                    attachment.put("path",docAttachmentse.getFilePath());
                    String orginName = docAttachmentse.getOrginName();
                    attachment.put("orginName", orginName);
                    attachment.put("name", orginName.substring(0, orginName.lastIndexOf(".")));
                    boolean isPPT = orginName.substring(orginName.lastIndexOf(".") + 1).equalsIgnoreCase("ppt");
                    attachment.put("isPPT", isPPT);
                    if(isPPT){
                        String pptPath = docAttachmentse.getFilePath().replaceAll("\\\\", File.separator);
                        pptPath = pptPath.substring(0,pptPath.lastIndexOf("."));
                        attachment.put("pptPath", pptPath);
                        File file = new File(request.getSession().getServletContext().getRealPath("") + File.separator + pptPath);
                        if(file.exists()){
                            attachment.put("total", file.listFiles(new FileFilter() {
                                public boolean accept(File file) {
                                    return file.getName().toLowerCase().endsWith(systemMap.get("pptSuffix").toString().toLowerCase());
                                }
                            }).length);
                        }else {
                            attachment.put("total",0);
                        }
                        //直接播放第一个ppt
                        if(!hasPpt){
                            model.addAttribute("pptPath", pptPath);
                            model.addAttribute("total", attachment.get("total"));
                            hasPpt = true;
                        }

                    }
                    boolean isTV = "dat,wmv,avi,mp3,mpg,mpeg".indexOf(orginName.substring(orginName.lastIndexOf(".") + 1)) >= 0;
                    attachment.put("isAVI", isTV);
                    if(isTV&&!hasAVI){
                        String tvPath =  docAttachmentse.getFilePath();
                        if(docAttachmentse.getRemark()!=null&&"tv".equals(docAttachmentse.getRemark())){
                            // tvPath = "/fileUpload/downloadTv.do?id=" + docAttachmentse.getId();
                            // tvPath = "/avi/" + docAttachmentse.getFilePath();
                            tvPath = CustomizedPropertyPlaceholderConfigurer.getContextProperty("mediaServiceIp") + docAttachmentse.getFilePath();
                            attachment.put("path", tvPath);
                        }
                        model.addAttribute("aviPath", tvPath);
                    }
                    attachments.add(attachment);
                }
                model.addAttribute("attachments", attachments);
            }
            model.addAttribute("bean", cmsArticle);

            //文章保存
            articleService.save(cmsArticle);

        } catch (Exception e) {
            log.error("error", e);
        }

        return "pages/case";
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
    public String consult(Model model) throws Exception {

        //样式控制
        model.addAttribute("type","6");
        return "pages/consult";
    }

    @RequestMapping
    public String conIndex(Model model,Integer pageNo,Integer pageSize) throws Exception {
        Page page = getPage(pageNo, 20);
        String hql = "from ConQuestion t where t.refId is null order by t.isTop desc";
        Page<ConQuestion> byPage = conQuestionService.findByPage(page,hql);

        List<ConQuestion> rows = byPage.getRows();
        for (ConQuestion row : rows) {
            row.setAsker(row.getAsker().substring(0,1)+"**");
        }
        model.addAttribute("page",byPage);

        //样式控制
        model.addAttribute("type","6");
        return "pages/conIndex";
    }

    @RequestMapping
    public String conView(Model model,Long conId) throws Exception {
        ConQuestion conQuestion = conQuestionService.findUnique("from ConQuestion t where t.id="+conId);

        String hql = "from ConQuestion t where t.refId =" + conId;
        List<ConQuestion> answers = conQuestionService.find(hql);

        conQuestion.setAsker(conQuestion.getAsker().substring(0,1)+"**");
        for (ConQuestion answer : answers) {
            answer.setResponser(answer.getResponser().substring(0,1)+"**");
        }
        model.addAttribute("question",conQuestion);
        model.addAttribute("answers",answers);
        //样式控制
        model.addAttribute("type","6");
        return "pages/conView";
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
        CmsArticle cmsArticle = articleService.get(articleId);
        model.addAttribute("title", cmsArticle.getTitle());
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

        String hql = "from CmsCollectCatagory where parent.id is null ";
        if(StringUtils.isNotEmpty(uid)) {
            hql = "from CmsCollectCatagory where parent.id = " + uid + "";
        }
        if(!PrivilegeUtils.isSysAdmin()){
            hql += " and (user_id is null or user_id="+SpringSecurityUtils.getCurrentUser().getId()+")";
        }
        hql += " order by treeId";

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
            if(cmsCollectCatagory.getName().length()>20){
                treeNode.setText(cmsCollectCatagory.getName().substring(0,20)+"...");
            }
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

        String hql = "from CmsCatalog where parent.id is null and isValid=1 and path='"+treeType+"'";
        List<CmsCatalog> cmsCatalogs = cmsCatalogService.findByQuery(hql);
        if(cmsCatalogs.size()>0){
            String treeId = cmsCatalogs.get(0).getTreeId();


            HashMap rootMap = new HashMap();
            rootMap.put("text","导航");
            rootMap.put("id",-1L);
            rootMap.put("isLoaded", true);

            treeDataList.add(rootMap);

            hql = "from CmsCatalog where treeId like '" + treeId + "%' and isValid=1 order by treeId";
            cmsCatalogs = cmsCatalogService.findByQuery(hql);
            for (CmsCatalog cmsCatalog : cmsCatalogs) {
                HashMap map = new HashMap();

                map.put("text",cmsCatalog.getName());
                if(cmsCatalog.getName().length()>14){
                    map.put("text",cmsCatalog.getName().substring(0,14)+"...");
                }
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
            String hql = "from CmsCatalog where parent.id is null and isValid=1 and path='"+treeType+"'";
            List<CmsCatalog> cmsCatalogs = cmsCatalogService.findByQuery(hql);
            treeId = cmsCatalogs.get(0).getTreeId();
        }else{
            CmsCatalog cmsCatalog = cmsCatalogService.get(id);
            treeId = cmsCatalog.getTreeId();
        }

        String hql = "from CmsArticle c where c.path.treeId like'"+treeId+"%' and c.isPublished=1 and c.isValid=1 and c.path.isValid=1 order by id asc";
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
            map.put("path",row.getAttachPath());
            map.put("author",row.getAuthor());

            // 将文章目录返回到前台
            if(row.getPath() != null) {
                map.put("catalog", row.getPath().getPath());
            } else {
                map.put("catalog", "");
            }

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
            if(cmsCollectCatagory.getType().equals("0")||StringUtils.isEmpty(cmsCollectCatagory.getUserId())){
                map.put("type","no_del");
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

        rootMap = new HashMap();
        rootMap.put("text","我的题库");
        rootMap.put("id",-4L);
        rootMap.put("isLoaded", true);

        treeDataList.add(rootMap);

        return treeDataList;
    }

    @RequestMapping
    public String myStudy(Long id,Integer pageNo,Integer pageSize,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currendTimeStr = format.format(new Date());
        Long userId = SpringSecurityUtils.getCurrentUser().getId();
        String hql = "from CmsStudyPlan p left join fetch p.course where p.course.startTime<='"+currendTimeStr+"' and p.course.endTime>='"+currendTimeStr+"' and p.user.id="+userId;
        String countHql = "select * from Cms_Study_Plan p  left join Cms_Study_Course c on c.id=p.course_id where c.start_Time<='"+currendTimeStr+"' and c.end_Time>='"+currendTimeStr+"' and p.user_id="+userId;
        Page<CmsStudyPlan> byPage = cmsStudyPlanService.findByPage(page,countHql ,hql);
        model.addAttribute("page",byPage);
        return "pages/myStudy";
    }

    /**
     * 学习历史
     *
     * @param id
     * @param pageNo
     * @param pageSize
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String studyHistory(Long id,Integer pageNo,Integer pageSize,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    @RequestMapping
    public String myPaper(Long id,Integer pageNo,Integer pageSize,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);

        Long userId = SpringSecurityUtils.getCurrentUser().getId();

        String hql = "select p.paper from ExaPaperUsergroup p   where p.user.id="+ userId;

        Page<ExaPaper> byPage = exaPaperService.findByPage(page ,hql);
        model.addAttribute("page",byPage);
        model.addAttribute("id",id);
        return "pages/paper";
    }

    @RequestMapping
    public String toPractice(Model model, Long paperId) throws Exception {
        ExaPaper exaPaper = exaPaperService.get(paperId);
        List<ExaPaperSection> exaPaperSections = exaPaperManager.getExaPaperSections(paperId);
        List<ExaPaperDetail> details = exaPaperManager.getPaperDetail(paperId);

        model.addAttribute("paper",exaPaper);
        model.addAttribute("sections",exaPaperSections);
        model.addAttribute("details",details);

        return "pages/practice";
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

    @RequestMapping
    public void comment(HttpServletResponse response,String content,Long id,String commentType) throws Exception {
        CmsArticle cmsArticle = articleService.get(id);
        CmsComment cmsComment = new CmsComment();
        cmsComment.setContent(content);
        cmsComment.setCommentType(commentType);
        cmsComment.setReplyer(sysUserService.get(SpringSecurityUtils.getCurrentUser().getId()));
        cmsComment.setKnowledge(cmsArticle);
        cmsComment.setReplyTime(new Timestamp(System.currentTimeMillis()));
        cmsComment.setIsAdopt(false);
        cmsCommentService.save(cmsComment);
        sendSuccessJSON(response, "添加评论成功");
    }

    @RequestMapping
    public String loadComment(Long id,Integer pageNo,Integer pageSize,Model model) throws Exception {
        Page page = getPage(pageNo, pageSize);
        page.setAutoCount(true);

        String commentHql = "from CmsComment where knowledge.id="+id+" order by replyTime desc";
        Page<CmsComment> byPage = cmsCommentService.findByPage(page,commentHql);

        model.addAttribute("page",byPage);
        return "pages/comment";
    }

    @RequestMapping
    public String catagoryInit(Model model, CmsCollectCatagory entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsCollectCatagoryService.get(entity.getId());
            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "pages/cmsCollectCatagoryEdit";
    }

    @RequestMapping
    public void deleteCatalogue(HttpServletResponse response,Long id) throws Exception {
        try {
            CmsCollectCatagory cmsCollectCatagory = cmsCollectCatagoryService.get(id);
            if(cmsCollectCatagory.getIsLeaf()){
                if(cmsCollectCatagory.getUserId()!=null&&cmsCollectCatagory.getUserId().equals(SpringSecurityUtils.getCurrentUser().getId().toString())){
                    cmsCollectCatagoryService.delete(cmsCollectCatagory);
                    sendSuccessJSON(response, "删除成功");
                } else{
                    sendSuccessJSON(response, "该收藏夹为默认收藏夹,不能删除");
                }

            } else{
                sendSuccessJSON(response, "请删除子目录后再删除");
            }
        } catch (Exception e) {
            sendSuccessJSON(response, "请先删除收藏夹里的文章");
        }
    }

    @RequestMapping
    public void exportToWord(HttpServletResponse response,Long paperId){
        response.setContentType("application/x-msdownload");
        ExaPaper exaPaper = exaPaperService.get(paperId);
        List<ExaPaperSection> exaPaperSections = exaPaperManager.getExaPaperSections(paperId);
        List<ExaPaperDetail> details = exaPaperManager.getPaperDetail(paperId);
        String TECH_INFO = "";
        String FOOT_INFO = "";

        try {
            Document document = new Document(PageSize.A4);
            RtfWriter2.getInstance(document, response.getOutputStream());
            document.open();
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            Font titleFont = new Font(bfChinese, 14.0F, 1);
            Font subTitleFont = new Font(bfChinese, 11.0F, 1);
            Font contextFont = new Font(bfChinese, 10.0F, 1);
            Font optionFont = new Font(bfChinese, 9.0F, 0);
            Font headerFooterFont = new Font(bfChinese, 9.0F, 0);
            Table header = new Table(2);
            header.setBorder(0);
            header.setWidth(100.0F);
            Paragraph address = new Paragraph(TECH_INFO);
            address.setFont(headerFooterFont);

            Cell dateCell = new Cell(address);
            dateCell.setBorder(0);
            header.addCell(dateCell);
            Paragraph date = new Paragraph("生成日期: " + (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
            date.setAlignment(2);
            date.setFont(headerFooterFont);
            dateCell = new Cell(date);
            dateCell.setBorder(0);
            header.addCell(dateCell);

            document.setHeader(new RtfHeaderFooter(header));
            Table footer = new Table(2);
            footer.setBorder(0);
            footer.setWidth(100.0F);
            Paragraph company = new Paragraph(FOOT_INFO);
            company.setFont(headerFooterFont);

            Cell footCell = new Cell(company);
            footCell.setBorder(0);
            footer.addCell(footCell);
            Paragraph pageNumber = new Paragraph("第 ");
            pageNumber.add(new RtfPageNumber());
            pageNumber.add(new Chunk(" 页"));
            pageNumber.setAlignment(2);
            pageNumber.setFont(headerFooterFont);
            footCell = new Cell(pageNumber);
            footCell.setBorder(0);
            footer.addCell(footCell);

            document.setFooter(new RtfHeaderFooter(footer));
            Paragraph title = new Paragraph(exaPaper.getPaperName());
            title.setAlignment(1);
            title.setFont(titleFont);
            document.add(title);


            for (ExaPaperSection exaPaperSection : exaPaperSections) {
                int row = 0;
                Paragraph subTitle = new Paragraph(exaPaperSection.getSectionName() + "," + exaPaperSection.getRemark() );
                subTitle.setFont(subTitleFont);
                subTitle.setSpacingBefore(10.0F);
                subTitle.setFirstLineIndent(0.0F);
                document.add(subTitle);
                for (ExaPaperDetail detail : details) {
                    if(detail.getSectionId().equals(exaPaperSection.getId())){
                        ExaQuestion question = detail.getQuestion();
                        Paragraph context = new Paragraph( (row+1) +":" + question.getContent());
                        context.setAlignment(0);
                        context.setFont(contextFont);
                        context.setSpacingBefore(10.0F);
                        context.setFirstLineIndent(0.0F);
                        document.add(context);
                        StringBuffer sbOptions = new StringBuffer("");

                        Set<ExaQuestionOptions> options = question.getOptions();
                        for (ExaQuestionOptions option : options) {
                            sbOptions.append(option.getOptionKey() + " : " + option.getOptionOption() + "\n");
                        }

                        Paragraph optionsParagraph = new Paragraph(sbOptions.toString());
                        optionsParagraph.setAlignment(0);
                        optionsParagraph.setFont(optionFont);
                        optionsParagraph.setSpacingBefore(10.0F);
                        optionsParagraph.setFirstLineIndent(0.0F);
                        document.add(optionsParagraph);
                    }
                }

            }

            document.close();
            response.setHeader("Content-Disposition", "attachment;filename=" +new String((exaPaper.getPaperName()+".doc").getBytes("GB2312"),"ISO-8859-1"));

        } catch (DocumentException e) {
            sendFailureJSON(response, "构造试卷报错");
        } catch (IOException e) {
            sendFailureJSON(response, "构造试卷报错");
        }

    }

    /**
     * 提交问题
     * @param response
     * @param conQuestion
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String submitQuestion(HttpServletResponse response,ConQuestion conQuestion) throws Exception {
        BaseUser currentUser = SpringSecurityUtils.getCurrentUser();
        conQuestion.setAsker(currentUser.getLoginName());
        conQuestion.setCreateUser(currentUser.getId().toString());
        conQuestion.setCreateTime(new Timestamp(System.currentTimeMillis()));
        conQuestion.setIsTop(false);
        conQuestion.setPublishTime(new Timestamp(System.currentTimeMillis()));
        conQuestionService.save(conQuestion);

        return "redirect:conIndex";

    }



}