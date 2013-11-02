<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="部门树" id="accordion1">
        <ul id="deptTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="query" title="查询窗口"  icon="search">
            <form id="sysDeptForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="10%" align="right">
                            单位代码：
                        </td>
                        <td width="25%">
                            <input type="text" value="" class="table_input" id="code" name="code" op="like" entity="t"/>
                        </td>

                        <td width="10%" align="right">
                            单位名称：
                        </td>
                        <td width="25%">
                            <input type="text" value="" class="table_input" id="name" name="name" op="like" entity="t" />
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('maingrid2','sysDeptForm');"/>&nbsp;
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="maingrid2" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysDept/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("maingrid2").setWidth(width);
            $.jui.get("query").setWidth(width);
        }
        });
        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#maingrid2").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50, type: 'int',hide:true },
                { display: '编码', name: 'code',width:"10%"},
                { display: '名称', name: 'name',width:"20%" },
                { display: '简称', name: 'shortName',width:"10%" },
                { display: '机构代码证', name: 'cardNo',width:"10%" },
                { display: '注册地', name: 'cityName',width:"15%" },
                { display: '省市', name: 'provinceName',width:"10%" },
                { display: '注册地址', name: 'address',width:"15%" },
                { display: '有效', name: 'isValid',render:isValid }
            ]
//        enabledEdit: true
        });
        //创建树
        createTree("deptTree",{ url:"${ctx}/sysDept/tree.do",createMenu:createMenu,onSelect:treeNodeClick});
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
        search('maingrid2','sysDeptForm');
    }

    function doMoveup(treeNode) {
        var tree = $.jui.get("deptTree");
        moveUp(tree, "com.comet.system.domain.SysDept");
    }

    function doMovedown(treeNode) {
        var tree = $.jui.get("deptTree");
        moveDown(tree, "com.comet.system.domain.SysDept");
    }

    function doView(id) {
        openWindow("sysDeptWindow", "查看", "${ctx}/sysDept/view.do?id=" + id, true, "sysDeptGrid");
    }

    function doDelete(){
        commonDelete("maingrid2","${ctx}/sysDept/delete.do",refreshRootNode);
    }

    function doAdd(){
        var parentId =$.jui.get("deptTree").getSelected()?$.jui.get("deptTree").getSelected().data.uid:"";
        var url = "${ctx}/sysDept/initAdd.do";
        if(parentId&&parentId!="root"){
            url +="?parent=" + parentId;
        }
        commonAddOrUpdate(url,"maingrid2",null,"sysDeptEditForm",{title:"新增单位信息",height:530,width:600,callback:refreshNode});
    }

    function doEdit(id){
        var url = "${ctx}/sysDept/initAddDept.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"maingrid2");
        }
        if(url){
            commonAddOrUpdate(url,"maingrid2",null,"sysDeptEditForm",{title:"新增单位信息",height:530,width:600,callback:refreshNode});
        }
    }

    function refreshNode(){
        var tree = $.jui.get("deptTree");
        var selectNode = tree.getSelected();
        if(selectNode){
            refreshTreeNode(selectNode.data,tree);
        }else{
            refreshRootNode();
        }

    }

    function refreshPNode(){
        var tree = $.jui.get("deptTree");
        refreshParentNode(tree);
    }

    function refreshRootNode(){
        var tree = $.jui.get("deptTree");
        refreshRoot(tree);
    }

</script>