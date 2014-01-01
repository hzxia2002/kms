<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <script src="${ctx}/view/sur/survey.js"></script>
</head>
<body>
<form id="surveyForm"  name="surveyForm" action="${ctx}/surUserQuestionary/saveQuestionary.do" method="post" onsubmit="return checkAnswer()">
    <input type="hidden" id="questionSize" value="${fn:length(questions)}">
    <%--<input type="hidden" id="userQuestioanryId" name="userQuestioanryId" value="${bean.id}">--%>
    <input type="hidden" id="questioanryId" name="questioanryId" value="${bean.id}">
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
                            <li style="padding-bottom: 5px;float:left;padding-right: 25px;height: 28px;">
                                <c:choose >
                                    <c:when test="${question.type==0}">
                                        <span style="font-size: 14px;font-weight: bold">${option.indexNo}.</span>&nbsp;<input type="radio" answerIndex="question_${status.index+1}" name="question_${question.id}" value="${option.id}">${option.content}
                                    </c:when>
                                    <c:when test="${question.type==1}">
                                        <span style="font-size: 14px;font-weight: bold">${option.indexNo}.</span>&nbsp;<input type="checkbox" answerIndex="question_${status.index+1}" name="question_${question.id}" value="${option.id}">${option.content}
                                    </c:when>
                                </c:choose>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div style="padding-top: 25px;text-align: center">
        <input type="submit" value="提交" class="btn_Ok">
    </div>
</form>
</body>
</html>