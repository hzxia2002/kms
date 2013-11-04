<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsCatalogLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="目录树" id="accordion1">
        <ul id="cmsCatalogTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="cmsCatalogQuery" title="查询窗口"  icon="search">
            <form id="cmsCatalogForm" action="">
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
                            目录名称：
                        </td>
                        <td width="25%">
                            <input type="text" value="" class="table_input" id="path" name="path" op="like" entity="t"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsCatalogGrid','cmsCatalogForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsCatalogGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsCatalog/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsCatalogLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsCatalogGrid").setWidth(width);
            $.jui.get("cmsCatalogQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsCatalogQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#cmsCatalogGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '名称', name: 'name', width: "30%"},
//				{display: '编码', name: 'code', width: "10%"},
                {display: '文件目录', name: 'path', width: "30%"},
//				{display: '显示图标', name: 'icon', width: "10%"},
//				{display: '父节点标识', name: 'parent', width: "10%"},
//				{display: '叶节点', name: 'isLeaf', width: "10%"},
//				{display: '树形层次', name: 'treeId', width: "10%"},
                {display: '是否有效', name: 'isValid', width: "20%",render:isValid}
            ]
//        enabledEdit: true
        });
        //创建树
        createTree("cmsCatalogTree",{ url:"${ctx}/cmsCatalog/tree.do",createMenu:createMenu,onSelect:treeNodeClick});

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
        search('cmsCatalogGrid','cmsCatalogForm');
    }

    function doMoveup(treeNode) {
        var tree = $.jui.get("cmsCatalogTree");
        moveUp(tree, "com.article.domain.CmsCatalog");
    }

    function doMovedown(treeNode) {
        var tree = $.jui.get("cmsCatalogTree");
        moveDown(tree, "com.article.domain.CmsCatalog");
    }

    function doView(id) {
        var url = "${ctx}/cmsCatalog/view.do";

        commonView(url, "cmsCatalogGrid");
    }

    function doDelete(){
        commonDelete("cmsCatalogGrid","${ctx}/cmsCatalog/delete.do",refreshRootNode);
    }

    function doAdd(){
        var url = "${ctx}/cmsCatalog/init.do";
        var parentId =$.jui.get("cmsCatalogTree").getSelected()?$.jui.get("cmsCatalogTree").getSelected().data.uid:"";
        if(parentId&&parentId!="root"){
            url +="?parent=" + parentId;
        }
        commonAddOrUpdate(url,"cmsCatalogGrid","${ctx}/cmsCatalog/save.do","cmsCatalogEditForm",{title:"新增目录",height:330,width:600,callback:refreshNode});
    }

    function doEdit(id){
        var url = "${ctx}/cmsCatalog/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsCatalogGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsCatalogGrid",null,"cmsCatalogEditForm",{title:"编辑目录",height:330,width:600,callback:refreshNode});
        }
    }

    function refreshNode(){
        var tree = $.jui.get("cmsCatalogTree");
        var selectNode = tree.getSelected();
        if(selectNode){
            refreshTreeNode(selectNode.data,tree);
        }else{
            refreshRootNode();
        }

    }

    function refreshPNode(){
        var tree = $.jui.get("cmsCatalogTree");
        refreshParentNode(tree);
    }

    function refreshRootNode(){
        var tree = $.jui.get("cmsCatalogTree");
        refreshRoot(tree);
    }

</script>

