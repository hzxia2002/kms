<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script type="text/javascript" src="${ctx}/pages/studyIndex.js"></script>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="leftxxyhkuang">
        <div class="leftxxyh">
            <div class="title"><img src="../skin/images/sc.png" width="16" height="16" />&nbsp;&nbsp;我的收藏</div>
            <div class="tree">
                <ul id="catalogTree" style="margin-top:3px;"></ul>
            </div>
        </div>
        <div class="leftxxyh1"><img src="../skin/images/ksdl.jpg" width="212px" height="39" /></div>
    </div>
    <div class="rightxxyh" id="studyContent">

    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
