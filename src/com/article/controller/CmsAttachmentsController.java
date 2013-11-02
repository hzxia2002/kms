package com.article.controller;

import com.article.daoservice.CmsAttachmentsService;
import com.article.domain.CmsAttachments;
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
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsAttachmentsController extends BaseCRUDActionController<CmsAttachments> {
    private static Log log = LogFactory.getLog(CmsAttachmentsController.class);

    @Autowired
	private CmsAttachmentsService cmsAttachmentsService;



	@RequestMapping
    @ResponseBody
	public Page<CmsAttachments> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from CmsAttachments t where 1=1 ";

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsAttachmentsService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, CmsAttachments entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsAttachmentsService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsAttachmentsEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsAttachments cmsAttachments = cmsAttachmentsService.get(id);

        model.addAttribute("bean", cmsAttachments);
        return "view/cms/cmsAttachmentsView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsAttachments entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "knowledge",
                    "orgName",
                    "targetName",
                    "extName",
                    "path",
                    "uploadDate",
                    "uploader"
            };

            CmsAttachments target;
            if (entity.getId() != null) {
                target = cmsAttachmentsService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            cmsAttachmentsService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsAttachmentsService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}