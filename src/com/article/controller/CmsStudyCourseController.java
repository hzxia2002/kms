package com.article.controller;

import com.article.daoservice.CmsArticleService;
import com.article.daoservice.CmsCourseArticleService;
import com.article.daoservice.CmsStudyCourseService;
import com.article.domain.CmsArticle;
import com.article.domain.CmsCourseArticle;
import com.article.domain.CmsStudyCourse;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class CmsStudyCourseController extends BaseCRUDActionController<CmsStudyCourse> {
    private static Log log = LogFactory.getLog(CmsStudyCourseController.class);

    @Autowired
    private CmsStudyCourseService cmsStudyCourseService;

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsCourseArticleService cmsCourseArticleService;


    @RequestMapping
    @ResponseBody
    public Map grid(Page page, String condition) {
        HashMap hashMap = new HashMap();
        try {
            page.setAutoCount(true);

            String hql = "from CmsStudyCourse t where 1=1 order by t.createTime desc" ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = cmsStudyCourseService.findByPage(page, queryTranslate.toString());
            hashMap.put("records",page.getRecords());
            hashMap.put("total",page.getTotal());
            hashMap.put("msg",page.getMsg());
            hashMap.put("pageSize",page.getPageSize());
            hashMap.put("page",page.getPage());

            List<CmsStudyCourse> rows = page.getRows();
            String ids = "";
            for (int i = 0; i < rows.size(); i++) {
                CmsStudyCourse row = rows.get(i);
                if(i<rows.size()-1){
                    ids += row.getId() + ",";
                }else{
                    ids += row.getId() ;
                }
            }
            HashMap<Long,List<CmsArticle>> articleMap = new HashMap<Long,List<CmsArticle>> ();
            if(!ids.equals("")){
                List<CmsCourseArticle> cmsCourseArticles = cmsCourseArticleService.find("from CmsCourseArticle t left join fetch t.article left join fetch t.course where t.course.id in(" + ids + ")");
                for (CmsCourseArticle cmsCourseArticle : cmsCourseArticles) {
                    List<CmsArticle> cmsArticles = articleMap.get(cmsCourseArticle.getCourse().getId());
                    if(cmsArticles ==null){
                        cmsArticles =new ArrayList<CmsArticle>();
                        articleMap.put(cmsCourseArticle.getCourse().getId(),cmsArticles);
                    }
                    cmsArticles.add(cmsCourseArticle.getArticle());
                }
            }

            ArrayList arrayList = new ArrayList();
            for (CmsStudyCourse cmsStudyCourse : rows) {
                HashMap map = new HashMap();
                map.put("id",cmsStudyCourse.getId()) ;
                map.put("remark",cmsStudyCourse.getRemark()) ;
                map.put("name",cmsStudyCourse.getName()) ;
                map.put("studyDuration",cmsStudyCourse.getStudyDuration()) ;
                map.put("startTime",cmsStudyCourse.getStartTime()) ;
                map.put("endTime",cmsStudyCourse.getEndTime()) ;
                if(cmsStudyCourse.getArticle()!=null){
                    List<CmsArticle> cmsArticles = articleMap.get(cmsStudyCourse.getId());
                    if(cmsArticles ==null){
                        cmsArticles =new ArrayList<CmsArticle>();
                        articleMap.put(cmsStudyCourse.getId(),cmsArticles);
                    }
                    cmsArticles.add(cmsStudyCourse.getArticle());
                }
                map.put("articles", articleMap.get(cmsStudyCourse.getId())) ;
                arrayList.add(map);

            }

            hashMap.put("rows",arrayList);
        } catch (Exception e) {
            log.error("error", e);
        }

        return hashMap;
    }

    @RequestMapping
    public String init(Model model, CmsStudyCourse entity) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = cmsStudyCourseService.get(entity.getId());
                List<CmsArticle> cmsArticles = cmsArticleService.find("select t.article from CmsCourseArticle t where t.course.id=" + entity.getId());
                if(entity.getArticle()!=null){
                    cmsArticles.add(entity.getArticle());
                }
                model.addAttribute("articles", cmsArticles);
            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/cms/cmsStudyCourseEdit";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        CmsStudyCourse cmsStudyCourse = cmsStudyCourseService.get(id);

        model.addAttribute("bean", cmsStudyCourse);
        return "view/cms/cmsStudyCourseView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") CmsStudyCourse entity,String articleIds)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "name",
                    "remark",
                    "article",
                    "startTime",
                    "endTime",
                    "studyDuration"
            };

            CmsStudyCourse target;
            if (entity.getId() != null) {
                target = cmsStudyCourseService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
                target.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                target.setUpdateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            } else {
                target = entity;
                target.setCreateTime(new Timestamp(System.currentTimeMillis()));
                target.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            }

            cmsStudyCourseService.save(target);

            if(StringUtils.isNotEmpty(articleIds)){
                String[] articleIdArr = articleIds.split(",");
                for (String id : articleIdArr) {
                    if(StringUtils.isNotEmpty(id)){
                        if(cmsCourseArticleService.find("from CmsCourseArticle where course.id="+target.getId()+" and article.id="+id).size()==0){
                            CmsArticle cmsArticle = cmsArticleService.get(Long.valueOf(id));
                            CmsCourseArticle cmsCourseArticle = new CmsCourseArticle();
                            cmsCourseArticle.setArticle(cmsArticle);
                            cmsCourseArticle.setCourse(target);
                            cmsCourseArticleService.save(cmsCourseArticle);
                        }

                    }
                }

            }
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        cmsStudyCourseService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public void deleteArticle(HttpServletResponse response, String articleIds,String courseId) throws Exception {
        if(StringUtils.isNotEmpty(courseId)){
            String[] articleIdArr = articleIds.split(",");
            for (String id : articleIdArr) {
                if(StringUtils.isNotEmpty(id)){
                    List<CmsCourseArticle> cmsCourseArticles = cmsCourseArticleService.find("from CmsCourseArticle where course.id=" + courseId + " and article.id=" + id);
                    for (CmsCourseArticle cmsCourseArticle : cmsCourseArticles) {
                        cmsCourseArticleService.delete(cmsCourseArticle);
                    }
                }
            }
        }
        sendSuccessJSON(response, "删除成功");
    }

}