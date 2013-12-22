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
            <td  align="right" style="width: 20%">
                问卷名称:
            </td>
            <td  class="container">
                <input type="text" name="questionaryName" class="table_input" value="${bean.questionary.title}" />&nbsp;
                <input type="hidden" name="questionary"  value="${bean.questionary.id}" />&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                题干:
            </td>
            <td  class="container">
                <textarea name="title" id="title" cols="55" rows="4" class="textarea_table" validate="{required:true}">${bean.title}</textarea>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                题型:
            </td>
            <td  class="container" >
                <select  name="type"  class="table_input">
                    <option value="0" <c:if test="${bean.type==0}">selected="selected" </c:if>>单选</option>
                    <option value="1" <c:if test="${bean.type==1}">selected="selected" </c:if>>多选</option>
                </select>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <textarea name="remark" cols="75" rows="5" class="textarea_table">${bean.remark}</textarea>
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

    function customerValidate(){
        var title = $("#title").html();
        if(!title){
            window.top.$.juiceDialog.warn('题干不能为空!');
            return false;
        }else{
            return true;
        }
    }
</script>