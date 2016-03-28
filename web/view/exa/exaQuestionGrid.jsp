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
                        <td width="10%" align="right">
                            题干：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input"   name="content" op="like" entity="t"/>
                            <input type="hidden"  id="id" name="id" op="eq" entity="t.db"/>
                        </td>

                        <td width="10%" align="right">
                            题型：
                        </td>
                        <td width="20%">
                            <select name="questionType" class="table_input"    op="eq" entity="t">
                                <option value="" >全部</option>
                                <option value="0" >单选</option>
                                <option value="1">多选</option>
                                <option value="2">判断</option>
                                <option value="3">填空</option>
                                <option value="4">问答</option>
                            </select>
                        </td>
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

        //grid
        $("#exaQuestionGrid").juiceGrid({
            showAddButton:false,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '题目类型', name: 'questionType', width: 50,hide:true },
				{display: '题目类型', name: 'questionTypeName', width:"20%" },
//				{display: '难易程度', name: 'questionLevel', width: 50,hide:true },
//				{display: '题目来源', name: 'questionFrom', width: 50,hide:true },
//				{display: '状态', name: 'status', width: 50,hide:true },
				{display: '题干', name: 'content',  width:"10%" },
				{display: '发布时间', name: 'postTime',  width:"10%" },
				{display: '选择题答案', name: 'skey', width:"10%" },
				{display: '文字描述答案', name: 'keyDesc',  width:"25%" }
            ]
//        enabledEdit: true
        });

        //创建树
        createTree("exaQuestionTree",{ url:"${ctx}/exaQuestion/tree.do",onSelect:treeNodeClick});
    });

    function treeNodeClick(treeNode){
        if(treeNode.data.uid=="root"){
            $("#id").val("");
        }else{
            $("#id").val(treeNode.data.uid);
        }
        search('exaQuestionGrid','exaQuestionForm');
    }


    function f_select(){
        return $.jui.get("exaQuestionGrid").getSelectedRows();
    }

    function f_cancel(){
        return $.jui.get("exaQuestionGrid").loadData(true);
    }


</script>
