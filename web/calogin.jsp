<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML+RDFa 1.1//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter"%>
<%@ page import="com.comet.core.utils.Constant" %>
<%@include file="common/taglibs.jsp"%>
<%
    String str = request.getContextPath();

    if(str.equals("/")){
        Constant.CONTEXT_PATH = str;
    } else {
        Constant.CONTEXT_PATH = str + "/";
    }
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="${ctx}/js/jquery/jquery-1.7.2.js" type="text/javascript"></script>
    <link href="${ctx}/skin/css/login.css" media="screen" rel="stylesheet" type="text/css">
    <link href="${ctx}/skin/dialog.css" media="screen" rel="stylesheet" type="text/css">

</head>

<body class="userlogin_body">
    <div style="width:100%"  align="center">
        <div class="login_bg_div">
            <div style="padding-top:150px;padding-left:0px">
            
				<br />
                <br />
                <br />
                <br />
                <br />
                <table width="367" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="42%" nowrap>
                            <a href="https://10.15.69.238/calogin_action.jsp"><img src="${ctx}/skin/images/login.png" border="0" width="99" height="36" /></a>
                        </td>
                    </tr>
					<%
						if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
					%>
					<tr>
                        <td width="42%" nowrap>
                           ${SPRING_SECURITY_LAST_EXCEPTION.message}
                        </td>
                    </tr>
					<%
						}
					%>
                </table>
            </div>
        </div>
    </div>
</body>
</html>