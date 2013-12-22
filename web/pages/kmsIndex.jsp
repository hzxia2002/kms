<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script type="text/javascript" src="${ctx}/pages/kmsIndex.js"></script>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="left">
        <div class="title">侦查知识点库</div>
        <div class="tree">  <ul id="catalogTree" style="margin-top:3px;overflow: auto"></ul></div>
    </div>
    <div class="right">
        <div class="one">
            <div class="title">搜索</div>
            <div class="main">
                <div class="search">
                    <div class="kuang"><input name="" type="text" class="input11" value="请输入关键词" />
                    </div>
                    <div class="kuang1"><img src="../skin/images/button_searth.jpg" width="92" height="33" />
                    </div>
                </div>
                <div class="search">
                    <input type="radio" name="radio" id="radio" value="radio" />
                    标题
                    <input type="radio" name="radio" id="radio2" value="radio" />
                    内容
                    <input type="radio" name="radio" id="radio3" value="radio" />
                    标题和内容</div>
            </div>
        </div>
        <div class="one">
            <div class="title" id="contentTitle">法律法规</div>
            <div class="main1">
                <ul width="80%"  id="content" style="height: 250px;">
                </ul>
                <div id="page" align="right">
                </div>
            </div>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
