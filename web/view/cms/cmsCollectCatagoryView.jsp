<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<div>
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                名称:
            </td>
            <td class="container">
                ${bean.name}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                代码:
            </td>
            <td class="container">
                ${bean.code}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                用户ID:
            </td>
            <td class="container">
                ${bean.user}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                类型,0表示全部用户共用,1用户自己创建:
            </td>
            <td class="container">
                ${bean.type}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                父节点标识:
            </td>
            <td class="container">
                ${bean.parent}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                叶节点:
            </td>
            <td class="container">
                ${bean.isLeaf}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                树形层次:
            </td>
            <td class="container">
                ${bean.treeId}
            </td>
        </tr>
    </table>
</div>
</body>
</html>