/**
 * Created by tcg on 2016/3/26.
 */
$(document).ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#questionForm").validate({
        rules: {
            title: {
                required: true,
                minlength: 5,
                maxlength:100
            },
            content:{
                required: true,
                minlength: 10
            }
        },
        messages: {
            title: {
                required:"请输入主题",
                minlength:"至少输入5个字符",
                maxlength:"最多输入100个字符"
            },
            content:
            {
                required: "请输入内容",
                minlength: "内容不能少于10个字符"
            }
        }
    });

    $("#content").blur(function(){
        validateContent();
    }).focus(function(){
        $("#contentMsg").hide();
        $("#contentLenMsg").hide();
    });
});

function checkForm(){
    return validateContent();
}


function validateContent(){
    var ret = true;
    if($("#content").val().replace(/\r/g, "").length==0){
        $("#contentMsg").show();
        ret =  false;
    }
    if($("#content").val().length>2000){
        $("#contentLenMsg").show();
        $("#content").val($("#content").val().substr(0,2000));
        ret =  false;
    }
    return ret;
}