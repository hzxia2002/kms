<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsStudyPlanLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="cmsStudyPlanQuery" title="查询窗口"  icon="search">
            <form id="cmsStudyPlanForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="4%" align="right" nowrap="nowrap">
                            课程名称：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input" id="name" name="name" op="like" entity="t.course"/>
                        </td>

                        <td width="4%" align="right" nowrap="nowrap">
                            用户：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input" id="displayName" name="displayName" op="like" entity="t.user"/>
                        </td>
                        <td width="4%" align="right" nowrap="nowrap">
                            学习时长(最少)：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input"   name="studyDuration" op="greatAndEq" entity="t"/>
                        </td>
                        <td width="4%" align="right" nowrap="nowrap">
                            学习时长(最大)：
                        </td>
                        <td width="15%">
                            <input type="text" value="" class="table_input"   name="studyDuration" op="lessAndEq" entity="t"/>
                        </td>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsStudyPlanGrid','cmsStudyPlanForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsStudyPlanGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsStudyPlan/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsStudyPlanLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsStudyPlanGrid").setWidth(width);
            $.jui.get("cmsStudyPlanQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsStudyPlanQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '批量分配', click: doAddAll, icon: 'add' },
                { text: '增加', click: doAdd, icon: 'add' },
                { line: true },
                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#cmsStudyPlanGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: '标识', name: 'id', width: 50,hide:true },
                {display: '课程', name: 'course.name', width: 150},
                {display: '学习人员', name: 'user.displayName', width: 150},
                {display: '学习时长(分钟)', name: 'studyDuration', width: 150},
                {display: '学习次数', name: 'studyTimes', width: 150},
                {display: '最后学习时间', name: 'lastStudyTime', width: 150 },
                {display: '最后学习时长', name: 'lastStudyDuration', width: 150}
            ]
//        enabledEdit: true
        });
        //创建树

    });

     function doView(id) {
        var url = "${ctx}/cmsStudyPlan/view.do";

        commonView(url, "cmsStudyPlanGrid");
    }

    function doDelete(){
        commonDelete("cmsStudyPlanGrid","${ctx}/cmsStudyPlan/delete.do",refreshRootNode);
    }

    function doAdd(){        
        var url = "${ctx}/cmsStudyPlan/init.do";
        commonAddOrUpdate(url,"cmsStudyPlanGrid",null,"cmsStudyPlanEditForm",{title:"新增学习记录",height:400,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/cmsStudyPlan/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsStudyPlanGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsStudyPlanGrid",null,"cmsStudyPlanEditForm",{title:"编辑学习记录",height:400,width:600});
        }
    }

    function doAddAll(){
        var url = "${ctx}/cmsStudyPlan/initTask.do";
        commonAddOrUpdate(url,"cmsStudyPlanGrid",null,"cmsStudyPlanEditForm",{title:"批量分配学习任务",height:400,width:600});
    }

</script>

