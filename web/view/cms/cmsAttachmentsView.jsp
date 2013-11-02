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
                        知识点ID:
                    </td>
                    <td class="container">
                      ${bean.knowledge}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        文件原名:
                    </td>
                    <td class="container">
                      ${bean.orgName}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        修改后的名称:
                    </td>
                    <td class="container">
                      ${bean.targetName}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        扩展名:
                    </td>
                    <td class="container">
                      ${bean.extName}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        附件目录:
                    </td>
                    <td class="container">
                      ${bean.path}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        上传时间:
                    </td>
                    <td class="container">
                      ${bean.uploadDate}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        上传人员:
                    </td>
                    <td class="container">
                      ${bean.uploader}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>