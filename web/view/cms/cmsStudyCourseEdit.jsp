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
                <div style="float: left">
                    <select id="articleNames" name="articleNames" multiple="multiple" size="8" style="width: 200px;" class="textarea_table">
                        <c:forEach var="article" items="${articles}">
                             <option value="${article.id}">${article.title}</option>
                        </c:forEach>
                    </select>
                </div>
                <div><img src="${ctx}/skin/icons/edit_add.png" onclick="selectArticle();" title="选择知识点"></div>
                <div><img src="${ctx}/skin/icons/edit_remove.png" onclick="deleteArticle();" title="删除知识点"></div>
                <input type="hidden" name="articleIds" id="articleIds">
                <input type="hidden" name="article"  value="">
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

        <tr class="inputTr">
            <td  align="right">
                关联试题:
            </td>
            <td  class="container">
                <div style="float: left">
                    <select id="paperNames" name="paperNames" multiple="multiple" size="8" style="width: 200px;" class="textarea_table">
                        <c:forEach var="paper" items="${papers}">
                            <option value="${paper.id}">${paper.paperName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div><img src="${ctx}/skin/icons/edit_add.png" onclick="selectPaper();" title="选择试题"></div>
                <div><img src="${ctx}/skin/icons/edit_remove.png" onclick="deletePaper();" title="删除试题"></div>
                <input type="hidden" name="paperIds" id="paperIds">
            </td>
        </tr>

    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $("#startTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.startTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>'});
        $("#endTime").juiceDateEditor({ showTime: true, format:"yyyy-MM-dd hh:mm:ss",width:200 ,initValue:'<fmt:formatDate type="both" value="${bean.endTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate>'});

        $("#articleName").juiceComboBox({
               onBeforeOpen: selectArticle, valueFieldID: 'article',width:300
        });

        $("#articleIds").val(getArticleIds());
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
        var datas = fn();
        if (!datas)
        {
            alert('请选择行!');
            return;
        }
        var articleIds = getArticleIds("");
        var htmlArr = [];
        for(var j=0;j<datas.length;j++){
            if(articleIds.indexOf(","+datas[j].id+",")>=0){
                continue;
            }else{
                articleIds += datas[j].id +",";
            }
            htmlArr.push("<option value='"+datas[j].id+"'>"+datas[j].title+"</option>");
        }
        $("#articleNames").append(htmlArr.join(""));
        if(!window.confirm("添加成功,是否继续添加?")){
            dialog.close();
        }else{
            var cancel = dialog.frame.f_cancel || dialog.frame.window.f_cancel;
            cancel();
        }
        $("#articleIds").val(getArticleIds());
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
    function deleteArticle(){
        var selecteds = $("#articleNames option:selected");
        if(selecteds.length<=0){
            alert("请选择知识点");
            return;
        }
        if(window.confirm("您是否要删除选中的知识点?")){

            var selectedIds = [];
            for(var i=0;i<selecteds.length;i++){
                selectedIds.push($(selecteds[i]).val()) ;
            }
            $.ajax({
                url:"${ctx}/cmsStudyCourse/deleteArticle?courseId=${bean.id}",
                type:"post",
                data:{articleIds:selectedIds.join(",")},
                success:function(){
                    $("#articleNames option:selected").remove();
                    $("#articleIds").val(getArticleIds());
                },failure:function(){
                    alert("删除失败") ;
                }
            });

        }
    }

    function deletePaper(){
        var selecteds = $("#paperNames option:selected");
        if(selecteds.length<=0){
            alert("请选择试卷");
            return;
        }
        if(window.confirm("您是否要删除选中的试卷?")){

            var selectedIds = [];
            for(var i=0;i<selecteds.length;i++){
                selectedIds.push($(selecteds[i]).val()) ;
            }
            $.ajax({
                url:"${ctx}/cmsStudyCourse/deletePaper?courseId=${bean.id}",
                type:"post",
                data:{paperIds:selectedIds.join(",")},
                success:function(){
                    $("#paperNames option:selected").remove();
                    $("#paperIds").val(getIds("paperNames"));
                },failure:function(){
                    alert("删除失败") ;
                }
            });

        }
    }


    function selectPaper(){
        window.top.$.juiceDialog.open({ title: '选择试题', name:'courseselector',width: 1024, height: 500, url: '${ctx}/view/exa/exaPaperSelectGrid.jsp', buttons: [
            { text: '确定', onclick: selectPaperOK },
            { text: '取消', onclick: selectArticleCancel }
        ]
        });
        return false;
    }

    function selectPaperOK(item, dialog){
        var fn = dialog.frame.f_selects || dialog.frame.window.f_selects;
        var datas = fn();

        if (!datas)
        {
            alert('请选择行!');
            return;
        }
        var paperIds = getIds("paperNames");
        var htmlArr = [];
        for(var j=0;j<datas.length;j++){
            if(paperIds.indexOf(","+datas[j].id+",")>=0){
                continue;
            }else{
                paperIds += datas[j].id +",";
            }
            htmlArr.push("<option value='"+datas[j].id+"'>"+datas[j].paperName+"</option>");
        }
        $("#paperNames").append(htmlArr.join(""));
        if(!window.confirm("添加成功,是否继续添加?")){
            dialog.close();
        }else{
            var cancel = dialog.frame.f_cancel || dialog.frame.window.f_cancel;
            cancel();
        }
        $("#paperIds").val(getIds("paperNames"));
    }

    function getIds(selectId){
        var allOptios = $("#"+selectId+" option");
        var ids = ",";
        for (var i = 0; i < allOptios.length; i++) {
            ids += $(allOptios[i]).val() + ",";
        }
        return ids;
    }

    function getArticleIds(){

        return getIds("articleNames");
    }

</script>