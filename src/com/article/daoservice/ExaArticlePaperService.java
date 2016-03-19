package com.article.daoservice;

import com.article.domain.ExaArticlePaper;
import com.article.domain.ExaPaper;
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
public class ExaArticlePaperService extends EntityService<ExaArticlePaper,Long> {
     @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, ExaArticlePaper.class);
    }
}