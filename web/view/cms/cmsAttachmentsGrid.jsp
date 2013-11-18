<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="cmsAttachmentsLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="知识点附件树" id="accordion1" style="overflow:auto;height:95%;">
        <ul id="cmsAttachmentsTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="cmsAttachmentsQuery" title="查询窗口"  icon="search">
            <form id="cmsAttachmentsForm" action="">
                <table style="width: 100%">
                    <tr>
					  <td>
                         <input type="button" value="查询" class="btn_Search" onclick="javascript:search('cmsAttachmentsGrid','cmsAttachmentsForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsAttachmentsGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsAttachments/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#cmsAttachmentsLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsAttachmentsGrid").setWidth(width);
            $.jui.get("cmsAttachmentsQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#cmsAttachmentsQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '增加', click: doAdd, icon: 'add' },
                { line: true },
                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#cmsAttachmentsGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '知识点ID', name: 'knowledge', width: 50,hide:true },
				{display: '文件原名', name: 'orgName', width: 50,hide:true },
				{display: '修改后的名称', name: 'targetName', width: 50,hide:true },
				{display: '扩展名', name: 'extName', width: 50,hide:true },
				{display: '附件目录', name: 'path', width: 50,hide:true },
				{display: '上传时间', name: 'uploadDate', width: 50,hide:true },
				{ display: '上传人员', name: 'uploader', width: 50,hide:true }
            ]
//        enabledEdit: true
        });
        //创建树

    });
    //树的右键菜单
    function createMenu(treeNode){
        var menu;
        if(treeNode.data.uid == 'root') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
//                        { text: '修改', click:function(item){doEdit(treeNode.uid)}},
//                        { text: '查看', icon:'add', click:function(item){doView(treeNode.uid)}}
                { line: true },
                { text: '刷新', click:function(item){refreshNode();}}
            ]
            });
        } else {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
                        { text: '修改', click:function(item){doEdit(treeNode.data.uid)}},
//                        { text: '查看', icon:'view', click:function(item){doView(treeNode.data.uid)}},
                { line: true },
                { text: '上移', click:function(item){doMoveup(treeNode)}},
                { text: '下移', click:function(item){doMovedown(treeNode)}},
                { line: true },
                { text: '刷新', click:function(item){refreshNode();}}
            ]
            });
        }
        return menu;
    }


     function doView(id) {
        var url = "${ctx}/cmsAttachments/view.do";

        commonView(url, "cmsAttachmentsGrid");
    }

    function doDelete(){
        commonDelete("cmsAttachmentsGrid","${ctx}/cmsAttachments/delete.do",refreshRootNode);
    }

    function doAdd(){        
        var url = "${ctx}/cmsAttachments/init.do";
        commonAddOrUpdate(url,"cmsAttachmentsGrid",null,"cmsAttachmentsGridForm",{title:"新增知识点附件",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/cmsAttachments/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"cmsAttachmentsGrid");
        }
        if(url){
            commonAddOrUpdate(url,"cmsAttachmentsGrid",null,"cmsAttachmentsForm",{title:"编辑知识点附件",height:530,width:600});
        }
    }


</script>

