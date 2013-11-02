package com.article.daoservice;

import com.article.domain.CmsAttachments;
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
public class CmsAttachmentsService extends EntityService<CmsAttachments,Long> {
     @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        initDao(sessionFactory, CmsAttachments.class);
    }
}