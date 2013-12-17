package com.article.controller;

import com.article.daoservice.CharpterQuestionService;
import com.article.domain.CharpterQuestion;
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
public class CharpterQuestionController extends BaseCRUDActionController<CharpterQuestion> {
    private static Log log = LogFactory.getLog(CharpterQuestionController.class);

    @Autowired
	private CharpterQuestionService charpterQuestionService;



	@RequestMapping
    @ResponseBody
	public Page<CharpterQuestion> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from CharpterQuestion t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = charpterQuestionService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, CharpterQuestion entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = charpterQuestionService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/res/charpterQuestionEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CharpterQuestion charpterQuestion = charpterQuestionService.get(id);

        model.addAttribute("bean", charpterQuestion);
        return "view/res/charpterQuestionView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CharpterQuestion entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "charpter",
                    "question"
            };

            CharpterQuestion target;
            if (entity.getId() != null) {
                target = charpterQuestionService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            charpterQuestionService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        charpterQuestionService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}