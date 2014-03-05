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

        #directionContainer ul{
            margin:0px;
            padding: 0px 0px 0px 20px;
        }
        .main{
            width:600px;
        }

        #directionWrapper{
            padding:15px 7px;
            width:234px;
            border:1px solid #CCC;
        }
        .directionTitle{
            font-weight: bold;
            font-size: 14px;
            padding-bottom:3px;
            border-bottom: 1px dashed #ccc;
        }
        .sectionItem{
            height:20px;
            padding: 4px;
            font-size: 14px;
            font-weight: 500;
            font-family: 微软雅黑,宋体,黑体;
            color: #136ec2;
        }

        .sectionItem a:hover {
            color: #136ec2;
            text-decoration: underline;
        }

        .sectionItem a:visited {
            color: #136ec2;
            text-decoration: none;
        }

        .sectionItem span{
            *zoom:1;
            display:inline-block;
        }

        .itemTitle a {
            color: #136ec2;
        }

        .itemTitle {
            color: #136ec2;
            padding-left: -10px;
        }

        .itemTitle span[style]{
            float:left !important;
            color: #136ec2 !important;
            margin-bottom: 5px !important;
            line-height: 22px !important;
            font-size: 14px !important;
            font-family: 微软雅黑,宋体,黑体 !important;
            font-weight: 500 !important;
        }

        p span {
            color: #333 !important;
            margin-bottom: 5px !important;
            text-indent: 2em !important;
            line-height: 22px !important;
            font-size: 14px !important;
            font-family: 微软雅黑,宋体,黑体 !important;
        }

            /*.fixTop{*/
            /*position: fixed;*/
            /*top: -1px;*/
            /*}*/

        .Header .logo .search .kuang .input11{
            float:left;
            width: 360px;
            border: 2px solid #008eca;
            font-family: "微软雅黑,宋体";
            font-size: 14px;
            color: #444444;
            height: 27px;
            line-height: 27px;
            text-indent: 10px;
        }
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
    <div class="title2" style="font-size: 18px;white-space: nowrap; text-overflow: ellipsis;height:40px;margin-top: 10px;">课程名称：${bean.title}</div>
    <div class="leftkck">
        <div class="leftkckone">

            <div class="two" width="330px" height="332px" id="media" style="width: 330px;height: 332px">
                <c:if test="${aviPath!=null}">
                    <OBJECT ID="hutia" height="330" width="330" CLASSID="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">
                        <param name="URL" value="${aviPath}" />
                        <param name="AutoStart" value="true" />
                        <param name="AudioStream" value="-1">
                        <param name="AutoSize" value="0">
                        <param name="AnimationAtStart" value="0">
                        <param name="AllowScan" value="true">
                        <param name="AllowChangeDisplaySize" value="-1">
                    </OBJECT>
                </c:if>
                <c:if test="${aviPath==null}">
                    <div style="width: 330px;height: 332px;vertical-align: middle;color: red"> 未添加视频</div>
                </c:if>
            </div>
        </div>
        <div class="leftkckone">
            <div class="title">相关学习资料</div>
            <div class="tree1">
                <table width="95%" border="0" align="center">
                    <c:forEach items="${attachments}" var="attachment" varStatus="status">
                        <tr>
                            <td height="30" align="left">

                                <c:if test="${attachment.isPPT}">
                                <a href="javascript:void(0)" title="点击查看"
                                   onclick="loadPPT('${ctx}${attachment.pptPath}','${attachment.total}')"
                                   style="cursor: hand">
                                    </c:if>
                                    <c:if test="${attachment.isAVI}">
                                    <a href="javascript:void(0)" title="点击查看"
                                       onclick="loadAVI('${attachment.path}')"
                                       style="cursor: hand">
                                        </c:if>
                                            ${status.index+1}、${attachment.name}</a>(<a href="${ctx}${attachment.path}" target="_blank" style="color: blue;font-size: 12px;">下载</a>)
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="rightkck">
        <div class="tree" id="ppt" style="width:677px ;height:477px; border: 1px solid #cedce9;">
            <c:if test="${bean.attachPath==null || bean.attachPath == ''}">
                <div style="color: red;width: 677px;height: 477px;"> 未添加PPT</div>
            </c:if>
            <c:if test="${bean.attachPath!=null && bean.attachPath != ''}">#${bean.attachPath}#
                <img src="${ctx}${bean.attachPath}" width="677px" height="477px" alt="未添加PPT" />
            </c:if>
        </div>
    </div>
    <div class="rightkck" style="padding-top: 10px;">
        <div class="one">
            <div class="title">课程介绍</div>
            <%--<div class="mainkjs" style="height: 485px; overflow-x: hidden; overflow-y: auto ">--%>
            <%--<div class="mainkjs" style="width:652px; height:485px; overflow-x: hidden; overflow-y: auto ">--%>
                <%--<br />--%>
                <%--${bean.content}--%>
                <%--<br />--%>
                <%--<br />--%>
            <%--</div>--%>
            <div class="main" style="width: 100%;">
                <input type="hidden" id="articleId" value="${bean.id}">
                <%--<h2 class="title1" id="title" style="text-align: left;overflow: hidden">--%>
                    <%--<div >${bean.title}</div>--%>
                <%--</h2>--%>
                <c:if test="${not empty bean.keyWord}">
                    <div style="clear: both">
                        关键字：${bean.keyWord}
                    </div>
                </c:if>
                <%--<div id="directionContainer" class="z-catalog nslog-area log-set-param" style="width: 75%;">--%>
                    <%--<table width="100%" style="border: 1px solid #dcdcdc;">--%>
                        <%--<tr>--%>
                            <%--<td width="10%" bgcolor="#FBFBFB"><h2><span>目录:</span></h2></td>--%>
                            <%--<td style="background-color: #fff;" valign="top">${bean.catalogue}</td>--%>
                        <%--</tr>--%>
                    <%--</table>--%>
                <%--</div>--%>
                <div class="mainmuen" style="margin-top: 30px;width: 100%;">
                    ${bean.content}
                </div>
            </div>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
