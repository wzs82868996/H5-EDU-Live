
/*弹出信息编辑浮窗*/

function showEditDiv () {
/*初始化编辑框*/
    $("#nickname").val($("#Name").text());

    $("#cover").show();
    $("#editDiv").show();
}

/*隐藏信息编辑浮窗*/
function hideEditDiv () {
    $("#editDiv").hide();
    $(".cover").css('display','none');
}

/*申请成为教师*/
function teacherApplication(){
    var user={};
    user.name=$("#Name");
    $.ajax("/submit",{
        type:"post",
        dataType:"json",
        contentType : "application/json",
        data : user,
        success:function (response) {
            if(response==='SUCCESS'){
                alert("申请提交成功");
            }
            else{
                alert("申请提交失败");
            }
        },error:function () {
            alert("申请提交失败");
        }
    })
}

/*控制简介字数*/
var max=200;
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
    if(strlen>200){
        $("#mod_profile").val(txtval.substring(0,max));
    }else {
        strlen = max - strlen;
        $("#count").empty();
        $("#count").append(strlen);
    }
}

/*提交个人信息给服务器*/
function save () {
    /*获取编辑框内的信息*/
    var nickname=$("#nickname").val();
    var gender=$('input:radio[name="gender"]:checked').val();
    var profession=$('input:radio[name="profession"]:checked').val();
    var birthday_year=$("#select_year").val();
    var birthday_month=$("#select_month").val();
    var birthday_day=$("#select_day").val();
    var province=$("#eprovinceName").val();
    var city=$("#ecityName").val();
    var country=$("#edistrictName").val();
    var profile=$("#mod_profile").val();
    var birthday=birthday_year+'-'+birthday_month+'-'+birthday_day;
    var zone=province+'-'+city+'-'+country;

    /*更新个人信息页面*/
    $("#Name").text(nickname);
    if(gender==="male"){
        $("#Gender").text("男");
    }
    else if(gender==="female"){
        $("#Gender").text("女");
    }
    if(profession==="student"){
        $("#Major").text("学生");
    }
    else if(profession==="teacher"){
        $("#Major").text("教师");
    }
    $("#Zone").text(zone);
    $("#Birthday").text(birthday);
    $("#Profile").text(profile);
    $(".mine").text(nickname);

    /*提交给服务器端*/
    var user={
        nickname:'',
        gender:'',
        profession:'',
        birthday:'',
        zone:'',
        profile:'',
    };
    user.name=$("#phone").text();
    user.password=$("#password").text();
    user.nickname=nickname;
    user.gender=gender;
    user.profession=profession;
    user.birthday=birthday;
    user.zone=zone;
    user.profile=profile;
    $.ajax("/modify", {
        type:"post",
        dataType:"json",
        contentType : "application/json",
        data : user,
        success:function (response) {
            if(response==='SUCCESS'){
                alert("信息修改成功！");
            }
            else{
                alert("信息修改失败！");
                return false;
            }
        },
        error:function () {
            alert("信息修改失败！");
            return false;
        }
    });
    //hideEditDiv();
    //hideEditDiv();
}

















