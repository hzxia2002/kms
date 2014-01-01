<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<div>
    <div>
        <ul>
            <c:forEach items="${users}" var="user">
                <li style="float: left;width: 80px;">${user.displayName}</li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>