<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="6"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="rightxxyh" style="width: 100% !important;">

        <div class="one" style="width: 100% !important;">
            <div class="title" id="contentTitle">
                <span>咨询栏目</span>
            </div>
            <div class="main">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabkuang">
                    <tr>
                        <th colspan="2" class="tab_second_tit"  align="center" style="text-align: center" >
                            <span style="font-size: 16px;font-weight: bold;word-break: break-all;">主题：${question.title}</span>
                        </th>
                    </tr>
                    <tr>
                        <td align="center" class="tab_second_tit" width="10%">作者</td>
                        <td align="center" class="tab_second_tit" width="90%">正文</td>
                    </tr>
                    <tr>
                        <td align="center" class="tabmain" bgcolor="#f9f9f9" width="10%" rowspan="2">
                            ${question.asker}
                        </td>
                        <td align="left"  class="tabmain"  bgcolor="#f9f9f9" width="90%">
                            <span style="float: left;">
                                <span style="font-weight: bold"> 提问时间:</span>
                                ${question.publishTime}</span>
                        </td>
                    </tr>
                    <tr style="min-height: 200px;">

                        <td align="left"  class="tabmain"  bgcolor="#f9f9f9" width="90%" style="word-break: break-all;">${question.content}</td>
                    </tr>
                    <c:forEach var="bean" items="${answers}" varStatus="status">
                        <tr>
                            <td align="center" class="tabmain" bgcolor="#f9f9f9" width="10%" rowspan="2">
                                    ${bean.responser}
                            </td>
                            <td align="left"  class="tabmain"  bgcolor="#f9f9f9" width="90%">
                            <span style="float: left;">
                                <span style="font-weight: bold"> 回复时间:</span>
                                    ${bean.responseTime}</span>
                            </td>
                        </tr>
                        <tr style="min-height: 200px;">
                            <td align="left" class="tabmain"  bgcolor="#f9f9f9" width="90%" style="word-break: break-all;">${bean.content}</td>
                        </tr>
                    </c:forEach>
                </table>

            </div>

        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
