package com.article.controller;

import com.article.daoservice.CmsCollectArticleService;
import com.article.domain.CmsCollectArticle;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.utils.ReflectionUtils;
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
public class CmsCollectArticleController extends BaseCRUDActionController<CmsCollectArticle> {
    private static Log log = LogFactory.getLog(CmsCollectArticleController.class);

    @Autowired
	private CmsCollectArticleService cmsCollectArticleService;



	@RequestMapping
    @ResponseBody
	public Page<CmsCollectArticle> grid(Page page, String condition) {
		try {
            page.setAutoCount(true);

            String hql = "from CmsCollectArticle t where 1=1 " ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsCollectArticleService.findByPage(page, queryTranslate.toString());
		} catch (Exception e) {
            log.error("error", e);
		}

        return page;
	}

    @RequestMapping
    public String init(Model model, CmsCollectArticle entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsCollectArticleService.get(entity.getId());

                model.addAttribute("bean", entity);
            }
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsCollectArticleEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsCollectArticle cmsCollectArticle = cmsCollectArticleService.get(id);

        model.addAttribute("bean", cmsCollectArticle);
        return "view/cms/cmsCollectArticleView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsCollectArticle entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "catagory",
                    "user",
                    "url",
                    "remark"
            };

            CmsCollectArticle target;
            if (entity.getId() != null) {
                target = cmsCollectArticleService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            cmsCollectArticleService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsCollectArticleService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }
}