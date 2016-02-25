package com.article.controller;

import com.article.daoservice.ExaQuestionService;
import com.article.domain.ExaQuestion;
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
public class ExaQuestionController extends BaseCRUDActionController<ExaQuestion> {
    private static Log log = LogFactory.getLog(ExaQuestionController.class);

    @Autowired
	private ExaQuestionService exaQuestionService;



	@RequestMapping
    @ResponseBody
	public Page<ExaQuestion> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaQuestion t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaQuestionService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaQuestion entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaQuestionService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaQuestionEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaQuestion exaQuestion = exaQuestionService.get(id);

        model.addAttribute("bean", exaQuestion);
        return "view/exa/exaQuestionView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaQuestion entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "db",
                    "questionType",
                    "questionLevel",
                    "questionFrom",
                    "status",
                    "content",
                    "postTime",
                    "skey",
                    "keyDesc",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            ExaQuestion target;
            if (entity.getId() != null) {
                target = exaQuestionService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaQuestionService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaQuestionService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}