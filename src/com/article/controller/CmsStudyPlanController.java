package com.article.controller;

import com.article.daoservice.CmsStudyPlanService;
import com.article.domain.CmsStudyPlan;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import org.apache.commons.lang.StringUtils;
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
public class CmsStudyPlanController extends BaseCRUDActionController<CmsStudyPlan> {
    private static Log log = LogFactory.getLog(CmsStudyPlanController.class);

    @Autowired
	private CmsStudyPlanService cmsStudyPlanService;



	@RequestMapping
    @ResponseBody
	public Page<CmsStudyPlan> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from CmsStudyPlan t where 1=1 ";

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsStudyPlanService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, CmsStudyPlan entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsStudyPlanService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsStudyPlanEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsStudyPlan cmsStudyPlan = cmsStudyPlanService.get(id);

        model.addAttribute("bean", cmsStudyPlan);
        return "view/cms/cmsStudyPlanView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsStudyPlan entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "lastStudyTime",
                    "lastStudyDuration",
                    "studyDuration",
                    "studyTimes",
                    "user",
                    "course",
                    "createTime",
                    "createUser",
                    "updateTime",
                    "updateUser"
            };

            CmsStudyPlan target;
            if (entity.getId() != null) {
                target = cmsStudyPlanService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            cmsStudyPlanService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsStudyPlanService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}