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
                        试卷名称:
                    </td>
                    <td class="container">
                      ${bean.paperName}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        开始时间:
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
                        作答时间:
                    </td>
                    <td class="container">
                      ${bean.paperMinute}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        试卷总分:
                    </td>
                    <td class="container">
                      ${bean.totalScore}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        时间排序类型:
                    </td>
                    <td class="container">
                      ${bean.questionOrderType}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        发布时间:
                    </td>
                    <td class="container">
                      ${bean.postTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        显示分数时间:
                    </td>
                    <td class="container">
                      ${bean.showScoreTime}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        是否随机时间:
                    </td>
                    <td class="container">
                      ${bean.isRandPaper}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        试卷状态(1开放，-1不开放):
                    </td>
                    <td class="container">
                      ${bean.status}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        备注:
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