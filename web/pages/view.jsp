<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script type="text/javascript" src="${ctx}/pages/view.js"></script>
    <style type="text/css">
        .submitCss{background: url("../skin/images/tj1.jpg") no-repeat;background-position: -8 0;}
        .submitCss:hover{background: url("../skin/images/tj.jpg") no-repeat;}
    </style>
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
                <input type="hidden" id="articleId" value="${bean.id}">
                <p class="text" align="center" id="title">${bean.title}</p>
                <p>
                    关键字：${bean.keyWord}<br />
                </p>
            </div>
            <div class="mainmuen">
                ${bean.content}
            </div>
            <div>
                <div>
                    <div style="float:left;font-weight: bold;">附件：</div>
                    <ul>
                        <c:forEach items="${docAttachmentses}" var="attachment">
                            <li><a href='${ctx}${attachment.filePath}' target='_blank'>${attachment.orginName}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <c:if test="${type==2||type==3}">
            <div class="leftyj">
                <div class="title">
                    <div class="one">评论与意见</div>
                </div>
                <div>
                    <c:forEach items="${comments}" var="comment">
                          <div style="border-bottom: 1px dashed #dcdcdc;">
                              <span style="color: #dcdcdc">${comment.replyer.displayName}发表评论(<fmt:formatDate value="${comment.replyTime}" type="both"></fmt:formatDate>)：</span>
                              <br>      <%request.setAttribute("vEnter","\n");%>
                              <span style="word-spacing: 2"> ${fn:replace(comment.content,vEnter,"<br>")}</span>
                          </div>
                    </c:forEach>
                </div>
                <div class="main">
                    <input type="radio" name="commentType"  value="1">意见
                    <input type="radio" name="commentType"  value="2" checked="checked">评论
                    <textarea rows="6" cols="70" style="width: 705px;height: 175px;" id="comment">
                    </textarea>
                    <%--<img src="../skin/images/yj1.jpg" width="705" height="175" />--%>
                </div>
            </div>
            <div class="lefttj">
                <%--<div class="onetj">验证码：--%>
                    <%--<input name="textfield" type="text" id="textfield" size="10" />&nbsp;&nbsp;--%>
                    <%--<img src="../skin/images/yzm.jpg" width="69" height="31" />--%>
                <%--</div>--%>
                <div class="twotj" align="center" style="width: 100%">
                    <div class="submitCss" style="width: 78px;height: 29px;" onclick="submitComment('${bean.id}')"></div>
                    <%--<img src="../skin/images/tj.jpg" width="78" height="29"  class="submitCss"/>--%>
                </div>
            </div>
        </c:if>
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
