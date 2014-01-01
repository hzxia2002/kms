package com.article.controller;

import com.article.daoservice.CmsArticleService;
import com.article.domain.CmsArticle;
import com.article.util.highlight.HighlightUtils;
import com.comet.core.orm.hibernate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 14-1-1.
 */
@Controller
public class SearchController {
    @Autowired
    private CmsArticleService cmsArticleService;

    @RequestMapping
    public String view(HttpServletRequest request,Model model,String key,Page page) throws Exception {
        String hql = "from CmsArticle t where (t.title like '%" + key + "%'";
        hql += " or t.content like '%" + key + "%'";
        hql += " or t.keyWord like '%" + key + "%')";
        hql += " order by t.publishDate desc";

        page.setAutoCount(true);
        page.setPageSize(10);

        page = cmsArticleService.findByPage(page, hql);

        // 对结果进行处理
        List list = page.getRows();

        if(list != null && list.size() > 0) {
            List retList = new ArrayList();
            for(int i=0; i<list.size(); i++) {
                CmsArticle bean = (CmsArticle)list.get(i);

                bean.setTitle(HighlightUtils.highlight(bean.getTitle(), key));

                String content = bean.getContent();

                // 去掉文字中的html标签
                content = HighlightUtils.trimHtml(content);

                bean.setContent(HighlightUtils.highlight(content, key, true, 180));

                retList.add(bean);
            }

            page.setRows(retList);
        }

        model.addAttribute("key", key);
        model.addAttribute("page", page);

        return "pages/search";
    }
}
