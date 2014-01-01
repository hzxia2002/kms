function checkAnswer(){
    var size = $("#questionSize").val();
    for(var i=1;i<=size;i++){
        var selectOptions = $("input[answerIndex='question_"+i+"']");
        var isSelected = false;
        for(var j=0;j<selectOptions.length;j++){
            if($(selectOptions[j]).attr("checked")){
                isSelected = true;
                break;
            }
        }
        if(!isSelected){
            alert("第"+i+"题没有选择");
            return false;
        }
    }

    return true;
}