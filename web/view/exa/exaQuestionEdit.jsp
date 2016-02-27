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
<div id="exaQuestionLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <input type="hidden" name="id" value="${bean.id}" />
    <div position="center"  title="(${db.dbName}) 题目列表" id="accordion1" style="overflow:hidden;height:100%;">
        <div id="exaQuestionQuery" title="查询窗口"  icon="search">
            <form id="exaQuestionForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            题干：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input"   name="title" op="like" entity="t"/>
                        </td>

                        <td width="10%" align="right">
                            题型：
                        </td>
                        <td width="20%">
                            <select name="type" class="table_input"    op="like" entity="t">
                                <option value="" >全部</option>
                                <option value="0" >单选</option>
                                <option value="1">多选</option>
                                <option value="2">判断</option>
                                <option value="3">问答</option>
                            </select>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('exaQuestionGrid','exaQuestionForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="exaQuestionGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/exaQuestion/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>

    <div position="right" id="accordion2">
        <div id="cmsArticleQuery" title="编辑"  icon="search">
            <form:form id="exaQuestionEditForm" modelAttribute="bean" name="exaQuestionEditForm" action="${ctx}/exaQuestion/save.do" method="post">
                <input type="hidden" name="id" id="id" value="${bean.id}" />
                <input type="hidden" name="dbId" id="questionary_id" value="${db.id}" />
                <table border="0" cellspacing="1" width="100%" class="inputTable">
                    <tr class="inputTr">
                        <td  align="right" style="width: 20%;" nowrap>
                            题干:
                        </td>
                        <td  class="container">
                            <textarea name="content" id="content" cols="55" rows="5" class="textarea_table" >${bean.content}</textarea>
                        </td>
                    </tr>
                    <tr class="inputTr">
                        <td  align="right">
                            题型:
                        </td>
                        <td  class="container" >
                            <select  name="questionType"  id="questionType" class="table_input">
                                <option value="0" >单选</option>
                                <option value="1">多选</option>
                                <option value="2">判断</option>
                                <option value="3">问答</option>
                            </select>
                        </td>
                    </tr>

                    <tr class="inputTr">
                        <td  align="right">
                            选项:
                        </td>
                        <td  class="container" >
                            <div style="max-height: 300px;overflow-y: auto;width: 100%">
                                <table border="0" cellspacing="1" width="90%" class="inputTable" id="fileTable" >
                                    <tr class="inputTr" style="height: 28px">
                                        <th>编号</th>
                                        <th>选项</th>
                                        <th>操作</th>
                                    </tr>
                                    <tr class="inputTr" id="template" style="display:none">
                                        <td >
                                            <input type="text" name="opIndexNo0"  class="table_input" style="width: 30px" />
                                            <input type="hidden" name="opIds0"   value=" "/>
                                        </td>
                                        <td >
                                            <input type="text" name="option0"  class="table_input" style="width: 280px" />
                                        </td>
                                        <td  class="container" style="width: 50px">
                                            <img src="${ctx}/skin/icons/edit_add.png" title="添加行" onclick="addRow();">&nbsp;
                                            <img src="${ctx}/skin/icons/edit_remove.png" title="删除行" onclick="removeRow(this);">&nbsp;
                                        </td>
                                    </tr>
                                    <tr class="inputTr">
                                        <td style="min-width: 32px">
                                            <input type="text" name="opIndexNo"  class="table_input" style="width: 30px" />
                                            <input type="hidden" name="opIds"  value=" " />
                                        </td>
                                        <td >
                                            <input type="text" name="option"  class="table_input" style="width: 280px" />
                                        </td>
                                        <td  class="container" style="width: 50px">
                                            <img src="${ctx}/skin/icons/edit_add.png" title="添加行" onclick="addRow();">&nbsp;
                                        </td>
                                    </tr>

                                </table>
                            </div>

                        </td>
                    </tr>
                    <tr class="inputTr">
                        <td  align="right" style="width: 20%;" nowrap>
                            选择题答案:
                        </td>
                        <td  class="container">
                            <input type="text" name="skey" value="${bean.skey}" />&nbsp;
                        </td>
                    </tr>

                    <tr class="inputTr">
                        <td  align="right" style="width: 20%;" nowrap>
                            文字描述题答案:
                        </td>
                        <td  class="container">
                            <input type="text" name="skey" value="${bean.keyDesc}" />&nbsp;
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <div style="text-align: center">
                                <input type="button" value="新增" onclick="saveData(0);" class="btn_Ok">
                                <input type="button" value="修改" id="modify" style="display: none" onclick="saveData(1);" class="btn_Ok">
                            </div>
                        </td>
                    </tr>

                </table>
            </form:form>

        </div>
    </div>

</div>
</body>

</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#exaQuestionLayout").juiceLayout({rightWidth: 550 , allowRightCollapse:false,allowRightResize: false});

        var toolbar={
            align:"right",
            items: [
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#exaQuestionGrid").juiceGrid({
            toolbar:toolbar,
            showAddButton:false,
            onDblClickRow:dbclick,
            allowUnSelectRow:true,
            selectRowButtonOnly:true,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '题干', name: 'content', width: "20%" },
                {display: '题目类型', name: 'questionType',  width: "8%" },
                {display: '题目来源', name: 'questionFrom', width: "8%" },
                {display: '状态', name: 'status',  width: "8%" },
                {display: '发布时间', name: 'postTime', width: "10%" },
                {display: '选择题答案', name: 'skey',  width: "8%"},
//                {display: '文字描述答案', name: 'keyDesc',  width: "10%" },
                {display: '创建时间', name: 'createTime',  width: "8%" },
                {display: '更新时间', name: 'updateTime',  width: "8%"}
            ]
//        enabledEdit: true
        });
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
        cloneTr.find("input[name='option0']").attr("name","option");
        cloneTr.find("input[name='opIds0']").attr("name","opIds");
        cloneTr.show();
        $("#fileTable").append(cloneTr);
        if(data){
            setTrValue(cloneTr,data);
        }
    }

    function setTrValue(tr,data){
        tr.find("input[name='opIndexNo']").val(data["indexNo"]);
        tr.find("input[name='option']").val(data["content"]);
        tr.find("input[name='opIds']").val(data["id"]);
    }

    function removeRow(btn){
        $(btn).parent().parent().remove();
    }

    function doDelete(){
        commonDelete("exaQuestionGrid","${ctx}/exaQuestion/delete.do");
    }

    function saveData(flag){
        var content = $("#content").val();
        var questionType = $("#questionType").val();
        if(!content){
            alert("请输入题干");
            return;
        }

        var indexNos = $("input[name='opIndexNo']");
        var options = $("input[name='option']");
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
                window.location.href = "${ctx}/exaQuestion/init.do?dbId=${db.id}"
            },
            error: function(xmlR, status, e) {
                showInfoMsg("[" + e + "]" + xmlR.responseText, "error");
            }
        });

    }


    function dbclick(data, rowindex, rowobj){
        $.ajax({
            type: 'POST',
            url: "${ctx}/exaQuestion/getQuestion.do?questionId="+data.id,
            dataType: 'json',
            success: function(data) {
                var question  = data.question;
                for(var key in question){
                    $("#"+key).val(question[key]);
                }

                //选项
                var datas = data.options;
                if(datas){
                    setTrValue($("#fileTable tr").eq(2),datas[0]);
                    $("#fileTable tr:gt(2)").remove();
                    for(var i=1;i<datas.length;i++){
                        addRow(datas[i]);
                    }
                }
                $("#modify").show();

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

    function opModify(data, rowindex, rowobj){
        return "<input type='button' style='padding: 1px' value='修改' onclick='doEdit("+rowindex+")'/>";
    }

    function doEdit(rowindex){
        var data = $.jui.get("exaQuestionGrid").getRow(rowindex) ;
        dbclick(data,rowindex);
    }
</script>