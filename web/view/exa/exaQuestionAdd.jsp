<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <style>
        .redborder{
            border-color: red;
        }
    </style>
</head>
<body style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
<div>
<form:form id="exaQuestionEditForm" modelAttribute="bean" name="exaQuestionEditForm" action="${ctx}/exaQuestion/save.do" method="post">
    <input type="hidden" name="id" id="id" value="${bean.id}" />
    <input type="hidden" name="dbId" id="dbId" value="${bean.db.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right" style="width:15%;" nowrap>
                题干:
            </td>
            <td  class="container">
                <textarea name="content" id="content" cols="90" rows="8" class="textarea_table" >${bean.content}</textarea>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                题型:
            </td>
            <td  class="container" >
                <select  name="questionType"  id="questionType" class="table_input">
                    <option value="0" <c:if test="${0==bean.questionType}">selected</c:if> >单选</option>
                    <option value="1" <c:if test="${1==bean.questionType}">selected</c:if>>多选</option>
                    <option value="2" <c:if test="${2==bean.questionType}">selected</c:if>>判断</option>
                    <option value="3" <c:if test="${3==bean.questionType}">selected</c:if>>填空</option>
                    <option value="4" <c:if test="${4==bean.questionType}">selected</c:if>>问答</option>
                </select>
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right">
                选项:
            </td>
            <td  class="container" >
                <div style="max-height: 250px;overflow-y: auto;width: 100%">
                    <table border="0" cellspacing="1" width="90%" class="inputTable" id="fileTable" >
                        <tr class="inputTr" style="height: 28px">
                            <th>编号</th>
                            <th>选项</th>
                            <th>操作</th>
                        </tr>
                        <tr class="inputTr" id="template" style="display:none">
                            <td>
                                <input type="text" name="opIndexNo0" class="table_input" style="width: 30px" />
                                <input type="hidden" name="opIds0" value=" "/>
                            </td>
                            <td >
                                <%--<input type="text" name="option0"  class="table_input" style="width: 280px" />--%>
                                <textarea name="option0" id="option0" cols="80" rows="3" class="textarea_table"></textarea>
                            </td>
                            <td  class="container" style="width: 50px">
                                <img src="${ctx}/skin/icons/edit_add.png" title="添加行" onclick="addRow();">&nbsp;
                                <img src="${ctx}/skin/icons/edit_remove.png" title="删除行" onclick="removeRow(this);">&nbsp;
                            </td>
                        </tr>
                        <c:forEach var="item" items="${options}" varStatus="status">
                            <tr class="inputTr">
                                <td style="min-width: 32px">
                                    <input type="text" name="opIndexNo" value="${item.indexNo}"  class="table_input" style="width: 30px" />
                                    <input type="hidden" name="opIds"  value="${item.id}" />
                                </td>
                                <td >
                                        <%--<input type="text" name="option"  class="table_input" style="width: 280px" />--%>
                                    <textarea name="option" id="option" cols="80" rows="3" class="textarea_table">${item.content}</textarea>
                                </td>
                                <td  class="container" style="width: 50px">
                                    <img src="${ctx}/skin/icons/edit_add.png" title="添加行" onclick="addRow();">&nbsp;
                                    <img src="${ctx}/skin/icons/edit_remove.png" title="删除行" onclick="removeRow(this);">
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty options}">
                            <tr class="inputTr">
                                <td style="min-width: 32px">
                                    <input type="text" name="opIndexNo"  class="table_input" style="width: 30px" />
                                    <input type="hidden" name="opIds"  value=" " />
                                </td>
                                <td >
                                    <%--<input type="text" name="option"  class="table_input" style="width: 280px" />--%>
                                    <textarea name="option" id="option" cols="80" rows="3" class="textarea_table"></textarea>
                                </td>
                                <td  class="container" style="width: 50px">
                                    <img src="${ctx}/skin/icons/edit_add.png" title="添加行" onclick="addRow();">&nbsp;
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right" nowrap>
                选择题答案:
            </td>
            <td  class="container">
                <input type="text" id="skey"  name="skey" value="${bean.skey}" class="table_input"/>&nbsp;
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right" nowrap>
                文字描述题答案:
            </td>
            <td  class="container">
                <textarea class="textarea_table" name="keyDesc" id="keyDesc"  cols="55" rows="8" >${bean.keyDesc}</textarea>
            </td>
        </tr>

        <tr>
            <td colspan="2" height="40">
                <div style="text-align: center">
                    <input type="button" value="保存" onclick="saveData();" class="btn_Ok">
                </div>
            </td>
        </tr>

    </table>
