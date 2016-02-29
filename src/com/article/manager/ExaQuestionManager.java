package com.article.manager;

import com.article.daoservice.*;
import com.article.domain.ExaQuestion;
import com.article.domain.ExaQuestionOptions;
import com.article.domain.SurOptions;
import com.article.domain.SurQuestion;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
