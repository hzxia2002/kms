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
            var tree = $("#catalogTree").juiceTree({
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
    }
    else{
        nodeId = node.data.id;
        loadCollect(nodeId);
    }
}

function loadContent(pageNo){
    var url = context_path+"/page/myStudy.do";
    if(pageNo){
        url += "&pageNo=" + pageNo;
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
        url += "&pageNo=" + pageNo;
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
        dataType: "text",
        success:function(ret){
            $("#studyContent").html(ret);
        }
    });
}