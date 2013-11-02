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
    <style type="text/css">
        .tab_product_list { margin:0 auto; padding:0; width:80%;}
        .tab_product_list tr th{ text-align: center;font-size: 14px;font-weight: bold;text-align: center}
        .tab_product_list tr th,td { line-height:24px;  padding:0;color:#303030; border-right:1px solid #bbbbbb;border-bottom:1px solid #000000;}
        .tab_product_list tr th a,
        .tab_product_list tr th a:visited { color:#202020;}
        .tab_product_list tr th a:hover { color:#5a83a1; text-decoration:none;}

        .tab_product_list tr th.current a.desc:hover { color:#4c6a0a; background-position:right -24px;}


        .tab_product_list tr td { line-height:20px; border-bottom:1px solid #000000; color:#000000;}
        .tab_product_list tr.odd { background-color:#fbffff;}
        .tab_product_list tr.even { background-color:#effdfe;}
        .tab_product_list tr td img { border:0;}
    </style>
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
    <%--<div position="left"  title="文章分类" id="accordion1">--%>
    <%--<ul id="cmsArticleTree" style="margin-top:3px;" idFieldName="id" parentIDFieldName="pid"></ul>--%>
    <%--</div>--%>
    <div position="center" >
        <table width="80%" class="tab_product_list" style="border:1px solid #000000;display: table;margin-left: 10%;margin-right: 10%;" cellspacing="0" cellpadding="2">
            <thead>
            <tr style="background-color: rgb(235,246,235);height: 30px;">
                <th style="text-align: center;font-size: 14px;font-weight: bold;text-align: center">
                    作者&nbsp;
                </th>
                <th style="text-align: center;font-size: 14px;font-weight: bold;text-align: center">
                    主题:${bean.title}
                </th>
            </tr>
            </thead>
            <tbody style="background: rgba(220,220,220,0.55)">
            <c:if test="${bean!=null}">
                <tr>
                    <td width="100px">
                            ${bean.createUser}
                    </td>
                    <td>
                        <div>
                            发表时间: ${bean.publishTime}
                            <div style="float: right">
                                <span onclick="alert(12)" class="button_css">只看楼主</span> &nbsp;
                                <span onclick="alert(12)" class="button_css">查看所有</span>  &nbsp;
                                <c:if test="${isCreateUser}">
                                    <span class="button_css">
                                        <a href="javascript:void(0)" onclick="editArticle('${bean.id}')"> 编辑</a>
                                    </span>
                                    <span class="button_css">
                                        <a href="javascript:void(0)" onclick="deleteArticle('${bean.id}')"> 删除</a>
                                    </span>  &nbsp;
                                </c:if>
                                <span class="button_css"><a href="#reply">回复</a></span>   &nbsp;
                            </div>
                            <hr>
                        </div>
                            ${bean.content}
                    </td>
                </tr>
            </c:if>
            <c:forEach items="${page.rows}" var="comment">
                <tr>
                    <td width="100px">
                            ${comment.replyer.loginName}
                    </td>
                    <td>
                        <div>
                            发表时间: ${comment.replyTime}
                            <div style="float: right">
                                <span onclick="alert(12)" class="button_css">只看楼主</span> &nbsp;
                                <span onclick="alert(12)" class="button_css">查看所有</span>  &nbsp;
                                <c:if test="${loginName==comment.replyer.loginName||isCreateUser}">
                                    <span class="button_css"><a href="javascript:void(0)" onclick="editReply('${comment.id}')"> 编辑</a></span>  &nbsp;
                                    <span class="button_css"><a href="javascript:void(0)" onclick="deleteReply('${comment.id}')"> 删除</a></span>  &nbsp;
                                </c:if>
                                <span class="button_css"><a href="#reply">回复</a></span>   &nbsp;
                            </div>
                            <hr>
                        </div>
                            ${comment.content}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div id="page" style="width: 80%;margin-left: 10%;margin-top: 5px;">
            <div style="float: right">
                <c:forEach var="pageNo" begin="1" end="${(page.total)}" step="1">
                    <a href="${ctx}/cmsArticle/detail.do?id=${id}&pageNo=${pageNo}" style="padding-left: 10px;padding-right: 10px;border: 1px solid #dcdcdc">${pageNo}</a>
                </c:forEach>
            </div>
        </div>
        <br>

        <div id="reply" style="width: 80%;margin-left: 10%;">
            <div>
                <span style="font-size: 14px">发表回复</span>
                <div style="float:right">
                    <span style="font-size: 14px"><a href="javascript:void(0)" onclick="submit();">回复</a></span>
                </div>
            </div>
            <script id="editor" type="text/plain" style="width:100%;height:350px"></script>
            <form id="replyForm" action="${ctx}/cmsArticle/replySave.do" method="post">
                <input type="hidden" name="content" id="content">
                <input type="hidden" name="id" value="${id}">
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

    function deleteReply(id){
        if(confirm("确认要删除此条回复")){
            window.location.href = "${ctx}/cmsArticle/deleteReply.do?id="+id;
        }
    }

    function deleteArticle(id){
        if(confirm("确认要删除改文章")){
            window.location.href = "${ctx}/cmsArticle/deleteArticle.do?id="+id;
        }
    }
    function editReply(id){
        window.location.href = "${ctx}/cmsComment/editReply.do?id="+id;
    }

    function editArticle(id){
        window.location.href = "${ctx}/cmsArticle/editArticle.do?id="+id;
    }


</script>


