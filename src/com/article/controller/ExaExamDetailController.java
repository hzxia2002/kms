package com.article.controller;

import com.article.daoservice.ExaExamDetailService;
import com.article.domain.ExaExamDetail;
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
public class ExaExamDetailController extends BaseCRUDActionController<ExaExamDetail> {
    private static Log log = LogFactory.getLog(ExaExamDetailController.class);

    @Autowired
	private ExaExamDetailService exaExamDetailService;



	@RequestMapping
    @ResponseBody
	public Page<ExaExamDetail> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaExamDetail t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaExamDetailService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaExamDetail entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaExamDetailService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaExamDetailEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaExamDetail exaExamDetail = exaExamDetailService.get(id);

        model.addAttribute("bean", exaExamDetail);
        return "view/exa/exaExamDetailView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaExamDetail entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "user",
                    "paper",
                    "question",
                    "answer",
                    "status",
                    "score",
                    "remark",
                    "questionType",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            ExaExamDetail target;
            if (entity.getId() != null) {
                target = exaExamDetailService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaExamDetailService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaExamDetailService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}