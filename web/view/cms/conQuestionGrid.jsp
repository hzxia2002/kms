<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="conQuestionLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="conQuestionQuery" title="查询窗口"  icon="search">
            <form id="conQuestionForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="4%" align="right" nowrap="nowrap">
                            主题：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input" id="name" name="title" op="like" entity="t"/>
                        </td>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('conQuestionGrid','conQuestionForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="conQuestionGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/conQuestion/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#conQuestionLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("conQuestionGrid").setWidth(width);
            $.jui.get("conQuestionQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#conQuestionQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#conQuestionGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '主题', name: 'title', width: "15%" },
				{display: '内容', name: 'title', width: "40%" },
				{display: '提问时间', name: 'publishTime', width: "10%" },
				{display: '回复时间', name: 'responseTime', width: "10%" },
				{display: '是否置顶', name: 'isTop', width: "5%",render:booleanFormatter },
				{display: '操作', name: 'op', width: "15%",render:renderOp }
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
        var url = "${ctx}/conQuestion/view.do";

        commonView(url, "conQuestionGrid");
    }

    function doDelete(){
        commonDelete("conQuestionGrid","${ctx}/conQuestion/delete.do");
    }

    function doReplay(id){
        var url = "${ctx}/conQuestion/doReplay.do?id="+id;
        if(url){
            commonAddOrUpdate(url,"conQuestionGrid",null,"conQuestionEditForm",{title:"回复",height:400,width:600});
        }
    }

    function renderOp(item,rowIndex){
        var opStr = "<input type='button' style='padding: 1px' value='查看' onclick='doView("+item.id+")'/>&nbsp;&nbsp;";
        if(item.isTop){
            opStr += "<input type='button' style='padding: 1px' value='取消置顶' onclick='cancelTop("+item.id+")'/>&nbsp;&nbsp;";
        }else{
            opStr += "<input type='button' style='padding: 1px' value='置顶' onclick='doTop("+item.id+")'/>&nbsp;&nbsp;";
        }
         opStr += "<input type='button' style='padding: 1px' value='回复管理' onclick='doReplay("+item.id+")'/>";
        return opStr;
    }

    function doView(id){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/conQuestion/conView.do?conId="+id+"&dialogJuiId="+juiId;
        var settings = {
            url: url,
            id:juiId,
            title:"查看咨询问题",
//            height: $(window.top.document.body).height()-20,
//            width:$(window.top.document.body).width()-100
            height: 600,
            width: 800
        };
        window.top.$.juiceDialog.open(settings);
    }

    function cancelTop(id){
        toTop(id,"您确定要取消置顶吗?",false);
    }

    function doTop(id){
        toTop(id,"您确定要置顶吗?",true);
    }

    function toTop(id,msg,isTop){
        $.juiceDialog.confirm(msg,function(yes){
            if(yes){
                $.ajax({
                    type: 'POST',
                    url: "${ctx}/conQuestion/toTop.do",
                    data: {conId:id,top:isTop},
                    dataType: 'json',
                    success: function(ret) {
                        search('conQuestionGrid','conQuestionForm');
                        alert(ret.msg);
                    },
                    error: function(xmlR, status, e) {
                        window.top.$.juiceDialog.error("[" + e + "]" + xmlR.responseText);
                    }
                });
            }
        }) ;
    }

</script>
