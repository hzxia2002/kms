<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function(){
        var pageType = $("#pageType").attr("type");
        if(pageType){
            $("#pageIndex"+pageType).addClass("tag");
        }
    });
</script>
<div class="Header">
    <div class="logo">
        <div class="title"><img src="../skin/images/title.png" /></div>
        <div class="search">
            <div class="kuang"><input name="" type="text" class="input1" value="请输入关键词" />
            </div>
            <div class="kuang1"><img src="../skin/images/button_searth.jpg" width="92" height="33" />
            </div>
        </div>
    </div>

    <div class="nav">
        <ul>
            <li class="n1"><a href="index.html" id="pageIndex1" >首页</a></li>
            <li class="n1"><a href="lawProgramIndex.html" id="pageIndex2">执法办案程序库</a></li>
            <li class="n1"><a href="kmsIndex.html" id="pageIndex3">侦查知识点库</a></li>
            <li class="n1"><a href="caseIndex.html" id="pageIndex4">技战法案例课程库</a></li>
            <li class="n1"><a href="studyIndex" id="pageIndex5">学习银行</a></li>
            <li class="n1"><a href="../main.jsp">系统管理</a></li>
        </ul>
    </div>
</div>