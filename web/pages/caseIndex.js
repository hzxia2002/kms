$(function(){

    $.jui.controls.Tree.prototype.expandAll = function(){
        var g = this, p = this.options;
        $(".roots_close", g.tree).click();
        $(".root_close", g.tree).click();
    };
    $.ajax({
        url: context_path+"/page/cagalogTree.do?treeType=jzfalkck",
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
        loadContent(nodeId);
    }
}

function loadContent(id,pageNo){
    var url = context_path+"/page/getContent.do?pageSize=10&treeType=jzfalkck";
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
            var records = ret.records;

            var htmlArr = [];
            var pNo = ret.page;
            for(var i=0;i<datas.length;i++){
                htmlArr.push('<li style="width: 375px;float: left;margin-left: 12px;padding-left:5px;border-bottom:1px dashed gray">' +
                    '<a target="_blank" href="view.html?id='+datas[i]["id"]+'"><div style="color:blue;overflow: hidden;text-overflow:ellipsis; width:290px;float:left;white-space:nowrap;" >'+datas[i]["title"]+
                    '</div><span style="color:black;font-size:12px;float:left;">&nbsp;['+datas[i]["publishDate"].substring(0,10)+']</span>'+'</a></li>');
            }
            $("#content").html(htmlArr.join(""));
            createPage(total,pNo,records);
//            var datas = ret.data;
//            var total = ret.total;
//            var records = ret.records;
//            var htmlArr = [];
//            var pNo = ret.page;
//            for(var i=0;i<datas.length;i++){
//                htmlArr.push('<li style="display:inline;position: relative;float: '+(i%2==0?"left;margin-left: 6%;":"right;margin-right:6%;")+'width: 41%;height: 180px;margin-bottom: 15px;">');
//                htmlArr.push('    <div style="width: 100%">');
//                htmlArr.push('        <img src="../skin/images/kck1_bg.jpg" width="100%" height="180px;">');
//                htmlArr.push('    </div>');
//                htmlArr.push('    <div style="float: left;z-index: 10;position: absolute;top:3px;left:3px;" >');
//                htmlArr.push('        <a href="showCase.html?id='+datas[i].id+'" target="_blank">');
//                htmlArr.push('          '+datas[i].title);
//                htmlArr.push( '       </a>');
//                htmlArr.push( '    </div>');
//                htmlArr.push( '    <div style="float: left;z-index: 10;position: absolute;top:50px;left:60px;" >');
//                htmlArr.push( '       <span>'+datas[i].author+'</span>');
//                htmlArr.push( '    </div>');
//                htmlArr.push( '    <div style="float: left;z-index: 10;position: absolute;bottom:20px;left:15px;" >');
////                htmlArr.push( '       <span>侦查办案民警 法制员<br />上海市公安局经侦总队</span>');
//                htmlArr.push( '    </div>');
//                htmlArr.push( '    <div style="float: left;z-index: 10;position: absolute;bottom:20px;right:15px;" >');
//
//                if(""+datas[i].path != ''){
//                    htmlArr.push( '        <img src="'+context_path+datas[i].path+'" style="height:104px;width:118px;" alt="">');
//                }
//
//                htmlArr.push( '     </div>');
//                htmlArr.push( '</li>');
////
////                htmlArr.push('<li style="width: 49%;float: left;padding-left: 1%;  height:170px;background:url('+context_path+datas[i]["path"]+')">' +
////                    '<a target="_blank" style="color:blue" href="view.html?id='+datas[i]["id"]+'">'+datas[i]["title"]+
////                    '<span style="color:black;font-size:12px;">&nbsp;('+datas[i]["publishDate"]+')</span>'+'</a></li>');
//            }
//            $("#content").html(htmlArr.join(""));
//            createPage(total,pNo,records);
        }
    });
}


function createPage(total,pageNo,records){
    var htmlArr = [];
    if(total>0){
        var min = 1;
        var max = total>=5?5:total;
        if(pageNo>5){
            min = pageNo - 4;
            max = pageNo;
        }
        htmlArr.push('<span style="float:left;color: #000000;">共计'+records+'条记录</span>');
        if(min>1){
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(1)+')" >'+'第一页'+'</a></span>');
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(pageNo-1)+')" >'+'上一页'+'</a></span>');
        }
        for(var i=min;i<=max;i++){
            var color = (i==pageNo)?"red":"blue";
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:'+color+'" onclick="loadContent(\''+nodeId+'\','+i+')" >'+i+'</a></span>');
        }
        if(max<total&&max>=5){
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(pageNo+1)+')" >'+'下一页'+'</a></span>');
            htmlArr.push('<span style="padding-left:5px;padding-right:5px;"><a href="javascript:void(0)" style="color:blue" onclick="loadContent(\''+nodeId+'\','+(total)+')" >'+'最后一页'+'</a></span>');
        }
        $("#page").html(htmlArr.join(""));
    } else{
        $("#page").html('<span style="float:left;">共计'+records+'条记录</span>');
    }
}