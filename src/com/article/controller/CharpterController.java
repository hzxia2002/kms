package com.article.controller;

import com.article.daoservice.CharpterService;
import com.article.domain.Charpter;
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
public class CharpterController extends BaseCRUDActionController<Charpter> {
    private static Log log = LogFactory.getLog(CharpterController.class);

    @Autowired
	private CharpterService charpterService;



	@RequestMapping
    @ResponseBody
	public Page<Charpter> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from Charpter t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = charpterService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, Charpter entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = charpterService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/res/charpterEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        Charpter charpter = charpterService.get(id);

        model.addAttribute("bean", charpter);
        return "view/res/charpterView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") Charpter entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "questionary",
                    "indexNo",
                    "description"
            };

            Charpter target;
            if (entity.getId() != null) {
                target = charpterService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            charpterService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        charpterService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}