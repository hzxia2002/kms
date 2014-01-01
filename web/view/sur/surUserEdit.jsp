<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="surUserQuestionaryEditForm" modelAttribute="bean" name="surUserQuestionaryEditForm" action="${ctx}/surUserQuestionary/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right" style="width: 20%">
                问卷名称:
            </td>
            <td  class="container">
                <input type="hidden" name="questionary" value="${bean.questionary.id}" />
                <input type="text" name="questionary_name"  class="table_input" value="${bean.questionary.title}" />
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                答卷人员:
            </td>

            <td  class="container">

                <input type="text" name="userName" id="userName" value="${bean.user.displayName}" class="table_input" validate="{required:true}" readonly="true"/>
                <input type="hidden" name="user" id="user" value="${bean.user.id}" class="table_input"/>
            </td>

        </tr>
        <tr class="inputTr">
            <td  align="right">
                起始时间:
            </td>
            <td  class="container">
                <input type="text"  name="startTime" id="startTime" class="jui-dateEditor"/>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                结束时间:
            </td>
            <td  class="container">
                <input type="text"  name="endTime" id="endTime" class="jui-dateEditor"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $("#startTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.startTime}"></fmt:formatDate>'});
        $("#endTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.endTime}"></fmt:formatDate>'});

        $.metadata.setType("attr", "validate");
        v = $('#surUserQuestionaryEditForm').validate();
    });


</script>