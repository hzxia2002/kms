<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="${ctx}/skin/default.css" rel="stylesheet" type="text/css" />
    <%@include file="common/header.jsp"%>
</head>
<body>
<form:form id="configTableEditForm" modelAttribute="bean" name="configTableEditForm"  method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%"  class="inputTable">

            <tr class="inputTr" style="height: 40px;">
                <td  align="right">
                    <span class="needInfo">*</span>主题名:
                </td>
                <td  class="container">
                    <input type="text" name="title" value="${title}" class="table_input" style="height: 24px;" maxlength="100" validate="{required:true}"/>&nbsp;
                </td>
            </tr>

            <tr class="inputTr" style="height: 24px;">
                <td  align="right" style="padding-top: 0px;vertical-align: top;">
                    <span class="needInfo">*</span>目录:
                </td>
                <td  class="container" >
                    <input type="text" name="dictory" id="dictory"   maxlength="100" validate="{required:true}"/>&nbsp;
                    <input type="hidden" name="articleId"   value="${articleId}"/>&nbsp;
                </td>
            </tr>
        </table>
    </div>
</form:form>
</body>

</html>
<script type="text/javascript">
    $(function(){
        $("#dictory").juiceComboBox({
            width: 200,
            selectBoxWidth: 200,
            selectBoxHeight: 200, valueField: 'uid',treeLeafOnly:false,
            tree: { url: 'dictoryTree.html', checkbox: false }
        });

        $.metadata.setType("attr", "validate");
        v = $('#configTableEditForm').validate();
    });
</script>
