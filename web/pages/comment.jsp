<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<div>
    <table width="100%">
        <c:forEach items="${page.rows}" var="comment">
            <tr style="border-bottom: 1px solid #dcdcdc;min-height: 50px;">
                <td style="width: 10%;vertical-align: top" nowrap>
                    <span style="font-weight: bold;float: left">${comment.replyer.displayName}发表评论：</span>
                    <br>
                    (<fmt:formatDate value="${comment.replyTime}" pattern="yyyy-MM-dd HH:mm" type="both"></fmt:formatDate>)
                    <%--<hr>--%>
                </td>
                <td style="text-align: left;vertical-align: top;border-left: 1px solid #dcdcdc;">
                    <%request.setAttribute("vEnter","\n");%>
                    <%request.setAttribute("br","\n");%>
                    <span style="word-spacing: 2;color: #0075cc;"> <c:out value="${fn:replace(comment.content,vEnter,br)}" ></c:out></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${page.total>1}">
        <div align="right">
            <c:set var="min" value="1"/>
            <c:set var="max" value="${page.total>=5?5:page.total}"/>
            <c:if test="${page.total>5}">
                <c:set var="min" value="${page.total-4}"></c:set>
            </c:if>
            <span style="float:left;">共${page.records}条评论</span>
            <c:if test="${min>1}">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadComments(1)" >第一页\</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadComments(${min-1})" >上一页</a></span>;
            </c:if>
            <c:forEach  begin="${min}" end="${max}" var="index">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:${(index==page.page)?"red":"blue"}" onclick="loadComments(${index})" >${index}</a></span>
            </c:forEach>
            <c:if test="${max>=5&&page.page<page.total}">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadComments(${max+1})" >下一页</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadComments(${page.total})" >最后一页</a></span>
            </c:if>
        </div>
    </c:if>
</div>