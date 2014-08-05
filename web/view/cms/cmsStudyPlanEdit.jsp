<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsStudyPlanEditForm" modelAttribute="bean" name="cmsStudyPlanEditForm" action="${ctx}/cmsStudyPlan/save.do" method="post">
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

                <input type="text" name="userName" id="userName" value="${bean.user.displayName}" class="table_input" validate="{required:true}"/>
                <input type="hidden" name="user" id="user" value="${bean.user.id}" class="table_input"/>
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
                ${bean.lastStudyTime}

            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                最后学习时长:
            </td>
            <td  class="container">
                ${bean.lastStudyDuration}
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
        window.top.$.juiceDialog.open({ title: '人员选择', name:'userselector',width: 1024, height: 500, url: '${ctx}/view/sys_new/sysUserGridSelect.jsp', buttons: [
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
</script>