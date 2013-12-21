package com.article.controller;

import com.article.daoservice.SurUserQuestionaryService;
import com.article.domain.SurUserQuestionary;
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
public class SurUserQuestionaryController extends BaseCRUDActionController<SurUserQuestionary> {
    private static Log log = LogFactory.getLog(SurUserQuestionaryController.class);

    @Autowired
	private SurUserQuestionaryService surUserQuestionaryService;



	@RequestMapping
    @ResponseBody
	public Page<SurUserQuestionary> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SurUserQuestionary t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = surUserQuestionaryService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SurUserQuestionary entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = surUserQuestionaryService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sur/surUserQuestionaryEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SurUserQuestionary surUserQuestionary = surUserQuestionaryService.get(id);

        model.addAttribute("bean", surUserQuestionary);
        return "view/sur/surUserQuestionaryView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SurUserQuestionary entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "questionary",
                    "user",
                    "startTime",
                    "endTime",
                    "answerTime",
                    "createTime",
                    "createUser",
                    "updateTime",
                    "updateUser"
            };

            SurUserQuestionary target;
            if (entity.getId() != null) {
                target = surUserQuestionaryService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            surUserQuestionaryService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        surUserQuestionaryService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}