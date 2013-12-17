package com.article.controller;

import com.article.daoservice.UserQuestionaryService;
import com.article.domain.UserQuestionary;
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
public class UserQuestionaryController extends BaseCRUDActionController<UserQuestionary> {
    private static Log log = LogFactory.getLog(UserQuestionaryController.class);

    @Autowired
	private UserQuestionaryService userQuestionaryService;



	@RequestMapping
    @ResponseBody
	public Page<UserQuestionary> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from UserQuestionary t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = userQuestionaryService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, UserQuestionary entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = userQuestionaryService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/res/userQuestionaryEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        UserQuestionary userQuestionary = userQuestionaryService.get(id);

        model.addAttribute("bean", userQuestionary);
        return "view/res/userQuestionaryView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") UserQuestionary entity)
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

            UserQuestionary target;
            if (entity.getId() != null) {
                target = userQuestionaryService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            userQuestionaryService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        userQuestionaryService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}