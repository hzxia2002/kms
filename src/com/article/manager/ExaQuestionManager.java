package com.article.manager;

import com.article.daoservice.*;
import com.article.domain.*;
import com.comet.core.security.util.SpringSecurityUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ExaQuestionManager {
    @Autowired
    private ExaQuestionService exaQuestionService;

    @Autowired
    private ExaPaperSectionService exaPaperSectionService;

    @Autowired
    private ExaPaperDetailService exaPaperDetailService;


    @Autowired
    private ExaQuestionOptionsService exaQuestionOptionsService;

    @Autowired
    private SurAnswerService surAnswerService;

    public void saveQuestion(ExaQuestion exaQuestion, List< ExaQuestionOptions> exaQuestionOptionses){
        exaQuestionService.save(exaQuestion);
        for (ExaQuestionOptions exaQuestionOptions : exaQuestionOptionses) {
            exaQuestionOptions.setQuestionId(exaQuestion.getId());
            exaQuestionOptionsService.save(exaQuestionOptions);
        }
    }

    public void saveQuestions(String questionIds,Long sectionId){
        ExaPaperSection exaPaperSection = exaPaperSectionService.get(sectionId);
        String [] questionIdArr = questionIds.split(",");
        String savedQuestionIds = getSectionQuestionIds(sectionId);
        for (String questionId : questionIdArr) {
            if(savedQuestionIds.contains("," + questionId + ",")){
                continue;
            }
            ExaPaperDetail paperDetail = new ExaPaperDetail();
            paperDetail.setQuestionId(Long.valueOf(questionId));
            paperDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
            paperDetail.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            paperDetail.setSectionId(sectionId);
            paperDetail.setPaperId(exaPaperSection.getPaperId());
            exaPaperDetailService.save(paperDetail);
        }
    }

    /**
     * 获取题目
     * @param sectionId
     * @return
     */
    public List<ExaPaperDetail> getPaperDetail(Long sectionId){
        return exaPaperDetailService.find("from ExaPaperDetail where sectionId="+sectionId);
    }

    public String getSectionQuestionIds(Long sectionId){
        List<ExaPaperDetail> details = getPaperDetail(sectionId);
        String ids = "";
        for (ExaPaperDetail detail : details) {
            ids += "," + detail.getQuestionId();
        }
        return  ids+",";
    }

}
