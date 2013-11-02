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
                        名称:
                    </td>
                    <td class="container">
                      ${bean.name}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        编码:
                    </td>
                    <td class="container">
                      ${bean.code}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        文件目录:
                    </td>
                    <td class="container">
                      ${bean.path}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        显示图标:
                    </td>
                    <td class="container">
                      ${bean.icon}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        父节点标识:
                    </td>
                    <td class="container">
                      ${bean.parent}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        叶节点:
                    </td>
                    <td class="container">
                      ${bean.isLeaf}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        树形层次:
                    </td>
                    <td class="container">
                      ${bean.treeId}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        类型目录:0  知识点目录;1 图片目录;2 附件目录:
                    </td>
                    <td class="container">
                      ${bean.type}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        是否有效:
                    </td>
                    <td class="container">
                      ${bean.isValid}
                    </td>
                </tr>
                <tr class="inputTr">
                     <td align="right" width="20%" nowrap="true">
                        备注:
                    </td>
                    <td class="container">
                      ${bean.description}
                    </td>
                </tr>
        </table>
    </div>
</body>
</html>