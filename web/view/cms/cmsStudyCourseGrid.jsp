<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsStudyCourseLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="学习课程树" id="accordion1">
        <ul id="cmsStudyCourseTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="cmsStudyCourseQuery" title="查询窗口"  icon="search">
            <form id="cmsStudyCourseForm" action="">
                <table style="width: 100%">
                    <tr>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsStudyCourseGrid','cmsStudyCourseForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsStudyCourseGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsStudyCourse/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsStudyCourseLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsStudyCourseGrid").setWidth(width);
            $.jui.get("cmsStudyCourseQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsStudyCourseQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#cmsStudyCourseGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: '标识', name: 'id', width: 50,hide:true },
				{display: '课程名称', name: 'name', width: 50,hide:true },
				{display: '课程描述', name: 'remark', width: 50,hide:true },
				{display: '知识点ID', name: 'article', width: 50,hide:true },
				{display: '开始时间', name: 'startTime', width: 50,hide:true },
				{display: '结束时间', name: 'endTime', width: 50,hide:true },
				{display: '学习时长(分钟)', name: 'studyDuration', width: 50,hide:true },
				{display: '创建时间', name: 'createTime', width: 50,hide:true },
				{display: '创建人(记录帐号）', name: 'createUser', width: 50,hide:true },
				{display: '更新时间', name: 'updateTime', width: 50,hide:true },
				{ display: '更新人(记录帐号）', name: 'updateUser', width: 50,hide:true }
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
        var url = "${ctx}/cmsStudyCourse/view.do";

        commonView(url, "cmsStudyCourseGrid");
    }

    function doDelete(){
        commonDelete("cmsStudyCourseGrid","${ctx}/cmsStudyCourse/delete.do",refreshRootNode);
    }

    function doAdd(){        
        var url = "${ctx}/cmsStudyCourse/init.do";
        commonAddOrUpdate(url,"cmsStudyCourseGrid",null,"cmsStudyCourseGridForm",{title:"新增学习课程",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/cmsStudyCourse/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsStudyCourseGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsStudyCourseGrid",null,"cmsStudyCourseForm",{title:"编辑学习课程",height:530,width:600});
        }
    }


</script>

