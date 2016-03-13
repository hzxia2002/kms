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
                ${paper.paperName}
            </span>
    </div>

    <ul>
        <c:forEach items="${sections}" var="section" varStatus="sectionStatus">
            <li style="padding-bottom: 20px;clear: both">
                <div style="font-size: 16px;font-family: 宋体;padding-bottom: 8px;">
                    <span style="font-size: 16px;font-weight: bold"> </span>${section.sectionName}
                </div>
                <ul style="padding-left: 20px;padding-right: 5%">
                    <c:forEach items="${details}" var="paperDetail" varStatus="status">
                        <c:set var="question" value="${paperDetail.question}"/>
                        <c:if test="${section.id==paperDetail.sectionId}">
                            <li style="padding-bottom: 20px;clear: both">
                                <div style="font-size: 16px;font-family: 宋体;padding-bottom: 8px;">
                                    <span style="font-size: 16px;font-weight: bold"> ${status.index+1}.</span>${question.content}
                                </div>
                                <ul style="padding-left: 20px;padding-right: 5%">
                                    <c:forEach items="${question.options}" var="option">
                                        <li style="padding-bottom: 5px;float:left;padding-right: 25px;height: 28px;">
                                            <c:choose >
                                                <c:when test="${question.questionType==1}">
                                                    <span style="font-size: 14px;font-weight: bold">${option.optionKey}.</span>&nbsp;<input type="radio" name="question_${question.id}" value="${option.id}">${option.optionOption}
                                                </c:when>
                                                <c:when test="${question.questionType==2}">
                                                    <span style="font-size: 14px;font-weight: bold">${option.optionKey}.</span>&nbsp;<input type="checkbox" name="question_${question.id}" value="${option.id}">${option.optionOption}
                                                </c:when>
                                            </c:choose>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>

            </li>
        </c:forEach>
    </ul>

</div>
</body>
</html>