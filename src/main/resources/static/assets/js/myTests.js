

/*更新考试页面*/
$(document).ready(function () {
    $.ajax("/homepage-exam/summaries",{
        type:"post",
        dataType:"json",
        contentType : "application/json",
        data :'userId',
        success:function (data) {
            var examInfo=eval("("+data+")");
            var str='';
            var myExams=$(".tab_box");
            myExams.empty();
            if(examInfo===0){
                myExams.append(str);
            }else{
                var courseId='';
                var name='';
                var totalScore='';
                var subScore=[];
                var maxScore='';
                var time='';
                var coursePic=[];
                //对其中的课程进行信息遍历
                for(var i in examInfo){
                    var exam=examInfo[i];
                    courseId=exam.courseId;
                    name=exam.name;
                    time=exam.time;
                    totalScore=exam.score;
                    coursePic=exam.coursePic;
                    maxScore=exam.maxScore;
                    subScore=exam.subScore;
                    str='';
                    //根据课程进度进行分类
                    if(totalScore!==null){//查看考试结果
                        str+='<li>'+'<div class="courseli">'+'<a href="#" target="_blank" onclick="exam_details(courseId)">'+'<img width="230" src="image" alt=depiction>'+'</a>'+
                            '<p class="memb_courname">'+'<a href="#" class="blacklink" onclick="exam_details(courseId)">'+name+'</a>'+'</p>'+ '<div class="mpp">'+ '<div class="lv" style="width:20%;">'+
                            '</div>'+ '</div>'+ '<p class="goon">'+'<a href="#" onclick="exam_details(courseId)">'+'<span>'+'查看考试'+'</span>'+'</a>'+'</p>'+ '</div>' +'</li>';
                        $("#examed").append(str);
                    }
                    else{//查看新考试
                        str+='<li>'+'<div class="courseli">'+'<a href="#" target="_blank">'+'<img width="230" src="pic" alt=depiction>'+'</a>'+
                            '<p class="memb_courname">'+'<a href="#" class="blacklink">'+name+'</a>'+'</p>'+ '<div class="mpp">'+ '<div class="lv" style="width:20%;">'+
                            '</div>'+ '</div>'+ '<p class="goon">'+'<a href="#">'+'<span>'+'开始考试'+'</span>'+'</a>'+'</p>'+ '</div>' +'</li>';
                        $("#toExam").append(str);
                    }
                }
            }
        },
        error:function () {
            alert("信息加载失败！");
        }
    })
});

function exam_details(course_id){
    $.ajax("/homepage-exam/exam/detail",{
        type:"post",
        dataType:"json",
        contentType : "application/json",
        data :'{ userId:'+userId+',courseId:'+course_id+'}',
        success:function (data) {
            //保存课程信息
            var name = data.name;
            var label = data.label;
            var lecture = data.lecture;
            var time = data.time;
            var duration = data.duration;
            var maxScore = data.maxScore;
            var subtitle=[];
            subtitle=data.subtitle;
            var subtitleScore=[];
            subtitleScore = data.subtitleScore;
            var solutions=[];
            solutions = data.solutions;
            var answers=[];
            answers = data.answers;
            var subScore=[];
            subScore  = data.subScore;
            var totalScore = data.totalScore;

            //更新考试页面
            var frame=$("iframe");
            frame.html("")



        }

})




}












