package com.article.controller;

import com.article.daoservice.SurAnswerService;
import com.article.daoservice.SurQuestionService;
import com.article.daoservice.SurQuestionaryService;
import com.article.daoservice.SurUserQuestionaryService;
import com.article.domain.*;
import com.article.manager.QuestionManager;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.orm.hibernate.EntityService;
import com.comet.core.orm.hibernate.Page;
import com.comet.core.orm.hibernate.QueryTranslate;
import com.comet.core.security.user.BaseUser;
import com.comet.core.security.util.SpringSecurityUtils;
import com.comet.core.utils.ReflectionUtils;
import com.comet.system.daoservice.SysUserService;
import com.comet.system.domain.SysUser;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0
 * @author: System
 */
@Controller
public class SurUserQuestionaryController extends BaseCRUDActionController<SurUserQuestionary> {
    private static Log log = LogFactory.getLog(SurUserQuestionaryController.class);

    @Autowired
    private SurUserQuestionaryService surUserQuestionaryService;

    @Autowired
    private SurQuestionaryService surQuestionaryService;

    @Autowired
    private SurQuestionService surQuestionService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SurAnswerService surAnswerService;

    @Autowired
    private QuestionManager questionManager;



    @RequestMapping
    @ResponseBody
    public Page<SurUserQuestionary> grid(Page page, String condition,Long questionaryId) {
        try {
            page.setAutoCount(true);

            String hql = "from SurUserQuestionary t where 1=1 and t.questionary.id="+questionaryId ;

            QueryTranslate queryTranslate = new QueryTranslate(hql, condition);

            // 查询
            page = surUserQuestionaryService.findByPage(page, queryTranslate.toString());
        } catch (Exception e) {
            log.error("error", e);
        }

        return page;
    }

    @RequestMapping
    public String init(Model model, SurUserQuestionary entity,Long questionaryId) throws Exception {
        try {
            if(entity != null && entity.getId() != null) {
                entity = surUserQuestionaryService.get(entity.getId());
            }else{
                SurQuestionary surQuestionary = surQuestionaryService.get(questionaryId);
                entity.setQuestionary(surQuestionary);
            }
            model.addAttribute("bean", entity);
        } catch (Exception e) {
            log.error("error", e);
        }
        if(entity.getId() != null){
            return "view/sur/surUserEdit";
        }
        return "view/sur/surUserQuestionaryEdit";
    }

    @RequestMapping
    public String initGrid(Model model, Long questionaryId) throws Exception {
        try {
            SurQuestionary surQuestionary = surQuestionaryService.get(questionaryId);
            model.addAttribute("bean",surQuestionary);
        } catch (Exception e) {
            log.error("error", e);
        }

        return "view/sur/surUserQuestionaryGrid";
    }

    @RequestMapping
    public String view(Model model, Long id) {
        SurUserQuestionary surUserQuestionary = surUserQuestionaryService.get(id);
        SurQuestionary surQuestionary = surUserQuestionary.getQuestionary();
        String hql = "select distinct q from SurQuestion q left join fetch q.surOptions where q.questionary.id="+surQuestionary.getId()+" order by q.indexNo";
        List<SurQuestion> surQuestions = surQuestionService.find(hql);
        model.addAttribute("bean", surQuestionary);
        model.addAttribute("questions", surQuestions);

        //取答案
        List<SurAnswer> surAnswers = surAnswerService.find("from SurAnswer a left join fetch a.question left join fetch a.option where a.userQuestionary.id=" + id);
        HashMap answerMap = new HashMap();
        for (SurAnswer surAnswer : surAnswers) {
            Long questionId = surAnswer.getQuestion().getId();
            Long optionId = surAnswer.getOption().getId();
            if(answerMap.get(questionId)==null){
                answerMap.put(questionId, optionId.toString()) ;
            }else{
                answerMap.put(questionId,answerMap.get(questionId)+","+ optionId) ;
            }
        }
        model.addAttribute("answerKey",answerMap);
        return "view/sur/surUserQuestionaryView";
    }

    @RequestMapping
    public void save(HttpServletResponse response, Model model, @ModelAttribute("bean") SurUserQuestionary entity)
            throws Exception {
        try {
            String[] columns = new String[]{
                    "id",
                    "questionary",
                    "user",
                    "startTime",
                    "endTime"
            };

            SurUserQuestionary target;
            if (entity.getId() != null) {
                target = surUserQuestionaryService.get(entity.getId());

                ReflectionUtils.copyBean(entity, target, columns);
            } else {
                target = entity;
            }

            surUserQuestionaryService.save(target);
        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, "保存成功");
    }

