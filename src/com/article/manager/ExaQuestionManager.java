package com.article.manager;

import com.article.daoservice.*;
import com.article.domain.*;
import com.comet.core.security.util.SpringSecurityUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 保存试卷问题
     *
     * @param questionIds
     * @param sectionId
     */
    public Map<String, Integer> saveQuestions(String questionIds, Long sectionId){
        Map map = new HashMap();
        int totalCount = 0;
        int includedCount = 0;

        ExaPaperSection exaPaperSection = exaPaperSectionService.get(sectionId);
        Long paperId = exaPaperSection.getPaperId();
        String [] questionIdArr = questionIds.split(",");
        String savedQuestionIds = getPaperQuestionIds(paperId);

        for (String questionId : questionIdArr) {
            if(savedQuestionIds.contains("," + questionId + ",")){
                includedCount++;
                continue;
            }

            ExaPaperDetail paperDetail = new ExaPaperDetail();
            paperDetail.setQuestion(exaQuestionService.get(Long.valueOf(questionId)));
            paperDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
            paperDetail.setCreateUser(SpringSecurityUtils.getCurrentUser().getLoginName());
            paperDetail.setSectionId(sectionId);
            paperDetail.setPaperId(exaPaperSection.getPaperId());

            exaPaperDetailService.save(paperDetail);
            totalCount++;
        }

        map.put("totalCount", Integer.valueOf(totalCount));
        map.put("includedCount", Integer.valueOf(includedCount));

        return map;
    }

    /**
     * 删除考试试题
     * @param questionIds
     * @param sectionId
     */
    public void deleteQuestions(String questionIds,Long sectionId){
        String [] questionIdArr = questionIds.split(",");
        String savedQuestionIds = getSectionQuestionIds(sectionId);
        for (String questionId : questionIdArr) {
            if(!savedQuestionIds.contains("," + questionId + ",")){
                continue;
            }
            exaPaperDetailService.delete(Long.valueOf(questionId));
        }
    }

    /**
     * 删除章节同时删除章节中的试题
     * @param sectionId
     */
    public void deleteQuestionsBySectionId(Long sectionId){
        List<ExaPaperDetail> details = getPaperDetail(sectionId);
        for (ExaPaperDetail detail : details) {
            exaPaperDetailService.delete(detail);
        }
         exaPaperSectionService.delete(sectionId);
    }



    /**
     * 获取章节题目
     * @param sectionId
     * @return
     */
    public List<ExaPaperDetail> getPaperDetail(Long sectionId){
        return exaPaperDetailService.find("from ExaPaperDetail where sectionId="+sectionId);
    }

    /**
     * 获取试卷试题
     * @param paperId
     * @return
     */
    public List<ExaPaperDetail> getPaperDetailByPaper(Long paperId){
        return exaPaperDetailService.find("from ExaPaperDetail where paperId="+paperId);
    }

    public String getSectionQuestionIds(Long sectionId){
        List<ExaPaperDetail> details = getPaperDetail(sectionId);
        String ids = "";
        for (ExaPaperDetail detail : details) {
            ids += "," + detail.getQuestion().getId();
        }
        return  ids+",";
    }

    /**
     * 获取一份试卷所有的试题id
     * @param paperId
     * @return
     */
    public String getPaperQuestionIds(Long paperId){
        List<ExaPaperDetail> details = getPaperDetailByPaper(paperId);
        String ids = "";
        for (ExaPaperDetail detail : details) {
            ids += "," + detail.getQuestion().getId();
        }
        return  ids+",";
    }

}
