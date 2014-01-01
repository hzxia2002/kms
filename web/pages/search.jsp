<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>经侦辅助办案知识库</title>
    <link href="${ctx}/skin/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/js/jui/skins/Aqua/css/jui-all.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctx}/js/application/page.js"></script>
<style type="text/css">
    <!--
    a:link { text-decoration:underline;color: blue}
    a:active { text-decoration:blink;color: blue}
    a:hover { text-decoration:underline;color: red}
    a:visited { text-decoration: underline;color: darkmagenta}
    -->
</style>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="leftzfwk">
        <div class="leftzf">
            <div class="title">
                <div class="one" style="overflow: hidden;text-overflow:ellipsis; width:80%;white-space:nowrap;font-size: 14px;font-weight: bold">
                    检索结果（共有检索结果<font color="red">${page.records}</font>条）
                </div>
                <div class="two" style="cursor: pointer;vertical-align: top;padding-top: 2px;">
                </div>
            </div>
            <div class="main">
            <form:form modelAttribute="page" name="newsList" action="${ctx}/search/search.do">
                <input type="hidden" id="key" name="key" value="${key}">
                <form:hidden path="pageSize" />
                <form:hidden path="total" />
                <form:hidden path="page" />
                <table>
                    <c:forEach var="news" items="${page.rows}">
                        <tr>
                            <td width="96%" align="left" style="padding-top: 10px; font-family: '微软雅黑 宋体'; font-size:14px;">
                                <a href="${ctx}/page/view.html?id=${news.id}" target="blank" style="">${news.title}</a>
                            </td>
                        </tr>
                        <tr>
                            <td width="96%" align="left" style="padding-top: 10px; color: #000000; font-family: '微软雅黑 宋体'; font-size:12px;">${news.content}</td>
                        </tr>
                        <tr>
                            <td width="96%" align="left" style="padding-bottom: 10px; color: green; font-family: '微软雅黑 宋体'; font-size:12px;">所属分类：${news.path.name}　　　<fmt:formatDate value="${news.publishDate}" type="both" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td>
                                <pager:page addForm="false" formName="page" attribute="page"/>
                            </td>
                        </tr>
                </table>
                </form:form>
            </div>
        </div>
    </div>
    <div class="rightct">
        <div class="rightzf">
            <div class="title">词条统计</div>
            <div class="mainct">

            </div>
        </div>
    </div>
    <div class="clr"></div>
    <div class="two"></div>
    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
