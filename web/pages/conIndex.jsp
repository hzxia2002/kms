<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script src="${ctx}/pages/conIndex.js" type="text/javascript"></script>
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
                <span style="float: right;margin-top: 2px;padding-bottom: 2px">
                    <a href="${ctx}/page/consult.do" >
                        <img src="${ctx}/skin/images/ask.gif">
                    </a>
                </span>
            </div>
            <div class="main">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabkuang">
                    <tr>
                        <td align="center" class="tabtit" width="5%">序号</td>
                        <td align="center" class="tabtit" width="30%">主题</td>
                        <td align="center" class="tabtit" >提问人</td>
                        <td align="center" class="tabtit" width="20%" nowrap="nowrap">提问时间</td>
                        <td align="center" class="tabtit" width="20%" nowrap="nowrap">回复时间</td>
                    </tr>
                    <c:forEach var="bean" items="${page.rows}" varStatus="status">
                        <tr>
                            <td bgcolor="#f9f9f9" class="tabmain"nowrap align="center">${status.index+1}&nbsp;</td>
                            <td bgcolor="#f9f9f9" class="tabmain" nowrap align="left" >
                                 <a href="${ctx}/page/conView?conId=${bean.id}" target="_blank" style="margin-left: 2px;">${bean.title}&nbsp;</a>
                            </td>
                            <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                                    ${bean.asker}
                            </td>
                            <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                                    ${bean.publishTime}
                            </td>
                            <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                                    ${bean.responseTime}
                            </td>

                        </tr>
                    </c:forEach>
                </table>

            </div>
            <c:if test="${page.total>1}">
                <div align="right">
                    <c:set var="min" value="1"/>
                    <c:set var="max" value="${page.total>=5?5:page.total}"/>
                    <c:if test="${page.total>5}">
                        <c:set var="min" value="${page.total-4}"></c:set>
                    </c:if>
                    <span style="float:left;">共计${page.records}条记录</span>
                    <c:if test="${min>1}">
                        <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadPaper(1)" >第一页\</a></span>
                        <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadPaper(${min-1})" >上一页</a></span>;
                    </c:if>
                    <c:forEach  begin="${min}" end="${max}" var="index">
                        <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:${(index==page.page)?"red":"blue"}" onclick="loadPaper(${index})" >${index}</a></span>
                    </c:forEach>
                    <c:if test="${max>=5&&page.page<page.total}">
                        <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadPaper(${max+1})" >下一页</a></span>
                        <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadPaper(${page.total})" >最后一页</a></span>
                    </c:if>

                </div>
            </c:if>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${ctx}/pages/conIndex.js"/>

</body>
</html>
