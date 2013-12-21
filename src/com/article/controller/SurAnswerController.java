package com.article.controller;

import com.article.daoservice.SurAnswerService;
import com.article.domain.SurAnswer;
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
public class SurAnswerController extends BaseCRUDActionController<SurAnswer> {
    private static Log log = LogFactory.getLog(SurAnswerController.class);

    @Autowired
	private SurAnswerService surAnswerService;



	@RequestMapping
    @ResponseBody
	public Page<SurAnswer> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SurAnswer t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = surAnswerService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SurAnswer entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = surAnswerService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sur/surAnswerEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SurAnswer surAnswer = surAnswerService.get(id);

        model.addAttribute("bean", surAnswer);
        return "view/sur/surAnswerView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SurAnswer entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "question",
                    "option",
                    "user",
                    "score",
                    "userQuestionary"
            };

            SurAnswer target;
            if (entity.getId() != null) {
                target = surAnswerService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            surAnswerService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        surAnswerService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}