<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<div style="width: 100%;">
    <div style="text-align: center;padding-bottom: 20px">
            <span style="font-size: 20px;font-weight: bold;">
                ${paper.paperName}
            </span><br/>
            <span style="font-size: 14px;font-weight: bold">
                答题时间:${paper.paperMinute}分钟&nbsp;&nbsp;总分:${paper.totalScore}分
            </span>
    </div>

    <ul>
        <c:set var="i" value="0"/>
        <c:forEach items="${sections}" var="section" varStatus="sectionStatus">
            <li style="clear: both">
                <div style="font-size: 14px;font-family: 宋体;padding-left: 10px;">
                    <span style="font-size:14px;font-weight: bold;">${section.sectionName}</span>
                </div>
                <ul style="padding-left: 20px;padding-right: 5%;">
                    <c:forEach items="${details}" var="paperDetail" varStatus="status">
                        <c:set var="question" value="${paperDetail.question}"/>
                        <c:if test="${section.id==paperDetail.sectionId}">
                            <c:set var="i" value="${i+1}"/>
                            <li style="padding-bottom: 20px;clear: both">
                                <div style="font-size: 14px;font-family: 宋体;padding-bottom: 8px;">
                                    <span style="font-size: 14px;"> ${i}.</span>${question.content}
                                </div>
                                <ul style="padding-left:20px;padding-right: 5%">
                                    <c:forEach items="${question.options}" var="option">
                                        <li style="height:28px;font-size: 14px;font-family: 宋体;">
                                            <c:choose >
                                                <c:when test="${question.questionType==0||question.questionType==2}">
                                                    <span style="font-size:14px;"><input type="radio" name="question_${question.id}" value="${option.id}">&nbsp;${option.optionKey}. </span>${option.optionOption}
                                                </c:when>
                                                <c:when test="${question.questionType==1}">
                                                    <span style="font-size: 14px;"><input type="checkbox" name="question_${question.id}" value="${option.id}">&nbsp;${option.optionKey}. </span>${option.optionOption}
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