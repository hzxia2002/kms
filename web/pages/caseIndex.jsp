<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script type="text/javascript" src="${ctx}/pages/caseIndex.js"></script>
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="${type}"></div>
</div>
<div class="clr"></div>
<div class="ContentHome">
    <div class="left">
        <div class="title">技战法案例课程库</div>
        <div class="tree">
            <ul id="catalogTree" style="margin-top:3px;"></ul>
        </div>
    </div>
    <div class="right">
        <div class="one">
            <div class="title">课程展示</div>
            <div class="maintp">
                <br />
                <ul width="80%"  id="content" style="height: 250px;">
                </ul>
                <div id="page" align="right">
                </div>
                <%--<table width="98%" border="0" align="center">--%>
                    <%--<tr>--%>
                        <%--<td  class="text"><table width="290" border="0" align="center" cellpadding="0" cellspacing="0" background="../skin/images/kck1_bg.jpg">--%>
                            <%--<tr>--%>
                                <%--<td height="183" valign="top" class="text"><table width="290" height="170" border="0" cellpadding="0" cellspacing="0">--%>
                                    <%--<tr>--%>
                                        <%--<td height="34" colspan="2">《恶意透支型信用卡诈骗案件的侦查》</td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td width="150" height="50" align="center">周晓军</td>--%>
                                        <%--<td width="124" rowspan="2" align="center"><img src="../skin/images/kck1_zp.jpg" width="104" height="118" /></td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td height="50" align="center">侦查办案民警 法制员<br />--%>
                                            <%--上海市公安局经侦总队</td>--%>
                                    <%--</tr>--%>
                                <%--</table></td>--%>
                            <%--</tr>--%>
                        <%--</table></td>--%>
                        <%--<td><table width="290" border="0" align="center" cellpadding="0" cellspacing="0" background="../skin/images/kck1_bg.jpg">--%>
                            <%--<tr>--%>
                                <%--<td height="183" valign="top" class="text"><table width="290" height="170" border="0" cellpadding="0" cellspacing="0">--%>
                                    <%--<tr>--%>
                                        <%--<td height="34" colspan="2">《恶意透支型信用卡诈骗案件的侦查》</td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td width="150" height="50" align="center">周晓军</td>--%>
                                        <%--<td width="124" rowspan="2" align="center"><img src="../skin/images/kck1_zp.jpg" alt="" width="104" height="118" /></td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td height="50" align="center">侦查办案民警 法制员<br />--%>
                                            <%--上海市公安局经侦总队</td>--%>
                                    <%--</tr>--%>
                                <%--</table></td>--%>
                            <%--</tr>--%>
                        <%--</table>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>&nbsp;</td>--%>
                        <%--<td>&nbsp;</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><table width="290" border="0" align="center" cellpadding="0" cellspacing="0" background="../skin/images/kck1_bg.jpg">--%>
                            <%--<tr>--%>
                                <%--<td height="183" valign="top" class="text"><table width="290" height="170" border="0" cellpadding="0" cellspacing="0">--%>
                                    <%--<tr>--%>
                                        <%--<td height="34" colspan="2">《恶意透支型信用卡诈骗案件的侦查》</td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td width="150" height="50" align="center">周晓军</td>--%>
                                        <%--<td width="124" rowspan="2" align="center"><img src="../skin/images/kck1_zp.jpg" width="104" height="118" /></td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td height="50" align="center">侦查办案民警 法制员<br />--%>
                                            <%--上海市公安局经侦总队</td>--%>
                                    <%--</tr>--%>
                                <%--</table></td>--%>
                            <%--</tr>--%>
                        <%--</table></td>--%>
                        <%--<td><table width="290" border="0" align="center" cellpadding="0" cellspacing="0" background="../skin/images/kck1_bg.jpg">--%>
                            <%--<tr>--%>
                                <%--<td height="183" valign="top" class="text"><table width="290" height="170" border="0" cellpadding="0" cellspacing="0">--%>
                                    <%--<tr>--%>
                                        <%--<td height="34" colspan="2">《恶意透支型信用卡诈骗案件的侦查》</td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td width="150" height="50" align="center">周晓军</td>--%>
                                        <%--<td width="124" rowspan="2" align="center"><img src="../skin/images/kck1_zp.jpg" alt="" width="104" height="118" /></td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td height="50" align="center">侦查办案民警 法制员<br />--%>
                                            <%--上海市公安局经侦总队</td>--%>
                                    <%--</tr>--%>
                                <%--</table></td>--%>
                            <%--</tr>--%>
                        <%--</table></td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
                <br />
            </div>
        </div>
    </div>

    <div class="clr"></div>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>
