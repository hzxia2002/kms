<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.*,
                java.io.*"
        %>
<%@ page import="com.comet.core.utils.FileUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>文件浏览</title>
    <link href="${ctx}/js/jui/skins/Aqua/css/jui-all.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/skin/default.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/jquery/jquery-1.7.2.js" type="text/javascript"></script>
</head>
<body>
<form id="fileForm">
    <table width="100%" id="viewTable">
        <tr bgcolor="#E5EFFE" align="center">
            <td width="10%" height="22"></td>
            <td width="60%"><b>文件名</b></td>
            <td><b>大小</b></td>
            <td><b>修改日期</b></td>
        </tr>
        <%
            String rootPath = "E:\\TDDOWNLOAD";
            String directory = StringUtils.defaultIfEmpty(request.getParameter("directory"), "");

            String docId = StringUtils.defaultIfEmpty(request.getParameter("docId"), "");
            String lastDirectory = "";
            if(directory.lastIndexOf(File.separator) != -1) {
                lastDirectory = directory.substring(0, directory.lastIndexOf(File.separator));
            }

            String filePath = rootPath + File.separator + directory;
            File file = new File(filePath);


        %>
        <tr>
            <td align="center">
                <input type="hidden" name="filePath" value="<%=directory%>">
                <input type="hidden" name="docId" value="<%=docId%>">
            </td>
            <td>
                <a href="browser.jsp?directory=.">根目录</a>&nbsp;
                <%
                    if(!lastDirectory.equals("")){
                %>
                <a href="browser.jsp?directory=<%=lastDirectory%>">上一级</a>
                <%
                    }
                %>
            </td>
        </tr>
        <%
            if(file.isDirectory()) {
                File[] files = file.listFiles();
                int i = 0;

                for(File tmp:files) {
                    i++;
        %>
        <tr <%=(i%2 == 0) ? "" : "bgcolor='#F5F5F5'"%> align="center">
            <td>
                <%
                    if(!tmp.isDirectory()) {
                %>
                <input  name="fileNames" type="checkbox" value="<%=tmp.getName()%>" />
                <%}%></td>
            <td align="left">
                <%
                    if(tmp.isDirectory()) {
                %>
                <a href="browser.jsp?directory=<%=directory + File.separator + tmp.getName()%>&docId=<%=docId%>"><%=tmp.getName()%></a>
                <%
                } else {
                %>
                <%=tmp.getName()%>
                <%
                    }
                %>
            </td>
            <td align="right"><%=FileUtils.formatFileSize(tmp)%></td>
            <td><fmt:formatDate value="<%=new Date(tmp.lastModified())%>" type="both" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <div style="text-align: center;width: 100%">
        <input type="button" value="保存" class="btn_Ok" onclick="saveData()"/>
    </div>
</form>
</body>
</html>
<script>
    function saveData(){
        var isSelected = false;
        var selectOptions = $("input[name='fileNames']");
        for(var j=0;j<selectOptions.length;j++){
            if($(selectOptions[j]).attr("checked")){
                isSelected = true;
                break;
            }
        }
        if(!isSelected){
            alert("请选择视频文件");
            return;
        }

        $.ajax({
            url:"${ctx}/fileUpload/relativeTv.do"  ,
            type: 'POST',
            dataType: 'json',
            data: $("#fileForm").serializeArray(),
            success:function(ret){
                if(parent.setFilePath){
                    parent.setFilePath(ret.list,ret.docId);
                }

                if(parent.closeDialog){
                    parent.closeDialog();
                }
            }
        });

    }
</script>