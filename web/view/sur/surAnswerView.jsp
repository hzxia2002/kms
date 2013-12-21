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
                        问题ID:
                    </td>
                    <td class="container">
                      ${bean.question}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        选项ID:
                    </td>
                    <td class="container">
                      ${bean.option}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        用户ID:
                    </td>
                    <td class="container">
                      ${bean.user}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        分数:
                    </td>
                    <td class="container">
                      ${bean.score}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        用户调研卷ID:
                    </td>
                    <td class="container">
                      ${bean.userQuestionary}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>