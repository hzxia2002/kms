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
				{display: '试卷名称', name: 'paperName', width: 50,hide:true },
				{display: '开始时间', name: 'startTime', width: 50,hide:true },
				{display: '结束时间', name: 'endTime', width: 50,hide:true },
				{display: '作答时间', name: 'paperMinute', width: 50,hide:true },
				{display: '试卷总分', name: 'totalScore', width: 50,hide:true },
				{display: '时间排序类型', name: 'questionOrderType', width: 50,hide:true },
				{display: '发布时间', name: 'postTime', width: 50,hide:true },
				{display: '显示分数时间', name: 'showScoreTime', width: 50,hide:true },
				{display: '是否随机时间', name: 'isRandPaper', width: 50,hide:true },
				{display: '试卷状态(1开放，-1不开放)', name: 'status', width: 50,hide:true },
				{display: '备注', name: 'remark', width: 50,hide:true },
				{display: '创建时间', name: 'createTime', width: 50,hide:true },
				{display: '更新时间', name: 'updateTime', width: 50,hide:true },
				{display: '更新人', name: 'updateUser', width: 50,hide:true },
				{ display: '创建人', name: 'createUser', width: 50,hide:true }
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
        commonAddOrUpdate(url,"exaPaperGrid",null,"exaPaperGridForm",{title:"新增试卷",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/exaPaper/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"exaPaperGrid");
        }
        if(url){
            commonAddOrUpdate(url,"exaPaperGrid",null,"exaPaperForm",{title:"编辑试卷",height:530,width:600});
        }
    }


</script>
