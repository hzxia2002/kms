<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="exaPaperLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="试卷章节树" id="accordion1">
        <ul id="exaPaperQuestionTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="exaPaperQuery" title="查询窗口"  icon="search">
            <form id="exaPaperForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            题干：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input"   name="content" op="like" entity="d.question"/>
                        </td>

                        <td width="10%" align="right">
                            题型：
                        </td>
                        <td width="20%">
                            <select name="questionType" class="table_input"    op="eq" entity="d.question">
                                <option value="" >全部</option>
                                <option value="0" >单选</option>
                                <option value="1">多选</option>
                                <option value="2">判断</option>
                                <option value="3">填空</option>
                                <option value="4">问答</option>
                            </select>
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
        <div id="exaPaperGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/exaPaperDetail/grid.do?paperId=${paperId}' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
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
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#exaPaperGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '题目类型', name: 'question.questionTypeName', width:"10%" },
//				{display: '难易程度', name: 'questionLevel', width: 50,hide:true },
//				{display: '题目来源', name: 'questionFrom', width: 50,hide:true },
//				{display: '状态', name: 'status', width: 50,hide:true },
                {display: '题干', name: 'question.content',  width:"20%" },
                {display: '发布时间', name: 'question.postTime',  width:"10%" },
                {display: '选择题答案', name: 'question.skey', width:"10%" },
                {display: '文字描述答案', name: 'question.keyDesc',  width:"25%" }

            ]
//        enabledEdit: true
        });
        //创建树
        createTree("exaPaperQuestionTree",{ url:"${ctx}/exaPaper/tree.do",createMenu:createMenu,onSelect:treeNodeClick});
    });
    //树的右键菜单
    function createMenu(treeNode){
        var menu;
        if(treeNode.data.uid == 'root') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAddSection(treeNode.data.uid)}},
                        { text: '刷新', click:function(item){refreshNode();}}
                    ]
            });
        } else {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加试题', icon:'add', click:function(item){doSelectQuestion(treeNode.data.uid)}},
                        { text: '修改', click:function(item){doEditSection(treeNode.data.uid)}},
                        { text: '删除', click:function(item){doDeleteSection(treeNode.data.uid)}}
                    ]
            });
        }
        return menu;
    }

    function treeNodeClick(treeNode){
        $("#treeId").val(treeNode.data.treeId);
        search('exaPaperGrid','exaPaperForm');
    }

    function doAddSection(){
        var url = "${ctx}/exaPaperSection/init.do?paperId=${paperId}";
        commonAddOrUpdate(url,"exaPaperGrid",null,"exaPaperSectionEditForm",{title:"新增试卷章节",height:530,width:600,callback:refreshRootNode});
    }

    function doEditSection(id){
        var url = "${ctx}/exaPaperSection/init.do?paperId=${paperId}";
        if(typeof id == "string"){
            url += "&id=" + id;
        }
        if(url){
            commonAddOrUpdate(url,"exaPaperGrid",null,"exaPaperSectionEditForm",{title:"编辑试卷章节",height:530,width:600,callback:refreshPNode});
        }
    }

    function doDeleteSection(id){
        $.juiceDialog.confirm("您确定要删除该章节及试题?",function(yes){
            if(yes){
                submitFormData(null, "${ctx}/exaPaperSection/delete.do?id=" + id,null,"exaPaperGrid",function(){
                    refreshPNode();
                    $.juiceDialog.success('章节删除成功');
                });
            }
        });
    }

    function doView(id) {
        var url = "${ctx}/exaPaper/view.do";

        commonView(url, "exaPaperGrid");
    }

    function doDelete(){
        commonDelete("exaPaperGrid","${ctx}/exaPaperDetail/delete.do");
    }

    function doAdd(){
        var tree = $.jui.get("exaPaperQuestionTree");
        var selectNode = tree.getSelected();
        if(selectNode){
            doSelectQuestion(selectNode.data.uid);
        }else{
            alert("请先选择左边试题章节");
        }
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

    function doSelectQuestion(id){
        var juiId =  window.top.$.jui.getId();
        var url = "${ctx}/exaPaper/selectQuestion.do?sectionId="+id+"&dialogJuiId="+juiId;
        var settings = {
            url: url,
            id:juiId,
            title:"添加试题",
            height: 500,
            width:$(window.top.document.body).width()*2/3,
            buttons: [
                { text: '确定', onclick: selectQuestionsOK },
                { text: '关闭', onclick: dialogCancel }
            ]
        };
        $.juiceDialog.open(settings);
        return false;
    }

    function selectQuestionsOK(item, dialog){
        var fn = dialog.frame.f_select || dialog.frame.window.f_select;
        var datas = fn();
        if (!datas)
        {
            alert('请选择行!');
            return;
        }
        var questionIds = [];
        for(var j=0;j<datas.length;j++){
            questionIds.push(datas[j].id);
        }

        var tree = $.jui.get("exaPaperQuestionTree");
        var selectNode = tree.getSelected();
        $.ajax({
            url:"${ctx}/exaPaper/addQuestions?",
            type:"post",
            dataType:"json",
            data:{questionIds:questionIds.join(","),sectionId:selectNode.data.uid||""},
            success:function(data){
                if(!window.confirm(data.msg+",是否继续添加?")){
                    $.jui.get("exaPaperGrid").loadData(true);
                    dialog.close();
                }else{
                    var cancel = dialog.frame.f_cancel || dialog.frame.window.f_cancel;
                    cancel();
                }
                search('exaPaperGrid','exaPaperForm');
            },failure:function(){
                alert("添加失败") ;
                dialog.close();
            }
        });
    }

    function dialogCancel(item, dialog){
        dialog.close();
    }

    function refreshNode(){
        var tree = $.jui.get("exaPaperQuestionTree");
        var selectNode = tree.getSelected();
        if(selectNode){
            refreshTreeNode(selectNode.data,tree);
        }else{
            refreshRootNode();
        }

    }

    function refreshPNode(){
        var tree = $.jui.get("exaPaperQuestionTree");
        refreshParentNode(tree);
    }

    function refreshRootNode(){
        var tree = $.jui.get("exaPaperQuestionTree");
        refreshRoot(tree);
    }

</script>
