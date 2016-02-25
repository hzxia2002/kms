<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaPaperDetailEditForm" modelAttribute="bean" name="exaPaperDetailEditForm" action="${ctx}/exaPaperDetail/save.do" method="post">
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
                        试卷ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="paper" value="${bean.paper}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        题目ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="question" value="${bean.question}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        章节ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="section" value="${bean.section}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        分数:
                    </td>
                    <td  class="container">
                        <input type="text" name="score" value="${bean.score}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        排序:
                    </td>
                    <td  class="container">
                        <input type="text" name="orderNo" value="${bean.orderNo}" />&nbsp;
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
        v = $('#exaPaperDetailEditForm').validate();
    });
</script>