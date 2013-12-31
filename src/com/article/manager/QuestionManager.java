package com.article.manager;

import com.article.daoservice.CmsCatalogService;
import com.article.daoservice.SurAnswerService;
import com.article.daoservice.SurOptionsService;
import com.article.daoservice.SurQuestionService;
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
public class QuestionManager {
    @Autowired
    private SurQuestionService surQuestionService;

    @Autowired
    private SurOptionsService surOptionsService;

    @Autowired
    private SurAnswerService surAnswerService;

    public void saveQuestion(SurQuestion surQuestion,List<SurOptions> surOptionses){
        surQuestionService.save(surQuestion);
        for (SurOptions surOptionse : surOptionses) {
            surOptionsService.save(surOptionse);
        }
    }

    public void delteQuestion(SurQuestion surQuestion){
        String hql = "from SurOptions where question.id="+surQuestion.getId();
        List<SurOptions> surOptionses = surOptionsService.find(hql);
        for (SurOptions surOptionse : surOptionses) {
            surOptionsService.delete(surOptionse);
        }
        surQuestionService.delete(surQuestion);
    }

    public List getAnswerList(Long id){
        String sql = "SELECT COUNT(a.user_id) as count,o.content as content,o.index_no as indexNo,o.id as 'oid',q.title as title,q.index_no AS qNo,q.id as qid FROM sur_answer a , sur_options o ,sur_question q " +
                "WHERE a.option_id = o.id AND a.question_id = q.id and q.questionary_id=" +id+
                " GROUP BY o.content,o.index_no,oid,q.title,q.index_no,qid" +
                " ORDER BY q.index_no,o.index_no";

        List<Object[]> answers = (List<Object[]>)surAnswerService.entityDao().createSQLQuery(sql).
                addScalar("count", Hibernate.INTEGER).
                addScalar("content", Hibernate.STRING).
                addScalar("indexNo", Hibernate.STRING).
                addScalar("oid", Hibernate.LONG).
                addScalar("title", Hibernate.STRING).
                addScalar("qNo", Hibernate.STRING).
                addScalar("qid",Hibernate.LONG).list();
        return answers;
    }
}
