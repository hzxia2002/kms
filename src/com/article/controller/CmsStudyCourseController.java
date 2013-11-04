package com.article.controller;

import com.article.daoservice.CmsStudyCourseService;
import com.article.domain.CmsStudyCourse;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
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
import java.sql.Timestamp;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsStudyCourseController extends BaseCRUDActionController<CmsStudyCourse> {
    private static Log log = LogFactory.getLog(CmsStudyCourseController.class);

    @Autowired
	private CmsStudyCourseService cmsStudyCourseService;



	@RequestMapping
    @ResponseBody
	public Page<CmsStudyCourse> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from CmsStudyCourse t where 1=1 order by t.createTime desc" ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsStudyCourseService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, CmsStudyCourse entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsStudyCourseService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsStudyCourseEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsStudyCourse cmsStudyCourse = cmsStudyCourseService.get(id);

        model.addAttribute("bean", cmsStudyCourse);
        return "view/cms/cmsStudyCourseView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsStudyCourse entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "name",
                    "remark",
                    "article",
                    "startTime",
                    "endTime",
                    "studyDuration"
            };

            CmsStudyCourse target;
            if (entity.getId() != null) {
                target = cmsStudyCourseService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
                target.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                target.setUpdateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            } else {
                target = entity;
                target.setCreateTime(new Timestamp(System.currentTimeMillis()));
                target.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            }

            cmsStudyCourseService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsStudyCourseService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}