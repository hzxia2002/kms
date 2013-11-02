<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="代码分类树" id="accordion1" style="height: 94%;width: 100%;">
        <ul id="codeTree" style="margin-top:3px;margin-bottom: 20px"></ul>
    </div>
    <div position="center">
        <div id="query" title="查询窗口"  icon="search">
            <form id="sysCodeForm" action="">
                <input type="hidden" id="sysCode_id" name="sysCode_id" value="" op="eq" entity="t"/>
                <input type="hidden" id="parent_id" name="parent_id" value="" op="eq" entity="t"/>
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="8%" nowrap="nowrap" align="left">
                            代码:
                            <input type="text" id="code" name="code" value="" class="table_input" op="like" entity="t" isCapital="false" dtype="String"/>
                        </td>
                        <td width="8%" nowrap="nowrap" align="left">
                            名称:
                            <input type="text" id="name" name="name" value="" class="table_input" op="like" entity="t" isCapital="false" dtype="String"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" class="btn_Search" onclick="javascript:search('sysCodeGrid','sysCodeForm');"/>&nbsp;
                            <%--<input type="button" value="新增" class="btn_Add" onClick="javascript:doAdd();"/>--%>
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="sysCodeGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysCodeDetail/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysCodeGrid").setWidth(width);
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
        $("#sysCodeGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '代码分类', name: 'sysCode.name',width:"15%"},
                { display: '编码', name: 'code',width:"15%" },
                { display: '名称', name: 'name',width:"15%"  },
                { display: '叶节点', name: 'isLeaf',width:"10%" ,render:booleanFormatter},
                { display: '系统定义', name: 'isReserved',width:"10%",render:booleanFormatter},
                { display: '特殊标记', name: 'tag',width:"10%",render:booleanFormatter},
                { display: '是否有效', name: 'isValid',width:"10%",render:booleanFormatter },
                { display: '备注', name: 'description',width:"20%" }
            ]
//        enabledEdit: true
        });
        createTree("codeTree",{ url:"${ctx}/sysCode/tree.do",createMenu:createMenu,onSelect:treeNodeClick});

    });

    function treeNodeClick(treeNode){
        var data = treeNode.data;

        if(data["id"] == 'root') {
            $("#sysCode_id").val("");
            $("#parent_id").val("");
        } else if(data["type"] == 'SysCode'){
            $("#sysCode_id").val(treeNode.data.uid);
            $("#parent_id").val("");
        } else if(data["type"] == 'SysCodeDetail'){
            $("#sysCode_id").val("");
            $("#parent_id").val(data["uid"]);
        }

        search('sysCodeGrid','sysCodeForm')
    }

    //树的右键菜单
    function createMenu(node){
        var menu;
        var treeNode = node.data;
        if(treeNode["uid"] == 'root') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加代码集', click:function(item){doAddCode()}},
                        { line: true },
                        { text: '刷新', click:function(item) {refreshNode()} }
                    ]
            });
        } else if (treeNode["type"] == 'SysCode') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加代码项', icon:'add', click:function(item){doAdd(treeNode.uid)}},
                        { line: true },
                        { text: '修改代码集', click:function(item){doCodeEdit(treeNode.uid)}},
                        { text: '删除代码集', click:function(item){doCodeDelete(treeNode.uid)}},
//                        { text: '查看代码集', click:function(item){doCodeView(treeNode.uid)}},
                        { line: true },
                        { text: '上移', click:function(item){doMoveup()}},
                        { text: '下移', click:function(item){doMovedown()}},
                        { line: true },
                        { text: '刷新', click:function(item) {refreshNode()} }
                    ]
            });
        } else if (treeNode["type"] == 'SysCodeDetail') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加子代码项', icon:'add', click:function(item){doDetailAdd(treeNode.uid)}},
                        { line: true },
                        { text: '删除代码项', click:function(item){doDetailDelete(treeNode.uid)}},
                        { line: true },
                        { text: '上移', click:function(item){doDetailMoveup()}},
                        { text: '下移', click:function(item){doDetailMovedown()}},
                        { line: true },
                        { text: '刷新', click:function(item) {refreshNode()} }
                    ]
            });
        }
        return menu;
    }

    function doView(id) {
        openWindow("sysCodeWindow", "查看", "${ctx}/sysCodeDetail/view.do?id=" + id, true, "sysCodeGrid");
    }

    function doDelete(){
        commonDelete("sysCodeGrid","${ctx}/sysCodeDetail/delete.do");
    }

    function doAdd(){
        var sysCodeId = $("#sysCode_id").val();

        if(sysCodeId == null || sysCodeId == "" || sysCodeId == 0) {
            $.juiceDialog.warn("请先选择代码项，再进行代码明细添加!");
            return;
        }

        commonAddOrUpdate("${ctx}/sysCodeDetail/initAdd.do?sysCode=" + sysCodeId,"sysCodeGrid",null,"sysCodeDetailEditForm",{title:"新增代码",height:360,width:500});
    }

    function doDetailAdd(uid){
        commonAddOrUpdate("${ctx}/sysCodeDetail/initAdd.do?parent=" + uid,"sysCodeGrid",null,"sysCodeDetailEditForm",{title:"新增代码",height:360,width:500});
    }

    function doEdit(){
        var url = "${ctx}/sysCodeDetail/init.do";
        url = initUrl(url,"sysCodeGrid");
        if(url){
            commonAddOrUpdate(url,"sysCodeGrid",null,"sysCodeDetailEditForm", {height:360, width:500});
        }
    }
    /**
     * 增加代码集
     */
    function doAddCode() {
        commonAddOrUpdate("${ctx}/sysCode/initAdd.do", "sysCodeGrid",null, "sysCodeEditForm",  {title:"新增代码集",height:380,width:500,callback:refreshNode});
    }

    function doCodeEdit(id) {
        commonAddOrUpdate("${ctx}/sysCode/init.do?id="+id, "sysCodeGrid",null, "sysCodeEditForm",  {title:"编辑代码集",height:380,width:500,callback:refreshPNode});
    }

    function doCodeDelete(id){
        $.juiceDialog.confirm("您确定要删除该代码集?",function(yes){
            if(yes){
                submitFormData(null, "${ctx}/sysCode/delete.do?id=" + id,null,"sysCodeGrid",function(){
                    refreshPNode();
                    $.juiceDialog.success('数据删除成功');
                });
            }
        });
    }

    function refreshNode(){
        var tree = $.jui.get("codeTree");
        refreshCurrentNode(tree);
    }

    function refreshPNode(){
        var tree = $.jui.get("codeTree");
        refreshParentNode(tree);
    }

    function doMoveup() {
        var tree = $.jui.get("codeTree");
        moveUp(tree, "com.comet.system.domain.SysCode");
    }

    function doMovedown() {
        var tree = $.jui.get("codeTree");
        moveDown(tree, "com.comet.system.domain.SysCode");
    }

    function doDetailMoveup() {
        var tree = $.jui.get("codeTree");
        moveUp(tree, "com.comet.system.domain.SysCodeDetail");
    }

    function doDetailMovedown() {
        var tree = $.jui.get("codeTree");
        moveDown(tree, "com.comet.system.domain.SysCodeDetail");
    }

    function doDetailDelete(id) {
        $.juiceDialog.confirm("您确定要删除该代码项?",function(yes){
            if(yes){
                submitFormData(null, "${ctx}/sysCodeDetail/delete.do?id=" + id,null,"sysCodeGrid",function(){
                    refreshPNode();
                    $.juiceDialog.success('数据删除成功');
                });
            }
        });
    }
</script>