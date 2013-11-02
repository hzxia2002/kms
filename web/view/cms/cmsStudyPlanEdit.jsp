<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsStudyPlanEditForm" modelAttribute="bean" name="cmsStudyPlanEditForm" action="${ctx}/cmsStudyPlan/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        最后学习时间:
                    </td>
                    <td  class="container">
						 <input type="text"  name="lastStudyTime" class="jui-dateEditor">&nbsp;

                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        最后学习时长:
                    </td>
                    <td  class="container">
                        <input type="text" name="lastStudyDuration" value="${bean.lastStudyDuration}" />&nbsp;
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
                        学习次数:
                    </td>
                    <td  class="container">
                        <input type="text" name="studyTimes" value="${bean.studyTimes}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        用户:
                    </td>
                    <td  class="container">
                        <input type="text" name="user" value="${bean.user}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        课程:
                    </td>
                    <td  class="container">
                        <input type="text" name="course" value="${bean.course}" />&nbsp;
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
        v = $('#cmsStudyPlanEditForm').validate();
    });
</script>