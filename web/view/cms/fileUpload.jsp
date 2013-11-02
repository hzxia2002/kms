<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <script>
        function checkData(){
            if(!$("input[type='file']").val()){
                alert("请选择文件");
                return false;
            }
            return false;
        }

        function addRow(){
            var cloneTr = $("#template").clone();
            cloneTr.attr("id","");
            cloneTr.show();
            $("#fileTable").append(cloneTr);
        }

        function removeRow(btn){
            $(btn).parent().parent().remove();
        }
    </script>
</head>
<body>
<form:form id="fileUploadForm" modelAttribute="bean" name="fileUploadForm"  method="post" enctype="multipart/form-data" onsubmit="return checkData();" action="${ctx}/fileUpload/uploadFiles.do">
    <table border="0" cellspacing="1" width="100%" class="inputTable" id="fileTable">
        <tr class="inputTr" id="template" style="display:none">
            <td  align="right">
                添加附件:
            </td>
            <td  class="container">
                <input type="file" name="file"  class="table_input" style="width: 300px"/>
                <img src="${ctx}/skin/icons/edit_add.png" title="添加行" onclick="addRow();">&nbsp;
                <img src="${ctx}/skin/icons/edit_remove.png" title="删除行" onclick="removeRow(this);">&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                添加附件:
            </td>
            <td  class="container">
                <input type="file" name="file" class="table_input" style="width: 300px"/>
                <img src="${ctx}/skin/icons/edit_add.png" title="添加行" onclick="addRow();">&nbsp;
            </td>
        </tr>

    </table>
    <div align="center">
        <input type="hidden" name="id" value="${id}"/>&nbsp;
        <input type="submit" value="保存" class="btn_Ok"/>
    </div>
</form:form>
</body>

</html>
