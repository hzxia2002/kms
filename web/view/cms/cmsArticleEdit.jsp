<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <link href="${ctx}/skin/ueditor/default/dialogbase.css"  rel="stylesheet" type="text/css">
    <link href="${ctx}/skin/ueditor/default/css/ueditor.css">
    <script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${ctx}/js/ueditor/zh-cn.js"></script>

    <script type="text/javascript">
        $(function(){
            /**
             * 进行校核绑定
             */
            $("#extension1").juiceComboBox({
                width: 500,
                selectBoxWidth: 500,
                selectBoxHeight: 500,
                valueFieldID: 'extension1',
                valueField: 'uid',
                textField: 'text',
                treeLeafOnly:true,
                valuePrefix:"SysCodeDetail_",
                initValue:"${bean.extension1}",
                initText:"${bean.extension1}",
                tree: { url: '${ctx}/sysCodeDetail/treeData.do?mainCode=ZUIMING&codeLength=3', checkbox: true }
            });

            $.metadata.setType("attr", "validate");
            v = $('#cmsArticleEditForm').validate();
        });
    </script>
</head>
<body>
<form:form id="cmsArticleEditForm" modelAttribute="bean" name="cmsArticleEditForm" action="${ctx}/cmsArticle/save.do" method="post" >
    <div align="center">
        <input type="hidden" name="id" value="${bean.id}" />
        <table border="0" cellspacing="1" width="80%" class="inputTable">
            <tr class="inputTr">
                <td  align="right" width="10%">
                    标题:
                </td>
                <td  class="container" width="40%">
                    <input type="text" name="title"  class="table_input" value="${bean.title}" validate="{required:true}" style="width:300px;"/>&nbsp;
                </td>
                <td align="right" width="10%">
                    所在目录:
                </td>
                <td  class="container">
                    <input type="text" name="pathName" class="table_input" value="${bean.path.name}" readonly="true"/>&nbsp;
                    <input type="hidden" name="path" value="${bean.path.id}">
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    摘要:
                </td>
                <td  class="container" colspan="3">
                    <textarea name="summary" cols="120" rows="4">${bean.summary}</textarea>
                        <%--<input type="text" name="isTop" value="${bean.isTop}" />&nbsp;--%>
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    作者:
                </td>
                <td  class="container">
                    <input type="text" name="author"  class="table_input" value="${bean.author}" />&nbsp;
                        <%--<input type="text" name="isValid" value="${bean.isValid}" />&nbsp;--%>
                </td>
                <td  align="right">
                    是否推荐:
                </td>
                <td  class="container">
                    <form:checkbox path="isRecommend" />
                    是否发布:<form:checkbox path="isPublished" />
                    是否有效: <form:checkbox path="isValid" />
                    是否置顶: <form:checkbox path="isTop" />
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    关键字:
                </td>
                <td  class="container">
                    <input type="text" name="keyWord" value="${bean.keyWord}" class="table_input" style="width: 300px;" />(多个关键字以逗号分隔)&nbsp;
                </td>
                <td  align="right">
                    学习时间:
                </td>
                <td class="container" colspan="3">
                    <input type="text" name="classHour" class="table_input" style="width: 60px;" value="${bean.classHour}" validType="digits"/> 分钟
                </td>
            </tr>
            <tr class="inputTr" height="30">
                <td align="right">
                    罪名:
                </td>
                <td class="container" colspan="3">
                    <input type="text" id="extension1" name="extension1"/>
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    上传图片:
                </td>
                <td  class="container" colspan="3">
                    <input type="text" name="attachPath" id="attachPath"  class="table_input" value="${bean.attachPath}"  readonly="true" style="width: 300px;"/>&nbsp;
                    <img src="${ctx}/skin/icons/add.gif" onclick="uploadImage();">
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    上传附件:
                </td>
                <td  class="container" colspan="3">
                    <ul id="files">
                         <c:forEach items=""></c:forEach>
                    </ul>
                    <%--<input type="text" name="filePath" id="filePath"  class="table_input"  readonly="true" style="width: 300px;"/>&nbsp;--%>
                    <img src="${ctx}/skin/icons/add.gif" onclick="uploadFiles();">
                    <input type="hidden" name="docId" id="docId"  value="${bean.docId}" />
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right" style="vertical-align: top;">
                    内容:
                </td>
                <td  class="container" colspan="3">
                    <script id="editor" type="text/plain" style="width:90%;height:300px"></script>
                    <input type="hidden" id="content" name="content" value='${bean.content}'/>&nbsp;
                </td>
            </tr>

                <%--<tr class="inputTr">--%>
                <%--<td  align="right">--%>
                <%--外部链接:--%>
                <%--</td>--%>
                <%--<td  class="container">--%>
                <%--<input type="text" name="linkUrl" value="${bean.linkUrl}" />&nbsp;--%>
                <%--</td>--%>
                <%--</tr>--%>

                <%--<tr class="inputTr">--%>
                <%--<td  align="right">--%>
                <%--发布范围:--%>
                <%--</td>--%>
                <%--<td  class="container">--%>
                <%--<input type="text" name="accessRangeId" value="${bean.accessRangeId}" />&nbsp;--%>
                <%--</td>--%>
                <%--</tr>--%>

        </table>
    </div>
</form:form>
</body>

</html>

<script type="text/javascript">
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
        initialFrameWidth:860,
        initialFrameHeight:400,
        focus:true
    };



    //实例化编辑器
    var ue = UE.getEditor('editor',options);

    ue.ready(function(){
        ue.setContent($("#content").val());
    });


    function insertHtml() {
        var value = prompt('插入html代码', '');
        ue.execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function customerValidate(){
        var content = UE.getEditor('editor').getContent();
        if(!content){
            window.top.$.juiceDialog.warn('内容不能为空!');
            return false;
        }else{
            $("#content").val(content);
            return true;
        }
    }

    function uploadImage(){
        var settings = {
            height: 250,
            url: "${ctx}/fileUpload/fileUploadInit.html?id=${bean.path.id}&type=1",
            width: 500
        };
        $.extend(settings,options);
        winDialog = $.juiceDialog.open(settings);
    }

    function uploadFiles(){
        var settings = {
            height: 400,
            url: "${ctx}/fileUpload/fileUploadInit.html?id=${bean.path.id}&type=2",
            width: 500
        };
        $.extend(settings,options);
        winDialog = $.juiceDialog.open(settings);
    }

    function closeDialog(){
        winDialog.close();
    }

    function setPicPath(data){
       $("#attachPath").val(data[0].url);
    }

    function setFilePath(data,docId){
        $("#docId").val(docId);
    }
</script>