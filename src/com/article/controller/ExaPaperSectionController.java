package com.article.controller;

import com.article.daoservice.ExaPaperSectionService;
import com.article.domain.ExaPaperSection;
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
public class ExaPaperSectionController extends BaseCRUDActionController<ExaPaperSection> {
    private static Log log = LogFactory.getLog(ExaPaperSectionController.class);

    @Autowired
	private ExaPaperSectionService exaPaperSectionService;



	@RequestMapping
    @ResponseBody
	public Page<ExaPaperSection> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaPaperSection t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaPaperSectionService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaPaperSection entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaPaperSectionService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaPaperSectionEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaPaperSection exaPaperSection = exaPaperSectionService.get(id);

        model.addAttribute("bean", exaPaperSection);
        return "view/exa/exaPaperSectionView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaPaperSection entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "paper",
                    "db",
                    "sectionName",
                    "perScore",
                    "questionNums",
                    "questionLevel",
                    "questionType",
                    "remark",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            ExaPaperSection target;
            if (entity.getId() != null) {
                target = exaPaperSectionService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaPaperSectionService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaPaperSectionService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}