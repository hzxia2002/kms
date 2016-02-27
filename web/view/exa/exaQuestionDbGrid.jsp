<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="exaQuestionDbLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">

    <div position="center">
        <div id="exaQuestionDbQuery" title="查询窗口"  icon="search">
            <form id="exaQuestionDbForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            问卷名称：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input" id="title" name="dbName" op="like" entity="t"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('exaQuestionDbGrid','exaQuestionDbForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="exaQuestionDbGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/exaQuestionDb/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#exaQuestionDbLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("exaQuestionDbGrid").setWidth(width);
            $.jui.get("exaQuestionDbQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#exaQuestionDbQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#exaQuestionDbGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '题库名称', name: 'dbName', width: "20%" },
                {display: '状态', name: 'status', width:"10%",render:renderStatus },
                {display: '备注', name: 'remark', width: "20%" },
                {display: '创建时间', name: 'createTime', width: 80 },
                { display: '创建人', name: 'createUser', width: 80},
                {display: '操作', name: 'createTime', width: '20%',render:renderOp }
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
        var url = "${ctx}/exaQuestionDb/view.do";

        commonView(url, "exaQuestionDbGrid");
    }

    function doDelete(){
        commonDelete("exaQuestionDbGrid","${ctx}/exaQuestionDb/delete.do");
    }

    function doAdd(){
        var url = "${ctx}/exaQuestionDb/init.do";
        commonAddOrUpdate(url,"exaQuestionDbGrid",null,"exaQuestionDbEditForm",{title:"新增题库",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/exaQuestionDb/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"exaQuestionDbGrid");
        }
        if(url){
            commonAddOrUpdate(url,"exaQuestionDbGrid",null,"exaQuestionDbEditForm",{title:"编辑题库",height:530,width:600});
        }
    }

    function renderStatus(data){
        if(data.status==1){
            return "<span style='color: blue'>开放</span>";
        }else{
            return "<span style='color: red'>锁定</span>";
        }
    }

    function renderOp(item,rowIndex){
        var opStr = "<input type='button' style='padding: 1px' value='试题管理' onclick='doAddQuestion("+item.id+")'/>";
        return opStr;
    }

    function doAddQuestion(id){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/exaQuestion/init.do?dbId="+id;
        var settings = {
            url: url,
            id:juiId,
            title:"题库题目管理",
            height: $(window.top.document.body).height()-10,
            width:$(window.top.document.body).width()
        };
        window.top.$.juiceDialog.open(settings);
    }

</script>
