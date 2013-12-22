<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="surQuestionaryLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="surQuestionaryQuery" title="查询窗口"  icon="search">
            <form id="surQuestionaryForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            问卷名称：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input" id="title" name="title" op="like" entity="t"/>
                        </td>

                        <td width="10%" align="right">
                            发起人：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input" id="sponsor" name="sponsor" op="like" entity="t"/>
                        </td>
                        <td width="10%" align="right">
                            创建时间：
                        </td>
                        <td width="100" align="right">
                            <input type="text" value="" class="jui-dateEditor" name="createTime" op="greatAndEq" entity="t" dType="date"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="100">
                            <input type="text" value="" class="jui-dateEditor" name="createTime" op="lessAndEq" entity="t" dType="date"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('surQuestionaryGrid','surQuestionaryForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="surQuestionaryGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/surQuestionary/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $.jui.parse();
        //布局
        $("#surQuestionaryLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("surQuestionaryGrid").setWidth(width);
            $.jui.get("surQuestionaryQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#surQuestionaryQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#surQuestionaryGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '问卷名称', name: 'title', width: '30%' },
                {display: '发起人', name: 'sponsor', width: '10%'},
//				{display: '类型：0问卷,1试卷', name: 'type', width: 50 },
                {display: '备注', name: 'remark', width: '20%' },
                {display: '创建时间', name: 'createTime', width: '10%' },
                {display: '操作', name: 'createTime', width: '20%',render:renderOp }
//				{display: '创建人(记录帐号）', name: 'createUser', width: 50},
//				{display: '更新时间', name: 'updateTime', width: 50},
//				{ display: '更新人(记录帐号）', name: 'updateUser', width: 50 }
            ]
//        enabledEdit: true
        });
        //创建树

    });

    function doView(id) {
        var url = "${ctx}/surQuestionary/view.do";

        commonView(url, "surQuestionaryGrid");
    }

    function doDelete(){
        commonDelete("surQuestionaryGrid","${ctx}/surQuestionary/delete.do");
    }

    function doAdd(){
        var url = "${ctx}/surQuestionary/init.do";
        commonAddOrUpdate(url,"surQuestionaryGrid",null,"surQuestionaryEditForm",{title:"新增调查表",height:330,width:500});
    }

    function doEdit(id){
        var url = "${ctx}/surQuestionary/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"surQuestionaryGrid");
        }
        if(url){
            commonAddOrUpdate(url,"surQuestionaryGrid",null,"surQuestionaryEditForm",{title:"编辑调查表",height:330,width:500});
        }
    }

    function renderOp(item,rowIndex){
        var opStr = "<input type='button' style='padding: 1px' value='添加试题' onclick='doAddQuestion("+item.id+")'/>";
        opStr += "&nbsp;<input type='button' style='padding: 1px' value='查看试题' onclick='doViewQuestion("+item.id+")'/>";
        return opStr;
    }

    function doAddQuestion(id){
        var url = "${ctx}/surQuestion/init.do?questionaryId="+id;
        commonAddOrUpdate(url,"",null,"surQuestionEditForm",{title:"新增调查题目",height:530,width:600});
    }

    function doViewQuestion(id){
        var url = "${ctx}/surQuestion/view.do";
        commonView(url,null,null,id);
    }
</script>

