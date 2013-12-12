var tree;
$(function(){

    $.jui.controls.Tree.prototype.expandAll = function(){
        var g = this, p = this.options;
        $(".roots_close", g.tree).click();
        $(".root_close", g.tree).click();
    };
    $.ajax({
        url: context_path+"/page/studyTree.do",
        dataType: "json",
        success:function(datas){
            tree = $("#catalogTree").juiceTree({
                data : datas,
                checkbox: false,
                slide: false,
                idFieldName :'id',
                parentIDFieldName :'pid',
//                nodeWidth: 120,
                attribute: ['nodename', 'url'],
                onSelect: function (node)
                {
                    treeNodeClick(node);
                }
            });

            tree.expandAll();
        }
    });
    loadContent();
});
var nodeId = "";
function treeNodeClick(node){
    if(node.data.id==-2){
        nodeId = node.data.id;
        loadContent();
    }else if(node.data.id==-1){
        loadCollect();
    }else if(node.data.id==-3){
        loadHistory();
    }else{
        nodeId = node.data.id;
        loadCollect(nodeId);
    }
}

function loadContent(pageNo){
    var url = context_path+"/page/myStudy.do";
    if(pageNo){
        url += "?pageNo=" + pageNo;
    }
    $.ajax({
        url:url,
        dataType: "text",
        success:function(ret){
            $("#studyContent").html(ret);
        }
    });
}

function loadHistory(pageNo){
    var url = context_path+"/page/studyHistory.do";
    if(pageNo){
        url += "?pageNo=" + pageNo;
    }
    $.ajax({
        url:url,
        dataType: "text",
        success:function(ret){
            $("#studyContent").html(ret);
        }
    });
}

function loadCollect(id,pageNo){
    var url = context_path+"/page/myCollect.do?";
    if(id){
        url += "&id=" + id;
    }
    if(pageNo){
        url += "&pageNo=" + pageNo;
    }
    $.ajax({
        url:url,
        type:"post",
        data:{articleName:$("#articleName").val()},
        dataType: "text",
        success:function(ret){
            $("#studyContent").html(ret);
        }
    });
}

function deleteCollect(catagoryId,pageNo,id){
    var url = context_path+"/page/deleteCollect.do?id="+id;
    if(confirm("您确定要删除此记录?")){
        $.ajax({
            url:url,
            dataType: "json",
            success:function(ret){
                loadCollect(catagoryId,pageNo);
                alert(ret.msg);
            }
        });
    }

}

function addCatalogue(){
    var url = context_path+"/page/catagoryInit.do";
    commonAddOrUpdate(url,"cmsCollectCatagoryGrid",null,"cmsCollectCatagoryEditForm",{title:"新增收藏夹",height:300,width:500,callback:refreshNode});
}

function editCatalogue(){
    var selectNode =  tree.getSelected();
    if(selectNode&&selectNode.data.id>0){
        if(selectNode.data.type=="no_del"){
            alert("系统默认收藏夹,您不能修改");
            return;
        }
        var url = context_path+"/page/catagoryInit.do?id="+selectNode.data.id;
        commonAddOrUpdate(url,"cmsCollectCatagoryGrid",null,"cmsCollectCatagoryEditForm",{title:"编辑收藏夹",height:300,width:500,callback:refreshNode});
    }else{
        alert("请选择收藏夹节点")
    }
}

function refreshNode(){
    window.location.href = context_path+"/page/studyIndex";
}

function delCatalogue(){
    var selectNode =  tree.getSelected();
    if(selectNode&&selectNode.data.id>0){
        $.ajax({
            url:context_path+"/page/deleteCatalogue.do?id="+selectNode.data.id,
            dataType: "json",
            success:function(ret){
                alert(ret.msg);
                refreshNode();
            }
        });
    }else{
        alert("请选择收藏夹节点")
    }
}

