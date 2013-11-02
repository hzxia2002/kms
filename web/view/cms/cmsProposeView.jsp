<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
    <div>
        <table border="0" cellspacing="1" width="100%" class="inputTable">
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        建议知识点:
                    </td>
                    <td class="container">
                      ${bean.knowledge.title}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                         建议内容:
                    </td>
                    <td class="container">
                        <c:out value="${bean.content}" escapeXml="true"/>
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        提交人:
                    </td>
                    <td class="container">
                      ${bean.replyer.displayName}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        提交时间:
                    </td>
                    <td class="container">
                      ${bean.replyTime}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>