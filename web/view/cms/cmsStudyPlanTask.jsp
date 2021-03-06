<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsStudyPlanEditForm" modelAttribute="bean" name="cmsStudyPlanEditForm" action="${ctx}/cmsStudyPlan/saveUsers.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right" width="120px;">
                课程:
            </td>
            <td  class="container">
                <input type="text" name="courseName" id="courseName" value="${bean.course.name}" readonly="true" class="table_input" validate="{required:true}"/>
                <input type="hidden" name="course" id="course" value="${bean.course.id}" />
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                参加学习人员:
            </td>
            <td  class="container">
                <div style="float: left">
                    <select id="users" name="users" multiple="multiple" size="8" style="width: 200px;" class="textarea_table">
                    </select>
                </div>
                <div><img src="${ctx}/skin/icons/edit_add.png" onclick="selectUsers();" title="选择用户"></div>
                <div><img src="${ctx}/skin/icons/edit_remove.png" onclick="deleteUsers();" title="删除用户"></div>
                <div><img src="${ctx}/skin/images/es.png" onclick="addUsersByGroup();" width="20px" title="选择用户组"></div>
                <div><img src="${ctx}/skin/images/eq.png" onclick="addAllUser();" width="20px" title="添加全体用户"></div>
                <input type="hidden" name="userIds" id="userIds">
                    <%--<input type="text" name="userName" id="userName" value="${bean.user.displayName}" class="table_input" validate="{required:true}"/>--%>
                    <%--<input type="hidden" name="user" id="user" value="${bean.user.id}" class="table_input"/>--%>
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right">
                学习时长(分钟):
            </td>
            <td  class="container">
                <input type="text" name="studyDuration" value="${bean.studyDuration}" class="table_input" validate="{number:true,min:0}"/>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                学习次数:
            </td>
            <td  class="container">
                <input type="text" name="studyTimes" value="${bean.studyTimes}" class="table_input" validate="{digits:true,min:0}"/>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                最后学习时间:
            </td>
            <td  class="container">
                <input type="text"  name="lastStudyTime" id="lastStudyTime">

            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                最后学习时长:
            </td>
            <td  class="container">
                <input type="text" name="lastStudyDuration" value="${bean.lastStudyDuration}" class="table_input" validate="{number:true,min:0}"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $("#lastStudyTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.lastStudyTime}"></fmt:formatDate>'});

        //选择文章
        $("#courseName").juiceComboBox({
            onBeforeOpen: selectCourse, valueFieldID: 'course',width:300
        });
        //选择用户
        $("#userName").juiceComboBox({
            onBeforeOpen: selectUser, valueFieldID: 'course',width:300
        });

        $("#userIds").val(getUserIds());
        $.metadata.setType("attr", "validate");
        v = $('#cmsStudyPlanEditForm').validate();
    });

    function selectCourse(){
        window.top.$.juiceDialog.open({ title: '选择知识点', name:'courseselector',width: 1024, height: 500, url: '${ctx}/view/cms/cmsStudyGridSelect.jsp', buttons: [
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
        $("#courseName").val(data.name);
        $("#course").val(data.id);
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
        window.top.$.juiceDialog.open({ title: '选择学习人员', name:'usersSelector',width: 1024, height: 500, url: '${ctx}/view/sys_new/sysUsersGridSelect.jsp', buttons: [
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