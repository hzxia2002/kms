<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaQuestionEditForm" modelAttribute="bean" name="exaQuestionEditForm" action="${ctx}/exaQuestion/save.do" method="post">
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
                        题库ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="db" value="${bean.db}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        题目类型(1单选，2多选，3填空，4判断，5问答
):
                    </td>
                    <td  class="container">
                        <input type="text" name="questionType" value="${bean.questionType}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        难易程度:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionLevel" value="${bean.questionLevel}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        题目来源:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionFrom" value="${bean.questionFrom}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        状态:
                    </td>
                    <td  class="container">
                        <input type="text" name="status" value="${bean.status}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        内容:
                    </td>
                    <td  class="container">
                        <input type="text" name="content" value="${bean.content}" />&nbsp;
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
                        选择题答案:
                    </td>
                    <td  class="container">
                        <input type="text" name="skey" value="${bean.skey}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        文字描述答案:
                    </td>
                    <td  class="container">
                        <input type="text" name="keyDesc" value="${bean.keyDesc}" />&nbsp;
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
        v = $('#exaQuestionEditForm').validate();
    });
</script>