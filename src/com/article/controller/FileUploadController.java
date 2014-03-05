package com.article.controller;

import com.article.daoservice.CmsCatalogService;
import com.article.daoservice.DocAttachmentsService;
import com.article.daoservice.DocDocumentService;
import com.article.domain.CmsCatalog;
import com.article.domain.DocAttachments;
import com.article.domain.DocDocument;
import com.article.manager.CatalogManager;
import com.article.manager.UploadConfig;
import com.article.util.PPTConvertHander;
import com.article.util.PPTOperator;
import com.article.util.PPTToJPGHander;
import com.comet.core.config.CustomizedPropertyPlaceholderConfigurer;
import com.comet.core.controller.BaseCRUDActionController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
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

    @Autowired
    private DocDocumentService docDocumentService;

    @Autowired
    private DocAttachmentsService docAttachmentsService;

    @Autowired
    private UploadConfig uploadConfig;

    @Autowired
    private HashMap systemMap;


    @RequestMapping
    public void uploadImage(HttpServletResponse response,HttpServletRequest request,Long id) throws Exception {
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);
        String catalogPath = catalogManager.getCatalogPath(cmsCatalog);
        String wholePath = getUploadPath(request, catalogPath);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);

        String oldStr = getFileDirByDate();
        String dateStr = oldStr.substring(1, (oldStr.length()-1));
        String dateDir = oldStr.substring(1);
        DateFormat format1 = new SimpleDateFormat("yyyy" + File.separator + "M" + File.separator
                + "dd");
        Date date = format1.parse(dateStr);

        Iterator fileNames = multipartRequest.getFileNames();
        HashMap map = new HashMap();
        while (fileNames.hasNext()) {
            String name = (String) fileNames.next();

            CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(name);
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName;
            if(StringUtils.isNotEmpty(fileName)){
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

        }

        response.getWriter().print(JSONObject.fromObject(map).toString());

    }

    public String  getUploadPath(HttpServletRequest request, String catalogPath) {
        return (StringUtils.isNotEmpty(uploadConfig.getPath())?uploadConfig.getPath():
                (request.getSession().getServletContext().getRealPath("")))+catalogPath;
    }


    @RequestMapping
    public String fileUploadInit(Model model, String id,String type,String docId) throws Exception {
        try {

            model.addAttribute("id", id);
            model.addAttribute("docId", StringUtils.isNotEmpty(docId)?docId:"");
        } catch (Exception e) {
            log.error("error", e);
        }
        if(type.equals("1")){
            return "view/cms/imageUpload";
        }else if(type.equals("3")) {
            return "view/cms/imageUploadEditor";
        }else {
            return "view/cms/fileUpload";
        }
    }

    @RequestMapping
    public String uploadFile(HttpServletResponse response,HttpServletRequest request,Long id,Model model,String type) throws Exception {
        ArrayList arrayList = new ArrayList();
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);
        String catalogPath = catalogManager.getCatalogPath(cmsCatalog);
        String wholePath = getUploadPath(request, catalogPath);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);

        String oldStr = getFileDirByDate();
        String dateStr = oldStr.substring(1, (oldStr.length()-1));
        String dateDir = oldStr.substring(1);
        DateFormat format1 = new SimpleDateFormat("yyyy" + File.separator + "M" + File.separator+ "dd");
        Date date = format1.parse(dateStr);

        Iterator fileNames = multipartRequest.getFileNames();
        while (fileNames.hasNext()) {
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
        model.addAttribute("list", arrayList);
        if(StringUtils.isNotEmpty(type)){
            return "view/cms/uploadResultEditor";
        }
        return "view/cms/uploadResult";
    }

    @RequestMapping
    public String uploadFiles(HttpServletResponse response,HttpServletRequest request,Long id,Long docId,Model model) throws Exception {
        ArrayList arrayList = new ArrayList();
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);
        String catalogPath = catalogManager.getCatalogPath(cmsCatalog);
        String wholePath = getUploadPath(request, catalogPath);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)(request);

        String oldStr = getFileDirByDate();
        String dateStr = oldStr.substring(1, (oldStr.length()-1));
        String dateDir = oldStr.substring(1);
        DateFormat format1 = new SimpleDateFormat("yyyy" + File.separator + "M" + File.separator+ "dd");
        Date date = format1.parse(dateStr);

        //附件 Document
        DocDocument docDocument ;
        if(docId!=null){
            docDocument = docDocumentService.get(docId);
        }else{
            docDocument = new DocDocument();
        }
        docDocument.setPath(wholePath);
        docDocumentService.save(docDocument);
        Iterator fileNames = multipartRequest.getFileNames();

        while (fileNames.hasNext()) {
            DocAttachments docAttachments = new DocAttachments();
            docAttachments.setDoc(docDocument);

            HashMap map = new HashMap();
            String name = (String) fileNames.next();

            CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(name);
            String originalFileName = multipartFile.getOriginalFilename();
            String fileName = originalFileName;
            if(StringUtils.isNotEmpty(fileName)){
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

                String pathName = wholePath + File.separator + dateDir + chgName;
                multipartFile.transferTo(new File(pathName));
                if("ppt".equalsIgnoreCase(extName)){
                    String pptDir = wholePath + File.separator + dateDir + dateLong;
                    File file = new File(pptDir);
                    file.mkdir();

                    PPTToJPGHander pptConvertHander= new PPTToJPGHander(pathName,pptDir);
                    pptConvertHander.start();
                }

                String path = catalogPath.replace(File.separator, "/") + "/" + dateDir.replace(File.separator, "/") + chgName;

                docAttachments.setOrginName(originalFileName);
                docAttachments.setFilePath(path);
                docAttachments.setName(chgName);
                String uploadDate = new Timestamp(System.currentTimeMillis()).toString().substring(0,19);
                docAttachments.setUploadDate(uploadDate);
                docAttachmentsService.save(docAttachments);
                map.put("url", path);
                map.put("original", originalFileName);
                map.put("attachmentId",docAttachments.getId());

                map.put("title", request.getParameter("pictitle"));
                map.put("state","SUCCESS");
                map.put("fileType","."+extName);
                arrayList.add(map);


            }

        }
