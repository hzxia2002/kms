<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="exaPaperSectionEditForm" modelAttribute="bean" name="exaPaperSectionEditForm" action="${ctx}/exaPaperSection/save.do" method="post">
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
                        题库ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="db" value="${bean.db}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        章节名称:
                    </td>
                    <td  class="container">
                        <input type="text" name="sectionName" value="${bean.sectionName}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        每题分数:
                    </td>
                    <td  class="container">
                        <input type="text" name="perScore" value="${bean.perScore}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        题目数量:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionNums" value="${bean.questionNums}" />&nbsp;
                    </td>
					
                </tr>
		         <tr class="inputTr">
		           
                    <td  align="right">
                        试题难易程度:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionLevel" value="${bean.questionLevel}" />&nbsp;
                    </td>
					
		           
                    <td  align="right">
                        试题题型:
                    </td>
                    <td  class="container">
                        <input type="text" name="questionType" value="${bean.questionType}" />&nbsp;
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
        v = $('#exaPaperSectionEditForm').validate();
    });
</script>