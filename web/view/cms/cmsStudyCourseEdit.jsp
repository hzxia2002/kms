<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsStudyCourseEditForm" modelAttribute="bean" name="cmsStudyCourseEditForm" action="${ctx}/cmsStudyCourse/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right" width="150px;">
                课程名称:
            </td>
            <td  class="container">
                <input type="text" name="name"  class="table_input" value="${bean.name}" validate="{required:true}" />&nbsp;
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right">
                知识点:
            </td>
            <td  class="container">
                <input type="text" name="articleName"  id="articleName" class="table_input" value="${bean.article.title}" validate="{required:true}"  readonly="true"/>&nbsp;
                <input type="hidden" name="article" id="article" class="table_input" value="${bean.article.id}"  />&nbsp;
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right">
                课程描述:
            </td>
            <td  class="container">
                <textarea  name="remark" cols="60" rows="6" class="textarea_table">${bean.remark}</textarea>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right" >
                开始时间:
            </td>
            <td  class="container">
                <input type="text"  name="startTime" id="startTime" readonly="true" value="${bean.startTime}"  >
            </td>
        </tr>
        <tr class="inputTr" >
            <td  align="right">
                结束时间:
            </td>
            <td  class="container" >
                <input type="text"  name="endTime"  id="endTime"  readonly="true" value="${bean.endTime}" >
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                学习时长(分钟):
            </td>
            <td  class="container">
                <input type="text" name="studyDuration" value="${bean.studyDuration}"  class="table_input" validate="{digits:true,min:1,required:true}"/>&nbsp;
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

        $("#articleName").juiceComboBox({
               onBeforeOpen: selectArticle, valueFieldID: 'article',width:300
        });

        $.metadata.setType("attr", "validate");
        v = $('#cmsStudyCourseEditForm').validate();
    });

   function selectArticle(){
       window.top.$.juiceDialog.open({ title: '选择知识点', name:'winselector',width: 1024, height: 500, url: '${ctx}/view/cms/cmsArticleGridSelect.jsp', buttons: [
           { text: '确定', onclick: selectArticleOK },
           { text: '取消', onclick: selectArticleCancel }
       ]
       });
       return false;

   }

    function selectArticleOK(item, dialog){
        var fn = dialog.frame.f_select || dialog.frame.window.f_select;
        var data = fn();
        if (!data)
        {
            alert('请选择行!');
            return;
        }
        $("#articleName").val(data.title);
        $("#article").val(data.id);
        dialog.close();

    }
    function selectArticleCancel(item, dialog){
        dialog.close();
    }

   function customerValidate(){
       if(!$("#startTime").val()){
           alert("开始时间不能为空");
           return false;
       }
       if(!$("#endTime").val()){
           alert("结束时间不能为空");
           return false;
       }

       if($("#endTime").val()<=$("#startTime").val()){
           alert("结束时间必须大于开始时间");
           return false;
       }
       return true;

   }
</script>