<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsGroupLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="cmsGroupQuery" title="查询窗口"  icon="search">
            <form id="cmsGroupForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="1%" align="right" nowrap="nowrap">
                            名称：
                        </td>
                        <td width="15%" nowrap="nowrap">
                            <input type="text" value="" class="table_input" id="name" name="name" op="like" entity="t"/>
                        </td>

                        <td width="1%" align="right" nowrap="nowrap">
                            代码：
                        </td>
                        <td width="15%" nowrap="nowrap">
                            <input type="text" value="" class="table_input" id="code" name="code" op="like" entity="t"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsGroupGrid','cmsGroupForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsGroupGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsGroup/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsGroupLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsGroupGrid").setWidth(width);
            $.jui.get("cmsGroupQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsGroupQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#cmsGroupGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '名称', name: 'name', width: 250 },
                { display: '编码', name: 'code', width: 250},
                { display: '操作', name: 'op', width: 100,render:userRender}
            ]
//        enabledEdit: true
        });
        //创建树

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


    function doView(id) {
        var url = "${ctx}/cmsGroup/view.do";

        commonView(url, "cmsGroupGrid");
    }

    function doDelete(){
        commonDelete("cmsGroupGrid","${ctx}/cmsGroup/delete.do");
    }

    function doAdd(){
        var url = "${ctx}/cmsGroup/init.do";
        commonAddOrUpdate(url,"cmsGroupGrid",null,"cmsGroupEditForm",{title:"新增用户组",height:150,width:300});
    }

    function doEdit(id){
        var url = "${ctx}/cmsGroup/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsGroupGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsGroupGrid",null,"cmsGroupEditForm",{title:"编辑用户组",height:150,width:300});
        }
    }

    function userRender(item){
        return "<img src='${ctx}/skin/images/eq.png' width='24' title='用户管理' onclick='addUser("+item.id+")'/>"
    }

    function addUser(id){
        var url = "${ctx}/cmsGroup/initAddUser.do?id=" + id;
        commonAddOrUpdate(url,"cmsGroupGrid",null,"cmsGroupEditForm",{title:"编辑用户",height:530,width:600,
            buttons:[
                { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
            ]});
    }

    function f_select(){
        return $.jui.get("cmsGroupGrid").getSelectedRows();
    }

    function f_cancel(){
        return $.jui.get("cmsGroupGrid").loadData(true);
    }
</script>
