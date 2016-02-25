package com.article.controller;

import com.article.daoservice.ExaPaperUsergroupService;
import com.article.domain.ExaPaperUsergroup;
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
public class ExaPaperUsergroupController extends BaseCRUDActionController<ExaPaperUsergroup> {
    private static Log log = LogFactory.getLog(ExaPaperUsergroupController.class);

    @Autowired
	private ExaPaperUsergroupService exaPaperUsergroupService;



	@RequestMapping
    @ResponseBody
	public Page<ExaPaperUsergroup> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaPaperUsergroup t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaPaperUsergroupService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaPaperUsergroup entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaPaperUsergroupService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaPaperUsergroupEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaPaperUsergroup exaPaperUsergroup = exaPaperUsergroupService.get(id);

        model.addAttribute("bean", exaPaperUsergroup);
        return "view/exa/exaPaperUsergroupView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaPaperUsergroup entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "paper",
                    "role",
                    "user",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            ExaPaperUsergroup target;
            if (entity.getId() != null) {
                target = exaPaperUsergroupService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaPaperUsergroupService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaPaperUsergroupService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}