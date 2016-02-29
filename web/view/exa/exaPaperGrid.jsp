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
                            <input type="text" value="" class="table_input" id="title" name="paperName" op="like" entity="t"/>
                        </td>
                        <td width="10%" align="right">
                            开始时间：
                        </td>
                        <td width="100" align="right">
                            <input type="text" value="" class="jui-dateEditor" name="startTime" op="greatAndEq" entity="t" dType="date"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="100">
                            <input type="text" value="" class="jui-dateEditor" name="startTime" op="lessAndEq" entity="t" dType="date"/>
                        </td>
                        <td width="10%" align="right">
                            结束时间：
                        </td>
                        <td width="100" align="right">
                            <input type="text" value="" class="jui-dateEditor" name="endTime" op="greatAndEq" entity="t" dType="date"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="100">
                            <input type="text" value="" class="jui-dateEditor" name="endTime" op="lessAndEq" entity="t" dType="date"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('exaPaperGrid','exaPaperForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="exaPaperGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/exaPaper/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
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
            $.jui.get("exaPaperGrid").setWidth(width);
            $.jui.get("exaPaperQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#exaPaperQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#exaPaperGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '试卷名称', name: 'paperName', width:"10%"},
                {display: '试卷类型', name: 'paperType', width: "10%" },
                {display: '开始时间', name: 'startTime', width: "10%" },
                {display: '结束时间', name: 'endTime', width: "10%"},
                {display: '作答时间', name: 'paperMinute', width: "10%"},
                {display: '试卷总分', name: 'totalScore', width:"10%" },
//				{display: '时间排序类型', name: 'questionOrderType', width: 50,hide:true },
                {display: '发布时间', name: 'postTime', width: "10%" },
                {display: '显示分数时间', name: 'showScoreTime', width: "10%" },
                {display: '操作', name: 'createTime', width: '10%',render:renderOp }

            ]
//        enabledEdit: true
        });
        //创建树

    });

    function doView(id) {
        var url = "${ctx}/exaPaper/view.do";

        commonView(url, "exaPaperGrid");
    }

    function doDelete(){
        commonDelete("exaPaperGrid","${ctx}/exaPaper/delete.do");
    }

    function doAdd(){
        var url = "${ctx}/exaPaper/init.do";
        commonAddOrUpdate(url,"exaPaperGrid",null,"exaPaperEditForm",{title:"新增试卷",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/exaPaper/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"exaPaperGrid");
        }
        if(url){
            commonAddOrUpdate(url,"exaPaperGrid",null,"exaPaperEditForm",{title:"编辑试卷",height:530,width:600});
        }
    }

    function renderOp(item,rowIndex){
        var opStr = "<input type='button' style='padding: 1px' value='添加试题' onclick='doAddQuestion("+item.id+")'/>";
        return opStr;
    }

    function doAddQuestion(id){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/exaPaper/addQuestion.do?paperId="+id+"&dialogJuiId="+juiId;
        var settings = {
            url: url,
            id:juiId,
            title:"添加试题",
            height: $(window.top.document.body).height()-10,
            width:$(window.top.document.body).width()
        };
        window.top.$.juiceDialog.open(settings);
    }


</script>
