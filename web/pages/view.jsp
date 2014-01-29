<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  dir="ltr" lang="zh-CN" xml:lang="zh-CN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<title>经侦辅助办案知识库</title>--%>
    <link href="${ctx}/skin/css/style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>

    <script type="text/javascript" src="${ctx}/pages/view.js"></script>

    <style type="text/css">
        .submitCss{background: url("../skin/images/tj.jpg") no-repeat;}
    </style>
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

    <script>
        $(function(){
             $(".itemTitle span").css({
                 "color":"#136ec2" ,
                 "float":"left" ,
                 "margin-bottom":"5px" ,
                 "font-size":"14px" ,
                 "font-family":"微软雅黑,宋体,黑体",
                 "font-weight":500,
                 "line-height":"22px"
             });

            $("h1 span").css({
                "color":"#000" ,
                "margin-bottom":"5px" ,
                "font-size":"24px" ,
                "font-family":"微软雅黑,宋体,黑体,Verdana",
                "line-height":"22px"
            });

            $("h1").css({
                "color":"#000" ,
                "margin-bottom":"5px" ,
                "font-size":"24px" ,
                "font-family":"微软雅黑,宋体,黑体,Verdana",
                "line-height":"22px",
                "font-weight":"500"
            });
        });

        </script>

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
                <div class="one" style="overflow: hidden;text-overflow:ellipsis; width:70%;white-space:nowrap;font-size: 14px;font-weight: bold"><c:out value="${paths}"></c:out></div>
                <div class="two" style="cursor: pointer;vertical-align: top;padding-top: 2px;width: 180px;">
                    <table width="100%">
                        <tr>
                            <td>
                                <img src="<c:url value="/skin/images/favorite.jpg"/>" width="24" height="24">
                            </td>
                            <td style="vertical-align: middle;" nowrap="nowrap">
                                <a href="javascript:void(0);" id="addCollect">收藏本页</a>
                            </td>
                            <td>
                                <img src="<c:url value="/skin/icons/print.png"/>" width="24" height="24">
                            </td>
                            <td style="vertical-align: middle;"  nowrap="nowrap">
                                <a href="javascript:void(0);" id="print">打印预览</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="main">
                <input type="hidden" id="articleId" value="${bean.id}">
                <h2 class="title1" id="title" style="text-align: left;overflow: hidden">
                    <div >${bean.title}</div>
                </h2>
                <c:if test="${not empty bean.keyWord}">
                    <div style="clear: both">
                        关键字：${bean.keyWord}
                    </div>
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



            <c:if test="${not empty docAttachmentses}">
                <div>
                    <div>
                        <table width="100%">
                            <tr>
                                <td width="35"><img src="<c:url value="/skin/images/attaches.jpg" />" height="35" width="35"></td>
                                <td width="60"><b>附件：</b></td>
                                <td>
                                    <ul style="line-height: 35px; vertical-align: bottom;">
                                        <c:forEach items="${docAttachmentses}" var="attachment">
                                            <a href='${ctx}${attachment.filePath}' title="点击下载" target='_blank'>${attachment.orginName}</a>&nbsp;
                                        </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${type==2||type==3}">
            <div class="leftyj">
                <c:if test="${type==2}">
                    <div class="title">
                        <div class="one">修订建议：</div>
                        <input type="hidden" name="commentType" value="1">
                    </div>
                </c:if>
                <c:if test="${type==3}">
                    <div class="title">
                        <div class="one"><b>最新点评：</b></div>
                        <input type="hidden" name="commentType" value="2">
                    </div>
                </c:if>
                <div id="comments">

                </div>
                <div class="main">
                    <c:if test="${type==2}">
                        提交修订建议：
                    </c:if>
                    <c:if test="${type==3}">
                        发表评论：
                    </c:if>
                    <textarea rows="6" cols="70" style="width: 778px;height: 175px;" id="comment"></textarea>
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
            <div   style="width: 170px;font-size: 14px;color: #444444;padding: 8px; line-height: 25px;display: inline">
                <table width="100%">
                    <tr>
                        <td align="right" width="70px">浏览次数：</td>
                        <td>${bean.visitTimes}</td>
                    </tr>
                    <tr>
                        <td align="right" style="vertical-align: top;">更新时间：</td>
                        <td><fmt:formatDate value="${bean.updateTime}" type="both" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    </tr>
                    <tr>
                        <td align="right">创建者：</td>
                        <td>${bean.createUser}</td>
                    </tr>
                </table>
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

