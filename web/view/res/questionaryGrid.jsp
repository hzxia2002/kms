<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="questionaryLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="调查表树" id="accordion1">
        <ul id="questionaryTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="questionaryQuery" title="查询窗口"  icon="search">
            <form id="questionaryForm" action="">
                <table style="width: 100%">
                    <tr>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('questionaryGrid','questionaryForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="questionaryGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/questionary/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#questionaryLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("questionaryGrid").setWidth(width);
            $.jui.get("questionaryQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#questionaryQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#questionaryGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '问卷名称', name: 'title', width: 50,hide:true },
				{display: '发起人', name: 'sponsor', width: 50,hide:true },
				{display: '类型：0问卷,1试卷', name: 'type', width: 50,hide:true },
				{display: '备注', name: 'remark', width: 50,hide:true },
				{display: '创建时间', name: 'createTime', width: 50,hide:true },
				{display: '创建人(记录帐号）', name: 'createUser', width: 50,hide:true },
				{display: '更新时间', name: 'updateTime', width: 50,hide:true },
				{ display: '更新人(记录帐号）', name: 'updateUser', width: 50,hide:true }
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
        var url = "${ctx}/questionary/view.do";

        commonView(url, "questionaryGrid");
    }

    function doDelete(){
        commonDelete("questionaryGrid","${ctx}/questionary/delete.do",refreshRootNode);
    }

    function doAdd(){        
        var url = "${ctx}/questionary/init.do";
        commonAddOrUpdate(url,"questionaryGrid",null,"questionaryGridForm",{title:"新增调查表",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/questionary/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"questionaryGrid");
        }
        if(url){
            commonAddOrUpdate(url,"questionaryGrid",null,"questionaryForm",{title:"编辑调查表",height:530,width:600});
        }
    }


</script>

