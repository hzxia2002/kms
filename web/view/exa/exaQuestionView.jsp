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
                        题库ID:
                    </td>
                    <td class="container">
                      ${bean.db}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        题目类型(1单选，2多选，3填空，4判断，5问答
):
                    </td>
                    <td class="container">
                      ${bean.questionType}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        难易程度:
                    </td>
                    <td class="container">
                      ${bean.questionLevel}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        题目来源:
                    </td>
                    <td class="container">
                      ${bean.questionFrom}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        状态:
                    </td>
                    <td class="container">
                      ${bean.status}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        内容:
                    </td>
                    <td class="container">
                      ${bean.content}
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
                        选择题答案:
                    </td>
                    <td class="container">
                      ${bean.skey}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        文字描述答案:
                    </td>
                    <td class="container">
                      ${bean.keyDesc}
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