<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsArticleLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="目录树" id="accordion1">
        <ul id="cmsArticleTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="cmsArticleQuery" title="查询窗口"  icon="search">
            <form id="cmsArticleForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            标题：
                        </td>
                        <td width="20%">
                            <input type="hidden" value="" id="treeId" name="treeId" op="leftLike" entity="t.path" />
                            <input type="text" value="" class="table_input" id="title" name="title" op="like" entity="t"/>
                        </td>

                        <td width="10%" align="right">
                            关键字：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input" id="keyWord" name="keyWord" op="like" entity="t"/>
                        </td>
                        <td width="10%" align="right">
                            是否已发布：
                        </td>
                        <td width="10%">
                            <select type="text" value="" style="width:80px;" id="isPublished" name="isPublished" op="eq" entity="t">
                                <option value="">全部</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsArticleGrid','cmsArticleForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsArticleGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsArticle/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
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
        //查询框初始化
        $("#cmsArticleQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '增加', click: doAdd, icon: 'add' },
                { line: true },
                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#cmsArticleGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '文件目录', name: 'path.name', width: "10%" },
                {display: '标题', name: 'title', width: "25%",render:preview},
                {display: '关键字', name: 'keyWord', width: "15%" },
                {display: '关键字', name: 'keyWord', width: "15%" },
                {display: '是否有效', name: 'isValid', width: "8%",render:isValid },
                {display: '是否推荐', name: 'isTop', width: "8%",render:isValid },
//				{display: '内容', name: 'content', width: 50 },

                {display: '是否发布', name: 'isPublished', width: "8%",render:isValid },
                {display: '发布时间', name: 'publishDate', width: "14%" },
                {display: '发布人', name: 'publisher.loginName', width: "10%" },
                {display: '访问次数', name: 'visitTimes', width: "10%",render:visitCount },
                {display: '收藏次数', name: 'clickNum', width: "10%" }

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
        createTree("cmsArticleTree",{ url:"${ctx}/cmsCatalog/tree.do",createMenu:createMenu,onSelect:treeNodeClick});
    });
    //树的右键菜单
    function createMenu(treeNode){
        var menu;
        if(treeNode.data.uid != 'root') {

            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}}
                    ]
            });
        }
        return menu;
    }


    function doView(id) {
        var url = "${ctx}/cmsArticle/view.do";

        commonView(url, "cmsArticleGrid");
    }

    function doDelete(){
        commonDelete("cmsArticleGrid","${ctx}/cmsArticle/delete.do");
    }

    function doAdd(){
        var tree = $.jui.get("cmsArticleTree");
        var node = tree.getSelected();
        if(!node||node.id=="root"){
            alert("请选择文章分类");
            return;
        }
        var url = "${ctx}/cmsArticle/init.do?path="+node.data.uid;
        commonAddOrUpdate(url,"cmsArticleGrid",null,"cmsArticleEditForm",{title:"新增文章",height:$(window.top.document.body).height()-20,width:$(window.top.document.body).width()});
    }

    function doEdit(id){
        var url = "${ctx}/cmsArticle/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsArticleGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsArticleGrid",null,"cmsArticleEditForm",{title:"编辑文章",height:$(window.top.document.body).height()-20,width:$(window.top.document.body).width()});
        }
    }

    function treeNodeClick(treeNode){
        $("#treeId").val(treeNode.data.treeId);
        search('cmsArticleGrid','cmsArticleForm');
    }

    function visitCount(item,index,value){
        return value!=null?value + "":"0";
    }

    function preview(item, index, value) {
        return "<a href='${ctx}/page/preView.do?id=" + item["id"] + "' title='点击预览' target='_blank'>" + item["title"] + "</a>";
    }
</script>

