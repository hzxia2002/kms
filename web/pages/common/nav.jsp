<%@ page import="com.comet.core.security.util.SpringSecurityUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function(){
        var pageType = $("#pageType").attr("type");
        if(pageType){
            $("#pageIndex"+pageType).addClass("tag");
        }

        $("#key").focus(function(){
            if($("#key").val() == '请输入关键词') {
                $("#key").val("");
            }
        });

        $("#search_button").click(function(){
            var key = $.trim($("#key").val());

            if(key == null || key == '' || key == '请输入关键词') {
                alert('请输入检索关键词');
                return;
            }

            $("#searchForm").submit();
        });
    });
</script>
<%
String initValue = StringUtils.defaultIfEmpty((String)request.getAttribute("key"), "请输入关键词");
%>
<div class="Header">
    <div class="logo">
        <div class="title"><img src="../skin/images/title.png" /></div>
        <div class="search">
            <form id="searchForm" name="searchForm" action="${ctx}/search/search.do" method="post">
            <div class="kuang"><input name="key" id="key" type="text" class="input11" value="<%=initValue%>" />
            </div>
            <div class="kuang1" ><img id="search_button" src="../skin/images/button_searth.jpg" width="92" height="33"/>
            </div>
            </form>
            <div style="text-align: right;">
                <%
                    if(SpringSecurityUtils.getCurrentUser() != null) {
                %>
                欢迎您,<%=SpringSecurityUtils.getCurrentUser().getRealName()%>！
                <a href="<c:url value="/j_spring_security_logout"/>">退出登录</a>
                <%
                    }
                %>
            </div>
        </div>

    </div>

    <div class="nav">
        <ul>
            <li class="n1"><a href="<c:url value="/page/index.html"/>" id="pageIndex1" >首页</a></li>
            <li class="n1"><a href="<c:url value="/page/lawProgramIndex.html"/>" id="pageIndex2">执法办案程序库</a></li>
            <li class="n1"><a href="<c:url value="/page/kmsIndex.html"/>" id="pageIndex3">侦查知识点库</a></li>
            <li class="n1"><a href="<c:url value="/page/caseIndex.html"/>" id="pageIndex4">技战法案例课程库</a></li>
            <li class="n1"><a href="<c:url value="/page/studyIndex"/>" id="pageIndex5">学习银行</a></li>
            <li class="n1"><a href="<c:url value="/page/questionIndex"/>" id="pageIndex6">咨询栏目</a></li>
            <li class="n1"><a href="../main.jsp">系统管理</a></li>
        </ul>
    </div>
</div>