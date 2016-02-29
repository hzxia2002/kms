<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaPaperSectionEditForm" modelAttribute="bean" name="exaPaperSectionEditForm" action="${ctx}/exaPaperSection/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <input type="hidden" name="paperId" value="${paper.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right">
                试卷:
            </td>
            <td  class="container">
                <input type="text" name="paperName" class="table_input"  readonly value="${paper.paperName}" />&nbsp;
            </td>
        </tr>

        <tr class="inputTr">

            <td  align="right">
                章节名称:
            </td>
            <td  class="container">
                <input type="text" name="sectionName" class="table_input"  value="${bean.sectionName}" style="width: 300px;" validate="{required:true}"/>&nbsp;
            </td>

        </tr>
        <tr class="inputTr">

            <td  align="right">
                排序:
            </td>
            <td  class="container">
                <input type="text" name="orderNo" class="table_input"  value="${bean.orderNo}"  validate="{digits:true,min:1,max:999}"/>&nbsp;
            </td>

        </tr>

        <tr class="inputTr">

            <td  align="right">
                每题分数:
            </td>
            <td  class="container">
                <input type="text" name="perScore" value="${bean.perScore}" class="table_input"  validate="{number:true}"/>&nbsp;
            </td>
        </tr>

        <tr class="inputTr">

            <td  align="right">
                题目数量:
            </td>
            <td  class="container">
                <input type="text" name="questionNums" class="table_input"  value="${bean.questionNums}"  validate="{digits:true,min:1}"/>&nbsp;
            </td>

        </tr>
            <%--<tr class="inputTr">--%>

            <%--<td  align="right">--%>
            <%--试题难易程度:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="questionLevel" value="${bean.questionLevel}" />&nbsp;--%>
            <%--</td>--%>


            <%--<td  align="right">--%>
            <%--试题题型:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="questionType" value="${bean.questionType}" />&nbsp;--%>
            <%--</td>--%>

            <%--</tr>--%>
        <tr class="inputTr">

            <td  align="right">
                描述:
            </td>
            <td  class="container">
                <textarea class="textarea_table" name="remark" id="remark"  cols="55" rows="8" >${bean.remark}</textarea>
            </td>
        </tr>

            <%--<tr class="inputTr">--%>

            <%--<td  align="right">--%>
            <%--创建时间:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text"  name="createTime" class="jui-dateEditor" showTime="true">--%>
            <%--</td>--%>

            <%--</tr>--%>
            <%--<tr class="inputTr">--%>

            <%--<td  align="right">--%>
            <%--更新时间:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text"  name="updateTime" class="jui-dateEditor" showTime="true">--%>
            <%--</td>--%>


            <%--<td  align="right">--%>
            <%--更新人:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="updateUser" value="${bean.updateUser}" />&nbsp;--%>
            <%--</td>--%>

            <%--</tr>--%>
            <%--<tr class="inputTr">--%>

            <%--<td  align="right">--%>
            <%--创建人:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="createUser" value="${bean.createUser}" />&nbsp;--%>
            <%--</td>--%>
            <%--<td width="25%">--%>
            <%--&nbsp;--%>
            <%--</td>--%>
            <%--<td width="25%">--%>
            <%--&nbsp;--%>
            <%--</td>--%>

            <%--</tr>--%>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#exaPaperSectionEditForm').validate();
    });
</script>