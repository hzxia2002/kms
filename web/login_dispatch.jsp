<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.comet.core.config.CustomizedPropertyPlaceholderConfigurer" %>
<%
    String url = StringUtils.defaultIfEmpty((String)request.getAttribute("url"), "/index.jsp");
    String serverIp = StringUtils.defaultIfEmpty((String)CustomizedPropertyPlaceholderConfigurer.getContextProperty("http.serverip"), "");

    if(url.indexOf("index.jsp") > -1) {
        response.sendRedirect(serverIp + request.getContextPath() + url);
    } else {
        response.sendRedirect(serverIp + request.getContextPath() + url);
    }
%>
