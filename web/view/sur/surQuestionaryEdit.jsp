<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="surQuestionaryEditForm" modelAttribute="bean" name="surQuestionaryEditForm" action="${ctx}/surQuestionary/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right" style="width: 20%">
                问卷名称:
            </td>
            <td  class="container">
                <input type="text" name="title" value="${bean.title}" class="table_input" validate="{required:true}"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                发起人:
            </td>
            <td  class="container">
                <input type="text" name="sponsor" value="${bean.sponsor}" class="table_input" />&nbsp;
            </td>
        </tr>
        <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
                <%--类型：0问卷,1试卷:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
                <%--<input type="text" name="type" value="${bean.type}" />&nbsp;--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr class="inputTr">
            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <textarea name="remark" cols="55" rows="5" class="textarea_table">${bean.remark}</textarea>
            </td>
        </tr>

    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#surQuestionaryEditForm').validate();
    });
</script>