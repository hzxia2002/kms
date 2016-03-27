<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="common/header.jsp"%>
    <script src="${ctx}/pages/consult.js" type="text/javascript"></script>
    <link href="${ctx}/skin/default.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="Header">
    <%@include file="common/nav.jsp"%>
    <div style="display: none;" id="pageType" type="6"></div>
</div>
<div class="clr"></div>
<form id="questionForm" action="${ctx}/page/submitQuestion" method="post" onsubmit="return checkForm();">
    <div class="ContentHome">
        <div class="right" style="width: 100%">

            <div class="one" style="width: 100%">
                <div class="title" id="contentTitle">咨询</div>
                <div class="main1">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabkuang">
                        <tr>
                            <td align="right" class="tabtit" width="20%">
                               <span style="color: red">*</span>
                                主题:
                            </td>
                            <td  class="tabtit" width="70%" colspan="2">
                                <input type="text" name="title" id="title"  style="height: 22px;width:600px;border: #71ccff 1px solid;" maxlength="100">
                            </td>
                        </tr>
                        <tr>
                            <td align="right" class="tabtit" width="30%">  <span style="color: red">*</span>内容(最多2000字):</td>
                            <td  class="tabtit" width="20%">
                                <textarea  name="content" id="content" rows="12" cols="110" style="border: #71ccff 1px solid;" title="提问内容" ></textarea>
                            </td>
                            <td style="padding-left: 2px">                                <span id="contentMsg" style="color: red;display: none">请输入提问内容</span>
                            </td>
                        </tr>
                        <tr style="padding-top: 12px;height: 50px;">
                            <td align="center" class="tabtit" colspan="3">
                                <input type="submit" value="提交" class="btn_Ok">
                                <input type="button" value="返回" class="btn_out" onclick="history.go(-1);">
                            </td>

                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div class="clr"></div>
    </div>
</form>
<%@include file="common/footer.jsp"%>
</body>
</html>
