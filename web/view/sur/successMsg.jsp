<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>操作成功</title>
    <script>
        function closePage(){
            window.close();
        }
    </script>
</head>
<body>
<p style="height: 100px;">保存成功,<a href="javascript:void(0)" onclick="closePage();">关闭该窗口</a></p>
<%@ include file="footer.jsp" %>
</body>
</html>