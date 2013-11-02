<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsCommentLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="cmsCommentQuery" title="查询窗口"  icon="search">
            <form id="cmsCommentForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="200">
                            评论知识点：<input type="text" value="" class="table_input" id="knowledge" name="knowledge.title" op="like" entity="t" style="width:120px"/>
                        </td>
                        <td width="200">
                            评论内容：<input type="text" value="" class="table_input" id="content" name="content" op="like" entity="t" style="width:120px"/>
                        </td>
                        <td width="200">
                            提交人：<input type="text" value="" class="table_input" id="replyer_displayName" name="replyer_displayName" op="like" entity="t" style="width:120px"/>
                        </td>
                        <td width="70">
                            提交时间：
                        </td>
                        <td width="100" align="right">
                            <input type="text" value="" class="jui-dateEditor" name="replyTime" op="greatAndEq" entity="t" dType="date"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="100">
                            <input type="text" value="" class="jui-dateEditor" name="replyTime" op="lessAndEq" entity="t" dType="date"/>
                        </td>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsCommentGrid','cmsCommentForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsCommentGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsComment/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $.jui.parse();

        //布局
        $("#cmsCommentLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsCommentGrid").setWidth(width);
            $.jui.get("cmsCommentQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsCommentQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#cmsCommentGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '评论知识点', name: 'knowledge.title', width: 300,hide:false },
				{display: '评论内容', name: 'content', width: 400},
				{display: '评论人', name: 'replyer.displayName', width: 100,hide:false },
				{display: '评论时间', name: 'replyTime', width: 150,hide:false },
				{display: '查看', name: 'view', width: 60,hide:false, render: titleRender }
            ]
//        enabledEdit: true
        });
        //创建树

    });

    function doView(id) {
        var url = "${ctx}/cmsComment/view.do";

        commonView(url, "cmsCommentGrid", null, id);
    }

    function doDelete(){
        commonDelete("cmsCommentGrid","${ctx}/cmsComment/delete.do");
    }

    function doEdit(id){
        var url = "${ctx}/cmsComment/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsCommentGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsCommentGrid",null,"cmsCommentForm",{title:"编辑评论",height:530,width:600});
        }
    }

    function titleRender(item, index, value) {
        return "<a href='javascript:doView(" + item["id"] + ")' title='点击查看'>查看</a>";
    }
</script>

