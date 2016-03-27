<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaPaperUserEditForm" modelAttribute="bean" name="exaPaperUserEditForm" action="${ctx}/exaPaperUsergroup/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right" width="120px;">
                试卷:
            </td>
            <td  class="container">
                <input type="text" name="paperName" id="paperName" value="${bean.paper.paperName}" readonly="true" class="table_input" validate="{required:true}"/>
                <input type="hidden" name="paper" id="paper" value="${bean.paper.id}" />
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                参加考试人员:
            </td>
            <td  class="container">
                <input type="text" name="userName" id="userName" value="${bean.user.displayName}" class="table_input" validate="{required:true}"/>
                <input type="hidden" name="user" id="user" value="${bean.user.id}" class="table_input"/>
            </td>
        </tr>

    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){

        //选择文章
        $("#paperName").juiceComboBox({
            onBeforeOpen: selectPaper, valueFieldID: 'course',width:300
        });
        //选择用户
        $("#userName").juiceComboBox({
            onBeforeOpen: selectUser, valueFieldID: 'course',width:300
        });

        $("#userIds").val(getUserIds());
        $.metadata.setType("attr", "validate");
        v = $('#exaPaperUserEditForm').validate();
    });

    function selectPaper(){
        window.top.$.juiceDialog.open({ title: '选择试题', name:'courseselector',width: 1024, height: 500, url: '${ctx}/view/exa/exaPaperSelectGrid.jsp', buttons: [
            { text: '确定', onclick: selectCourseOK },
            { text: '取消', onclick: dialogCancel }
        ]
        });
        return false;
    }

    function selectUser(){
        window.top.$.juiceDialog.open({ title: '选择学习人员', name:'userselector',width: 1024, height: 500, url: '${ctx}/view/sys_new/sysUserGridSelect.jsp', buttons: [
            { text: '确定', onclick: selectUserOK },
            { text: '取消', onclick: dialogCancel }
        ]
        });
        return false;
    }

    function selectCourseOK(item, dialog){
        var fn = dialog.frame.f_select || dialog.frame.window.f_select;
        var data = fn();
        if (!data)
        {
            alert('请选择行!');
            return;
        }

        $("#paperName").val(data.paperName);
        $("#paper").val(data.id);
        dialog.close();
    }

    function selectUserOK(item, dialog){
        var fn = dialog.frame.f_select || dialog.frame.window.f_select;
        var data = fn();
        if (!data)
        {
            alert('请选择行!');
            return;
        }
        $("#userName").val(data.displayName);
        $("#user").val(data.id);
        dialog.close();
    }

    function dialogCancel(item, dialog){
        dialog.close();
    }

    function selectUsers(){
        window.top.$.juiceDialog.open({ title: '选择考试人员', name:'usersSelector',width: 1024, height: 500, url: '${ctx}/view/sys_new/sysUsersGridSelect.jsp', buttons: [
            { text: '确定', onclick: selectUsersOK },
            { text: '关闭', onclick: dialogCancel }
        ]
        });
        return false;
    }
    /**
     * 获取用户Ids
     * @return {string}
     */
    function getUserIds() {
        var allOptios = $("#users option");
        var userIds = ",";
        for (var i = 0; i < allOptios.length; i++) {
            userIds += $(allOptios[i]).val() + ",";
        }
        return userIds;
    }

    function selectUsersOK(item, dialog){
        var fn = dialog.frame.f_select || dialog.frame.window.f_select;
        var datas = fn();
        if (!datas)
        {
            alert('请选择行!');
            return;
        }
        var userIds = getUserIds();
        var htmlArr = [];
        for(var j=0;j<datas.length;j++){
            if(userIds.indexOf(","+datas[j].id+",")>=0){
                continue;
            }else{
                userIds += datas[j].id +",";
            }
            htmlArr.push("<option value='"+datas[j].id+"'>"+datas[j].displayName+"</option>");
        }
        $("#users").append(htmlArr.join(""));
        if(!window.confirm("添加成功,是否继续添加?")){
            dialog.close();
        }else{
            var cancel = dialog.frame.f_cancel || dialog.frame.window.f_cancel;
            cancel();
        }
        $("#userIds").val(getUserIds());
    }

    function deleteUsers(){
        if(window.confirm("您是否要删除选中的人员?")){
            $("#users option:selected").remove();
        }
        $("#userIds").val(getUserIds());
    }

    function addUsersByGroup(){
        window.top.$.juiceDialog.open({ title: '选择用户组', name:'usersSelector',width: 1024, height: 500, url: '${ctx}/view/cms/cmsGroupGrid.jsp', buttons: [
            { text: '确定', onclick: selectGroupOK },
            { text: '关闭', onclick: dialogCancel }
        ]
        });
        return false;
    }

    function selectGroupOK(item, dialog){
        var fn = dialog.frame.f_select || dialog.frame.window.f_select;
        var datas = fn();
        if (!datas)
        {
            alert('请选择行!');
            return;
        }
        var groupIds = [];
        for(var j=0;j<datas.length;j++){
            groupIds.push(datas[j].id);
        }
        $.ajax({
            url:"${ctx}/cmsGroup/getUsers",
            type:"post",
            data:{groupIds:groupIds.join(",")},
            success:function(retDatas){
                var userIds = getUserIds();
                var htmlArr = [];
                for(var j=0;j<retDatas.length;j++){
                    if(userIds.indexOf(","+retDatas[j].id+",")>=0){
                        continue;
                    }else{
                        userIds += retDatas[j].id +",";
                    }
                    htmlArr.push("<option value='"+retDatas[j].id+"'>"+retDatas[j].displayName+"</option>");
                }
                $("#users").append(htmlArr.join(""));
                if(!window.confirm("添加成功,是否继续添加?")){
                    dialog.close();
                }else{
                    var cancel = dialog.frame.f_cancel || dialog.frame.window.f_cancel;
                    cancel();
                }
                $("#userIds").val(getUserIds());
            },failure:function(){
                alert("添加失败") ;
                dialog.close();
            }
        });


    }


    function addAllUser(){
        $.ajax({
            url:"${ctx}/cmsGroup/getAllUsers",
            type:"post",
            success:function(retDatas){
                var userIds = getUserIds();
                var htmlArr = [];
                for(var j=0;j<retDatas.length;j++){
                    if(userIds.indexOf(","+retDatas[j].id+",")>=0){
                        continue;
                    }else{
                        userIds += retDatas[j].id +",";
                    }
                    htmlArr.push("<option value='"+retDatas[j].id+"'>"+retDatas[j].displayName+"</option>");
                }
                $("#users").append(htmlArr.join(""));
                $("#userIds").val(getUserIds());
                alert("添加成功")
            },failure:function(){
                alert("添加失败") ;
            }
        });

    }

</script>