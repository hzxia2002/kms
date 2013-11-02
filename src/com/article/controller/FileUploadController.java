package com.article.controller;

import com.article.daoservice.CmsCatalogService;
import com.article.domain.CmsArticle;
import com.article.domain.CmsCatalog;
import com.article.domain.DocDocument;
import com.article.manager.CatalogManager;
import com.article.util.Uploader;
import com.comet.core.controller.BaseCRUDActionController;
import com.comet.core.security.util.SpringSecurityUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date: 13-8-27
 * Time: 下午3:11
 */
@Controller
public class FileUploadController extends BaseCRUDActionController {
    @Autowired
    private CmsCatalogService cmsCatalogService;

    @Autowired
    private CatalogManager catalogManager;

    @RequestMapping
    public void uploadImage(HttpServletResponse response,HttpServletRequest request,Long id) throws Exception {
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);
        String catalogPath = catalogManager.getCatalogPath(cmsCatalog);
        String wholePath = request.getSession().getServletContext().getRealPath("")+File.separator+catalogPath;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);
        Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();

        String oldStr = getFileDirByDate();
        String dateStr = oldStr.substring(1, (oldStr.length()-1));
        String dateDir = oldStr.substring(1);
        DateFormat format1 = new SimpleDateFormat("yyyy" + File.separator + "M" + File.separator
                + "dd");
        Date date = format1.parse(dateStr);

        Iterator fileNames = multipartRequest.getFileNames();
        HashMap map = new HashMap();
        if (fileNames.hasNext()) {
            String name = (String) fileNames.next();

            CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(name);
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName;
            String extName = "";
            int index = originalFileName.lastIndexOf(".");
            if(index>0){
                fileName = originalFileName.substring(0, index);
                extName = originalFileName.substring(index+1, originalFileName.length());
            }
            Long dateLong = System.currentTimeMillis()-date.getTime();
            String chgName =    dateLong + "." + extName;
            File dest = new File(wholePath +File.separator +dateDir );
            if(!dest.exists()){
                dest.mkdirs();
            }

            multipartFile.transferTo(new File(wholePath +File.separator +dateDir+chgName));
            map.put("original", originalFileName);
            map.put("url", request.getHeader("Host")+request.getContextPath() +"/" +catalogPath.replace(File.separator,"/")+"/"+ dateDir.replace(File.separator,"/") + chgName);
            map.put("title", request.getParameter("pictitle"));
            map.put("state","SUCCESS");
            map.put("fileType","."+extName);
        }

        response.getWriter().print(JSONObject.fromObject(map).toString());

    }


    @RequestMapping
    public String fileUploadInit(Model model, String id,String type) throws Exception {
        try {

            model.addAttribute("id", id);
        } catch (Exception e) {
            log.error("error", e);
        }
        if(type.equals("1")){
            return "view/cms/imageUpload";
        }else {
            return "view/cms/fileUpload";
        }
    }

    @RequestMapping
    public String uploadFile(HttpServletResponse response,HttpServletRequest request,Long id,Model model) throws Exception {
        ArrayList arrayList = new ArrayList();
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);
        String catalogPath = catalogManager.getCatalogPath(cmsCatalog);
        String wholePath = request.getSession().getServletContext().getRealPath("")+File.separator+catalogPath;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);
        Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();

        String oldStr = getFileDirByDate();
        String dateStr = oldStr.substring(1, (oldStr.length()-1));
        String dateDir = oldStr.substring(1);
        DateFormat format1 = new SimpleDateFormat("yyyy" + File.separator + "M" + File.separator+ "dd");
        Date date = format1.parse(dateStr);

        Iterator fileNames = multipartRequest.getFileNames();
        if (fileNames.hasNext()) {
            HashMap map = new HashMap();
            String name = (String) fileNames.next();

            CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(name);
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName;
            String extName = "";
            int index = originalFileName.lastIndexOf(".");
            if(index>0){
                fileName = originalFileName.substring(0, index);
                extName = originalFileName.substring(index+1, originalFileName.length());
            }
            Long dateLong = System.currentTimeMillis()-date.getTime();
            String chgName =    dateLong + "." + extName;
            File dest = new File(wholePath +File.separator +dateDir );
            if(!dest.exists()){
                dest.mkdirs();
            }

            multipartFile.transferTo(new File(wholePath +File.separator +dateDir+chgName));
            map.put("original", originalFileName);
            map.put("url", catalogPath.replace(File.separator,"/")+"/"+ dateDir.replace(File.separator,"/") + chgName);
            map.put("title", request.getParameter("pictitle"));
            map.put("state","SUCCESS");
            map.put("fileType","."+extName);
            arrayList.add(map);
        }

        model.addAttribute("msg","上传成功");
        model.addAttribute("ret", JSONArray.fromObject(arrayList).toString());
        return "view/cms/uploadResult";
    }

    @RequestMapping
    public String uploadFiles(HttpServletResponse response,HttpServletRequest request,Long id,Model model) throws Exception {
        ArrayList arrayList = new ArrayList();
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);
        String catalogPath = catalogManager.getCatalogPath(cmsCatalog);
        String wholePath = request.getSession().getServletContext().getRealPath("")+File.separator+catalogPath;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);
        Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();

        String oldStr = getFileDirByDate();
        String dateStr = oldStr.substring(1, (oldStr.length()-1));
        String dateDir = oldStr.substring(1);
        DateFormat format1 = new SimpleDateFormat("yyyy" + File.separator + "M" + File.separator+ "dd");
        Date date = format1.parse(dateStr);

        //附件 Document
        DocDocument docDocument = new DocDocument();
        docDocument.setPath(wholePath);

        Iterator fileNames = multipartRequest.getFileNames();

        if (fileNames.hasNext()) {
            HashMap map = new HashMap();
            String name = (String) fileNames.next();

            CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(name);
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName;
            String extName = "";
            int index = originalFileName.lastIndexOf(".");
            if(index>0){
                fileName = originalFileName.substring(0, index);
                extName = originalFileName.substring(index+1, originalFileName.length());
            }
            Long dateLong = System.currentTimeMillis()-date.getTime();
            String chgName =    dateLong + "." + extName;
            File dest = new File(wholePath +File.separator +dateDir );
            if(!dest.exists()){
                dest.mkdirs();
            }

            multipartFile.transferTo(new File(wholePath +File.separator +dateDir+chgName));
            map.put("original", originalFileName);
            map.put("url", catalogPath.replace(File.separator,"/")+"/"+ dateDir.replace(File.separator,"/") + chgName);
            map.put("title", request.getParameter("pictitle"));
            map.put("state","SUCCESS");
            map.put("fileType","."+extName);
            arrayList.add(map);
        }
//        new
        model.addAttribute("msg","上传成功");
        model.addAttribute("ret", JSONArray.fromObject(arrayList).toString());
//        model.addAttribute("docId", );
        return "view/cms/uploadResult";
    }


    /**
     * 拼接日期为目录
     * @return
     */
    public static String getFileDirByDate(){
        StringBuffer sb = new StringBuffer();
        String rightSlash = File.separator;
        Calendar calendar = Calendar.getInstance();
        sb.append(rightSlash).append(calendar.get(Calendar.YEAR)).
                append(rightSlash).append(calendar.get(Calendar.MONTH)+1).
                append(rightSlash).append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(rightSlash);
        return sb.toString();
    }

}
