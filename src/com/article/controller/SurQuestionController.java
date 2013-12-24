package com.article.controller;

import com.article.daoservice.SurOptionsService;
import com.article.daoservice.SurQuestionService;
import com.article.daoservice.SurQuestionaryService;
import com.article.domain.SurOptions;
import com.article.domain.SurQuestion;
import com.article.manager.QuestionManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SurQuestionController extends BaseCRUDActionController<SurQuestion> {
    private static Log log = LogFactory.getLog(SurQuestionController.class);

    @Autowired
	private SurQuestionService surQuestionService;

    @Autowired
    private SurQuestionaryService surQuestionaryService;

    @Autowired
    private SurOptionsService surOptionsService;

    @Autowired
    private QuestionManager questionManager;

	@RequestMapping
    @ResponseBody
	public Page<SurQuestion> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from SurQuestion t where 1=1 " ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = surQuestionService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, SurQuestion entity,Long questionaryId) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = surQuestionService.get(entity.getId());

            }
            if(questionaryId!=null){
                entity.setQuestionary(surQuestionaryService.get(questionaryId));
            }
            model.addAttribute("bean", entity);
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
                    "indexNo",
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
        SurQuestion surQuestion = surQuestionService.get(id);
        questionManager.delteQuestion(surQuestion);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public void saveData(HttpServletResponse response, Model model, @ModelAttribute("bean") SurQuestion entity,String option,String opIndexNo,String opIds,Integer flag)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "title",
                    "type",
                    "indexNo",

                    "questionary"
            };

            SurQuestion target;
            if (entity.getId() != null&&flag==1) {
                target = surQuestionService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
                target.setId(null);
            }
            //保存题目及选项
            ArrayList arrayList = new ArrayList();
            String[] options = option.split(",");
            String[] indexNos = opIndexNo.split(",");
            String[] ids = opIds.split(",");
            for (int i = 0; i < indexNos.length; i++) {
                SurOptions surOptions = new SurOptions();
                String no = indexNos[i];
                String opt = options[i];
                String id = ids[i];
                surOptions.setIndexNo(no);
                surOptions.setContent(opt);
                if(StringUtils.isNotEmpty(id.trim())&&flag==1){
                    surOptions.setId(Long.valueOf(id));
                }
                surOptions.setQuestion(target);
                arrayList.add(surOptions);
            }
            questionManager.saveQuestion(target,arrayList);

            //
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, "改试题已经被");
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    @ResponseBody
    public Map getQuestion(Long questionId) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("question",surQuestionService.get(questionId));
            hashMap.put("options",surOptionsService.find("from SurOptions where question.id="+questionId));

            // 查询
        } catch (Exception e) {
            log.error("error", e);
        }

        return hashMap;
    }



}