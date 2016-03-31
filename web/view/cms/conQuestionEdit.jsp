<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="conQuestionEditForm" modelAttribute="bean" name="conQuestionEditForm" action="${ctx}/conQuestion/replySave.do" method="post">
    <input type="hidden" name="refId" value="${bean.id}" />
    <input type="hidden" name="id" value="${reply.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right" width="10%">
                主题:
            </td>
            <td  class="container" colspan="2">
                    ${bean.title}
            </td>
        </tr>
        <tr class="inputTr" style="min-height: 100px;">
            <td  align="right" width="10%">
                内容:
            </td>
            <td  class="container" colspan="2" style="word-break: break-all; line-height: 24px;">
                    ${bean.content}
            </td>

        </tr>

        <tr class="inputTr">
            <td  align="right" width="10%">
                回复:
            </td>
            <td  class="container" colspan="2">
                <textarea id="content" name="content" rows="8" cols="70" class="textarea_table">${reply.content}</textarea>
            </td>
        </tr>



        <%--<c:if test="${not empty answers}">--%>
            <%--<tr class="inputTr">--%>
                <%--<td colspan="3">--%>
                    <%--已回复内容:--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:if>--%>
        <%--<c:foreach ites="${answers}" var="answer">--%>
            <%--<tr class="inputTr">--%>
                <%--<td  align="right">--%>
                    <%--回复时间:${answer.responsTime}&nbsp;&nbsp;--%>
                <%--</td>--%>
                <%--<td  class="container">--%>
                    <%--<textarea id="content" name="content" rows="6" cols="60" class="textarea_table"></textarea>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:foreach>--%>

    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#conQuestionEditForm').validate();
    });
</script>