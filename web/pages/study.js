$(function(){
   setInterval("inform()",60500);
});

function inform(){
    $.ajax({
        url: context_path+"/study/inform.do?planId="+$("#planId").val(),
        dataType: "json",
        success:function(datas){

        }
    });

    $("#addCollect").click(function(){
        var url =  "collectInit.do?title="+encodeURI($("#title").html())+"&articleId="+$("#articleId").val();

        var iWidth=400; //窗口宽度
        var iHeight=250;//窗口高度
        var iTop=(window.screen.height-iHeight)/2;
        var iLeft=(window.screen.width-iWidth)/2;
        window.showModalDialog(url,window,"dialogHeight: "+iHeight+"px; dialogWidth: "+iWidth+"px;dialogTop: "+iTop+"; dialogLeft: "+iLeft+"; resizable: no; localtion:no;status: no;scroll:no");
    });
}
