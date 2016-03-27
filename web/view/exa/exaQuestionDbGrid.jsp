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
                        <td width="10%" align="right" height="30">
                            题库名称：
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
        <div id="exaQuestionDbGrid" checkbox="true" rownumbers="true"  height="300"  url='${ctx}/exaQuestionDb/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
        <div id="exaQuestionGrid" checkbox="true" rownumbers="true"  height="400"  url='${ctx}/exaQuestion/grid.do?dbId=0' root="rows" record="records"  width="100%"  colDraggable="true" >
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

        var toolbar2={
            align:"right",
            items: [
                { text: '删除', click: doQuestionDelete, icon:'delete' }
            ]
        };

        //grid
        $("#exaQuestionDbGrid").juiceGrid({
            toolbar:toolbar,
            title: "题库列表",
            onDblClickRow: selectQuestion,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '题库名称', name: 'dbName', width: "20%" },
                {display: '状态', name: 'status', width:"10%",render:renderStatus },
                {display: '备注', name: 'remark', width: "20%" },
                {display: '创建时间', name: 'createTime', width: 150 },
                { display: '创建人', name: 'createUser', width: 120},
                {display: '操作', name: 'createTime', width: '20%',render:renderOp }
            ]
//        enabledEdit: true
        });

        //grid
        $("#exaQuestionGrid").juiceGrid({
            toolbar:toolbar2,
            title: "试题列表",
            columns: [
                {display: 'ID', name: 'id', width: 1,hide:true },
//                {display: '题目类型', name: 'questionType', width: 50,hide:true },
                {display: '题目类型', name: 'questionTypeName', width:"10%" },
//				{display: '难易程度', name: 'questionLevel', width: 50,hide:true },
//				{display: '题目来源', name: 'questionFrom', width: 50,hide:true },
//				{display: '状态', name: 'status', width: 50,hide:true },
                {display: '题干', name: 'content',  width:"30%" },
                {display: '发布时间', name: 'postTime',  width:"10%" },
                {display: '选择题答案', name: 'skey', width:"10%" },
                {display: '文字描述答案', name: 'keyDesc',  width:"25%" },
                {display: '操作', name: 'createTime', width: '10%',render:renderQuestionOp }
            ]
//        enabledEdit: true
        });
    });

    function doView(id) {
        var url = "${ctx}/exaQuestionDb/view.do";

        commonView(url, "exaQuestionDbGrid");
    }

    function doDelete(){
        commonDelete("exaQuestionDbGrid","${ctx}/exaQuestionDb/delete.do");
    }

    function doAdd(){
        var url = "${ctx}/exaQuestionDb/init.do";
        commonAddOrUpdate(url,"exaQuestionDbGrid",null,"exaQuestionDbEditForm",{title:"新增题库",height:300,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/exaQuestionDb/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"exaQuestionDbGrid");
        }
        if(url){
            commonAddOrUpdate(url,"exaQuestionDbGrid",null,"exaQuestionDbEditForm",{title:"编辑题库",height:300,width:600});
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
        var opStr = "<input type='button' style='padding: 1px' value='查看试题' onclick='viewQuestions("+item.id+")'/>";
        opStr += "&nbsp;<input type='button' style='padding: 1px' value='增加试题' onclick='doAddQuestion("+item.id+")'/>";
        return opStr;
    }

    function renderQuestionOp(item,rowIndex){
        var opStr = "<input type='button' style='padding: 1px' value='编辑试题' onclick='doEditQuestions("+item.id+")'/>";
        return opStr;
    }

    function doAddQuestion(id){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/exaQuestion/initAdd.do?dbId="+id;
        var settings = {
            url: url,
            id:juiId,
            title:"题库题目管理",
            height: $(window.top.document.body).height()-10,
            width:$(window.top.document.body).width()
        };
        window.top.$.juiceDialog.open(settings);
    }

    function doEditQuestions(id){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/exaQuestion/init.do?id="+id;
        var settings = {
            url: url,
            id:juiId,
            title:"题目编辑",
            height: $(window.top.document.body).height()-10,
            width:800
        };
        window.top.$.juiceDialog.open(settings);
    }

    function viewQuestions(dbId) {
        $.jui.get("exaQuestionGrid")._setUrl('${ctx}/exaQuestion/grid.do?dbId=' + dbId);
    }

    function selectQuestion(rowdata,rowid,rowobj) {
        $.jui.get("exaQuestionGrid")._setUrl('${ctx}/exaQuestion/grid.do?dbId=' + rowdata['id']);
    }

    function doQuestionDelete() {
        commonDelete("exaQuestionGrid","${ctx}/exaQuestion/delete.do");
    }
</script>