    @RequestMapping
    public void delete(HttpServletResponse response, Long id) throws Exception {
        surUserQuestionaryService.delete(id);

        sendSuccessJSON(response, "删除成功");
    }

    @RequestMapping
    public void saveUsers(HttpServletResponse response, Model model, @ModelAttribute("bean") SurUserQuestionary entity,String userIds)
            throws Exception {
        String msg = "保存成功";
        try {
            if(StringUtils.isNotEmpty(userIds)){
                String[] userIdArr = userIds.split(",");
                String hql = "from SurUserQuestionary sq left join fetch sq.user where sq.questionary.id="+entity.getQuestionary().getId()+" and sq.user.id in("+userIds+")";
                List<SurUserQuestionary> surUserQuestionaries = surUserQuestionaryService.find(hql);
                String ids = ",";
                for (SurUserQuestionary surUserQuestionary : surUserQuestionaries) {
                    ids += surUserQuestionary.getUser().getId()+",";
                }
                for (String id : userIdArr) {
                    if(StringUtils.isNotEmpty(id)&&!ids.contains(","+id+",")){
                        SysUser sysUser = sysUserService.get(Long.valueOf(id));
                        SurUserQuestionary surUserQuestionary = new SurUserQuestionary();
                        surUserQuestionary.setStatus("0");
                        surUserQuestionary.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        surUserQuestionary.setQuestionary(entity.getQuestionary());
                        surUserQuestionary.setUser(sysUser);
                        surUserQuestionary.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
                        surUserQuestionary.setStartTime(entity.getStartTime());
                        surUserQuestionary.setEndTime(entity.getEndTime());

                        surUserQuestionaryService.save(surUserQuestionary);
                    }
                }
            }

        } catch (Exception e) {
            log.error("error", e);
            super.processException(response, e);
        }

        sendSuccessJSON(response, msg);
    }


    @RequestMapping
    public String saveQuestionary(HttpServletRequest request,HttpServletResponse response,Long questioanryId,Model model) throws Exception {
        BaseUser currentUser = SpringSecurityUtils.getCurrentUser();

        String hql = "from SurUserQuestionary sq left join fetch sq.questionary where sq.user.id="+ currentUser.getId()+" and sq.questionary.id="+questioanryId;
        List<SurUserQuestionary> surUserQuestionaries = surUserQuestionaryService.find(hql);
        if(surUserQuestionaries.size()>0){
            SurUserQuestionary surUserQuestionary = surUserQuestionaries.get(0);

            String status = surUserQuestionary.getStatus();
            //已提交
            if("1".equals(status)){
                model.addAttribute("msg","该调查问卷已经作答!");
                return "view/common/error";
            }else{
                Long questionaryId = surUserQuestionary.getQuestionary().getId();
                String questionHql = "from SurQuestion where questionary.id = " + questionaryId;
                List<SurQuestion> surQuestions = surQuestionService.find(questionHql);
                HashMap hashMap = new HashMap();
                for (SurQuestion surQuestion : surQuestions) {
                    String[] answerStrs =(String[]) request.getParameterValues("question_" + surQuestion.getId());
//                    String[] answerStrs = answer.split(",");
                    if(answerStrs != null && answerStrs.length > 0) {
                        for (String answerStr : answerStrs) {
                            SurAnswer surAnswer = new SurAnswer();
                            surAnswer.setQuestion(surQuestion);
                            surAnswer.setUser(sysUserService.get(currentUser.getId()));
                            surAnswer.setOption(new SurOptions(Long.valueOf(answerStr)));
                            surAnswer.setUserQuestionary(surUserQuestionary);
                            surAnswerService.save(surAnswer);
                        }
                    }
                }

                surUserQuestionary.setStatus("1");
                surUserQuestionary.setAnswerTime(new Timestamp(System.currentTimeMillis()));
                surUserQuestionaryService.save(surUserQuestionary);
            }
        }
        model.addAttribute("msg","您已成功完成答卷");
        model.addAttribute("path","view/sur/successMsg.jsp");
        return "view/common/success";
    }


