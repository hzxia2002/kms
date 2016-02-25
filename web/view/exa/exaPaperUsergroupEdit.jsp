<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaPaperUsergroupEditForm" modelAttribute="bean" name="exaPaperUsergroupEditForm" action="${ctx}/exaPaperUsergroup/save.do" method="post">
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
                        角色ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="role" value="${bean.role}" />&nbsp;
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
        v = $('#exaPaperUsergroupEditForm').validate();
    });
</script>