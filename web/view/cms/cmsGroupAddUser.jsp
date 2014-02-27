<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <input type="hidden" id="groupId" name="groupId" value="${bean.id}"/>
    <div position="center">
        <%--<div id="query" >--%>
            <form id="sysUserForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="20%" nowrap="nowrap" align="left">
                            登录名:
                            <input type="text" id="loginName" name="loginName" value="" class="table_input" op="like" entity="t.user" dtype="String" style="width: 100px;"/>
                        </td>
                        <td width="20%" nowrap="nowrap" align="left">
                            显示名称:
                            <input type="text" id="displayName" name="displayName" value="" class="table_input"  op="like" entity="t.user" dtype="String" style="width: 100px;"/>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('sysUserGrid','sysUserForm');"/>&nbsp;
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
            </form>
        <%--</div>--%>
        <div id="sysUserGrid"  checkbox="true" rownumbers="true"   height="100%"  url='${ctx}/cmsGroup/userGrid.do?groupId=${bean.id}' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysUserGrid").setWidth(width);
            $.jui.get("query").setWidth(width);
        }
        });
        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏

        var toolbar={
            align:"right",
            items: [
                { text: '增加', click: selectUsers, icon: 'add' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#sysUserGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50, type: 'int',hide:true },
                { display: '登录名', name: 'loginName',width:"20%"},
                { display: 'userId', name: 'userId',width:"20%"},
                { display: '显示名称', name: 'displayName',width:"17%" },
                { display: '状态', name: 'status',width:"10%",render:statusFormatter},
                { display: '截止日期', name: 'closeDate',width:"10%" }
            ]
//        enabledEdit: true
        });
        //创建树
    });

    function doView(id) {
        openWindow("sysDeptWindow", "查看", "${ctx}/sysUser/view.do?id=" + id, true, "sysUserGrid");
    }

    function statusFormatter(item,index,value){
        if(value=="1"){
            return "正常";
        }else if(value=="0"){
            return "锁定";
        }else if(value=="2"){
            return "失效";
        }
    }

    function selectUsers(){
        window.top.$.juiceDialog.open({ title: '选择学习人员', name:'usersSelector',width: 1024, height: 500, url: '${ctx}/view/sys_new/sysUsersGridSelect.jsp', buttons: [
            { text: '确定', onclick: selectUsersOK },
            { text: '关闭', onclick: dialogCancel }
        ]
        });
        return false;
    }

    function dialogCancel(item, dialog){
        dialog.close();
    }

    function doDelete(){
        commonDelete("sysUserGrid","${ctx}/cmsGroup/deleteUser.do?groupId=${bean.id}");
    }

    function selectUsersOK(item, dialog){
        var fn = dialog.frame.f_select || dialog.frame.window.f_select;
        var datas = fn();
        if (!datas)
        {
            alert('请选择行!');
            return;
        }
        var userIds = [];
        for(var j=0;j<datas.length;j++){
                userIds.push(datas[j].id);
        }
        $.ajax({
            url:"${ctx}/cmsGroup/addUsers?",
            type:"post",
            data:{userIds:userIds.join(","),groupId:$("#groupId").val()},
            success:function(){
                if(!window.confirm("添加成功,是否继续添加?")){
                    $.jui.get("sysUserGrid").loadData(true);
                    dialog.close();
                }else{
                    var cancel = dialog.frame.f_cancel || dialog.frame.window.f_cancel;
                    cancel();
                }
            },failure:function(){
                alert("添加失败") ;
                dialog.close();
            }
        });


    }

</script>