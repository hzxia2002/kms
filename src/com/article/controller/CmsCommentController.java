package com.article.controller;

import com.article.daoservice.CmsCommentService;
import com.article.domain.CmsComment;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.ReflectionUtils;
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

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsCommentController extends BaseCRUDActionController<CmsComment> {
    private static Log log = LogFactory.getLog(CmsCommentController.class);

    @Autowired
    private CmsCommentService cmsCommentService;

    @RequestMapping
    @ResponseBody
    public Page<CmsComment> grid(Page page, String condition,String type) {
        try {
            page.setAutoCount(true);
            if(StringUtils.isEmpty(type)){
                type = "1";
            }

            String hql = "from CmsComment t where commentType= " + type + " order by replyTime desc";

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsCommentService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    public String init(Model model, CmsComment entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsCommentService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsCommentEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsComment cmsComment = cmsCommentService.get(id);

        model.addAttribute("bean", cmsComment);
        return "view/cms/cmsCommentView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsComment entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "knowledge",
                    "content",
                    "replyer",
                    "replyTime"
            };

            CmsComment target;
            if (entity.getId() != null) {
                target = cmsCommentService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            cmsCommentService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsCommentService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public String editReply(Model model, Long id) throws Exception {
        String noPrivilegePage =  "view/cms/cmsNoPrivilege";
        if(id!=null){
            CmsComment cmsComment = cmsCommentService.get(id);
            if(!cmsComment.getReplyer().getLoginName().equals(SpringSecurityUtils.getCurrentUser().getLoginName())){
                return  noPrivilegePage;
            }else{
                model.addAttribute("comment",cmsComment) ;
                model.addAttribute("bean",cmsComment.getKnowledge()) ;
//                model.addAttribute("id",cmsComment.getReplyer().getId()) ;
            }
        }else{
            return noPrivilegePage;
        }

        return "view/cms/editReply";
    }

    /**
     * 更新回复内容
     * @param model
     * @param id
     * @param content
     * @return
     * @throws Exception
     */
    @RequestMapping
    public String updateReply(Model model, Long id,String content) throws Exception {
        CmsComment cmsComment = cmsCommentService.get(id);
        cmsComment.setContent(content);
        cmsCommentService.save(cmsComment);
        model.addAttribute("id",cmsComment.getKnowledge().getId());
        return "view/cms/replaySaveSuccess";
    }

}