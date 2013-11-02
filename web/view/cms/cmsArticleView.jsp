<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<div>
    <table border="0" cellspacing="1" width="80%" class="inputTable">

        <tr class="inputTr">
            <td  align="right" style="widht:45px">
                文件分类:
            </td>
            <td  class="container">
                ${bean.path.name}
            </td>
            <td  align="right">
                标题:
            </td>
            <td  class="container">
               ${bean.title}&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                作者:
            </td>
            <td  class="container">
                 ${bean.author}
            </td>
            <td  align="right">
                是否推荐:
            </td>
            <td  class="container">
                ${isRecommend?"是":"否"}
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right">
                摘要:
            </td>
            <td  class="container" colspan="3">
                ${bean.summery}
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                关键字:
            </td>
            <td  class="container">
                ${bean.keyWord}
            </td>
            <td  align="right">
                是否发布:
            </td>
            <td  class="container">
                ${isPublished?"是":"否"}
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                是否有效:
            </td>
            <td  class="container">
                ${isValid?"是":"否"}
            </td>
            <td  align="right">
                是否置顶:
            </td>
            <td  class="container">
                ${isTop?"是":"否"}
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                学习开始时间:
            </td>
            <td  class="container">
                <fmt:formatDate value="${bean.studyStartTime}" type="both"></fmt:formatDate>
            </td>
            <td  align="right">
                学习结束时间:
            </td>
            <td  class="container">
                <fmt:formatDate value="${bean.studyEndTime}" type="both"></fmt:formatDate>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                课时(小时):
            </td>
            <td  class="container" colspan="3">
                ${bean.classHour}
            </td>

        </tr>
        <tr class="inputTr">
            <td  align="right">
                内容:
            </td>
            <td  class="container" colspan="3">
                ${bean.content}
            </td>
        </tr>

    </table>

</div>
</body>
</html>