$(function(){

    $.jui.controls.Tree.prototype.expandAll = function(){
        var g = this, p = this.options;
        $(".roots_close", g.tree).click();
        $(".root_close", g.tree).click();
    };
    $.ajax({
        url: context_path+"/page/cagalogTree.do?treeType=zfbacxk",
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
    if(node.data.id>0){
        nodeId = node.data.id;
        $("#contentTitle").html(node.data.text);
        loadContent(nodeId);
    }
}

function loadContent(id,pageNo){
    var url = context_path+"/page/getContent.do?treeType=zfbacxk";
    if(id){
        url += "&id=" + id;
    }
    if(pageNo){
        url += "&pageNo=" + pageNo;
    }
    $.ajax({
        url:url,
        dataType: "json",
        success:function(ret){
            var datas = ret.data;
            var total = ret.total;
            var htmlArr = [];
            var pNo = ret.page;
            for(var i=0;i<datas.length;i++){
                htmlArr.push('<li style="width: 375px;float: left;margin-left: 12px;padding-left:5px;border-bottom:1px dashed gray">' +
                    '<a target="_blank" href="view.html?id='+datas[i]["id"]+'"><div style="color:blue;overflow: hidden;text-overflow:ellipsis; width:260px;float:left;white-space:nowrap;" >'+datas[i]["title"]+
                    '</div><span style="color:black;font-size:12px;float:left;">&nbsp;('+datas[i]["publishDate"].substring(0,16)+')</span>'+'</a></li>');
            }
            $("#content").html(htmlArr.join(""));
            createPage(total,pNo);
        }
    });
}


function createPage(total,pageNo){
    var htmlArr = [];
    if(total>1){
        var min = 1;
        var max = total>=5?5:total;
        if(pageNo>5){
            min = pageNo - 4;
            max = pageNo;
        }
        htmlArr.push('<span style="float:left;">共计'+total+'条记录</span>');
        if(min>1){
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(1)+')" >'+'第一页'+'</a></span>');
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(min-1)+')" >'+'上一页'+'</a></span>');
        }
        for(var i=min;i<=max;i++){
            var color = (i==pageNo)?"red":"blue";
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:'+color+'" onclick="loadContent(\''+nodeId+'\','+i+')" >'+i+'</a></span>');
        }
        if(max<total&&max>=5){
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(max+1)+')" >'+'下一页'+'</a></span>');
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(total)+')" >'+'最后一页'+'</a></span>');
        }
        $("#page").html(htmlArr.join(""));
    }
}