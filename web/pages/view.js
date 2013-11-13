$(function(){
    var callback = function(data){
       alert(data.msg);
    };
    $("#addCollect").click(function(){
        var settings = {
            height: 350,
            title:"添加到收藏夹",
            url: "collectInit.do?title="+encodeURI($("#title").html())+"&articleId="+$("#articleId").val(),
            width: 450,
            buttons:[
                { text: '保存', onclick: function (item, dialog) {
                    var g = this;
                    var frameWin = dialog.jiframe[0].contentWindow|| dialog.jiframe[0].contentWindow.document ;

                    if(frameWin&&frameWin.v.form()){
                        if(frameWin["customerValidate"]&&!frameWin["customerValidate"]()) {
                            return;
                        }
                        submitFormData("configTableEditForm","collect.do",dialog,null,callback)
                    }else{
                        return;
                    }
                    dialog.close();
                } },
                { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
            ]
        };
        window.top.$.juiceDialog.open(settings);

//        $.ajax({
//            url:"collect.do",
//            data:{url:window.location.href},
//            success:function(){
//                alert(12)
//            }
//        });
    });
});

function submitComment(id){
    var comment = $("#comment").val().trim();

    if(comment == null || comment == '') {
        alert('请选填写内容！');
        return;
    }

    $.ajax({
        url: context_path+"/page/comment.do?id="+id,
        dataType: "json",
        type:"post",
        data:{content:comment,commentType:$("input[name='commentType']").val()},
        success:function(ret){
            alert(ret.msg);
            $("#comment").val("");
        }
    });
}