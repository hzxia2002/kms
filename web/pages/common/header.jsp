<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%--<title>经侦辅助办案知识库</title>--%>
<link href="${ctx}/skin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/jui/skins/Aqua/css/jui-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jui/js/jui-all.js"></script>
<script src="${ctx}/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/js/application/common.js" type="text/javascript"></script>
<script>
    var context_path = "${ctx}";
</script>
