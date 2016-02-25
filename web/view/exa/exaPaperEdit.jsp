<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaPaperEditForm" modelAttribute="bean" name="exaPaperEditForm" action="${ctx}/exaPaper/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
		         <tr class="inputTr">
		           
                    <td  align="right">
                        ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="id" value="${bean.id}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        试卷名称:
                    </td>
                    <td  class="container">
                        <input type="text" name="paperName" value="${bean.paperName}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        开始时间:
                    </td>
                    <td  class="container">
                        <input type="text" name="startTime" value="${bean.startTime}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        结束时间:
                    </td>
                    <td  class="container">
                        <input type="text" name="endTime" value="${bean.endTime}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        作答时间:
                    </td>
                    <td  class="container">
                        <input type="text" name="paperMinute" value="${bean.paperMinute}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        试卷总分:
                    </td>
                    <td  class="container">
                        <input type="text" name="totalScore" value="${bean.totalScore}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        时间排序类型:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionOrderType" value="${bean.questionOrderType}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        发布时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="postTime" class="jui-dateEditor" showTime="true">
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        显示分数时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="showScoreTime" class="jui-dateEditor" showTime="true">
                    </td>
					
		           
                    <td  align="right">
                        是否随机时间:
                    </td>
                    <td  class="container">
                        <input type="text" name="isRandPaper" value="${bean.isRandPaper}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        试卷状态(1开放，-1不开放):
                    </td>
                    <td  class="container">
                        <input type="text" name="status" value="${bean.status}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        备注:
                    </td>
                    <td  class="container">
                        <input type="text" name="remark" value="${bean.remark}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        创建时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="createTime" class="jui-dateEditor" showTime="true">
                    </td>
					
		           
                    <td  align="right">
                        更新时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="updateTime" class="jui-dateEditor" showTime="true">
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        更新人:
                    </td>
                    <td  class="container">
                        <input type="text" name="updateUser" value="${bean.updateUser}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        创建人:
                    </td>
                    <td  class="container">
                        <input type="text" name="createUser" value="${bean.createUser}" />&nbsp;
                    </td>
					
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#exaPaperEditForm').validate();
    });
</script>