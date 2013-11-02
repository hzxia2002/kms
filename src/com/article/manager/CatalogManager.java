package com.article.manager;

import com.article.daoservice.CmsCatalogService;
import com.article.domain.CmsCatalog;
import com.article.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Date: 13-8-18
 * Time: 下午8:19
 */
@Service
public class CatalogManager {

    @Autowired
    private CmsCatalogService cmsCatalogService;

    public void  saveCmsCatalog(CmsCatalog cmsCatalog,String path){
        cmsCatalog.setType(Constants.FILE_VISUAL);
        cmsCatalogService.save(cmsCatalog);

//        //创建附件文件夹
//        CmsCatalog cmsCatalogAttach = new CmsCatalog();
//        cmsCatalogAttach.setName(cmsCatalog.getName());
//        cmsCatalogAttach.setPath(cmsCatalog.getPath());
//        cmsCatalogAttach.setCode(cmsCatalog.getId().toString());
//        cmsCatalogAttach.setIsValid(true);
//        cmsCatalogAttach.setType(Constants.FILE_ATTACH);
//        cmsCatalogService.save(cmsCatalogAttach);
//        File attachFile = new File(getWholePath(cmsCatalog, path + File.separator + Constants.ATTACH_NAME + File.separator + Constants.FILE_NAME));
//        if(!attachFile.exists()){
//            attachFile.mkdirs();
//        }
//        //创建图片保存文件夹
//        CmsCatalog cmsCatalogPic = new CmsCatalog();
//        cmsCatalogPic.setPath(cmsCatalog.getPath());
//        cmsCatalogPic.setName(cmsCatalog.getName());
//        cmsCatalogPic.setCode(cmsCatalog.getId().toString());
//        cmsCatalogPic.setIsValid(true);
//        cmsCatalogPic.setType(Constants.FILE_PIC);
//        cmsCatalogService.save(cmsCatalogPic);
//        File picFile = new File(getWholePath(cmsCatalog, path + File.separator + Constants.ATTACH_NAME +  File.separator+ Constants.PIC_NAME));
//        if(!picFile.exists()){
//            picFile.mkdirs();
//        }

    }

    /**
     * 获取文件路径
     * @param cmsCatalog
     * @param path
     * @return
     */
    public String getWholePath(CmsCatalog cmsCatalog,String path){
        path += File.separator + cmsCatalog.getPath();
        CmsCatalog parent = cmsCatalog.getParent();
        while (parent!=null){
            path += File.separator + parent.getPath();
        }
        return path;
    }
    /**
     * 获取文件路径
     * @param cmsCatalog
     * @return
     */
    public String getCatalogPath(CmsCatalog cmsCatalog){
        String path = "";
        path += File.separator + cmsCatalog.getPath();
        CmsCatalog parent = cmsCatalog.getParent();
        while (parent!=null){
            path += File.separator + parent.getPath();
        }
        return path;
    }
}
