package com.article.controller;

import com.article.daoservice.SurQuestionService;
import com.article.domain.SurQuestion;
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
public class SurQuestionController extends BaseCRUDActionController<SurQuestion> {
    private static Log log = LogFactory.getLog(SurQuestionController.class);

    @Autowired
	private SurQuestionService surQuestionService;



	@RequestMapping
    @ResponseBody
	public Page<SurQuestion> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SurQuestion t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = surQuestionService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SurQuestion entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = surQuestionService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sur/surQuestionEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SurQuestion surQuestion = surQuestionService.get(id);

        model.addAttribute("bean", surQuestion);
        return "view/sur/surQuestionView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SurQuestion entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "title",
                    "type",
                    "remark",
                    "questionary"
            };

            SurQuestion target;
            if (entity.getId() != null) {
                target = surQuestionService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            surQuestionService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        surQuestionService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}