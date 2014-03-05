package com.article.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Date: 14-3-5
 * Time: 下午9:25
 */
public class PPTToJPGHander extends Thread{
    private static Log log = LogFactory.getLog(PPTToJPGHander.class);
    private String filePath;
    private String outDirctory;

    public PPTToJPGHander(String filePath, String outDirctory) {
        this.filePath = filePath;
        this.outDirctory = outDirctory;
    }

    public  void pptToJPG(){
        PPTOperator jac = null;
        try {
            jac = new PPTOperator(this.filePath,true);
            jac.PPTToJPG(this.outDirctory);
        } catch (Exception e) {
            log.error("PPT转图片失败");
        }
    }


    @Override
    public void run() {
        pptToJPG();
    }
}
