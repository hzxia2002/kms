<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsCollectCatagoryEditForm" modelAttribute="bean" name="cmsCollectCatagoryEditForm" action="${ctx}/cmsCollectCatagory/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <c:if test="${bean.parent.id!=null}">
            <tr class="inputTr">
                <td  align="right">
                    上层节点:
                </td>
                <td  class="container">
                    <input type="hidden" name="parent" value="${bean.parent.id}" />
                    <input type="text" name="parent_name" value="${bean.parent.name}" readonly="true" class="table_input" disabled="true"/>&nbsp;
                </td>
            </tr>
        </c:if>
        <tr class="inputTr">
            <td  align="right">
                名称:
            </td>
            <td  class="container">
                <input type="text" name="name" value="${bean.name}" class="table_input" validate="{required:true}"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                代码:
            </td>
            <td  class="container">
                <input type="text" name="code" value="${bean.code}"  class="table_input" validate="{required:true}"/>&nbsp;
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#cmsCollectCatagoryEditForm').validate();
    });
</script>