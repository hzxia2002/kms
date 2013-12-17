<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="charpterEditForm" modelAttribute="bean" name="charpterEditForm" action="${ctx}/charpter/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        问卷ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionary" value="${bean.questionary}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        排序号:
                    </td>
                    <td  class="container">
                        <input type="text" name="indexNo" value="${bean.indexNo}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        描述:
                    </td>
                    <td  class="container">
                        <input type="text" name="description" value="${bean.description}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#charpterEditForm').validate();
    });
</script>