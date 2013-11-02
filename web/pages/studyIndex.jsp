<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="leftxxyhkuang">
        <div class="leftxxyh">
            <div class="title"><img src="../skin/images/sc.png" width="16" height="16" />&nbsp;&nbsp;我的收藏</div>
            <div class="tree"><img src="../skin/images/tree.png" width="241" height="203" /></div>
        </div>
        <div class="leftxxyh1"><img src="../skin/images/ksdl.jpg" width="246" height="39" /></div>
    </div>
    <div class="rightxxyh">
        <div class="one">
            <div class="title">学习任务</div>
            <div class="main">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabkuang">
                    <tr>
                        <td align="center" class="tabtit">序号</td>
                        <td align="center" class="tabtit">课程名称</td>
                        <td align="center" class="tabtit">完成情况</td>
                        <td align="center" class="tabtit">进度</td>
                        <td align="center" class="tabtit">学习时间</td>
                    </tr>
                    <tr>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                    </tr>
                    <tr>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                    </tr>
                    <tr>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                    </tr>
                    <tr>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                        <td bgcolor="#f9f9f9" class="tabmain">&nbsp;</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
