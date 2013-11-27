<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
    <link href="${ctx}/skin/ueditor/default/dialogbase.css"  rel="stylesheet" type="text/css">
    <link href="${ctx}/skin/ueditor/default/css/ueditor.css">
    <script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${ctx}/js/ueditor/zh-cn.js"></script>
</head>
<body style="width: 100%">
<div style="height: 50px;width: 100%;position: relative;background-color: #ebf6eb">
    <div style="float: left;position: absolute">pic</div>
    <div style="float: right;position: absolute;right: 10px;top:20px;">
        <%--<form id="queryForm">--%>
        <%--<input type="text" style="width: 150px;" name="queryCondition" id="queryCondition" value="${condition}"/>--%>
        <%--<input type="hidden"  name="catagoryId" id="catagoryId" />--%>
        <%--<input type="button" name="queryConfirm" id="queryConfirm" value="搜索全文" style="width: 60px;" onclick="query();">--%>
        <%--</form>--%>
    </div>
</div>
<div id="cmsArticleLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;" >
    <%--<div position="left"  title="文章分类" id="accordion1" style="overflow:auto;height:95%;">--%>
    <%--<ul id="cmsArticleTree" style="margin-top:3px;" idFieldName="id" parentIDFieldName="pid"></ul>--%>
    <%--</div>--%>
    <div position="center" >

        <div id="reply" style="width: 80%;margin-left: 10%;">
            <div>
                <span style="font-size: 14px;font-weight: bold">回复信息修改</span>
                <div style="float:right">
                    <span style="font-size: 14px" class="button_css">
                        <a href="javascript:void(0)" onclick="submit();">保存</a>
                    </span>
                </div>
            </div>
            <script id="editor" type="text/plain" style="width:100%;height:350px"></script>
            <div style="margin-top: 10px;">
                <span style="font-size: 14px">回复信息修改</span>
                <div style="float:right">
                    <span style="font-size: 14px" class="button_css">
                        <a href="javascript:void(0)" onclick="submit();">保存</a>
                    </span>
                </div>
            </div>
            <form id="replyForm" action="${ctx}/cmsComment/updateReply.do" method="post">
                <input type="hidden" name="content" id="content" value='${comment.content}'/>
                <input type="hidden" name="id" value="${comment.id}"/>
            </form>
        </div>
    </div>

</div>
</body>
</html>

<script>
    var UEDITOR_HOME_URL = "${ctx}";
    var options = {
        imageUrl: "${ctx}/fileUpload/uploadImage.do?id=${bean.path.id}",
        imagePath:"http://",

        scrawlUrl:UEDITOR_HOME_URL + "/cmsArticle/init.do",
        scrawlPath:"http://",

        fileUrl:"${ctx}/fileUpload/uploadImage.do?id=${bean.path.id}",
        filePath:"http://",

        catcherUrl:UEDITOR_HOME_URL + "/cmsArticle/init.do",
        catcherPath:UEDITOR_HOME_URL + "php/",

        imageManagerUrl:UEDITOR_HOME_URL + "/cmsArticle/init.do",
        imageManagerPath:"http://",

        snapscreenHost:'ueditor.baidu.com',
        snapscreenServerUrl:UEDITOR_HOME_URL + "../yunserver/yunSnapImgUp.php",
        snapscreenPath:"http://",

        wordImageUrl:UEDITOR_HOME_URL + "../yunserver/yunImageUp.php",
        wordImagePath:"http://", //

        getMovieUrl:UEDITOR_HOME_URL + "../yunserver/getMovie.php",

        lang:/^zh/.test(navigator.language || navigator.browserLanguage || navigator.userLanguage) ? 'zh-cn' : 'en',
        langPath:"${ctx}/js/ueditor/lang/",

        webAppKey:"9HrmGf2ul4mlyK8ktO2Ziayd",
//        initialFrameWidth:100%,
        initialFrameHeight:420,
        focus:false
    };



    //实例化编辑器
    var ue = UE.getEditor('editor',options);

    ue.ready(function(){
        ue.setContent($("#content").val());
    });

    function submit(){
        var content = UE.getEditor('editor').getContent();
        if(!content){
            alert('内容不能为空!');
            return false;
        }else{
            $("#content").val(content);
            $("#replyForm").submit();
        }
    }


</script>


