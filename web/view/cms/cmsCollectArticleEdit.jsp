<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsCollectArticleEditForm" modelAttribute="bean" name="cmsCollectArticleEditForm" action="${ctx}/cmsCollectArticle/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td  align="right">
                收藏夹ID:
            </td>
            <td  class="container">
                <input type="text" name="catagory" value="${bean.catagory}" />&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                用户ID:
            </td>
            <td  class="container">
                <input type="text" name="user" value="${bean.user}" />&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                链接地址:
            </td>
            <td  class="container">
                <input type="text" name="url" value="${bean.url}" />&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                备注名称:
            </td>
            <td  class="container">
                <input type="text" name="remark" value="${bean.remark}" />&nbsp;
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#cmsCollectArticleEditForm').validate();
    });
</script>