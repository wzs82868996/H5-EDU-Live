package h5EDULive.web.query;

import com.alibaba.fastjson.JSONObject;
import h5EDULive.dao.domain.User;
import h5EDULive.service.UserService;
import h5EDULive.web.dto.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 描述：用户
 * @Author ZhangRongrong
 */
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* 页面 */
    /* 首页 */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /* 登录 */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /* 登录失败 */
    @RequestMapping("/login-error")
    public String userLoginFail(){
        return "loginFail";
    }

    /* 注册 */
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /* 修改个人信息 */
    @GetMapping("/modify")
    public String modify(){
        return "modify";
    }



    /* 功能 */
    /* 注册验证 */
    @ResponseBody
    @PostMapping("/register/check")
    public JSONObject userRegisterCheck(String mobile)
    {
        if(userService.isExist(mobile))
        {
            return JsonResult.strToJson("用户已存在");
        }else{
            return JsonResult.strToJson("用户不存在");
        }
    }

    /* 注册提交 */
    @ResponseBody
    @PostMapping("/register/submit")
    public JSONObject userRegister(User user){
        if(userService.insert(user))
        {
            return JsonResult.strToJson("注册成功");
        }else{
            return JsonResult.strToJson("注册失败");
        }
    }

    /* 修改密码验证 */
    @ResponseBody
    @PostMapping("/modify/code/check")
    public JSONObject userPreCodeCheck(int id, String preCode)
    {
        if(userService.isRight(id, preCode))
        {
            return JsonResult.strToJson("密码正确");
        }else{
            return JsonResult.strToJson("密码错误");
        }

    }

    /* 修改密码提交 */
    @ResponseBody
    @PostMapping("/modify/code/submit")
    public JSONObject userPasswordModify(int id, String newCode)
    {
        userService.updatePassword(id, newCode);
        return JsonResult.strToJson("修改成功");
    }

    /* 修改用户名 */
    @ResponseBody
    @PostMapping("/modify/name")
    public JSONObject userNameModify(int id, String name)
    {
        userService.updateName(id, name);
        return JsonResult.strToJson("修改成功");
    }

    /* 修改专业 */
    @ResponseBody
    @PostMapping("/modify/major")
    public JSONObject userMajorModify(int id, String major)
    {
        userService.updateMajor(id, major);
        return JsonResult.strToJson("修改成功");
    }

    /* 修改性别 */
    @ResponseBody
    @PostMapping("/modify/gender")
    public JSONObject userGenderModify(int id, String gender)
    {
        userService.updateGender(id, gender);
        return JsonResult.strToJson("修改成功");
    }

    /* 修改生日 */
    @ResponseBody
    @PostMapping("/modify/birth")
    public JSONObject userBirthModify(int id, String birth)
    {
        userService.updateBirth(id, birth);
        return JsonResult.strToJson("修改成功");
    }

    /* 修改头像 */
    @PostMapping("/modify/profile")
    public JSONObject userProfileModify(int id, MultipartFile file) throws IOException {
        /* 上传头像 */
        if (file.isEmpty()) {
            return JsonResult.strToJson("上传失败，未选择文件") ;
        }
        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        String pikId = UUID.randomUUID().toString().replaceAll("-", "");
        String newProfileName = pikId + "." + fileExt;
        String savePaths = "/users/temp";
        File fileSave = new File(savePaths, newProfileName);
        file.transferTo(fileSave);

        /* 存储头像url */
        userService.updateProfile(id, newProfileName);

        /* 返回url */
        String webPaths = "http://39.106.107.209:8080/" + newProfileName;
        return JsonResult.strToJson(webPaths);
    }

    /* 修改邮箱 */
    @ResponseBody
    @PostMapping("/modify/mail")
    public JSONObject userMailModify(int id, String mail)
    {
        userService.updateMail(id, mail);
        return JsonResult.strToJson("修改成功");
    }

    /* 修改地址 */
    @ResponseBody
    @PostMapping("/modify/location")
    public JSONObject userLocationModify(int id, String location)
    {
        userService.updateLocation(id, location);
        return JsonResult.strToJson("修改成功");
    }


}
