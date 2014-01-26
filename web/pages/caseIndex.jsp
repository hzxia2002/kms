<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script type="text/javascript" src="${ctx}/pages/caseIndex.js"></script>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="left">
        <div class="title">技战法案例课程库</div>
        <div class="tree">
            <ul id="catalogTree" style="margin-top:3px;overflow: auto"></ul>
        </div>
    </div>
    <div class="right">
        <div class="one">
            <div class="title">课程展示</div>
            <div class="maintp">
                <br />
                <ul  id="content" style="height:350px;width: 100%;padding-left: 0%">

                </ul>
                <div id="page" align="right" style="clear: both">
                </div>
                <br />
            </div>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
