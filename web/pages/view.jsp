<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script type="text/javascript" src="${ctx}/pages/view.js"></script>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="leftzfwk">
        <div class="leftzf">
            <div class="title">
                <div class="one"><c:out value="${paths}"></c:out></div>
                <div class="two" id="addCollect" style="cursor: pointer;"><a href="javascript:void(0);"> 收藏本页</a></div>
            </div>
            <div class="main">
                <p class="text" align="center" id="title">${bean.title}</p>
                <p>
                    关键字：${bean.keyWord}<br />
                </p>
            </div>
            <div class="mainmuen">
                ${bean.content}
            </div>
        </div>
        <div class="leftyj">
            <div class="title">
                <div class="one">提交修改意见</div>
            </div>
            <div class="main"><img src="../skin/images/yj1.jpg" width="705" height="175" /></div>
        </div>
        <div class="lefttj">
            <div class="onetj">验证码：
                <input name="textfield" type="text" id="textfield" size="10" />&nbsp;&nbsp;<img src="../skin/images/yzm.jpg" width="69" height="31" /> </div>
            <div class="twotj"><img src="../skin/images/tj.jpg" width="78" height="29" /></div>
        </div>
    </div>
    <div class="rightct">
        <div class="rightzf">
            <div class="title">词条统计</div>
            <div class="mainct">
                <p>          浏览次数：${bean.visitTimes}次<br />
                    更新时间：<fmt:formatDate value="${bean.updateTime}" type="both"></fmt:formatDate><br />
                    创建者：${bean.createUser}</p>
                <p><br />
                </p>
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
