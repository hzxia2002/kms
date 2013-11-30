<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/header.jsp"%>
<div class="one">
    <div class="title">收藏夹内容</div>
    <div>
        <input type="text" name="articleName" id="articleName" value="${articleName}" style="height: 22px;width:200px;border: #71ccff 1px solid;">
        <input type="button" onclick="loadCollect('${id}');" style="height: 24px;width: 60px;" value="查询">
    </div>
    <div class="main">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabkuang">
            <tr>
                <td align="center" class="tabtit" width="5%">序号</td>
                <td align="center" class="tabtit" width="30%">所属收藏夹</td>
                <td align="center" class="tabtit">知识点名称</td>
                <td align="center" class="tabtit" width="10%">操作</td>
            </tr>
            <c:forEach var="bean" items="${page.rows}" varStatus="status">
                <tr>
                    <td bgcolor="#f9f9f9" class="tabmain"nowrap align="center">${status.index+1}&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">${bean.catagory.name}&nbsp;</td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                        <c:if test="${bean.article == null}">
                            <a target="_blank" style="color:blue" href="${ctx}${bean.url}">${bean.remark}</a>
                        </c:if>
                        <c:if test="${bean.article != null}">
                            <a target="_blank" style="color:blue" href="${ctx}/page/view.html?id=${bean.article.id}">${bean.remark}</a>
                        </c:if>
                    </td>
                    <td bgcolor="#f9f9f9" class="tabmain" nowrap align="center">
                        <a style="color:blue" href="javascript:void(0)" onclick="deleteCollect('${id}',1,${bean.id})">删除</a>
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
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadCollect('${id}',1)" >第一页\</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadCollect('${id}',${min-1})" >上一页</a></span>;
            </c:if>
            <c:forEach  begin="${min}" end="${max}" var="index">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:${(index==page.page)?"red":"blue"}" onclick="loadCollect('${id}',${index})" >${index}</a></span>
            </c:forEach>
            <c:if test="${max>=5&&page.page<page.total}">
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadCollect('${id}',${max+1})" >下一页</a></span>
                <span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadCollect('${id}',${page.total})" >最后一页</a></span>
            </c:if>

        </div>
    </c:if>
</div>