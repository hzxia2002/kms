package com.article.manager;

import com.article.daoservice.ExaPaperDetailService;
import com.article.daoservice.ExaPaperSectionService;
import com.article.domain.ExaPaperDetail;
import com.article.domain.ExaPaperSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tcg on 2016/2/29.
 */
@Service
public class ExaPaperSectionManager {

    @Autowired
    private ExaPaperSectionService exaPaperSectionService;

    @Autowired
    private ExaPaperDetailService exaPaperDetailService;

    public void deletePaperSection(Long sectionId){
        exaPaperSectionService.delete(sectionId);

        List<ExaPaperDetail> exaPaperDetails = exaPaperDetailService.find("from ExaPaperDetail where sectionId="+sectionId);
        for (ExaPaperDetail exaPaperDetail : exaPaperDetails) {
            exaPaperDetailService.delete(exaPaperDetail);
        }

    }

}
