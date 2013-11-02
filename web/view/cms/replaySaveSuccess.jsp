<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%

    response.sendRedirect(request.getContextPath()+"/cmsArticle/detail.do?id="+request.getAttribute("id"));
%>