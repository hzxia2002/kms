<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/js/application/page.js"></script>
<div class="ContentHome">
    <div style="width: 100%;">
        <div style="text-align: center;padding-bottom: 20px">
             <h1 class="title1">经侦调研</h1>
        </div>
        <div style="width: 100%; height: 470px;overflow-x: hidden; overflow-y: auto;">
        <form:form modelAttribute="page" name="newsList" action="${ctx}/surUserQuestionary/list.do">
            <form:hidden path="pageSize" />
            <form:hidden path="total" />
            <form:hidden path="page" />
            <table width="70%" align="center">
                <c:forEach var="news" items="${page.rows}">
                    <tr>
                        <td width="96%" align="left" style="padding-top: 10px; font-family: '微软雅黑 宋体'; font-size:16px;border-bottom-color: #000000; border-bottom-width: 1px;border-bottom-style:dotted;">
                            ◇<a href="${ctx}/surUserQuestionary/survey.html?id=${news.id}" target="blank" style="">${news.title}</a>
                            &nbsp;&nbsp;[<fmt:formatDate value="${news.createTime}" type="both" pattern="yyyy-MM-dd"></fmt:formatDate>]
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td height="30">
                        <pager:page addForm="false" formName="page" attribute="page"/>
                    </td>
                </tr>
            </table>
        </form:form>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>