<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <style type="text/css">
        .next{ height: 30px; width: 30px;background: url(../skin/icons/gray_next.png) no-repeat;background-position: 0 -30px;}
        .next:hover{background-position: 0 0;}
        .prev{height: 30px; width: 30px;background: url(../skin/icons/gray_prev.png) no-repeat; background-position: 0 -30px;}
        .prev:hover{background-position: 0 0;}
    </style>
    <script type="text/javascript" src="${ctx}/pages/case.js"></script>

</head>

<body onload="loadPPT('${ctx}${pptPath}','${total}')">
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome" style="width: 1019px;">
    <div class="leftkck">
        <div class="leftkckone">
            <div class="title" style="font-size: 32px;">${bean.title}</div>
            <div class="tree" id="ppt">
                <img src="${ctx}${bean.attachPath}" width="370px" height="222px" />
            </div>
        </div>
        <div class="leftkckone">
            <div class="title">相关学习资料</div>
            <div class="tree1">
                <table width="250" border="0" align="center">
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <c:forEach items="${attachments}" var="attachment" varStatus="status">
                        <tr>
                            <td height="30" align="left">
                                <a href="javascript:void(0)"
                                        <c:if test="${attachment.isPPT}">
                                            onclick="loadPPT('${ctx}${attachment.pptPath}','${attachment.total}')"
                                            style="cursor: hand"
                                        </c:if>
                                        <c:if test="${attachment.isAVI}">
                                            onclick="loadAVI('${ctx}${attachment.path}')"
                                            style="cursor: hand"
                                        </c:if>
                                        >
                                        ${status.index+1}、${attachment.name}(<a href="${ctx}${attachment.path}" target="_blank" style="color: blue;font-size: 12px;">下载</a>)
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="rightkck">
        <div class="two" width="627" height="483" id="media">
            <OBJECT ID="hutia" height="483" width="627" CLASSID="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
                <param name="URL" value="${ctx}${aviPath}" />
                <param name="AutoStart" value="true" />
                <param name="AudioStream" value="-1">
                <param name="AutoSize" value="0">
                <param name="AnimationAtStart" value="0">
                <param name="AllowScan" value="true">
                <param name="AllowChangeDisplaySize" value="-1">
            </OBJECT>
        </div>
    </div>
    <div class="rightkck">
        <div class="one">
            <div class="title">课程介绍</div>
            <div class="mainkjs">
                <br />
                <%  request.setAttribute("vEnter", "\n");%>
                ${fn:replace(bean.summary,vEnter,"<br>")}
                <br />
                <br />
            </div>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
