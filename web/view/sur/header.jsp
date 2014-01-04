<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="${ctx}/view/sur/images/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        //处理.Png格式图片周围显示成白色的Js代码
        function correctPNG() {
            for (var i = 0; i < document.images.length; i++) {
                var img = document.images[i];
                var imgName = img.src.toUpperCase();
                if (imgName.substring(imgName.length - 3, imgName.length) == "PNG") {
                    var imgID = (img.id) ? "id='" + img.id + "' " : "";
                    var imgClass = (img.className) ? "class='" + img.className + "' " : "";
                    var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' ";
                    var imgStyle = "display:inline-block;" + img.style.cssText;
                    if (img.align == "left") imgStyle = "float:left;" + imgStyle;
                    if (img.align == "right") imgStyle = "float:right;" + imgStyle;
                    if (img.parentElement.href) imgStyle = "cursor:pointer;" + imgStyle;
                    var strNewHTML = "<span " + imgID + imgClass + imgTitle
                            + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
                            + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
                            + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>";
                    img.outerHTML = strNewHTML;
                    i = i - 1;
                }
            }
        }
    </script>
    <style type="text/css">
        <!--
        .apDiv1 {
            position:absolute;
            left:915px;
            top:742px;
            width:241px;
            height:237px;
            z-index:1;
            border: 1px solid #f1f1f1;
            background-color: #fff;
            font-family: "微软雅黑";
            font-size: 14px;
            line-height: 22px;
            padding-top: 10px;
            padding-right: 10px;
            padding-bottom: 10px;
            padding-left: 10px;
        }
        -->
    </style>
</head>
<body onload="correctPNG()">
<div class="title_bg">
    <div class="clr"></div>
</div>
<div class="Header">
    <div class="title">
        <object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="1002" height="169">
            <param name="movie" value="${ctx}/view/sur/images/3_1.swf" />
            <param name="quality" value="high" />
            <param name="wmode" value="opaque" />
            <param name="swfversion" value="6.0.65.0" />
            <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
            <param name="expressinstall" value="Scripts/expressInstall.swf" />
            <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
            <!--[if !IE]>-->
            <object type="application/x-shockwave-flash" data="images/3_1.swf" width="1002" height="169">
                <!--<![endif]-->
                <param name="quality" value="high" />
                <param name="wmode" value="opaque" />
                <param name="swfversion" value="6.0.65.0" />
                <param name="expressinstall" value="${ctx}/view/sur/Scripts/expressInstall.swf" />
                <!-- 浏览器将以下替代内容显示给使用 Flash Player 6.0 和更低版本的用户。 -->
                <div>
                    <h4>此页面上的内容需要较新版本的 Adobe Flash Player。</h4>
                    <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获取 Adobe Flash Player" width="112" height="33" /></a></p>
                </div>
                <!--[if !IE]>-->
            </object>
            <!--<![endif]-->
        </object>
    </div>
    <div class="nav">
        <ul>
            <li class="n1"><a href="index.html" >首页</a></li>
            <li class="n2"><a href="main.html">通知文件</a></li>
            <li class="n3"><a href="#">重要精神</a></li>
            <li class="n4"><a href="#">创先争优</a></li>
            <li class="n5"><a href="#">党务公开</a></li>
            <li class="n6"><a href="#">组织人事</a></li>
            <li class="n7"><a href="#">意见箱</a></li>
            <li class="n8"><a href="#">意见箱</a></li>
        </ul>
    </div>
    <div class="clr"></div>
</div>
