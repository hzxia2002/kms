package com.article.controller;

import com.article.daoservice.ExaQuestionDbService;
import com.article.domain.ExaQuestionDb;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
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
import java.sql.Timestamp;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class ExaQuestionDbController extends BaseCRUDActionController<ExaQuestionDb> {
    private static Log log = LogFactory.getLog(ExaQuestionDbController.class);

    @Autowired
	private ExaQuestionDbService exaQuestionDbService;



	@RequestMapping
    @ResponseBody
	public Page<ExaQuestionDb> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaQuestionDb t where 1=1 " ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaQuestionDbService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaQuestionDb entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaQuestionDbService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaQuestionDbEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaQuestionDb exaQuestionDb = exaQuestionDbService.get(id);

        model.addAttribute("bean", exaQuestionDb);
        return "view/exa/exaQuestionDbView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaQuestionDb entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "dbName",
                    "remark",
                    "status"

            };

            ExaQuestionDb target;
            if (entity.getId() != null) {
                target = exaQuestionDbService.get(entity.getId());
                target.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                target.setUpdateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
                target.setCreateTime(new Timestamp(System.currentTimeMillis()));
                target.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            }

            exaQuestionDbService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaQuestionDbService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}