<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsGroupEditForm" modelAttribute="bean" name="cmsGroupEditForm" action="${ctx}/cmsGroup/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        名称:
                    </td>
                    <td  class="container">
                        <input type="text" name="name" class="table_input" value="${bean.name}" validate="{required:true}"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        编码:
                    </td>
                    <td  class="container">
                        <input type="text" name="code" class="table_input"  value="${bean.code}" validate="{required:true}"/>&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#cmsGroupEditForm').validate();
    });
</script>