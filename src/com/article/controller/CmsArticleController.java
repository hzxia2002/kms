package com.article.controller;

import com.article.daoservice.CmsArticleService;
import com.article.daoservice.CmsCatalogService;
import com.article.daoservice.CmsCommentService;
import com.article.daoservice.DocAttachmentsService;
import com.article.domain.CmsArticle;
import com.article.domain.CmsCatalog;
import com.article.domain.CmsComment;
import com.article.domain.DocAttachments;
import com.article.util.Constants;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Condition;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.DateTimeHelper;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysUser;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsArticleController extends BaseCRUDActionController<CmsArticle> {
    private static Log log = LogFactory.getLog(CmsArticleController.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsCatalogService cmsCatalogService;

    @Autowired
    private CmsCommentService cmsCommentService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DocAttachmentsService docAttachmentsService;

    @RequestMapping
    @ResponseBody
    public Page<CmsArticle> grid(Page page, String condition) {
        try {
            page.setAutoCount(true);

            String hql = "from CmsArticle t where 1=1 " ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsArticleService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    public String init(Model model, CmsArticle entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsArticleService.get(entity.getId());
            } else{
                entity.setIsValid(true);
                entity.setIsPublished(true);
                entity.setAuthor(SpringSecurityUtils.getCurrentUser().getLoginName());
            }
            if(entity.getDocId()!=null){
                List<DocAttachments> docAttachments = docAttachmentsService.find("from DocAttachments t where t.doc.id=" + entity.getDocId());
                model.addAttribute("docAttachments", docAttachments);
            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsArticleEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsArticle cmsArticle = cmsArticleService.get(id);

        model.addAttribute("bean", cmsArticle);
        return "pages/cmsArticleView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsArticle entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "path",
                    "title",
                    "keyWord",
                    "content",
                    "clickNum",
                    "isPublished",
//                    "publisher",
//                    "publishDate",
                    "isValid",
                    "isTop",
                    "attachPath",
                    "thumbPath",
                    "linkUrl",
                    "author",
                    "summary",
                    "classHour",
                        "docId",
                    "isRecommend"
//                    "accessRange"
            };

            CmsArticle target;
            if (entity.getId() != null) {
                target = cmsArticleService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
                target.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                target.setUpdateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            } else {
                target = entity;
                target.setCreateTime(new Timestamp(System.currentTimeMillis()));
                target.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
                target.setVisitTimes(0L);
            }

            if(target.getIsPublished()){
                target.setPublisher(sysUserService.get(SpringSecurityUtils.getCurrentUser().getId()));
                //设置发布时间
                if(target.getPublishDate()==null){
                    target.setPublishDate(new Timestamp(System.currentTimeMillis()));
                }
            }

            target.setUpdateTime(DateTimeHelper.getTimestamp());

            cmsArticleService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsArticleService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public String articleQuery(Model model,String condition) throws Exception {
//        String hql = "from CmsArticle t where 1=1 and isPublished = 1 " ;
//        if(StringUtils.isNotEmpty(conditoin)){
//            hql += "and keyWord like '%"+conditoin+"%' or title like '%"+conditoin+"%' or content like '%"+conditoin+"%'";
//        }
//        Page<CmsArticle> cmsArticlePage = new Page<CmsArticle>();
//        cmsArticlePage = cmsArticleService.findByPage(cmsArticlePage, hql);
//        model.addAttribute("beans",cmsArticlePage.getRows());
        model.addAttribute("condition",condition);
        return "view/cms/articleQuery";
    }

    @RequestMapping
    @ResponseBody
    public Page<CmsArticle> queryGrid(Page page, String condition,String keyWord) {
        try {
            page.setAutoCount(true);

            //文档目录ID
            String catagoryId = "";
            if (StringUtils.isNotEmpty(condition)) {
                JSONObject jsonObject = JSONObject.fromObject(condition);
                for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();){
                    Condition conditionBean = (Condition) JSONObject.toBean(jsonObject.getJSONObject(iterator.next().toString()), Condition.class);
                    if("catagoryId".equals(conditionBean.getPropertyName())){
                        catagoryId =  conditionBean.getFirstValue();
                    }
                }

            }

            String sql = "select * from Cms_Article where 1=1 " ;
            if(StringUtils.isNotEmpty(keyWord)){
                sql = "select * from (select * from Cms_Article  where title like '%"+keyWord+"%' ";
                sql +="union (select *  from Cms_Article  where key_word like '%"+keyWord+"%') ";
                sql +=" union (select * from Cms_Article  where content like '%"+keyWord+"%') ) as pp where 1=1 "+ (StringUtils.isNotEmpty(catagoryId)?" and path_id="+catagoryId:"");
            }


            // 查询
            page = cmsArticleService.findBySql(page, sql, new Object[]{new CmsArticle()});
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }


    @RequestMapping
    @ResponseBody
    public List<Map> tree(String condition) throws Exception {
        List<Map> ret = new ArrayList<Map>();

        Map root = new HashMap();
        root.put("id", "-1");
        root.put("text", "文章分类");
        root.put("isexpand", true);
        root.put("isLoaded", true);
        ret.add(root);

        List<CmsCatalog> cmsCatalogs ;
        if(StringUtils.isEmpty(condition)){
            String hql = "select distinct c from CmsCatalog c left join fetch c.parent where c.type=" + Constants.FILE_VISUAL ;
            cmsCatalogs = cmsCatalogService.find(hql);
        } else{
            String hql = "select distinct c.path from CmsArticle c  where c.title like '%"+condition+"%'";
            hql += " or c.keyWord like '%"+condition+"%'";
            hql += " or c.content like '%"+condition+"%'";
            cmsCatalogs = cmsCatalogService.find(hql);
        }

        for (CmsCatalog cmsCatalog : cmsCatalogs) {
            Map map = new HashMap();

            map.put("id", cmsCatalog.getId());
            map.put("text", cmsCatalog.getName());
            map.put("isexpand", true);
            map.put("isLoaded", true);

            if(cmsCatalog.getParent() != null) {
                map.put("pid", cmsCatalog.getParent().getId());
            } else {
                map.put("pid", -1);
            }

            ret.add(map);
        }
        return ret;
    }

//    @RequestMapping
//    public String editArticle(Model model,String id) throws Exception {
//        if(StringUtils.isNotEmpty(id)){
//            CmsComment cmsComment = cmsCommentService.get(Long.valueOf(id));
//            model.addAttribute("id",cmsComment.getKnowledge().getId());
//            if(cmsComment.getReplyer().getLoginName().equals(SpringSecurityUtils.getCurrentUser().getLoginName())){
//                cmsCommentService.delete(cmsComment);
//            }
//        }
//        return "view/cms/replaySaveSuccess";
//    }

    @RequestMapping
    public String deleteArticle(Model model,String id) throws Exception {
        String noPrivilegePage =  "view/cms/cmsNoPrivilege";
        if(StringUtils.isNotEmpty(id)){
            CmsArticle cmsArticle = cmsArticleService.get(Long.valueOf(id));
            model.addAttribute("id",cmsArticle.getId());
            if(cmsArticle.getCreateUser().equals(SpringSecurityUtils.getCurrentUser().getLoginName())){
                List<CmsComment> cmsComments = cmsCommentService.find("from CmsComment t where t.knowledge.id=" + id);
                for (CmsComment cmsComment : cmsComments) {
                    cmsCommentService.delete(cmsComment);
                }
                cmsArticleService.delete(cmsArticle);
            }else{
                return noPrivilegePage ;
            }
        }else {
            return noPrivilegePage;
        }
        return "view/cms/articleQuery";
    }


    @RequestMapping
    public String detail(Model model,String id,String onlyCreateUser,Integer pageNo) throws Exception {


        if(pageNo==null){
            pageNo = 1;
        }
        Page<CmsComment> cmsCommentPage = new Page<CmsComment>();

        CmsArticle cmsArticle = cmsArticleService.get(Long.valueOf(id));
        if(pageNo==1){
            model.addAttribute("bean",cmsArticle);
        }

        cmsCommentPage.setPageSize(10);
        cmsCommentPage.setAutoCount(true);
        cmsCommentPage.setPage(pageNo);
        String hql = "from CmsComment t left join fetch t.replyer where t.knowledge.id="+id;
        String countSql = "select * from Cms_Comment t where t.knowledge_id="+id;
        Page<CmsComment> byPage = cmsCommentService.findByPage(cmsCommentPage, countSql, hql);
//        List<CmsComment> cmsComments = cmsCommentService.find(hql);
//        if(pageNo == 1){
//            byPage.setTotal(byPage.getTotal());
//        }
        model.addAttribute("page",byPage);
        model.addAttribute("id",id);
        model.addAttribute("loginName",SpringSecurityUtils.getCurrentUser().getLoginName());
        model.addAttribute("isCreateUser",SpringSecurityUtils.getCurrentUser().getLoginName().equals(cmsArticle.getCreateUser()));

        return "view/cms/articleDetail";
    }

    @RequestMapping
    public String replySave(Model model,String id,String onlyCreateUser,String content) throws Exception {
        if(StringUtils.isNotEmpty(content)){

            CmsArticle cmsArticle = cmsArticleService.get(Long.valueOf(id));
            CmsComment cmsComment = new CmsComment();
            cmsComment.setContent(content);
            cmsComment.setKnowledge(cmsArticle);
            cmsComment.setReplyTime(new Timestamp(System.currentTimeMillis()));
            SysUser sysUser =  sysUserService.get(SpringSecurityUtils.getCurrentUser().getId())  ;
            cmsComment.setReplyer(sysUser);
            cmsCommentService.save(cmsComment);
        }
        model.addAttribute("id",id);
        return "view/cms/replaySaveSuccess";
    }


    @RequestMapping
    public String deleteReply(Model model,String id) throws Exception {
        if(StringUtils.isNotEmpty(id)){
            CmsComment cmsComment = cmsCommentService.get(Long.valueOf(id));
            model.addAttribute("id",cmsComment.getKnowledge().getId());
            if(cmsComment.getReplyer().getLoginName().equals(SpringSecurityUtils.getCurrentUser().getLoginName())){
                cmsCommentService.delete(cmsComment);
            }
        }
        return "view/cms/replaySaveSuccess";
    }

    @RequestMapping
    public String editArticle(Model model, Long id) throws Exception {
        String noPrivilegePage =  "view/cms/cmsNoPrivilege";
        if(id!=null){
            CmsArticle cmsArticle = cmsArticleService.get(id);
            if(!cmsArticle.getCreateUser().equals(SpringSecurityUtils.getCurrentUser().getLoginName())){
                return  noPrivilegePage;
            }else{
                model.addAttribute("bean",cmsArticle) ;
//                model.addAttribute("id",cmsArticle.getId()) ;
            }
        }else{
            return noPrivilegePage;
        }

        return "view/cms/editArticle";
    }

    /**
     * 更新文章内容
     * @param model
     * @param id
     * @param content
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String updateArticle(Model model, Long id,String content) throws Exception {
        CmsArticle cmsArticle = cmsArticleService.get(id);
        cmsArticle.setContent(content);
        cmsArticleService.save(cmsArticle);
        model.addAttribute("id",cmsArticle.getId());
        return "view/cms/replaySaveSuccess";
    }

    /**
     * 取消文章推荐
     * @param response
     * @param model
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void cancelRecommend(HttpServletResponse response,Model model, Long id) throws Exception {
        CmsArticle cmsArticle = cmsArticleService.get(id);
        cmsArticle.setIsTop(false);
        cmsArticleService.save(cmsArticle);
        sendSuccessJSON(response, "取消推荐成功");
    }

    /**
     * 推荐文章
     * @param response
     * @param model
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void recommendArcticle(HttpServletResponse response,Model model, Long id) throws Exception {
        CmsArticle cmsArticle = cmsArticleService.get(id);
        cmsArticle.setIsTop(true);
        cmsArticle.setPublishTime(new Timestamp(System.currentTimeMillis()));
        cmsArticleService.save(cmsArticle);
        sendSuccessJSON(response, "推荐成功");
    }

    @RequestMapping
    @ResponseBody
    public Page<CmsArticle> recommendGrid(Page page, String condition) {
        try {
            page.setAutoCount(true);

            String hql = "from CmsArticle t where 1=1 and t.isTop=1" ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsArticleService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

}