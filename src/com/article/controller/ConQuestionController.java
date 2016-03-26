package com.article.controller;

import com.article.daoservice.ConQuestionService;
import com.article.domain.CmsComment;
import com.article.domain.ConQuestion;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.util.SpringSecurityUtils;
import org.openqa.selenium.remote.server.JeeServletHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tcg on 2016/3/20.
 */
@Controller
public class ConQuestionController extends BaseCRUDActionController<ConQuestion> {
    @Autowired
    private ConQuestionService conQuestionService;


    @RequestMapping
    @ResponseBody
    public Page<ConQuestion> grid(Page page, String condition) {
        try {
            page.setAutoCount(true);

            String hql = "from ConQuestion t where t.refId is null order by t.isTop desc";

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = conQuestionService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    public String conView(Model model, Long conId) throws Exception {
        ConQuestion conQuestion = conQuestionService.findUnique("from ConQuestion t where t.id=" + conId);

        String hql = "from ConQuestion t where t.refId =" + conId;
        List<ConQuestion> answers = conQuestionService.find(hql);

        model.addAttribute("question", conQuestion);
        model.addAttribute("answers", answers);
        return "view/cms/conView";
    }

    @RequestMapping
    public void toTop(HttpServletResponse response,Model model, Long conId,Boolean top) throws Exception {
        ConQuestion conQuestion = conQuestionService.findUnique("from ConQuestion t where t.id=" + conId);
        conQuestion.setIsTop(top);
        conQuestionService.save(conQuestion);
        sendSuccessJSON(response, top?"设置置顶成功":"取消置顶成功");
    }

    @RequestMapping
    public String doReplay(Model model, Long id) throws Exception {
        ConQuestion conQuestion = conQuestionService.get(id);
        model.addAttribute("conId",id);
        model.addAttribute("bean",conQuestion);

        List<ConQuestion> conQuestions = getConQuestions(id);
        if(conQuestions.size()>0){
            model.addAttribute("reply",conQuestions.get(0));
        }
        return "view/cms/conQuestionEdit";
    }

    public List<ConQuestion> getConQuestions(Long id) {
        return conQuestionService.find("from ConQuestion t where t.refId=" + id);
    }

    @RequestMapping
    public void replySave(HttpServletResponse response,Model model, ConQuestion entity) throws Exception {
        ConQuestion conQuestion = conQuestionService.get(entity.getRefId());
        conQuestion.setResponser(SpringSecurityUtils.getCurrentUser().getLoginName());
        conQuestion.setResponseTime(new Timestamp(System.currentTimeMillis()));

        ConQuestion target ;
        if(entity.getId()!=null){
            target = conQuestionService.get(entity.getId());
            target.setContent(entity.getContent());
        }else{
            target = entity;
            target.setResponser(SpringSecurityUtils.getCurrentUser().getLoginName());
            target.setResponseTime(new Timestamp(System.currentTimeMillis()));
        }
        conQuestionService.save(conQuestion);
        conQuestionService.save(target);
        sendSuccessJSON(response,"回复成功");
    }


    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        conQuestionService.delete(id);
        List<ConQuestion> conQuestions = getConQuestions(id);
        for (ConQuestion conQuestion : conQuestions) {
            conQuestionService.delete(conQuestion);
        }

        sendSuccessJSON(response, "删除成功");
    }

}
