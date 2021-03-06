package com.article.controller;

import com.article.daoservice.*;
import com.article.domain.*;
import com.article.manager.ExaPaperManager;
import com.article.util.Constants;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Condition;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysUser;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class StudyController extends PageController {
    private static Log log = LogFactory.getLog(StudyController.class);

    @Autowired
    private CmsArticleService articleService;

    @Autowired
    private CmsStudyPlanService studyPlanService;

    @Autowired
    private ExaPaperManager exaPaperManager;

    @Autowired
    private DocAttachmentsService docAttachmentsService;

    @Autowired
    private CmsStudyCourseService cmsStudyCourseService;

    @Autowired
    private CmsCourseArticleService cmsCourseArticleService;

    @RequestMapping
    public String init(HttpServletRequest request,Model model, Long studyPlanId,Long articleId) throws Exception {
        try {
            CmsStudyPlan cmsStudyPlan = studyPlanService.get(studyPlanId);
            CmsStudyCourse cmsStudyCourse = cmsStudyPlan.getCourse();
            CmsArticle cmsArticle = new CmsArticle();
            List<CmsArticle> cmsArticles = articleService.find("select t.article from CmsCourseArticle t where t.course.id=" + cmsStudyCourse.getId());
            if(cmsStudyCourse.getArticle()!=null){
                cmsArticle = cmsStudyCourse.getArticle();
                cmsArticles.add(cmsArticle);
            }
            if(articleId!=null){
                cmsArticle = articleService.get(articleId);
            }else if(cmsArticles.size()>0) {
                cmsArticle =  cmsArticles.get(0);
            }
            model.addAttribute("articles",cmsArticles);
            model.addAttribute("planId",studyPlanId);

            CmsCatalog path = cmsArticle.getPath();
            String paths = cmsArticle.getTitle();
            while (path!=null) {
                paths = path.getName()+ ">>" + paths;
                if(path.getParent()==null){
                    if(path.getPath().equals(Constants.LAW_PROGRAM_KMS)){
                        model.addAttribute("type","2");
                    }else if (path.getPath().equals(Constants.INVESTIGATION_KMS)){
                        model.addAttribute("type","3");
                    }else if (path.getPath().equals(Constants.CASE_KMS)){
                        model.addAttribute("type","4");
                        // 微课程去学习页面，激战法使用普通页面浏览
                        if(org.apache.commons.lang.StringUtils.equals(cmsArticle.getPath().getPath(), "weikecheng")){
                            return view(request,model,cmsArticle.getId());
                        }
                    }
                }
                path = path.getParent();
            }
            //点击次数增加1
            Long visitTimes = cmsArticle.getVisitTimes();
            if(visitTimes==null){
                visitTimes = 1L;
            }else{
                visitTimes += 1L;
            }
            cmsArticle.setVisitTimes(visitTimes);
            Long docId = cmsArticle.getDocId();
            if(docId!=null){
                List<DocAttachments> docAttachmentses = docAttachmentsService.find("from DocAttachments where doc.id=" + docId);
                model.addAttribute("docAttachmentses", docAttachmentses);
            }
            model.addAttribute("bean", cmsArticle);
            model.addAttribute("paths",paths);

            //试卷
            List<ExaPaper> exaPapers = exaPaperManager.getExaPapers(cmsStudyCourse.getId());
            model.addAttribute("papers",exaPapers);


            //文章保存
            if(cmsArticle.getId()!=null){
                articleService.save(cmsArticle);
            }
            //最后开始学习时间
            cmsStudyPlan.setLastStudyTime(new Timestamp(System.currentTimeMillis()));
            Long studyTimes = cmsStudyPlan.getStudyTimes();
            if(studyTimes == null){
                studyTimes = 0L;
            }
            cmsStudyPlan.setStudyTimes(studyTimes + 1);
            cmsStudyPlan.setLastStudyDuration(0L);
            studyPlanService.save(cmsStudyPlan);

        } catch (Exception e) {
            log.error("error", e);
        }
        return "pages/study";
    }

    @RequestMapping
    public void inform(HttpServletResponse response,Long planId) throws Exception {
        CmsStudyPlan cmsStudyPlan = studyPlanService.get(planId);
        Long studyDuration = cmsStudyPlan.getStudyDuration();
        if(studyDuration==null){
            studyDuration = 0L;
        }
        Timestamp lastStudyTime = cmsStudyPlan.getLastStudyTime();
        if(lastStudyTime==null){
            lastStudyTime = new Timestamp(System.currentTimeMillis());
            cmsStudyPlan.setLastStudyTime(lastStudyTime);
        }
        Long lastStudyDuration = (System.currentTimeMillis() - lastStudyTime.getTime()) / (60000);
        studyDuration += 1;
        cmsStudyPlan.setStudyDuration(studyDuration);
        cmsStudyPlan.setLastStudyDuration(lastStudyDuration);
        studyPlanService.save(cmsStudyPlan);
        sendSuccessJSON(response, "更新成功");
    }

}