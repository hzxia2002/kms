<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script type="text/javascript" src="${ctx}/pages/study.js"></script>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="leftzfwk">
        <div class="leftzf">
            <div class="title">
                <div class="one"><c:out value="${paths}"></c:out></div>
                <div class="two" id="addCollect" style="cursor: pointer;"></div>
            </div>
            <div class="main">
                <input type="hidden" id="articleId" value="${bean.id}">
                <input type="hidden" id="planId" value="${planId}">
                <p class="text" align="center" id="title"  style="font-size: 20px;">${bean.title}</p>
                <p>
                    关键字：${bean.keyWord}<br />
                </p>
            </div>
            <div class="mainmuen">
                ${bean.content}
            </div>
            <div>
                <div>
                    <div style="float:left;font-weight: bold;">附件：</div>
                    <ul>
                        <c:forEach items="${docAttachmentses}" var="attachment">
                            <li><a href='${ctx}${attachment.filePath}' target='_blank'>${attachment.orginName}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="rightct">
        <div class="rightzf">
            <div class="title">词条统计</div>
            <div class="mainct">
                <table width="180">
                    <tr>
                        <td align="right" width="70">浏览次数：</td>
                        <td>${bean.visitTimes}</td>
                    </tr>
                    <tr>
                        <td align="right" style="vertical-align: top;">更新时间：</td>
                        <td><fmt:formatDate value="${bean.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    </tr>
                    <tr>
                        <td align="left">创建者：</td>
                        <td>${bean.createUser}</td>
                    </tr>
                </table>
                </p>
            </div>
        </div>
    </div>
    <div class="clr"></div>
    <div class="two"></div>
    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
