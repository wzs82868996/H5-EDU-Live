
/*更新课程页面*/
$(document).ready(function () {
    $.ajax("/teacher/list",{
        type:"post",
        dataType:"json",
        contentType : "application/json",
        data :' ',
        success:function (data) {
            var courseInfo=eval("("+data+")");
            var str='';
            var myCourse=$(".tab_box");
            myCourse.empty();
            if(courseInfo===0){
                myCourse.append(str);
            }else{
                var id='';
                var name='';
                var lecture='';
                var depiction='';
                var pubdate='';
                var video='';
                var label='';
                var pic='';
                //对其中的课程进行信息遍历
                for(var i in courseInfo){
                    var course=courseInfo[i];
                    id=course.course_id;
                    name=course.name;
                    lecture=course.lecture;
                    depiction=course.depiction;
                    pubdate=course.pubdate;
                    video=course.video;
                    label=course.label;
                    pic=course.pic;
                    str='';
                    //根据课程进度进行分类
                        str+='<li>'+'<div class="courseli">'+'<a href=video target="_blank">'+'<img width="230" src="pic" alt="depiction">'+'</a>'+
                            '<p class="memb_courname">'+'<a href=video class="blacklink">'+name+'</a>'+'</p>'+ '<div class="mpp">'+ '<div class="lv" style="width:20%;">'+
                            '</div>'+ '</div>'+ '<p class="goon">'+'<a href=video>'+'<span>'+'查看课程'+'</span>'+'</a>'+'</p>'+ '</div>' +'</li>';
                        $("#publicCourse").append(str);
                }
            }
        },
        error:function () {
            alert("课程加载失败！");
        }
    });
    //根据是否直播控制是否上传视频
    $("input:radio[name='course']").change(function () {
        var style=$('input:radio[name="course"]:checked').val();
        if(style==="live"){
            $("#picture").attr("disabled","disabled");
            $("#video").attr("disabled","disabled");
        }
        else if(style==="video"){
            $("#picture").attr("disabled",false);
            $("#video").attr("disabled",false);
        }
    });
});


/*弹出浮窗*/
function showEditDiv () {
    /*初始化编辑框*/
    $("input:text").val(' ');
    $("#mod_profile").val('');
    $("input:radio").attr('checked',false);
    $("input:file").val('');
    $("#publishDiv").show();
}
/*隐藏浮窗*/
function hideEditDiv () {
    $("#publishDiv").hide();
}



/*监听事件*/
$('input:text[name="name"]').on("keypress",function () {
    $("#name_check").text(" ");
});
$('input:text[name="duration"]').on("keypress",function(){
        $("#duration_check").text(" ");
});
$('input:radio[name="style"]').on("change",function(){
    $("#style_check").text(" ");
});
$('#mod_profile').on("keypress",function(){
        $("#profile_check").text(" ");
});
$("#picture").on("click",function(){
    $("#picture_check").text(" ");
});
$('#video').on("click",function(){
    $("#video_check").text(" ");
});

/*控制简介字数*/
var max=300;
var strlen ;
var txtval=0;
//判断是不是中文字符
function isChinese(str){
    var reCh = /[u00-uff]/;
    return !reCh.test(str);
}
//检查还可以输入多少字
function numChange(){
    strlen=0;
    txtval = $.trim($("#mod_profile").val());
    for(var i =0;i<txtval.length;i++) {
        if (isChinese(txtval.charAt(i)) === true) {
            strlen = strlen + 2;
        } else {
            strlen = strlen + 1;
        }
    }
    strlen = Math.ceil(strlen / 2); //中英文相加除2取整数
    if(strlen>300){
        $("#mod_profile").val(txtval.substring(0,max));
    }else {
        strlen = max - strlen;
        $("#count").empty();
        $("#count").append(strlen);
    }
}


function upload() {
    var name = $("#name_box").val();
    var label = $("#label_box").val();
    var duration = $("#duration_box").val();
    var profile = $("#mod_profile").val();
    var style = $("input:radio[name='course']:checked").val();
    var pic = '';
    var course = {};
    course.name = name;
    course.label = label;
    course.duration = duration;
    course.profile = profile;
    course.style = style;
    course.pic = $("#picture").val();
    var file = '';
    //检查输入
    if(name===" "){
       $("#name_check").text("请输入课程名！");
    }
    else if(duration===" "){
        $("#duration_check").text("请输入课程时长！");
    }
    else if(profile===" "){
       $("#profile_check").text("请输入课程简介！")
    }
    else if(style===''){
        $("#style_check").text("请选择教学方式")
    }
    else if (style === "video") {
        file = $("#video").val();
        if(file===''){
            $("#video_check").text("请选择上传视频！")
        }
        else if(pic===''){
            $("#image_check").text("请选择视频封面！")
        }
    } else if (style === "live") {
        file = "此课程将进行直播";
        pic = "";
    }
    $.ajax("/upload", {
        type: "post",
        dataType: "json",
        contentType: "application/json",
        data: "{file:" + file + ",course:" + course + "}",
        success: function (data) {
            //添加新课程
            var nCourse = eval("(" + data + ")");
            var str = '';
            var id = nCourse.course_id;
            var name = nCourse.name;
            var lecture = nCourse.lecture;
            var depiction = nCourse.depiction;
            var pubdate = nCourse.pubdate;
            var video = nCourse.video;
            var label = nCourse.label;
            var pic = nCourse.pic;
            //根据课程进度进行分类
            str += '<li>' + '<div class="courseli">' + '<a href=video target="_blank">' + '<img width="230" src=pic alt="depiction">' + '</a>' +
                '<p class="memb_courname">' + '<a href=video class="blacklink">' + name + '</a>' + '</p>' + '<div class="mpp">' + '<div class="lv" style="width:20%;">' +
                '</div>' + '</div>' + '<p class="goon">' + '<a href=video>' + '<span>' + '查看课程' + "(发布时间" + pubdate + ')' + '</span>' + '</a>' + '</p>' + '</div>' + '</li>';
            $("#publicCourse").append(str);
            if(style==="live"){
                $.load("39.106.107.209:5080/demos.live.html");
            }
        },
        error: function () {
            alert("课程上传失败");
        }
    })
}
















