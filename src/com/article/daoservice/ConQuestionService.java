package com.article.daoservice;

import com.article.domain.CmsStudyPlan;
import com.article.domain.ConQuestion;
import com.comet.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tcg on 2016/3/20.
 */

@Service
public class ConQuestionService extends EntityService<ConQuestion,Long> {
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, ConQuestion.class);
    }
}
