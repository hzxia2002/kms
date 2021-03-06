<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/header.jsp"%>
<div class="one">
    <div class="title">学习任务</div>
    <div class="main">
        <table width="100%" border="1" cellpadding="1" cellspacing="1" class="tabkuang" style="background-color: #ffffff">
            <tr style="height:60px; background-color: #F0F9FF;">
                <td align="center" class="tabtit" width="5%">序号</td>
                <td align="center" class="tabtit" >课程名称</td>
                <td align="center" class="tabtit" width="8%">总时长<br/>(分钟)</td>
                <td align="center" class="tabtit" width="8%">学习次数</td>
                <td align="center" class="tabtit" width="8%">学习时长<br/>(分钟)</td>
                <td align="center" class="tabtit" width="8%">完成率</td>
                <td align="center" class="tabtit" width="20%">末次学习时间</td>
                <td align="center" class="tabtit" width="8%">开始学习</td>
            </tr>
            <c:forEach var="bean" items="${page.rows}" varStatus="status">
                <tr>
                    <td bgcolor="#f9f9f9" class="tabmain" width="5%" nowrap align="center">${status.index+1}&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap>
                            ${bean.course.name}
                    </td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">${bean.course.studyDuration}&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">${bean.studyTimes}&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">${bean.studyDuration}&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">${bean.studyDuration*100/bean.course.studyDuration}%&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                        <fmt:formatDate value="${bean.lastStudyTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                        <a target="_blank" style="color:blue" href="${ctx}/study/init.html?studyPlanId=${bean.id}">进入>></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <c:if test="${page.total>1}">
        <div align="right">
            <c:set var="min" value="1"/>
            <c:set var="max" value="${page.total>=5?5:page.total}"/>
            <c:if test="${page.total>5}">
                <c:set var="min" value="${page.total-4}"></c:set>
            </c:if>
            <span style="float:left;">共计${page.records}条记录</span>
            <c:if test="${min>1}">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(1)" >第一页\</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(${min-1})" >上一页</a></span>;
            </c:if>
            <c:forEach  begin="${min}" end="${max}" var="index">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:${(index==page.page)?"red":"blue"}" onclick="loadContent(${index})" >${index}</a></span>
            </c:forEach>
            <c:if test="${max>=5&&page.page<page.total}">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(${max+1})" >下一页</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(${page.total})" >最后一页</a></span>
            </c:if>

        </div>
    </c:if>
</div>