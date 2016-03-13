package com.article.controller;

import com.article.daoservice.ExaPaperDetailService;
import com.article.daoservice.ExaQuestionService;
import com.article.domain.ExaPaperDetail;
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
public class ExaPaperDetailController extends BaseCRUDActionController<ExaPaperDetail> {
    private static Log log = LogFactory.getLog(ExaPaperDetailController.class);

    @Autowired
	private ExaPaperDetailService exaPaperDetailService;

    @Autowired
    private ExaQuestionService exaQuestionService;


	@RequestMapping
    @ResponseBody
	public Page<ExaPaperDetail> grid(Page page, String condition,Long paperId) {
		try {
            page.setAutoCount(true);
            String hql = "select d from ExaPaperDetail d where d.paperId= "+paperId;

//            String hql = "select d from ExaPaperDetail d , ExaQuestion q where q.id=d.questionId and d.paperId="+paperId;

//            List<ExaQuestion> exaQuestions = exaQuestionService.find(hql0);
//
//            String hql = "from ExaPaperDetail t where 1=1 and t.paperId=" + paperId;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaPaperDetailService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaPaperDetail entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaPaperDetailService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaPaperDetailEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaPaperDetail exaPaperDetail = exaPaperDetailService.get(id);

        model.addAttribute("bean", exaPaperDetail);
        return "view/exa/exaPaperDetailView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaPaperDetail entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "paper",
                    "question",
                    "section",
                    "score",
                    "orderNo"
            };

            ExaPaperDetail target;
            if (entity.getId() != null) {
                target = exaPaperDetailService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaPaperDetailService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaPaperDetailService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}