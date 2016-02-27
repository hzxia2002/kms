<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="exaQuestionLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="题库" id="accordion1">
        <ul id="exaQuestionTree" style="margin-top:3px;">

        </ul>
    </div>
    <div position="center">
        <div id="exaQuestionQuery" title="查询窗口"  icon="search">
            <form id="exaQuestionForm" action="">
                <table style="width: 100%">
                    <tr>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('exaQuestionGrid','exaQuestionForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="exaQuestionGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/exaQuestion/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#exaQuestionLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("exaQuestionGrid").setWidth(width);
            $.jui.get("exaQuestionQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#exaQuestionQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#exaQuestionGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '题库ID', name: 'db', width: 50,hide:true },
				{display: '题目类型', name: 'questionType', width: 50,hide:true },
				{display: '难易程度', name: 'questionLevel', width: 50,hide:true },
				{display: '题目来源', name: 'questionFrom', width: 50,hide:true },
				{display: '状态', name: 'status', width: 50,hide:true },
				{display: '内容', name: 'content', width: 50,hide:true },
				{display: '发布时间', name: 'postTime', width: 50,hide:true },
				{display: '选择题答案', name: 'skey', width: 50,hide:true },
				{display: '文字描述答案', name: 'keyDesc', width: 50,hide:true },
				{display: '创建时间', name: 'createTime', width: 50,hide:true },
				{display: '更新时间', name: 'updateTime', width: 50,hide:true },
				{display: '更新人', name: 'updateUser', width: 50,hide:true },
				{ display: '创建人', name: 'createUser', width: 50,hide:true }
            ]
//        enabledEdit: true
        });

        //创建树
        createTree("exaQuestionTree",{ url:"${ctx}/exaQuestion/tree.do"});

    });


     function doView(id) {
        var url = "${ctx}/exaQuestion/view.do";

        commonView(url, "exaQuestionGrid");
    }

    function doDelete(){
        commonDelete("exaQuestionGrid","${ctx}/exaQuestion/delete.do",refreshRootNode);
    }

    function doAdd(){        
        var url = "${ctx}/exaQuestion/init.do";
        commonAddOrUpdate(url,"exaQuestionGrid",null,"exaQuestionGridForm",{title:"新增试题",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/exaQuestion/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"exaQuestionGrid");
        }
        if(url){
            commonAddOrUpdate(url,"exaQuestionGrid",null,"exaQuestionForm",{title:"编辑试题",height:530,width:600});
        }
    }


</script>
