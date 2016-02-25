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
                        试卷ID:
                    </td>
                    <td class="container">
                      ${bean.paper}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        题库ID:
                    </td>
                    <td class="container">
                      ${bean.db}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        章节名称:
                    </td>
                    <td class="container">
                      ${bean.sectionName}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        每题分数:
                    </td>
                    <td class="container">
                      ${bean.perScore}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        题目数量:
                    </td>
                    <td class="container">
                      ${bean.questionNums}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        试题难易程度:
                    </td>
                    <td class="container">
                      ${bean.questionLevel}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        试题题型:
                    </td>
                    <td class="container">
                      ${bean.questionType}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        描述:
                    </td>
                    <td class="container">
                      ${bean.remark}
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
                        更新时间:
                    </td>
                    <td class="container">
                      ${bean.updateTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        更新人:
                    </td>
                    <td class="container">
                      ${bean.updateUser}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        创建人:
                    </td>
                    <td class="container">
                      ${bean.createUser}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>