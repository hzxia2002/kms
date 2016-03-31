<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="exaPaperLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="exaPaperQuery" title="查询窗口"  icon="search">
            <form id="exaPaperForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            试卷名称：
                        </td>
                        <td width="20%" >
                            <input type="text" value="" class="table_input" id="title" name="paperName" op="like" entity="t.paper"/>
                        </td>
                        <td width="10%" align="right">
                            开始时间：
                        </td>
                        <td width="100" align="right">
                            <input type="text" value="" class="jui-dateEditor" name="startTime" op="greatAndEq" entity="t.paper" dType="date"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="100">
                            <input type="text" value="" class="jui-dateEditor" name="startTime" op="lessAndEq" entity="t.paper" dType="date"/>
                        </td>
                        <td width="10%" align="right">
                            结束时间：
                        </td>
                        <td width="100" align="right">
                            <input type="text" value="" class="jui-dateEditor" name="endTime" op="greatAndEq" entity="t.paper" dType="date"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="100">
                            <input type="text" value="" class="jui-dateEditor" name="endTime" op="lessAndEq" entity="t.paper" dType="date"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('exaPaperUserGrid','exaPaperForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="exaPaperUserGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/exaPaperUsergroup/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $.jui.parse();
        //布局
        $("#exaPaperLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("exaPaperUserGrid").setWidth(width);
            $.jui.get("exaPaperQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#exaPaperQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '批量分配', click: doAddAll, icon: 'add' },
                { line: true },
                { text: '增加', click: doAdd, icon: 'add' },
//                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#exaPaperUserGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '试卷名称', name: 'paper.paperName', width:"20%",render:renderOp},
                {display: '试卷类型', name: 'paper.paperTypeName', width: "5%" },
                {display: '开始时间', name: 'paper.startTime', width: "10%" },
                {display: '结束时间', name: 'paper.endTime', width: "10%"},
                {display: '作答时间(分钟)', name: 'paper.paperMinute', width: "5%"},
                {display: '考试人', name: 'user.displayName', width:"8%" },
//				{display: '时间排序类型', name: 'questionOrderType', width: 50,hide:true },
                {display: '发布时间', name: 'paper.postTime', width: "12%" },
                {display: '显示分数时间', name: 'paper.showScoreTime', width: "12%" },
                {display: '创建时间', name: 'createTime', width: "12%" },
                {display: '创建人', name: 'createUser', width: "8%" }
//                {display: '操作', name: 'createTime', width: '20%',render:renderOp }

            ]
//        enabledEdit: true
        });
        //创建树

    });

    function renderOp(item,rowIndex){
        var opStr  = "<a href='#' onclick='doViewQuestion(" + item.paper.id + ")'>" + item.paper.paperName + "</a>";
        return opStr;
    }

    function doViewQuestion(paperId){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/exaPaper/exaPaperView.do?paperId="+paperId+"&dialogJuiId="+juiId;
        var settings = {
            url: url,
            id:juiId,
            title:"查看试题",
            height: $(window.top.document.body).height()-10,
            width:$(window.top.document.body).width()
        };
        window.top.$.juiceDialog.open(settings);
    }

    function doDelete(){
        commonDelete("exaPaperUserGrid","${ctx}/exaPaperUsergroup/deleteUser.do",refreshRootNode);
    }

    function doAdd(){
        var url = "${ctx}/exaPaperUsergroup/addUser.do";
        commonAddOrUpdate(url,"exaPaperUserGrid",null,"exaPaperUserEditForm",{title:"分配考试",height:400,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/exaPaperUsergroup/addUser.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"exaPaperUserGrid");
        }
        if(url){
            commonAddOrUpdate(url,"exaPaperUserGrid",null,"exaPaperUserEditForm",{title:"编辑考试记录",height:400,width:600});
        }
    }

    function doAddAll(){
        var url = "${ctx}/exaPaperUsergroup/addUsers.do";
        commonAddOrUpdate(url,"exaPaperUserGrid",null,"exaPaperUserEditForm",{title:"批量分配考试",height:400,width:600});
    }
</script>
