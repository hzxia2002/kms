<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsCatalogEditForm" modelAttribute="bean" name="cmsCatalogEditForm" action="${ctx}/cmsCatalog/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <c:if test="${bean.parent.id!=null}">
            <tr class="inputTr">
                <td  align="right">
                    上层节点:
                </td>
                <td  class="container">
                    <input type="hidden" name="parent" value="${bean.parent.id}" />
                    <input type="text" name="parent_name" value="${bean.parent.name}" readonly="true" class="table_input" disabled="true"/>&nbsp;
                </td>
            </tr>
        </c:if>
        <tr class="inputTr">
            <td  align="right">
                名称:
            </td>
            <td  class="container">
                <input type="text" name="name" class="table_input" value="${bean.name}" validate="{required:true}"/>&nbsp;
            </td>
        </tr>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--编码:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="code" value="${bean.code}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
        <tr class="inputTr">
            <td  align="right">
                文件目录:
            </td>
            <td  class="container">
                <input type="text" name="path" value="${bean.path}" class="table_input" validate="{required:true}"/>&nbsp;
            </td>
        </tr>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--显示图标:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="icon" value="${bean.icon}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--父节点标识:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="parent" value="${bean.parent}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--叶节点:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="isLeaf" value="${bean.isLeaf}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--树形层次:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="treeId" value="${bean.treeId}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--类型目录:0  知识点目录;1 图片目录;2 附件目录:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="type" value="${bean.type}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
        <tr class="inputTr">
            <td  align="right">
                是否有效:
            </td>
            <td  class="container">
                <form:checkbox path="isValid"/>
                    <%--<input type="checkbox" name="isValid"  <c:if test="${bean.isValid}">"checked"</c:if>  value="${bean.isValid}" />&nbsp;--%>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <textarea rows="4" cols="60" class="textarea_table" name="description" id="memo">${bean.description}</textarea>
                    <%--<input type="text" name="description" value="${bean.description}" />&nbsp;--%>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#cmsCatalogEditForm').validate();
    });
</script>