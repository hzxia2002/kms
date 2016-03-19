<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
    <style type="text/css">
    </style>
</head>
<body style="width: 100%">

<div id="cmsStudyCourseLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="cmsStudyCourseQuery" title="查询窗口"  icon="search">
            <form id="cmsStudyCourseForm" action="">
                <table style="width: 100%">
                    <tr style="height: 30px;">
                        <td width="4%" align="right" nowrap="nowrap">
                            课程名称：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input" id="name" name="name" op="like" entity="t"/>
                        </td>

                        <td width="4%" align="right" nowrap="nowrap">
                            知识点：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input" id="title" name="title" op="like" entity="t.article"/>
                        </td>
                        <td  align="right"  width="4%" nowrap="nowrap">
                            开始时间:
                        </td>
                        <td  class="container" td width="15%">
                            <input type="text"  name="startTime" id="startTime"  value="${bean.startTime}"  op="greatAndEq" entity="t">
                        </td>
                        <td  align="right"  width="4%" nowrap="nowrap">
                            结束时间:
                        </td>
                        <td  class="container">
                            <input type="text"  name="endTime" id="endTime"  value="${bean.endTime}"  op="lessAndEq" entity="t">
                        </td>
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

        $("#startTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 });
        $("#endTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 });
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
                {display: '课程名称', name: 'name', width: 150},
                {display: '知识点', name: 'articles', width: 350,render:articleRender},
                {display: '课程描述', name: 'remark', width: 200 },
                {display: '开始时间', name: 'startTime', width: 120},
                {display: '结束时间', name: 'endTime', width: 120 },
                {display: '学习时长(分钟)', name: 'studyDuration', width: 100}
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
        commonDelete("cmsStudyCourseGrid","${ctx}/cmsStudyCourse/delete.do");
    }

    function doAdd(){
        var url = "${ctx}/cmsStudyCourse/init.do";
        commonAddOrUpdate(url,"cmsStudyCourseGrid",null,"cmsStudyCourseEditForm",{title:"新增学习课程",height:550,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/cmsStudyCourse/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsStudyCourseGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsStudyCourseGrid",null,"cmsStudyCourseEditForm",{title:"编辑学习课程",height:550,width:600});
        }
    }

    function articleRender(item){
        if(item.articles&&item.articles.length>0){
             var htmlArr = [];
            for(var i=0;i<item.articles.length;i++){
                htmlArr.push("<a style='cursor: pointer' href='${ctx}/page/preView.do?id="+item.articles[i].id+"' target='_blank'>"+item.articles[i].title+"</a>&nbsp;&nbsp;")
            }
            return htmlArr.join("");
        }else{
            return "";
        }
    }

</script>

