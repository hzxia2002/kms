package com.article.daoservice;

import com.article.domain.CmsCatalog;
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
public class CmsCatalogService extends EntityService<CmsCatalog,Long> {
     @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, CmsCatalog.class);
    }
}