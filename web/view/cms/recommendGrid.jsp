<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsArticleLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="cmsArticleQuery" title="查询窗口"  icon="search">
            <form id="cmsArticleForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="10%" align="right">
                            标题：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input" id="title" name="title" op="like" entity="t"/>
                        </td>

                        <td width="10%" align="right">
                            关键字：
                        </td>
                        <td width="20%">
                            <input type="text" value="" class="table_input" id="keyWord" name="keyWord" op="like" entity="t"/>
                        </td>
                        <td width="10%">
                            <select type="text" value="" class="table_input" id="isPublished" name="isPublished" op="eq" entity="t">
                                <option value="">全部</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </td>
                        <td>
                            <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsArticleGrid','cmsArticleForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsArticleGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsArticle/recommendGrid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsArticleLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsArticleGrid").setWidth(width);
            $.jui.get("cmsArticleQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsArticleQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '取消推荐', click: doCancelRecommend, icon:'delete' }
            ]
        };
        //grid
        $("#cmsArticleGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: 'ID', name: 'id', width: 50,hide:true },
                {display: '文件目录', name: 'path.name', width: "10%" },
                {display: '标题', name: 'title', width: "10%" },
                {display: '关键字', name: 'keyWord', width: "15%" },
//				{display: '内容', name: 'content', width: 50 },

                {display: '发布时间', name: 'publishDate', width: "14%" },
                {display: '发布人', name: 'publisher.loginName', width: "10%" },
                {display: '访问次数', name: 'visitTimes', width: "10%" ,render:visitCount}
//                {display: '点击次数', name: 'clickNum', width: "10%" }
            ]
//        enabledEdit: true
        });
        //创建树
        //创建树
    });



    function doView(id) {
        var url = "${ctx}/cmsArticle/view.do";

        commonView(url, "cmsArticleGrid");
    }

    function doCancelRecommend(){
        commonDelete("cmsArticleGrid","${ctx}/cmsArticle/cancelRecommend.do");
    }

    function visitCount(item,index,value){
       return value!=null?value:"0";
    }
</script>

