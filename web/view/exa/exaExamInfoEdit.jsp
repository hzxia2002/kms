<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaExamInfoEditForm" modelAttribute="bean" name="exaExamInfoEditForm" action="${ctx}/exaExamInfo/save.do" method="post">
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
                        用户ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="user" value="${bean.user}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        试卷ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="paper" value="${bean.paper}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        开始答题时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="startTime" class="jui-dateEditor" showTime="true">
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        结束时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="endTime" class="jui-dateEditor" showTime="true">
                    </td>
					
		           
                    <td  align="right">
                        IP:
                    </td>
                    <td  class="container">
                        <input type="text" name="ip" value="${bean.ip}" />&nbsp;
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
                        状态：1已答卷，0未答卷:
                    </td>
                    <td  class="container">
                        <input type="text" name="status" value="${bean.status}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        描述:
                    </td>
                    <td  class="container">
                        <input type="text" name="remark" value="${bean.remark}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        创建时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="createTime" class="jui-dateEditor" showTime="true">
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        更新时间:
                    </td>
                    <td  class="container">
					    <input type="text"  name="updateTime" class="jui-dateEditor" showTime="true">
                    </td>
					
		           
                    <td  align="right">
                        更新人:
                    </td>
                    <td  class="container">
                        <input type="text" name="updateUser" value="${bean.updateUser}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        创建人:
                    </td>
                    <td  class="container">
                        <input type="text" name="createUser" value="${bean.createUser}" />&nbsp;
                    </td>
                         <td width="25%">
                          &nbsp;
                         </td>
						 <td width="25%">
                          &nbsp;
                         </td>
					
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#exaExamInfoEditForm').validate();
    });
</script>