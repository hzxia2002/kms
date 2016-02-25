package com.article.controller;

import com.article.daoservice.ExaPaperRandomService;
import com.article.domain.ExaPaperRandom;
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
public class ExaPaperRandomController extends BaseCRUDActionController<ExaPaperRandom> {
    private static Log log = LogFactory.getLog(ExaPaperRandomController.class);

    @Autowired
	private ExaPaperRandomService exaPaperRandomService;



	@RequestMapping
    @ResponseBody
	public Page<ExaPaperRandom> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaPaperRandom t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaPaperRandomService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaPaperRandom entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaPaperRandomService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaPaperRandomEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaPaperRandom exaPaperRandom = exaPaperRandomService.get(id);

        model.addAttribute("bean", exaPaperRandom);
        return "view/exa/exaPaperRandomView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaPaperRandom entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "paper",
                    "user",
                    "paperOrderDetail",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            ExaPaperRandom target;
            if (entity.getId() != null) {
                target = exaPaperRandomService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaPaperRandomService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaPaperRandomService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}