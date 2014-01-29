$(function(){
    var callback = function(data){
       alert(data.msg);
    };
    $("#addCollect").click(function(){
        var url =  "collectInit.do?title="+encodeURI($("#title").html())+"&articleId="+$("#articleId").val();
//        var settings = {
//            height: 350,
//            title:"添加到收藏夹",
//            url: "collectInit.do?title="+encodeURI($("#title").html())+"&articleId="+$("#articleId").val(),
//            width: 450,
//            buttons:[
//                { text: '保存', onclick: function (item, dialog) {
//                    var g = this;
//                    var frameWin = dialog.jiframe[0].contentWindow|| dialog.jiframe[0].contentWindow.document ;
//
//                    if(frameWin&&frameWin.v.form()){
//                        if(frameWin["customerValidate"]&&!frameWin["customerValidate"]()) {
//                            return;
//                        }
//                        submitFormData("configTableEditForm","collect.do",dialog,null,callback)
//                    }else{
//                        return;
//                    }
//                    dialog.close();
//                } },
//                { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
//            ]
//        };
//        window.top.$.juiceDialog.open(settings);
        var iWidth=400; //窗口宽度
        var iHeight=250;//窗口高度
        var iTop=(window.screen.height-iHeight)/2;
        var iLeft=(window.screen.width-iWidth)/2;
        window.showModalDialog(url,window,"dialogHeight: "+iHeight+"px; dialogWidth: "+iWidth+"px;dialogTop: "+iTop+"; dialogLeft: "+iLeft+"; resizable: no; location:no;status: no;scroll:no");
 //       $.ajax({
//            url:"collect.do",
//            data:{url:window.location.href},
//            success:function(){
//                alert(12)
//            }
//        });
    });

    $("#print").click(function(){
        var url =  "printView.do?articleId="+$("#articleId").val();

        var iWidth=880; //窗口宽度
        var iHeight=600;//窗口高度
        var iTop=(window.screen.height-iHeight)/2;
        var iLeft=(window.screen.width-iWidth)/2;
        window.showModalDialog(url,window,"dialogHeight: "+iHeight+"px; dialogWidth: "+iWidth+"px;dialogTop: "+iTop+"; dialogLeft: "+iLeft+"; resizable: no; location:no;status: no;scroll:yes;");
    });
    loadComments("");
});





function submitComment(id){
    var comment = $.trim($("#comment").val());
    if(comment == null || comment == '') {
        alert('请选填写内容！');
        return;
    }

    $.ajax({
        url: CONTEXT_PATH+"/page/comment.do?id="+id,
        dataType: "json",
        type:"post",
        data:{content:comment,commentType:$("input[name='commentType']").val()},
        success:function(ret){
            alert(ret.msg);
            $("#comment").val("");
            loadComments("",$("#articleId").val());
        }
    });
}

function loadComments(pageNo){
    var url = CONTEXT_PATH+"/page/loadComment.do?id="+$("#articleId").val();
    if(pageNo){
        url += "&pageNo=" + pageNo;
    }
    $.ajax({
        url:url,
        dataType: "text",
        success:function(ret){
            $("#comments").html(ret);
        }
    });
}
