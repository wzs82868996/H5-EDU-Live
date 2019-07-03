package h5EDULive.web.query;

import com.alibaba.fastjson.JSONObject;
import h5EDULive.service.TeacherCourseService;
import h5EDULive.web.dto.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class TeacherCourseController {

    private final TeacherCourseService teacherCourseService;

    public TeacherCourseController(TeacherCourseService teacherCourseService) {
        this.teacherCourseService = teacherCourseService;
    }

    @ResponseBody
    @RequestMapping("/teacher/list")
    public List<String> getList(int id){
        return teacherCourseService.getList(id);
    }

    @ResponseBody
    @RequestMapping("/teacher/add")
    public boolean add(int tchId, int courseId){
        return teacherCourseService.insert(tchId, courseId);
    }

    @ResponseBody
    @RequestMapping("/teacher/delete")
    public JSONObject delete(int courseId){
        teacherCourseService.delete(courseId);
        return JsonResult.strToJson("删除成功");
    }

    @ResponseBody
    @RequestMapping("/teacher/modify")
    public JSONObject modify(int courseId, String dep){
        teacherCourseService.modifyInfo(courseId, dep);
        return JsonResult.strToJson("修改成功");
    }


    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject uploadVideo(MultipartFile file) throws IllegalStateException, IOException {
        if (file.isEmpty()) {
            return JsonResult.strToJson("上传失败") ;
        }
        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        String pikId = UUID.randomUUID().toString().replaceAll("-", "");
        String newVideoName = pikId + "." + fileExt;

        String savePaths = "/users/videos";
        File fileSave = new File(savePaths, newVideoName);
        file.transferTo(fileSave);

        /* 存储视频url */
        teacherCourseService.upload();

        /* 返回url */
        String webPaths = "http://39.106.107.209:8080/" + newVideoName;
        return JsonResult.strToJson(webPaths);
    }

}
