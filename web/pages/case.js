var i = 1;
function goNext(){
    if(i<$("#total").val()){
        i++;
    }
    toPage(i);
}

function goPrev(){
    if(i>1){
        i--;
    }
    toPage(i);
}

function toPage(){
    $("#pic").attr('src',$("#path").val()+"/pack"+i+".png");
    $("#page").val(i);
}

function loadPPT(path,total){
    var htmlArr = [];
    htmlArr.push('<div   style="width: 370px;height:222px;position: relative;border: 1px solid #dcdcdc;background-color: #d2d8ff"> ');
    htmlArr.push('   <img src="'+path+'/pack1.png" style="width: 370px;height: 222px;z-index: 1" id="pic"> ');
    htmlArr.push('   <div style="position: absolute; left: 0px;bottom:105px;cursor: pointer;z-index: 11"> ') ;
    htmlArr.push('   <div class="prev" onclick="goPrev()"></div>') ;
    htmlArr.push('   </div>') ;
    htmlArr.push('   <div style="position: absolute; right: 0px;bottom:105px;cursor: pointer;z-index: 11">') ;
    htmlArr.push('   <div class="next" onclick="goNext()"></div>') ;
    htmlArr.push('   </div>') ;
    htmlArr.push('   <div style="position: absolute; right: 150px;bottom: -2px;cursor: pointer;z-index: 11">') ;
    htmlArr.push('       <input type="text" value="1" id="page" width="60px" style="width: 20px; text-align: center;border:1px solid #dcdcdc " />/'+total+'页') ;
    htmlArr.push('       <input type="hidden" value="'+total+'" id="total"/>') ;
    htmlArr.push('       <input type="hidden" value="'+path+'" id="path"/>') ;
    htmlArr.push('    </div>') ;
    htmlArr.push('</div>') ;
    if(total){
        $("#ppt").html(htmlArr.join(""));
    }
    $("#page").change(function(){
        i = parseInt($("#page").val());
        if(i){
            if(i>$("#total").val()){
                i = $("#total").val();
            }else if(i<1){
                i = 1;
            }
        } else{
            i = 1;
        }

        toPage();
    });
}

function loadAVI(path){
    var htmlArr = [];
    htmlArr.push('<OBJECT ID="hutia" height="483" width="627" CLASSID="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6"> ');
    htmlArr.push('   <param name="URL" value="'+path+'" /> ');
    htmlArr.push('   <param name="AutoStart" value="true" /> ') ;
    htmlArr.push('   <param name="AudioStream" value="-1">') ;
    htmlArr.push('   <param name="AutoSize" value="0">') ;
    htmlArr.push('   <param name="AnimationAtStart" value="0">') ;
    htmlArr.push('   <param name="AllowScan" value="true">') ;
    htmlArr.push('   <param name="AllowChangeDisplaySize" value="-1">') ;
    htmlArr.push('</OBJECT>') ;
    $("#media").html(htmlArr.join(""));
}

$(function(){

})