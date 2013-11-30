package com.article.controller;

import com.article.daoservice.CmsCommentService;
import com.article.domain.CmsComment;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
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
public class CmsProposeController extends BaseCRUDActionController<CmsComment> {
    private static Log log = LogFactory.getLog(CmsProposeController.class);

    @Autowired
    private CmsCommentService cmsCommentService;

    @RequestMapping
    @ResponseBody
    public Page<CmsComment> grid(Page page, String condition) {
        try {
            page.setAutoCount(true);

            String hql = "from CmsComment t where commentType='2' order by replyTime desc";

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

        return "view/cms/cmsProposeEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsComment cmsComment = cmsCommentService.get(id);

        model.addAttribute("bean", cmsComment);
        return "view/cms/cmsProposeView";
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
}