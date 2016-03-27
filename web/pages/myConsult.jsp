<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/header.jsp"%>
<div class="one">
    <div class="title">我的咨询</div>
    <%--<div>--%>
    <%--<input type="text" name="paperName" id="paperName"   style="height: 22px;width:200px;border: #71ccff 1px solid;">--%>
    <%--<input type="button" onclick="loadConsult();" style="height: 24px;width: 60px;" value="查询">--%>
    <%--</div>--%>
    <div class="main">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabkuang">
            <tr>
                <td align="center" class="tabtit" width="5%">序号</td>
                <td align="center" class="tabtit" width="30%">主题</td>
                <td align="center" class="tabtit" width="20%" nowrap="nowrap">咨询时间</td>
                <td align="center" class="tabtit" width="20%" nowrap="nowrap">答复时间</td>
                <td align="center" class="tabtit" >操作</td>
            </tr>
            <c:forEach var="bean" items="${page.rows}" varStatus="status">
                <tr>
                    <td bgcolor="#f9f9f9" class="tabmain"nowrap align="center">${status.index+1}&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                        <a href="${ctx}/page/conView?conId=${bean.id}" target="_blank" style="margin-left: 2px;">${bean.title}&nbsp;</a>
                    </td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                            ${bean.publishTime}
                    </td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                            ${bean.responseTime}
                    </td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                        <a style="color:blue" href="${ctx}/page/conView?conId=${bean.id}" target="_blank">查看</a>&nbsp;&nbsp;
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
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadConsult(1)" >第一页\</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadConsult(${min-1})" >上一页</a></span>;
            </c:if>
            <c:forEach  begin="${min}" end="${max}" var="index">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:${(index==page.page)?"red":"blue"}" onclick="loadConsult(${index})" >${index}</a></span>
            </c:forEach>
            <c:if test="${max>=5&&page.page<page.total}">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadConsult(${max+1})" >下一页</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadConsult(${page.total})" >最后一页</a></span>
            </c:if>

        </div>
    </c:if>
</div>