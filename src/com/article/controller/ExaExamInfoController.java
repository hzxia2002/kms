package com.article.controller;

import com.article.daoservice.ExaExamInfoService;
import com.article.domain.ExaExamInfo;
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
public class ExaExamInfoController extends BaseCRUDActionController<ExaExamInfo> {
    private static Log log = LogFactory.getLog(ExaExamInfoController.class);

    @Autowired
	private ExaExamInfoService exaExamInfoService;



	@RequestMapping
    @ResponseBody
	public Page<ExaExamInfo> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from ExaExamInfo t where 1=1 " + page.getOrderByString("t.treeId asc");

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = exaExamInfoService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, ExaExamInfo entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = exaExamInfoService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/exa/exaExamInfoEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        ExaExamInfo exaExamInfo = exaExamInfoService.get(id);

        model.addAttribute("bean", exaExamInfo);
        return "view/exa/exaExamInfoView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") ExaExamInfo entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "user",
                    "paper",
                    "startTime",
                    "endTime",
                    "ip",
                    "score",
                    "status",
                    "remark",
                    "createTime",
                    "updateTime",
                    "updateUser",
                    "createUser"
            };

            ExaExamInfo target;
            if (entity.getId() != null) {
                target = exaExamInfoService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            exaExamInfoService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        exaExamInfoService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}