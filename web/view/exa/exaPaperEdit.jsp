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
            <td  align="right" >
                开始时间:
            </td>
            <td  class="container">
                <input type="text"  name="startTime" id="startTime" readonly="true" value="${bean.startTime}"  >
            </td>
        </tr>
        <tr class="inputTr" >
            <td  align="right">
                结束时间:
            </td>
            <td  class="container" >
                <input type="text"  name="endTime"  id="endTime"  readonly="true" value="${bean.endTime}" >
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                作答时间(分钟):
            </td>
            <td  class="container">
                <input type="text" name="paperMinute" value="${bean.paperMinute}"   class="table_input" validate="{digits:true,min:1,required:true}"/>&nbsp;
            </td>
        </tr>
        <tr>
            <td  align="right">
                试卷总分:
            </td>
            <td  class="container">
                <input type="text" name="totalScore" value="${bean.totalScore}"  class="table_input"  validate="{digits:true,min:1,max:150,required:true}"/>&nbsp;
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td  align="right">--%>
                <%--试卷排序类型:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
                <%--<input type="text" name="questionOrderType" value="${bean.questionOrderType}" />&nbsp;--%>
            <%--</td>--%>

        <%--</tr>--%>
        <tr class="inputTr">
            <td  align="right">
                发布时间:
            </td>
            <td  class="container">
                <input type="text"  id="postTime" name="postTime" value="${bean.postTime}">
            </td>

        </tr>
        <tr class="inputTr">
            <td  align="right">
                显示分数时间:
            </td>
            <td  class="container">
                <input type="text"   id="showScoreTime" name="showScoreTime" value="${bean.showScoreTime}">
            </td>

        </tr>
        <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
                <%--是否随机:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
                <%--<input type="text" name="isRandPaper" value="${bean.isRandPaper}" />&nbsp;--%>
            <%--</td>--%>

        <%--</tr>--%>
        <tr class="inputTr">

            <td  align="right">
                试卷类型:
            </td>
            <td  class="container">
                <select name="paperType" id="paperType">
                    <option value="0" <c:if test="${bean.paperType=='0'}">selected</c:if>>模拟</option>
                    <option value="1" <c:if test="${bean.paperType=='1'}">selected</c:if>>练习</option>
                    <option value="2" <c:if test="${bean.paperType=='2'}">selected</c:if>>考试</option>
                </select>
            </td>
        </tr>
        <tr class="inputTr">

            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <textarea class="textarea_table" name="remark" id="remark"  cols="55" rows="8" >${bean.remark}</textarea>
            </td>

        </tr>


    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $("#startTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd",width:200 ,initValue:'${bean.startTime}'});
        $("#endTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd",width:200 ,initValue:'${bean.endTime}'});
        $("#showScoreTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd 00:00:00",width:200 ,initValue:'${bean.showScoreTime}'});
        $("#postTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd 00:00:00",width:200 ,initValue:'${bean.postTime}'});
        $.metadata.setType("attr", "validate");
        v = $('#exaPaperEditForm').validate();

    });
</script>