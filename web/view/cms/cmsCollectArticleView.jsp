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
                收藏夹ID:
            </td>
            <td class="container">
                ${bean.catagory}
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
                链接地址:
            </td>
            <td class="container">
                ${bean.url}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                备注名称:
            </td>
            <td class="container">
                ${bean.remark}
            </td>
        </tr>
    </table>
</div>
</body>
</html>