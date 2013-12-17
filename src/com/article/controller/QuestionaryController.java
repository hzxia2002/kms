package com.article.controller;

import com.article.daoservice.QuestionaryService;
import com.article.domain.Questionary;
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
public class QuestionaryController extends BaseCRUDActionController<Questionary> {
    private static Log log = LogFactory.getLog(QuestionaryController.class);

    @Autowired
	private QuestionaryService questionaryService;



	@RequestMapping
    @ResponseBody
	public Page<Questionary> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from Questionary t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = questionaryService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, Questionary entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = questionaryService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/res/questionaryEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        Questionary questionary = questionaryService.get(id);

        model.addAttribute("bean", questionary);
        return "view/res/questionaryView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") Questionary entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "title",
                    "sponsor",
                    "type",
                    "remark",
                    "createTime",
                    "createUser",
                    "updateTime",
                    "updateUser"
            };

            Questionary target;
            if (entity.getId() != null) {
                target = questionaryService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            questionaryService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        questionaryService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}