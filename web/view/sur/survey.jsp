<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<script src="${ctx}/view/sur/survey.js"></script>
<script src="${ctx}/js/jquery/jquery-1.7.2.js"></script>

<div class="ContentHome">
<form id="surveyForm"  name="surveyForm" action="${ctx}/surUserQuestionary/saveQuestionary.do" method="post" onsubmit="return checkAnswer()">
    <input type="hidden" id="questionSize" value="${fn:length(questions)}">
    <%--<input type="hidden" id="userQuestioanryId" name="userQuestioanryId" value="${bean.id}">--%>
    <input type="hidden" id="questionaryId" name="questionaryId" value="${bean.id}">
    <div style="width: 100%;">
        <div style="text-align: center;padding-bottom: 20px">
             <h1 class="title1">${bean.title}</h1>
        </div>
        <ul style="padding-right: 5%">
            <c:forEach items="${questions}" var="question" varStatus="status">
                <li style="padding-bottom: 20px;clear: both">
                    <div style="font-size: 16px;font-family: '微软雅黑 宋体';padding-bottom: 8px;text-align: left">
                        <span style="font-size: 16px;"> ${status.index+1}.</span>${question.title}
                    </div>
                    <ul>
                        <c:forEach items="${question.surOptions}" var="option">
                            <li style="padding-bottom: 5px;float:left;padding-right: 25px;height: 28px;">
                                <c:choose >
                                    <c:when test="${question.type==0}">
                                        <span style="font-size: 14px;">${option.indexNo}.</span>&nbsp;<input type="radio" answerIndex="question_${status.index+1}" name="question_${question.id}" value="${option.id}">${option.content}
                                    </c:when>
                                    <c:when test="${question.type==1}">
                                        <span style="font-size: 14px;">${option.indexNo}.</span>&nbsp;<input type="checkbox" answerIndex="question_${status.index+1}" name="question_${question.id}" value="${option.id}">${option.content}
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
        <input type="reset" value="重置" class="btn_Ok">
    </div>
</form>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>