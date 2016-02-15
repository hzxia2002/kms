<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML+RDFa 1.1//EN" "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://ogp.me/ns/fb#" xml:lang="en" lang="en">
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
    <link href="${ctx}/skin/login.css" media="screen" rel="stylesheet" type="text/css">
    <link href="${ctx}/skin/dialog.css" media="screen" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function reloadImage(){
            document.getElementById("pic").src = "<c:url value="/common/imageCode.jsp"/>?" + Math.random();;
        }

        function submitForm(){
            $("#dlg_flash_msg").hide();
            var form = document.getElementById("loginForm");
            var loginName = document.getElementById("j_username").value;
            var password = document.getElementById("j_password").value;
            var validation_code = document.getElementById("j_validation_code").value;

            if(loginName == null || loginName == "" || loginName == "请输入用户名或邮箱"){
                alert("用户名必须输入！");
                document.getElementById("j_username").focus();
                return;
            }

            if(password == null || password == "" || password == "请输入登录密码"){
                alert("密码必须输入！");
                document.getElementById("j_password").focus();
                return;
            }

            if(validation_code == null || validation_code == ""){
                alert("验证码必须输入！");
                document.getElementById("j_validation_code").focus();
                return;
            }

            form.submit();
        }
    </script>
</head>

<body class="userlogin_body">
<form id="loginForm" name="loginForm" method="post" action="index.html">
    <div style="width:100%"  align="center">
        <div class="login_bg_div">
            <div style="padding-top:150px;padding-left:0px">              <br />
                <br />
                <br />
                <br />
                <br />
                <table width="367" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="42%" nowrap><img src="Content/themes/base/login/images/login.png" width="99" height="36" /></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>