    @RequestMapping
    public String survey(Model model, Long id) {
        BaseUser currentUser = SpringSecurityUtils.getCurrentUser();
        String hql = "from SurUserQuestionary t left join fetch t.questionary where t.questionary.id="+ id +" and t.user.id=" + currentUser.getId();
//        String currentTimeStr = new Timestamp(System.currentTimeMillis()).toString();
//        hql +=" and (t.startTime<='"+ currentTimeStr +"' and t.endTime>='"+currentTimeStr+"')";
//        hql +=" or t.startTime is null or endTime is null";
        hql += " and t.status=0";
        List<SurUserQuestionary> surUserQuestionaries = surUserQuestionaryService.find(hql);
        if(surUserQuestionaries.size()==1){
            SurQuestionary surQuestionary = surUserQuestionaries.get(0).getQuestionary();
            String questionHql = "select distinct q from SurQuestion q left join fetch q.surOptions where q.questionary.id="+id+" order by q.indexNo";
            List<SurQuestion> surQuestions = surQuestionService.find(questionHql);
            model.addAttribute("bean", surQuestionary);
            model.addAttribute("questions", surQuestions);
            return "view/sur/survey";
        }else{
            model.addAttribute("msg", "您的问卷不存在或者已作答");
            return "view/common/error";
        }

    }

    @RequestMapping
    public String statReport(Model model, Long id) {
        //查询多少人参加
        String countSql = "SELECT COUNT(STATUS) as 'count',STATUS as 'status' FROM sur_user_questionary WHERE questionary_id = "+id +" GROUP BY STATUS ";
        List<Object[]> counts =(List<Object[]>) surAnswerService.findBySql(countSql);
        int examNum = 0;
        int noExamNum = 0;
        int totalNum = 0;
        for (Object[] data : counts) {
            if(Integer.valueOf(data[1].toString())==1){
                examNum = Integer.valueOf(data[0].toString());
            }else{
                noExamNum = Integer.valueOf(data[0].toString());
            }
        }
        totalNum = examNum + noExamNum;
        //查询每道题答案的选项
//        String sql = "SELECT COUNT(a.user_id),o.content,o.index_no,o.id as 'oid',q.title,q.index_no AS qNo,q.id as qid FROM sur_answer a , sur_options o ,sur_question q " +
//                "WHERE a.option_id = o.id AND a.question_id = q.id and q.questionary_id=" +id+
//                " GROUP BY o.content,o.index_no,oid,q.title,q.index_no,qid" +
//                " ORDER BY q.index_no,o.index_no";
//
//        List<Object[]> answers = (List<Object[]>)surAnswerService.findBySql(sql);
        List<Object[]> answers = questionManager.getAnswerList(id);
        ArrayList questionList = new ArrayList();
        Long lastId = null;
        HashMap<String, Object> questionMap = new HashMap<String, Object>();
        NumberFormat nf = NumberFormat.getInstance();

        for (Object[] answer : answers) {
            float value = Float.valueOf(answer[0].toString()) * 100 / examNum;
            String formatValue = String.format("%1$.2f", value);
            if(!Long.valueOf(answer[6].toString()).equals(lastId)){
                questionMap = new HashMap<String, Object>();
                questionMap.put("count",answer[0]);
                questionMap.put("questionTitle",answer[4]);
                questionMap.put("questionNo",answer[5]);
                questionMap.put("questionId",answer[6]);
                //添加选项
                ArrayList arrayList = new ArrayList();
                HashMap optionMap = new HashMap();
                optionMap.put("optionTitle",answer[1]);
                optionMap.put("optionNo",answer[2]);
                optionMap.put("percent", formatValue);
                optionMap.put("optionId", answer[3]);
                arrayList.add(optionMap);
                questionMap.put("options",arrayList);

                questionList.add(questionMap);
            }else{
                List arrayList =(List)questionMap.get("options");
                HashMap optionMap = new HashMap();
                optionMap.put("optionTitle",answer[1]);
                optionMap.put("optionNo", answer[2]);
                optionMap.put("optionId", answer[3]);
                optionMap.put("percent", formatValue);
                arrayList.add(optionMap);
            }
            lastId = Long.valueOf(answer[6].toString());
        }


        model.addAttribute("examNum",examNum);
        model.addAttribute("totalNum",totalNum);
        model.addAttribute("noExamNum",noExamNum);
        model.addAttribute("questions",questionList);
        return "view/sur/statView";

    }

    @RequestMapping
    public String statUser(Model model, Long id,Long questionId) {
        List<SysUser> sysUsers = sysUserService.find("select t.user from SurAnswer t where t.option.id=" + id+" and t.question.id="+questionId);
        model.addAttribute("users",sysUsers);
        return "view/sur/statUser";
    }
}