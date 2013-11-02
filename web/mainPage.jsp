<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="./common/header.jsp" %>
    <script type="text/javascript" src="mainPage.js"></script>
    <style type="text/css">
        body{
            font-size: 12px;
            font-family: 宋体;
            color: #000000;
        }
        .navBar{
            float: left;
            height: 18px;
            top: 48px;
            width: 80%;
            /*margin-left:10%;*/
            margin-left: 10%;
            margin-right: 10%;
            padding-top: 4px;
            position: absolute;
            background: url("skin/icons/datagrid_title_bg.png");
        }
        .nav{
            display:block;
            float: left;
            font-family: 宋体;
            font-size: 14px;
            font-weight: bold;
            text-decoration:dashed;
            text-align: center;
            padding-left: 20px;
            padding-right: 20px;
            vertical-align: middle;
        }
        .nav a:visited{
            color: blue;
            text-decoration: none;
        }
        .nav a:link{
            color: blue;
            text-decoration:none;
            cursor: pointer;
        }
        .hotContentDiv{
            width: 60%;
            height: 300px;
            border: 1px solid #dcdcdc;
            float: left;
            margin-left: 1px;
            text-align: left;
        }
        .newContentDiv{
            width: 39%;
            height: 300px;
            border: 1px solid #dcdcdc;
            float: right;
            margin-right: 1px;
            text-align: left;
        }
        .recommendContentDiv{
            width: 60%;
            height: 200px;
            border: 1px solid #dcdcdc;
            float: left;
            margin-left: 1px;
            text-align: left;
        }
        .friendLinkDiv{
            width: 39%;
            height: 200px;
            border: 1px solid #dcdcdc;
            float: right;
            margin-right: 1px;
            text-align: left;
        }
        .titleWrap{
            background: url("skin/icons/datagrid_title_bg.png") repeat-x;
            font-family: 宋体;
            height: 20px;
            padding-top: 4px;
            font-size: 14px;
            font-weight: bold;
            padding-left: 20px;
        }

        .cotentWrap{
            list-style: none;
            padding-left: 0px;
        }

        .cotentWrap li{
            padding-left: 20px;
            height: 24px;
            font-size: 13px;
            background: url("skin/icons/expand.jpg") no-repeat;
        }
    </style>
</head>
<body style="width: 100%;overflow-x: hidden" >
<div align="center" style="width: 100%;background-color: #000088;height: 600px;">
    <div style="width: 1024px;" >
        <div style="width: 100%;height: 70px;position: relative;background-color: #508fdb">
            <div style="float: left;">
                <img src="" >
            </div>
            <div class="navBar">
                <span class="nav"> <a href="#">首  页</a></span>
                <span class="nav"><a href="#">办案程序库</a></span>
                <span class="nav"><a href="#">知识点库</a></span>
                <span class="nav"><a href="#">案例课程库</a></span>
                <span class="nav"><a href="#">系  统</a></span>
            </div>
        </div>
        <div id="content" style="background: #ffffff;overflow: hidden">
            <div class="hotContentDiv">
                <div>
                    <div class="titleWrap">热门主题</div>
                    <ul  class="cotentWrap">
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                    </ul>
                </div>
            </div>
            <div class="newContentDiv">
                <div>
                    <%--<div style="font-weight: bold;text-decoration-style: dashed;font-style: oblique;color: red">(new!)</div>--%>
                    <div class="titleWrap">最新文章</div>
                    <ul class="cotentWrap">
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                    </ul>
                </div>
            </div>
            <div style="height: 8px;clear: both;"> </div>

            <div class="recommendContentDiv">
                <div>
                    <div class="titleWrap">推荐主题</div>
                    <ul class="cotentWrap">
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                    </ul>
                </div>
            </div>
            <div class="friendLinkDiv">
                <div>
                    <div class="titleWrap">
                        <span>友情链接</span>
                    </div>
                    <ul class="cotentWrap">
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                        <li>内容:中国人历史地理焚枯食淡六点四十</li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>