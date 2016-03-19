package com.article.manager;

import com.article.daoservice.ExaPaperDetailService;
import com.article.daoservice.ExaPaperSectionService;
import com.article.domain.ExaPaperDetail;
import com.article.domain.ExaPaperSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tcg on 2016/3/19.
 */
@Service
public class ExaPaperManager {
    @Autowired
    private ExaPaperSectionService exaPaperSectionService;

    @Autowired
    private ExaPaperDetailService exaPaperDetailService;

    /**
     * 获取试卷篇章
     * @param paperId
     * @return
     */
    public List<ExaPaperSection> getExaPaperSections(Long paperId){
        return exaPaperSectionService.find("from ExaPaperSection where paperId="+paperId+" order by orderNo");
    }

    /**
     * 获取试题
     * @param paperId
     * @return
     */
    public  List<ExaPaperDetail> getPaperDetail(Long paperId){
        return exaPaperDetailService.find("select distinct d from ExaPaperDetail d left join fetch d.question q left join fetch q.options  p where d.paperId="+paperId);
    }



}
