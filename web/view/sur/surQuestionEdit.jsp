<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="surQuestionEditForm" modelAttribute="bean" name="surQuestionEditForm" action="${ctx}/surQuestion/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        题干:
                    </td>
                    <td  class="container">
                        <input type="text" name="title" value="${bean.title}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        题型:
                    </td>
                    <td  class="container">
                        <input type="text" name="type" value="${bean.type}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        备注:
                    </td>
                    <td  class="container">
                        <input type="text" name="remark" value="${bean.remark}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        问卷ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionary" value="${bean.questionary}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#surQuestionEditForm').validate();
    });
</script>