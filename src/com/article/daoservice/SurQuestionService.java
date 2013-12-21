package com.article.daoservice;

import com.article.domain.SurQuestion;
import com.comet.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tcg
 * User: tcg
 *
 */
@Service
public class SurQuestionService extends EntityService<SurQuestion,Long> {
     @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, SurQuestion.class);
    }
}