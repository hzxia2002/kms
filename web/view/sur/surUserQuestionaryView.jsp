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
                        问卷ID:
                    </td>
                    <td class="container">
                      ${bean.questionary}
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
                        起始时间:
                    </td>
                    <td class="container">
                      ${bean.startTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        结束时间:
                    </td>
                    <td class="container">
                      ${bean.endTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        答题时间:
                    </td>
                    <td class="container">
                      ${bean.answerTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        创建时间:
                    </td>
                    <td class="container">
                      ${bean.createTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        创建人(记录帐号）:
                    </td>
                    <td class="container">
                      ${bean.createUser}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        更新时间:
                    </td>
                    <td class="container">
                      ${bean.updateTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        更新人(记录帐号）:
                    </td>
                    <td class="container">
                      ${bean.updateUser}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>