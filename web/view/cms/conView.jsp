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
            <th colspan="2"    align="center" style="text-align: center" >
                <span style="font-size: 16px;font-weight: bold">主题：${question.title}</span>
            </th>
        </tr>
        <tr class="inputTr">
            <td align="center"  width="10%">作者</td>
            <td align="center"  width="90%">正文</td>
        </tr>
        <tr class="inputTr">
            <td align="center"   width="10%" rowspan="2">
                ${question.asker}
            </td>
            <td align="left"   width="90%">
                            <span style="float: left;">
                                <span style="font-weight: bold"> 提问时间:</span>
                                ${question.publishTime}</span>
            </td>
        </tr>
        <tr class="inputTr" style="min-height: 200px;">

            <td align="left"    width="90%">${question.content}</td>
        </tr>
        <c:forEach var="bean" items="${answers}" varStatus="status">
            <tr class="inputTr">
                <td align="center"  width="10%" rowspan="2">
                        ${bean.responser}
                </td>
                <td align="left"    width="90%">
                            <span style="float: left;">
                                <span style="font-weight: bold"> 回复时间:</span>
                                    ${bean.responseTime}</span>
                </td>
            </tr>
            <tr class="inputTr" style="min-height: 200px;">
                <td align="left"   width="90%">${bean.content}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>