<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="optionsLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="选项树" id="accordion1">
        <ul id="optionsTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="optionsQuery" title="查询窗口"  icon="search">
            <form id="optionsForm" action="">
                <table style="width: 100%">
                    <tr>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('optionsGrid','optionsForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="optionsGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/options/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#optionsLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("optionsGrid").setWidth(width);
            $.jui.get("optionsQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#optionsQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#optionsGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '问题ID', name: 'question', width: 50,hide:true },
				{display: '选项描述', name: 'content', width: 50,hide:true },
				{ display: '编号', name: 'indexNo', width: 50,hide:true }
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
        var url = "${ctx}/options/view.do";

        commonView(url, "optionsGrid");
    }

    function doDelete(){
        commonDelete("optionsGrid","${ctx}/options/delete.do",refreshRootNode);
    }

    function doAdd(){        
        var url = "${ctx}/options/init.do";
        commonAddOrUpdate(url,"optionsGrid",null,"optionsGridForm",{title:"新增选项",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/options/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"optionsGrid");
        }
        if(url){
            commonAddOrUpdate(url,"optionsGrid",null,"optionsForm",{title:"编辑选项",height:530,width:600});
        }
    }


</script>