</form:form>
</div>
</body>

</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#exaQuestionLayout").juiceLayout({rightWidth: 550 , allowRightCollapse:false,allowRightResize: false});
    });

    function doDelete(){
        commonDelete("exaQuestionGrid","${ctx}/exaQuestionGrid/delete.do");
    }

    function customerValidate(){
        var title = $("#title").html();
        if(!title){
            window.top.$.juiceDialog.warn('题干不能为空!');
            return false;
        }else{
            return true;
        }
    }

    function addRow(data){
        var cloneTr = $("#template").clone();
        cloneTr.attr("id","");
        cloneTr.find("input[name='opIndexNo0']").attr("name","opIndexNo");
        cloneTr.find("textarea[name='option0']").attr("name","option");
        cloneTr.find("input[name='opIds0']").attr("name","opIds");
        cloneTr.show();
        $("#fileTable").append(cloneTr);
        if(data){
            setTrValue(cloneTr,data);
        }
    }

    function setTrValue(tr,data){
        tr.find("input[name='opIndexNo']").val(data["indexNo"]);
        tr.find("textarea[name='option']").val(data["content"]);
        tr.find("input[name='opIds']").val(data["id"]);
    }

    function removeRow(btn){
        $(btn).parent().parent().remove();
    }

    function doDelete(){
        commonDelete("exaQuestionGrid","${ctx}/exaQuestion/delete.do");
    }

    function saveData(){
        var ids = $("#id").val();
        var flag = 1;

        if(ids == null || ids == "") {
            flag = 0;
        }

        var content = $("#content").val();
        var questionType = $("#questionType").val();
        if(!content){
            alert("请输入题干");
            return;
        }

        var indexNos = $("input[name='opIndexNo']");
        var options = $("textarea[name='option']");
        if(questionType<=2){
            for(var i=0;i<indexNos.length;i++){
                if(!$(indexNos[i]).val()){
                    if(!$(indexNos[i]).hasClass("redborder")){
                        $(indexNos[i]).addClass("redborder");
                    }
                    $(indexNos[i]).focus();
                    $(indexNos[i]).bind("blur",function(){
                        if($(indexNos[i]).val()&&$(indexNos[i]).hasClass("redborder")){
                            $(indexNos[i]).removeClass("redborder");
                        }
                    });
                    alert("编号不能为空");
                    return;
                }
            }
        }

        for(var i=0;i<options.length;i++){
            var $option = $(options[i]);
            if(!$option.val()){
                if(!$option.hasClass("redborder")){
                    $option.addClass("redborder");
                }
                $option.focus();
                $option.bind("blur",function(){
                    if($option.val()&&$option.hasClass("redborder")){
                        $($option).removeClass("redborder");
                    }
                });
                alert("选项不能为空");
                return;
            }
        }
        var formData = $("#exaQuestionEditForm" ).serializeArray();

        $.ajax({
            type: 'POST',
            url: "${ctx}/exaQuestion/saveData.do?flag="+flag,
            data: formData,
            dataType: 'json',
            success: function(data) {
                alert(data.msg);
                <%--window.location.href = "${ctx}/exaQuestion/init.do?id=${db.id}"--%>
            },
            error: function(xmlR, status, e) {
                showInfoMsg("[" + e + "]" + xmlR.responseText, "error");
            }
        });

    }

    function questionType(data, rowindex, rowobj) {
        if(data.type==0){
            return "单选题";
        }else if(data.type==1){
            return "多选题";
        }
    }
</script>