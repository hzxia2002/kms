<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.comet.core.config.CustomizedPropertyPlaceholderConfigurer" %>
<%
    String serverIp = StringUtils.defaultIfEmpty((String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("http.serverip"), "");
    serverIp = serverIp + request.getContextPath();
%>

<script type="text/javascript">
    window.location.href = "<%=serverIp%>/page/index.do";
</script>