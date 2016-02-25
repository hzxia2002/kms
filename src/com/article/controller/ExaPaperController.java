package com.article.controller;

import com.article.daoservice.ExaPaperService;
import com.article.domain.ExaPaper;
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
public class ExaPaperController extends BaseCRUDActionController<ExaPaper> {
    private static Log log = LogFactory.getLog(ExaPaperController.class);

    @Autowired
	private ExaPaperService exaPaperService;



	@RequestMapping
    @ResponseBody
	public Page<ExaPaper> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaPaper t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaPaperService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaPaper entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaPaperService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaPaperEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaPaper exaPaper = exaPaperService.get(id);

        model.addAttribute("bean", exaPaper);
        return "view/exa/exaPaperView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaPaper entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "paperName",
                    "startTime",
                    "endTime",
                    "paperMinute",
                    "totalScore",
                    "questionOrderType",
                    "postTime",
                    "showScoreTime",
                    "isRandPaper",
                    "status",
                    "remark",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            ExaPaper target;
            if (entity.getId() != null) {
                target = exaPaperService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaPaperService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaPaperService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}