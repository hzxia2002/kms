
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="one" style="position: relative">
        <div class="zttj_title" style="float: left">推荐主题</div>

        <c:forEach var="bean" items="${page.rows}">
            <div class="zttj_main">
                <div class="left"><img src="${ctx}/${bean.attachPath}" width="208" height="121" /></div>
                <div class="right">
                    <a href="view.html?id=${bean.id}" target="_blank"><strong class="text">${bean.title}</strong></a><br>
                    &nbsp;&nbsp;&nbsp;&nbsp;<a href="view.html?id=${bean.id}" target="_blank">
                    ${bean.summary}
                </a></div>
            </div>
        </c:forEach>
        <c:if test="${page.total>1}">
            <div align="right">
                <c:set var="min" value="1"/>
                <c:set var="max" value="${page.total>=5?5:page.total}"/>
                <c:if test="${page.total>5}">
                    <c:set var="min" value="${page.total-4}"></c:set>
                </c:if>
                <span style="float:left;">共计${page.records}条记录</span>
                <c:if test="${min>1}">
                    <span style="padding-left:5px;padding-right:5px;"><a href="${ctx}/page/more.html?pageNo=1" style="color:blue"  >第一页\</a></span>
                    <span style="padding-left:5px;padding-right:5px;"><a href="${ctx}/page/more.html?pageNo=${min-1}" style="color:blue" >上一页</a></span>;
                </c:if>
                <c:forEach  begin="${min}" end="${max}" var="index">
                    <span style="padding-left:5px;padding-right:5px;"><a href="${ctx}/page/more.html?pageNo=${index}" style="color:${(index==page.page)?"red":"blue"}" >${index}</a></span>
                </c:forEach>
                <c:if test="${max>=5&&page.page<page.total}">
                    <span style="padding-left:5px;padding-right:5px;"><a href="${ctx}/page/more.html?pageNo=${max+1}" style="color:blue"   >下一页</a></span>
                    <span style="padding-left:5px;padding-right:5px;"><a href="${ctx}/page/more.html?pageNo=${page.total}" style="color:blue" >最后一页</a></span>
                </c:if>

            </div>
        </c:if>
    </div>
    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