//        new
        model.addAttribute("msg","上传成功");
        model.addAttribute("docId",docDocument.getId());
        model.addAttribute("ret", JSONArray.fromObject(arrayList).toString());
//        model.addAttribute("docId", );
        return "view/cms/uploadResult";
    }


    @RequestMapping
    public void deleteAttachment(HttpServletResponse response,HttpServletRequest request,Long id,Long catagoryId,Model model){
        DocAttachments docAttachments = docAttachmentsService.get(id);
        String filePath = docAttachments.getFilePath();
        if(filePath.contains("\\\\")){
            filePath = filePath.replaceAll("\\\\", File.separator);
        }
        File file = new File(request.getSession().getServletContext().getRealPath("")+File.separator+filePath);
        if(file.exists()){
            file.delete();
        }
        docAttachmentsService.delete(docAttachments);
        sendSuccessJSON(response, "删除成功");
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

    /**
     * 获取图片
     * @param response
     * @param request
     * @param id
     * @throws Exception
     */
    @RequestMapping
    public void getPictures(HttpServletResponse response,HttpServletRequest request,Long id) throws Exception {
        CmsCatalog cmsCatalog = cmsCatalogService.get(id);
        String catalogPath = catalogManager.getCatalogPath(cmsCatalog);
        String wholePath = getUploadPath(request, catalogPath);
        ArrayList list = new ArrayList();
        getFileList(wholePath,list,request.getSession().getServletContext().getRealPath(""));
        StringBuffer sb = new StringBuffer();
        for (Object o : list) {
            sb.append(o).append("ue_separate_ue");
        }
        response.getWriter().print(sb.toString());
    }


    public void getFileList(String path,List<String> list,String contextPath){
        File fileDir = new File(path);
        if(fileDir.exists()){
            File[] files = fileDir.listFiles();
            for (File file : files) {
                if(file.isDirectory()){
                    getFileList(file.getPath(),list,contextPath);
                }else{
                    String extName = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                    if("|gif|jpeg|jpg|png|bmp|".contains(extName.toLowerCase())&&!file.getName().contains(systemMap.get("pptPrefix").toString())){
                        list.add(file.getPath().substring(contextPath.length()+1).replaceAll("\\\\", "/"));
                    }
                }
            }
        }

    }

    @RequestMapping
    @ResponseBody
    public Map relativeTv(HttpServletRequest request,String filePath,String[] fileNames,Long docId){
        HashMap retMap = new HashMap();
        //附件 Document
        DocDocument docDocument ;
        if(docId!=null){
            docDocument = docDocumentService.get(docId);
        }else{
            docDocument = new DocDocument();
        }
        docDocumentService.save(docDocument);
        ArrayList arrayList = new ArrayList();

        for (String fileName : fileNames) {

            DocAttachments docAttachments = new DocAttachments();
            docAttachments.setDoc(docDocument);

            String path = filePath + File.separator + fileName;
            docAttachments.setOrginName(fileName);
            docAttachments.setFilePath(path);
            docAttachments.setRemark("tv");
            docAttachments.setName(fileName);
            String uploadDate = new Timestamp(System.currentTimeMillis()).toString().substring(0,19);
            docAttachments.setUploadDate(uploadDate);
            docAttachmentsService.save(docAttachments);

            String extName = "";
            int index = fileName.lastIndexOf(".");
            if(index>0){
                extName = fileName.substring(index+1, fileName.length());
            }

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("url", "/fileUpload/downloadTv.do?id="+docAttachments.getId());
            map.put("original", fileName);
            map.put("attachmentId",docAttachments.getId());

            map.put("state","SUCCESS");
            map.put("fileType","."+extName);
            arrayList.add(map);

        }
        retMap.put("docId",docDocument.getId());
        retMap.put("list",arrayList);
        return retMap;
    }

    @RequestMapping
    public void downloadTv(HttpServletResponse response,Long id) throws Exception {
        FileInputStream in = null;
        OutputStream outp = null;
        try {
            DocAttachments docAttachments = docAttachmentsService.get(id);
            in = new FileInputStream(CustomizedPropertyPlaceholderConfigurer.getContextProperty("aviFilePath") + File.separator + docAttachments.getFilePath());
            response.reset();
            outp = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" +new String(docAttachments.getOrginName().getBytes("GB2312"),"ISO-8859-1"));
            byte[] b = new byte[1024];
            int i;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }

        } catch (IOException e) {
            throw new Exception("下载文件在服务器中不存在");
        } finally {
            if (outp!=null){
                outp.close();
            }
        }
    }

}
