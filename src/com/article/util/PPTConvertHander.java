package com.article.util;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Date: 13-11-8
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
public class PPTConvertHander extends Thread{
    public static final String PNG_EXT_NAME = "png";
    private String filePath;
    private String outDirctory;
    private String fileNamePrefix;

    public PPTConvertHander(String filePath, String outDirctory, String fileNamePrefix) {
        this.filePath = filePath;
        this.outDirctory = outDirctory;
        this.fileNamePrefix = fileNamePrefix;
    }

    public  void pptToPng(){
        HSLFSlideShow hslfSlideShow = null;

        try {

            File dictory = new File(outDirctory);
            if(!dictory.exists()){
                dictory.mkdirs();
            }
            hslfSlideShow = new HSLFSlideShow(filePath);
            SlideShow ppt = new SlideShow(hslfSlideShow);
            Dimension pgsize = ppt.getPageSize();
            Slide[] slide = ppt.getSlides();
            for (int i = 0; i < slide.length; i++) {
                BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();
                //clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill((Shape) new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
//                       / /render
                slide[i].draw(graphics);


                FileOutputStream out  = new FileOutputStream(outDirctory + File.separator+fileNamePrefix+(i + 1) +"."+PNG_EXT_NAME);
                javax.imageio.ImageIO.write(img, PNG_EXT_NAME, out);
                out.flush();
                out.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOutDirctory() {
        return outDirctory;
    }

    public void setOutDirctory(String outDirctory) {
        this.outDirctory = outDirctory;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public void setFileNamePrefix(String fileNamePrefix) {
        this.fileNamePrefix = fileNamePrefix;
    }

    @Override
    public void run() {
        pptToPng();
    }
}
