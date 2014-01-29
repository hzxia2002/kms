<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.comet.core.config.CustomizedPropertyPlaceholderConfigurer" %>
<%
    String url = StringUtils.defaultIfEmpty((String)request.getAttribute("url"), "/index.jsp");

    if(url.indexOf("index.jsp") > -1) {
        response.sendRedirect(CustomizedPropertyPlaceholderConfigurer.getContextProperty("http.serverip") + request.getContextPath() + url);
    } else {
        response.sendRedirect(CustomizedPropertyPlaceholderConfigurer.getContextProperty("http.serverip") + request.getContextPath() + url);
    }
%>
