
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
        <%--<c:if test="${hasMore}">--%>
            <div style="float: right;position: absolute;top:6px;right: 6px;font-weight: bold;cursor: pointer" ><a href="${ctx}/page/more.html" style="color: #0000ff;">更多>></a></div>
        <%--</c:if>--%>
        <c:forEach var="bean" items="${recommends}">
            <div class="zttj_main">
                <div class="left"><img src="${ctx}/${bean.attachPath}" width="208" height="121" /></div>
                <div class="right">
                    <a href="view.html?id=${bean.id}" target="_blank"><strong class="text">${bean.title}</strong></a><br>
                    &nbsp;&nbsp;&nbsp;&nbsp;<a href="view.html?id=${bean.id}" target="_blank">
                        ${bean.summary}
                </a></div>
            </div>
        </c:forEach>
    </div>
    <div class="clr"></div>
    <div class="two">
        <div class="rdzt">
            <div class="title">热点主题</div>
            <div class="neirong">
                <c:forEach var="bean" items="${hotKmsRows}">
                    <ul class="line">
                        <li><img src="../skin/images/dit.jpg" style="float: left;margin-top: 10px"/>&nbsp;&nbsp;<a href="view.html?id=${bean.id}" target="_blank">
                            <div style="color:blue;text-overflow:ellipsis; width:334px;margin-left: 12px;overflow: hidden;float:left;white-space: nowrap;">${bean.title}</div>
                        </a>
                            [<fmt:formatDate value="${bean.updateTime!=null?bean.updateTime:bean.createTime}" type="both" pattern="yy-MM-dd"></fmt:formatDate>/${bean.visitTimes}]
                        </li>
                    </ul>
                </c:forEach>

            </div>
        </div>
        <div class="zxzt">
            <div class="title">最新主题</div>
            <div class="neirong">
                <c:forEach var="bean" items="${newKmsRows}">
                    <ul class="line">
                        <li ><img src="../skin/images/dit.jpg" style="float: left;margin-top: 10px"/>&nbsp;&nbsp;<a href="view.html?id=${bean.id}" target="_blank">
                            <div style="color:blue;text-overflow:ellipsis; width:334px;float: left;margin-left: 12px;overflow: hidden;white-space: nowrap;">${bean.title}</div>
                        </a>[<fmt:formatDate value="${bean.updateTime!=null?bean.updateTime:bean.createTime}" type="both" pattern="yy-MM-dd"></fmt:formatDate>/${bean.visitTimes}]</li>
                    </ul>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
