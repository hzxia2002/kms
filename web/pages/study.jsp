<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<title>经侦辅助办案知识库</title>--%>
    <link href="${ctx}/skin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>

    <%--<link href="${ctx}/js/ueditor/themes/default/css/ueditor.min.css" rel="stylesheet" type="text/css" />--%>
    <%--<link href="${ctx}/js/ueditor/themes/default/css/ueditor.css">--%>
    <style>
        #directionContainer ul{
            margin:0px;
            padding: 0px 0px 0px 20px;
        }
        .main{
            width:1024px;
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
            font-family: 微软雅黑,黑体;
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
            font-family: 微软雅黑,黑体 !important;
            font-weight: 500 !important;
        }

        p span {
            color: #333 !important;
            margin-bottom: 5px !important;
            text-indent: 2em !important;
            line-height: 22px !important;
            font-size: 14px !important;
            font-family: 微软雅黑,黑体 !important;
        }

        /*.fixTop{*/
        /*position: fixed;*/
        /*top: -1px;*/
        /*}*/
        .Header .logo .search .kuang .input11{
            float:left;
            width: 360px;
            border: 2px solid #008eca;
            font-family: "微软雅黑";
            font-size: 14px;
            color: #444444;
            height: 27px;
            line-height: 27px;
            text-indent: 10px;
        }
    </style>
    <script type="text/javascript" src="${ctx}/pages/study.js"></script>
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
                <div class="one" style="font-weight: bold;"><c:out value="${paths}"></c:out></div>
                <div class="two" style="cursor: pointer;vertical-align: top;padding-top: 2px;">

                </div>
            </div>
            <div class="main">
                <input type="hidden" id="articleId" value="${bean.id}">
                <h1 class="title1" id="title">
                    <div class="lemmaTitleH1">${bean.title}</div>
                </h1>
                <c:if test="${not empty bean.keyWord}">
                    <p>
                        关键字：${bean.keyWord}<br />
                    </p>
                </c:if>
            </div>
            <div id="directionContainer" class="z-catalog nslog-area log-set-param" style="width: 75%;">
                <table width="100%" style="border: 1px solid #dcdcdc;">
                    <tr>
                        <td width="10%" bgcolor="#FBFBFB"><h2><span>目录:</span></h2></td>
                        <td style="background-color: #fff;" valign="top">${bean.catalogue}</td>
                    </tr>
                </table>
            </div>
            <div class="mainmuen" style="margin-top: 20px;">
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
            <div style="clear: both;font-size: 14px">
                <div>
                    <div style="float:left;font-weight: bold;">试卷：</div>
                    <ul>
                        <c:forEach items="${papers}" var="paper">
                            <c:if test="${!paper.canNotDownload}">
                                <li> <a style="color:blue" href="${ctx}/page/exportToWord.do?paperId=${paper.id}" target="_blank">${paper.paperName}</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="rightct">
        <div class="rightzf">
            <div class="title">词条统计</div>
            <div class="mainct">
                <table width="180">
                    <tr>
                        <td align="right" width="70">浏览次数：</td>
                        <td>${bean.visitTimes}</td>
                    </tr>
                    <tr>
                        <td align="right" style="vertical-align: top;">更新时间：</td>
                        <td><fmt:formatDate value="${bean.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    </tr>
                    <tr>
                        <td align="right">创建者：</td>
                        <td>${bean.createUser}</td>
                    </tr>
                </table>
                </p>
            </div>
        </div>
    </div>
    <div class="rightct" >
        <div class="rightzf" style="overflow: hidden">
            <div class="title">全部知识点</div>
            <div class="mainct">
                <c:forEach var="article" items="${articles}">
                    <div style="width: 170px;white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis;overflow: hidden">
                        <a href="${ctx}/study/init.html?studyPlanId=${planId}&articleId=${article.id}">${article.title}</a>
                    </div>
                </c:forEach>
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
