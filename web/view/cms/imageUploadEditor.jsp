<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <script>
        function checkData(){
            if(!$("#file").val()){
                alert("请选择文件");
                return false;
            }
        }
    </script>
</head>
<body>
<form:form id="fileUploadForm" modelAttribute="bean" name="fileUploadForm"  method="post" enctype="multipart/form-data" onsubmit="return checkData();" action="${ctx}/fileUpload/uploadFile.do?type=1">
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right">
                添加图片:
            </td>
            <td  class="container">
                <input type="hidden" name="id" value="${id}"/>&nbsp;
                <input type="file" name="file" id="file" class="table_input" style="width: 300px"/>&nbsp;
            </td>
        </tr>
         <tr>
             <td colspan="2" align="right">
                 <input type="submit" value="保存" class="btn_Ok"/>
             </td>
         </tr>
    </table>
</form:form>
</body>

</html>
