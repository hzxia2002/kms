<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsCollectCatagoryLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="收藏夹树" id="accordion1" style="overflow:auto;height:95%;">
        <ul id="cmsCollectCatagoryTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="cmsCollectCatagoryQuery" title="查询窗口"  icon="search">
            <form id="cmsCollectCatagoryForm" action="">
                <input type="hidden" value="" id="treeId" name="treeId" op="leftLike" entity="t" />
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            名称：
                        </td>
                        <td width="25%">
                            <input type="text" value="" class="table_input" id="name" name="name" op="like" entity="t"/>
                        </td>

                        <td width="10%" align="right">
                            代码：
                        </td>
                        <td width="25%">
                            <input type="text" value="" class="table_input" id="code" name="code" op="like" entity="t"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsCollectCatagoryGrid','cmsCollectCatagoryForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsCollectCatagoryGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsCollectCatagory/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsCollectCatagoryLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsCollectCatagoryGrid").setWidth(width);
            $.jui.get("cmsCollectCatagoryQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsCollectCatagoryQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#cmsCollectCatagoryGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '名称', name: 'name', width: 250},
                {display: '代码', name: 'code', width: 250}
//                {display: '用户ID', name: 'user', width: 50}
            ]
//        enabledEdit: true
        });
        //创建树
        createTree("cmsCollectCatagoryTree",{ url:"${ctx}/cmsCollectCatagory/tree.do",createMenu:createMenu,onSelect:treeNodeClick});

    });
    //树的右键菜单
    function createMenu(treeNode){
        var menu;
        if(treeNode.data.uid == 'root') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
//                        { text: '修改', click:function(item){doEdit(treeNode.uid)}},
//                        { text: '查看', icon:'add', click:function(item){doView(treeNode.uid)}}
                { line: true },
                { text: '刷新', click:function(item){refreshNode();}}
            ]
            });
        } else {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
                        { text: '修改', click:function(item){doEdit(treeNode.data.uid)}},
//                        { text: '查看', icon:'view', click:function(item){doView(treeNode.data.uid)}},
                { line: true },
                { text: '上移', click:function(item){doMoveup(treeNode)}},
                { text: '下移', click:function(item){doMovedown(treeNode)}},
                { line: true },
                { text: '刷新', click:function(item){refreshNode();}}
            ]
            });
        }
        return menu;
    }

    function treeNodeClick(treeNode){
        $("#treeId").val(treeNode.data.treeId);
        search('cmsCollectCatagoryGrid','cmsCollectCatagoryForm');
    }

    function doMoveup(treeNode) {
        var tree = $.jui.get("cmsCollectCatagoryTree");
        moveUp(tree, "com.article.domain.CmsCollectCatagory");
    }

    function doMovedown(treeNode) {
        var tree = $.jui.get("cmsCollectCatagoryTree");
        moveDown(tree, "com.article.domain.CmsCollectCatagory");
    }

    function doView(id) {
        var url = "${ctx}/cmsCollectCatagory/view.do";

        commonView(url, "cmsCollectCatagoryGrid");
    }

    function doDelete(){
        commonDelete("cmsCollectCatagoryGrid","${ctx}/cmsCollectCatagory/delete.do",refreshRootNode);
    }

    function doAdd(){
        var url = "${ctx}/cmsCollectCatagory/init.do";
        var parentId =$.jui.get("cmsCollectCatagoryTree").getSelected()?$.jui.get("cmsCollectCatagoryTree").getSelected().data.uid:"";
        if(parentId&&parentId!="root"){
            url +="?parent=" + parentId;
        }
        commonAddOrUpdate(url,"cmsCollectCatagoryGrid",null,"cmsCollectCatagoryEditForm",{title:"新增收藏夹",height:250,width:600,callback:refreshNode});
    }

    function doEdit(id){
        var url = "${ctx}/cmsCollectCatagory/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsCollectCatagoryGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsCollectCatagoryGrid",null,"cmsCollectCatagoryEditForm",{title:"编辑收藏夹",height:250,width:600,callback:refreshNode});
        }
    }

    function refreshNode(){
        var tree = $.jui.get("cmsCollectCatagoryTree");
        var selectNode = tree.getSelected();
        if(selectNode){
            refreshTreeNode(selectNode.data,tree);
        }else{
            refreshRootNode();
        }

    }

    function refreshPNode(){
        var tree = $.jui.get("cmsCollectCatagoryTree");
        refreshParentNode(tree);
    }

    function refreshRootNode(){
        var tree = $.jui.get("cmsCollectCatagoryTree");
        refreshRoot(tree);
    }

</script>

