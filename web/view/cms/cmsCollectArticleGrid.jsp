<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsCollectArticleLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="cmsCollectArticleQuery" title="查询窗口"  icon="search">
            <form id="cmsCollectArticleForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="180">
                            用户姓名：<input type="text" value="" class="table_input" style="width: 100px;" id="user_displayName" name="user_displayName" op="like" entity="t"/>
                        </td>
                        <td width="280">
                            收藏知识点：<input type="text" value="" class="table_input" style="width: 150px;" id="article_title" name="article_title" op="like" entity="t"/>
                        </td>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsCollectArticleGrid','cmsCollectArticleForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsCollectArticleGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsCollectArticle/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsCollectArticleLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsCollectArticleGrid").setWidth(width);
            $.jui.get("cmsCollectArticleQuery").setWidth(width);
        }
        });

        //查询框初始化
        $("#cmsCollectArticleQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#cmsCollectArticleGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '目录', name: 'catagory.name', width: 150},
				{display: '用户', name: 'user.displayName', width: 100},
				{display: '收藏知识点', name: 'article.title', width: 450}
            ]
//        enabledEdit: true
        });
        //创建树

    });

    function doDelete(){
        commonDelete("cmsCollectArticleGrid","${ctx}/cmsCollectArticle/delete.do");
    }
</script>

