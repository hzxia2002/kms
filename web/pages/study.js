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
}
