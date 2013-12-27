<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="surUserQuestionaryEditForm" modelAttribute="bean" name="surUserQuestionaryEditForm" action="${ctx}/surUserQuestionary/saveUsers.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right" style="width: 20%">
                问卷名称:
            </td>
            <td  class="container">
                <input type="hidden" name="questionary" value="${bean.questionary.id}" />
                <input type="text" name="questionary_name"  class="table_input" value="${bean.questionary.title}" />
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                答卷人员:
            </td>
            <td  class="container">
                <div style="float: left">
                    <select id="user" name="user" multiple="multiple" size="8" style="width: 200px;" class="textarea_table">
                    </select>
                </div>
                <div><img src="${ctx}/skin/icons/edit_add.png" onclick="selectUsers();"></div>
                <div><img src="${ctx}/skin/icons/edit_remove.png" onclick="deleteUsers();"></div>
                <input type="hidden" name="userIds" id="userIds">
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                起始时间:
            </td>
            <td  class="container">
                <input type="text"  name="startTime" id="startTime" class="jui-dateEditor"/>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                结束时间:
            </td>
            <td  class="container">
                <input type="text"  name="endTime" id="endTime" class="jui-dateEditor"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $("#startTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.startTime}"></fmt:formatDate>'});
        $("#endTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.endTime}"></fmt:formatDate>'});

        $.metadata.setType("attr", "validate");
        v = $('#surUserQuestionaryEditForm').validate();
    });

    function dialogCancel(item, dialog){
        dialog.close();
    }

    function selectUsers(){
        window.top.$.juiceDialog.open({ title: '选择答卷人员', name:'usersSelector',width: 1024, height: 500, url: '${ctx}/view/sys_new/sysUsersGridSelect.jsp', buttons: [
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
        var allOptios = $("#user option");
        var userIds = "";
        for (var i = 0; i < allOptios.length; i++) {
            userIds += $(allOptios[i]).val() + ",";
        }
        if(userIds.length>0){
            userIds = userIds.substring(0,userIds.length-1);
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
        $("#user").append(htmlArr.join(""));
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
            $("#user option:selected").remove();
        }
        $("#userIds").val(getUserIds());
    }
</script>