package com.article.controller;

import com.article.daoservice.QuestionService;
import com.article.domain.Question;
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
public class QuestionController extends BaseCRUDActionController<Question> {
    private static Log log = LogFactory.getLog(QuestionController.class);

    @Autowired
	private QuestionService questionService;



	@RequestMapping
    @ResponseBody
	public Page<Question> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from Question t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = questionService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, Question entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = questionService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/res/questionEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        Question question = questionService.get(id);

        model.addAttribute("bean", question);
        return "view/res/questionView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") Question entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "store",
                    "title",
                    "type",
                    "remark"
            };

            Question target;
            if (entity.getId() != null) {
                target = questionService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            questionService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        questionService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}