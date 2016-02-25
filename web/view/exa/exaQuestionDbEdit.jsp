<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaQuestionDbEditForm" modelAttribute="bean" name="exaQuestionDbEditForm" action="${ctx}/exaQuestionDb/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right">
                题库名称:
            </td>
            <td  class="container">
                <input type="text" class="table_input" name="dbName" value="${bean.dbName}" style="width: 250px;"/>&nbsp;
            </td>

        </tr>
        <tr class="inputTr">
            <td  align="right">
                状态:
            </td>
            <td  class="container">
                <select id="status"  name="status"  class="table_input" style="width: 100px;">
                    <option value="1" <c:if test="${bean.status==1}" >selected</c:if>>开放</option>
                    <option value="0" <c:if test="${bean.status==0}" >selected</c:if>>锁定</option>
                </select>
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <textarea  class="ke-edit-textarea" name="remark" rows="6" cols="60" >${bean.remark}</textarea>
            </td>

        </tr>


    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#exaQuestionDbEditForm').validate();
    });
</script>