<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>

</head>
<body style="width: 100%">
<div style="height: 50px;width: 100%;position: relative;background-color: #ebf6eb">
    <div style="float: left;position: absolute">pic</div>
    <div style="float: right;position: absolute;right: 10px;top:20px;">
        <form id="queryForm">
            <input type="text" style="width: 150px;" name="queryCondition" id="queryCondition" value="${condition}"/>
            <input type="hidden"  name="catagoryId" id="catagoryId" />
            <input type="button" name="queryConfirm" id="queryConfirm" value="搜索全文" style="width: 60px;" onclick="query();">
        </form>
    </div>
</div>
<div id="cmsArticleLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">

    <div position="left"  title="文章分类" id="accordion1" style="overflow:auto;height:95%;">
        <ul id="cmsArticleTree" style="margin-top:3px;" idFieldName="id" parentIDFieldName="pid"></ul>
    </div>
    <div position="center">
        <div id="cmsArticleGrid" checkbox="false" rownumbers="true"  height="100%"   url='${ctx}/cmsArticle/queryGrid.do?keyWord=${condition}' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsArticleLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsArticleGrid").setWidth(width);
            $.jui.get("cmsArticleQuery").setWidth(width);
        }
        });

        //grid
        $("#cmsArticleGrid").juiceGrid({
//            toolbar:toolbar,
            showAddButton:false,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '标题', name: 'title', width: "35%",render:createLink,align:"left" },
                {display: '关键字', name: 'keyWord', width: "15%",align:"left" },
//				{display: '内容', name: 'content', width: 50 },

                {display: '发布时间', name: 'publishTime', width: "10%" },
                {display: '发布人', name: 'publisher', width: "10%" },
                {display: '回复', name: 'visitTimes', width: "10%" },
                {display: '查看', name: 'clickNum', width: "10%" }

//				{display: '展示图片', name: 'attachPath', width: 50 },
//				{display: '缩略图', name: 'thumbPath', width: 50 },
//				{display: '外部链接', name: 'linkUrl', width: 50 },
//				{display: '创建时间', name: 'createTime', width: 50 },
//				{display: '更新时间', name: 'updateTime', width: 50 },
//				{display: '更新人', name: 'updateUser', width: 50 },
//				{display: '创建人', name: 'createUser', width: 50 },
//				{ display: '发布范围', name: 'accessRange', width: 50 }
            ]
//        enabledEdit: true
        });

        //创建树
        //创建树
        createTree("cmsArticleTree",{ url:"${ctx}/cmsArticle/tree.do?condition=${condition}",createMenu:null,onSelect:treeNodeClick});

        $(".myTable tbody tr:odd").addClass("odd_Class");
    });

    function treeNodeClick(treeNode){
        $("#catagoryId").val(treeNode.data.id);
        search('cmsArticleGrid','queryForm');
    }

    function createLink(items){
        return "<a href='${ctx}/cmsArticle/detail.do?id="+items["id"]+"' target='_blank'>"+items["title"]+"</a>"
    }

    function query(){
        var condition = $("#queryCondition").val();
        window.location.href = "${ctx}/cmsArticle/articleQuery.do?condition="+condition;
    }

</script>

