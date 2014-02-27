<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsCourseArticleEditForm" modelAttribute="bean" name="cmsCourseArticleEditForm" action="${ctx}/cmsCourseArticle/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        文章:
                    </td>
                    <td  class="container">
                        <input type="text" name="article" value="${bean.article}" />&nbsp;
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
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#cmsCourseArticleEditForm').validate();
    });
</script>