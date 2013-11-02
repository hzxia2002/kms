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
                    <td  align="right">
                        课程名称:
                    </td>
                    <td  class="container">
                        <input type="text" name="name" value="${bean.name}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        课程描述:
                    </td>
                    <td  class="container">
                        <input type="text" name="remark" value="${bean.remark}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        知识点ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="article" value="${bean.article}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        开始时间:
                    </td>
                    <td  class="container">
						 <input type="text"  name="startTime" class="jui-dateEditor">&nbsp;

                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        结束时间:
                    </td>
                    <td  class="container">
						 <input type="text"  name="endTime" class="jui-dateEditor">&nbsp;

                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        学习时长(分钟):
                    </td>
                    <td  class="container">
                        <input type="text" name="studyDuration" value="${bean.studyDuration}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        创建时间:
                    </td>
                    <td  class="container">
						 <input type="text"  name="createTime" class="jui-dateEditor">&nbsp;

                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        创建人(记录帐号）:
                    </td>
                    <td  class="container">
                        <input type="text" name="createUser" value="${bean.createUser}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        更新时间:
                    </td>
                    <td  class="container">
						 <input type="text"  name="updateTime" class="jui-dateEditor">&nbsp;

                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        更新人(记录帐号）:
                    </td>
                    <td  class="container">
                        <input type="text" name="updateUser" value="${bean.updateUser}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#cmsStudyCourseEditForm').validate();
    });
</script>