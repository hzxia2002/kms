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
                <div class="feed-item" style="display: block;">
                    <div>
                        <a href="${ctx}/page/conView?conId=${question.id}" target="_blank" style="margin-left: 10px;">
                            <span style="word-break: break-all;margin-left: 10px;" class="comment-title">${question.title}</span></a>
                        <span class="comment-gray" style="margin-right: 10px;">咨询</span>
                    </div>
                    <div style="padding-left: 10px; clear: both;border-top: 1px solid #eee; ">
                        <span class="comment-gray" style="margin-left: 10px; margin-right: 10px;">${question.asker}</span>提交于
                        <span class="comment-gray" style="margin-left: 10px;"><fmt:formatDate value="${question.publishTime}" type="both"></fmt:formatDate></span>
                        <br/>
                        <div class="comment-body" style="margin-left: 2px; margin-right: 12px;word-break: break-all;text-indent: 2em">${question.content}</div>
                    </div>
                    <c:forEach var="bean" items="${answers}" varStatus="status">
                        <div style="padding-left: 10px;border-top: 1px solid #eee;">
                                <span class="comment-gray" style="margin-left: 10px; margin-right: 10px;">${bean.responser}</span>回复于
                                <span class="comment-gray" style="margin-left: 10px;"><fmt:formatDate value="${bean.responseTime}" type="both"></fmt:formatDate></span>
                            <br/>
                            <div class="comment-body" style="margin-left: 2px; margin-right: 12px;word-break: break-all;text-indent: 2em">${bean.content}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
