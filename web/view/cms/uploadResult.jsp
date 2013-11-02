<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <script>
        function returnData(){
           <c:if test="${docId==null}">
            if(parent.setPicPath){
                parent.setPicPath(${ret});
            }
            </c:if>
            <c:if test="${docId!=null}">
            if(parent.setFilePath){
                parent.setFilePath(${ret},${docId});
            }
            </c:if>
            if(parent.closeDialog){
                parent.closeDialog();
            }
        }
    </script>
</head>
<body>
<form:form id="fileUploadForm" modelAttribute="bean" name="fileUploadForm" >
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td >
                    ${msg}
            </td>
            <td  class="container">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <input type="button" value="确定" class="btn_Ok" onclick="returnData();"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>
