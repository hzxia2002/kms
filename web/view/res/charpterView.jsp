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
                        排序号:
                    </td>
                    <td class="container">
                      ${bean.indexNo}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        描述:
                    </td>
                    <td class="container">
                      ${bean.description}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>