<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="surOptionsEditForm" modelAttribute="bean" name="surOptionsEditForm" action="${ctx}/surOptions/save.do" method="post">
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
                        选项描述:
                    </td>
                    <td  class="container">
                        <input type="text" name="content" value="${bean.content}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        编号:
                    </td>
                    <td  class="container">
                        <input type="text" name="indexNo" value="${bean.indexNo}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#surOptionsEditForm').validate();
    });
</script>