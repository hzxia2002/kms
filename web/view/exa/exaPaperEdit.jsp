<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaPaperEditForm" modelAttribute="bean" name="exaPaperEditForm" action="${ctx}/exaPaper/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right">
                试卷名称:
            </td>
            <td  class="container">
                <input type="text" name="paperName" class="table_input" value="${bean.paperName}" />&nbsp;
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                开始时间:
            </td>
            <td  class="container">
                <input type="text" name="startTime" id="startTime" value="${bean.startTime}" class="jui-dateEditor"/>&nbsp;
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                结束时间:
            </td>
            <td  class="container">
                <input type="text"  id="endTime"  name="endTime" value="${bean.endTime}" class="jui-dateEditor"/>&nbsp;
            </td>

        </tr>
        <tr class="inputTr">
            <td  align="right">
                作答时间:
            </td>
            <td  class="container">
                <input type="text" name="paperMinute" value="${bean.paperMinute}" />&nbsp;
            </td>


            <td  align="right">
                试卷总分:
            </td>
            <td  class="container">
                <input type="text" name="totalScore" value="${bean.totalScore}" />&nbsp;
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                时间排序类型:
            </td>
            <td  class="container">
                <input type="text" name="questionOrderType" value="${bean.questionOrderType}" />&nbsp;
            </td>


            <td  align="right">
                发布时间:
            </td>
            <td  class="container">
                <input type="text"  name="postTime" class="jui-dateEditor" showTime="true">
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                显示分数时间:
            </td>
            <td  class="container">
                <input type="text"  name="showScoreTime" class="jui-dateEditor" showTime="true">
            </td>


            <td  align="right">
                是否随机时间:
            </td>
            <td  class="container">
                <input type="text" name="isRandPaper" value="${bean.isRandPaper}" />&nbsp;
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                试卷状态(1开放，-1不开放):
            </td>
            <td  class="container">
                <input type="text" name="status" value="${bean.status}" />&nbsp;
            </td>


            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <input type="text" name="remark" value="${bean.remark}" />&nbsp;
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                试卷状态(1开放，-1不开放):
            </td>
            <td  class="container">
                <input type="text" name="status" value="${bean.status}" />&nbsp;
            </td>


            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <input type="text" name="remark" value="${bean.remark}" />&nbsp;
            </td>

        </tr>

    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $("#startTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:00",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.startTime}"></fmt:formatDate>'});
        $("#endTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:00",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.endTime}"></fmt:formatDate>'});
        $.metadata.setType("attr", "validate");
        v = $('#exaPaperEditForm').validate();

    });
</script>