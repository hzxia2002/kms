<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  dir="ltr" lang="zh-CN" xml:lang="zh-CN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<title>经侦辅助办案知识库</title>--%>
    <link href="${ctx}/skin/css/style.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>

    <style type="text/css">
        .submitCss{background: url("../skin/images/tj.jpg") no-repeat;}
    </style>
    <style>
        #directionContainer ul{
            margin:0px;
            padding: 0px 0px 0px 20px;
        }
        .main{
            width:840px;
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

        function doPrint(){
            document.getElementById("printTitle").style.display="none";
            window.print();
            document.getElementById("printTitle").style.display="block";
        }

    </script>


</head>

<body style="overflow-x: hidden">
<div class="ContentHome" style="width: 864px;overflow: hidden;overflow-x:hidden;margin-left: 1px; ">
    <div class="leftzfwk">
        <div class="leftzf">
            <div class="title" id="printTitle">
                <div class="one" style="overflow: hidden;text-overflow:ellipsis; width:70%;white-space:nowrap;font-size: 14px;font-weight: bold"><c:out value="${paths}"></c:out></div>
                <div class="two" style="cursor: pointer;vertical-align: top;padding-top: 2px;width: 60px;">
                    <table width="100%">
                        <tr>
                            <td>
                                <img src="<c:url value="/skin/icons/print.png"/>" width="16" height="16">
                            </td>
                            <td style="vertical-align: middle;font-size: 14px"  nowrap="nowrap">
                                <a href="javascript:void(0);" id="print" onclick="doPrint();">打印</a>
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
        </div>

        <div class="clr"></div>
        <div class="two"></div>
        <div class="clr"></div>
    </div>
</div>
</body>
</html>

