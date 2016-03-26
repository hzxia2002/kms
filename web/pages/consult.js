/**
 * Created by tcg on 2016/3/26.
 */
$(document).ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#questionForm").validate({
        rules: {
            title: "required",
            content:{
                required: true,
                minlength: 10
            }
        },
        messages: {
            title: "请输入主题",
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
    return ret;
}