<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <style>
        th{
            text-align: center;font-size: 14px;font-weight: bold
        }
    </style>
    <script>
        function viewResult(id,questionId){
                var juiId =  window.top.$.jui.getId();
                var url = "${ctx}/surUserQuestionary/statUser.do?id="+id+"&questionId="+questionId;
                var settings = {
                    url: url,
                    id:juiId,
                    title:"人员查看",
                    height: $(window.top.document.body).height()/2,
                    width:$(window.top.document.body).width()*0.5
                };
                window.top.$.juiceDialog.open(settings);
        }
    </script>
</head>
<body >
<div >
    <div>
        总分配人数：${totalNum};答卷人数:${examNum};未答卷人数:${noExamNum}
    </div>
    <table border="1px" cellspacing="1" cellpadding="1" width="100%" class="inputTable" style="border: 1px solid #000000">
        <tr class="inputTr">
            <th >题目序号</th>
            <th >题目</th>
            <th >答案</th>
            <th >选择率</th>
        </tr>
        <c:forEach items="${questions}" var="question" varStatus="status">
            <tr class="inputTr">
                <td align="center" width="10%" nowrap="true" rowspan="${fn:length(question.options)}" style="border-color: #dcdcdc">
                        ${status.index+1}
                </td>
                <td class="container" width="40%"  rowspan="${fn:length(question.options)}">
                        ${question.questionTitle}
                </td>
                <td class="container"  >
                        ${question.options[0].optionNo}.<a href="javascript:void(0)" title="查看选择人员" onclick="viewResult('${question.options[0].optionId}','${question.questionId}')"> ${question.options[0].optionTitle} </a>
                </td>
                <td class="container" width="10%">
                        ${question.options[0].percent} %
                </td>
            </tr >
            <c:forEach items="${question.options}" var="option" begin="1">
                <tr class="inputTr">
                    <td class="container" >
                            ${option.optionNo}.<a href="javascript:void(0)" title="查看选择人员" onclick="viewResult('${option.optionId}','${question.questionId}')"> ${option.optionTitle} </a>
                    </td>
                    <td class="container" >
                            ${option.percent}%
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
</div>
</body>
</html>