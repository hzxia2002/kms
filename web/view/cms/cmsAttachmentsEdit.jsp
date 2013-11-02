<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsAttachmentsEditForm" modelAttribute="bean" name="cmsAttachmentsEditForm" action="${ctx}/cmsAttachments/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        知识点ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="knowledge" value="${bean.knowledge}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        文件原名:
                    </td>
                    <td  class="container">
                        <input type="text" name="orgName" value="${bean.orgName}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        修改后的名称:
                    </td>
                    <td  class="container">
                        <input type="text" name="targetName" value="${bean.targetName}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        扩展名:
                    </td>
                    <td  class="container">
                        <input type="text" name="extName" value="${bean.extName}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        附件目录:
                    </td>
                    <td  class="container">
                        <input type="text" name="path" value="${bean.path}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        上传时间:
                    </td>
                    <td  class="container">
                        <input type="text" name="uploadDate" value="${bean.uploadDate}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        上传人员:
                    </td>
                    <td  class="container">
                        <input type="text" name="uploader" value="${bean.uploader}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#cmsAttachmentsEditForm').validate();
    });
</script>