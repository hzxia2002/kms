<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<title>经侦辅助办案知识库</title>--%>
    <link href="${ctx}/skin/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>

    <link href="${ctx}/js/ueditor/themes/default/css/ueditor.min.css" rel="stylesheet" type="text/css" />
    <%--<link href="${ctx}/js/ueditor/themes/default/css/ueditor.css">--%>
    <style type="text/css">
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
        }
        .sectionItem span{
            *zoom:1;
            display:inline-block;
        }
        .itemTitle span{
            color: #0000ff;
            float:left;
        }


            /*.fixTop{*/
            /*position: fixed;*/
            /*top: -1px;*/
            /*}*/
    </style>
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
                <div class="one" style="overflow: hidden;text-overflow:ellipsis; width:40%;white-space:nowrap;font-size: 14px;font-weight: bold"><c:out value="${paths}"></c:out></div>
                <div class="two" id="addCollect" style="cursor: pointer;"></div>
            </div>
            <div class="main">
                <p class="text" align="center" id="title"  style="font-size: 20px;color:blue;"> ${bean.title}</p>
                <p>
                    关键字：${bean.keyWord}<br />
                </p>
            </div>
            <div id="directionContainer" style="border: dashed 1px blue;float: left;width: 50%;">
                <div style="color: #000000;font-size: 14px;font-weight: bold;text-align: center">目录:</div>
                <div>
                    ${bean.catalogue}
                </div>

            </div>
            <div class="mainmuen">
                ${bean.content}
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
                        <td align="left">创建者：</td>
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
