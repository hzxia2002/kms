<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="surUserQuestionaryLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="surUserQuestionaryQuery" title="查询窗口"  icon="search">
            <form id="surUserQuestionaryForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="4%" align="right" nowrap="nowrap">
                            用户：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input" id="displayName" name="displayName" op="like" entity="t.user"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('surUserQuestionaryGrid','surUserQuestionaryForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="surUserQuestionaryGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/surUserQuestionary/grid.do?questionaryId=${bean.id}' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){

        //查询框初始化
        $("#surUserQuestionaryQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#surUserQuestionaryGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: 'questionaryId', name: 'questionary.id', width: 50,hide:true },
                {display: '问卷', name: 'questionary.title', width: '20%'},
                {display: '用户', name: 'user.displayName', width: '15%'},
                {display: '起始时间', name: 'startTime', width: '15%'},
                {display: '结束时间', name: 'endTime', width: '15%'},
                {display: '答题时间', name: 'answerTime', width: '15%'},
                {display: '结果查看', name: 'answerTime', width: '15%',render:viewResult}
            ]
//        enabledEdit: true
        });
        //创建树

    });

    function doView(id) {
        var url = "${ctx}/surUserQuestionary/view.do";

        commonView(url, "surUserQuestionaryGrid");
    }

    function doDelete(){
        commonDelete("surUserQuestionaryGrid","${ctx}/surUserQuestionary/delete.do");
    }

    function doAdd(){
        var url = "${ctx}/surUserQuestionary/init.do?questionaryId=${bean.id}";
        commonAddOrUpdate(url,"surUserQuestionaryGrid",null,"surUserQuestionaryEditForm",{title:"新增用户调查表",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/surUserQuestionary/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"surUserQuestionaryGrid");
        }
        if(url){
            commonAddOrUpdate(url,"surUserQuestionaryGrid",null,"surUserQuestionaryEditForm",{title:"编辑用户调查表",height:530,width:600});
        }
    }

    function viewResult(item,rowIndex){
        var opStr = "";
        if(item.status==1){
            opStr = "<input type='button' style='padding: 1px' value='查看' onclick='doViewResult("+item.id+")'/>";
        }
        return opStr;
    }

    function doViewResult(id){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/surUserQuestionary/view.do?id="+id;
        var settings = {
            url: url,
            id:juiId,
            title:"试题查看",
            height: $(window.top.document.body).height()-10,
            width:$(window.top.document.body).width()*3/4
        };
        window.top.$.juiceDialog.open(settings);
    }
</script>

