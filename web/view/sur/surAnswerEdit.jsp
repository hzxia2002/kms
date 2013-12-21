<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="surAnswerEditForm" modelAttribute="bean" name="surAnswerEditForm" action="${ctx}/surAnswer/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        问题ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="question" value="${bean.question}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        选项ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="option" value="${bean.option}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        用户ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="user" value="${bean.user}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        分数:
                    </td>
                    <td  class="container">
                        <input type="text" name="score" value="${bean.score}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        用户调研卷ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="userQuestionary" value="${bean.userQuestionary}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#surAnswerEditForm').validate();
    });
</script>