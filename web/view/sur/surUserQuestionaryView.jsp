<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<div style="width: 100%;">
    <div style="text-align: center;padding-bottom: 20px">
            <span style="font-size: 24px;font-weight: bold">
                ${bean.title}
            </span>
    </div>
    <ul style="padding-left: 10%;padding-right: 5%">
        <c:forEach items="${questions}" var="question" varStatus="status">
            <li style="padding-bottom: 20px;clear: both">
                <div style="font-size: 16px;font-family: 宋体;padding-bottom: 8px;">
                    <span style="font-size: 16px;font-weight: bold"> ${status.index+1}.</span>${question.title}
                </div>
                <ul>
                    <c:forEach items="${question.surOptions}" var="option">
                        <c:set var="opt" value=",${option.id},"/>
                        <c:set var="key" value=",${answerKey[question.id]},"/>
                        <li style="padding-bottom: 5px;float:left;padding-right: 25px;height: 28px;">
                            <c:choose >
                                <c:when test="${question.type==0}">
                                    <span style="font-size: 14px;font-weight: bold">${option.indexNo}.</span>&nbsp;<input type="radio" disabled="disabled" name="question_${question.id}" <c:if test="${fn:contains(key,opt)}">checked="checked" </c:if> value="${option.id}">${option.content}
                                </c:when>
                                <c:when test="${question.type==1}">
                                    <span style="font-size: 14px;font-weight: bold">${option.indexNo}.</span>&nbsp;<input type="checkbox" disabled="disabled" name="question_${question.id}" <c:if test="${fn:contains(key,opt)}">checked="checked" </c:if> value="${option.id}" >${option.content}
                                </c:when>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>

</div>
</body>
</html>