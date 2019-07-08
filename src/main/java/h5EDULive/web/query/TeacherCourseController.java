package h5EDULive.web.query;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import h5EDULive.dao.domain.Course;
import h5EDULive.service.CourseService;
import h5EDULive.service.TeacherCourseService;
import h5EDULive.web.dto.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class TeacherCourseController {

    private final TeacherCourseService teacherCourseService;
    private final CourseService courseService;

    public TeacherCourseController(TeacherCourseService teacherCourseService, CourseService courseService) {
        this.teacherCourseService = teacherCourseService;
        this.courseService = courseService;
    }

    @ResponseBody
    @RequestMapping("/teacher/list")
    public JSONArray getList(int id){
        return JsonResult.listToJson(teacherCourseService.getList(id));

    }

    @ResponseBody
    @RequestMapping("/teacher/add")
    public JSONObject add(int tchId, int courseId){
        teacherCourseService.insert(tchId, courseId);
        return JsonResult.strToJson("添加成功");
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
<<<<<<< HEAD
    public JSONObject uploadVideo(MultipartFile file, Course course) throws IllegalStateException, IOException {
=======
    public JSONObject uploadVideo(MultipartFile file) {
>>>>>>> 4f97aca82a6a38af1c1af482d678d0449af70477
        if (file.isEmpty()) {
            return JsonResult.strToJson("上传失败，未选择视频") ;
        }
        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        String pikId = UUID.randomUUID().toString().replaceAll("-", "");
        String newVideoName = pikId + "." + fileExt;

        String savePaths = "/static/videos";
        File fileSave = new File(savePaths, newVideoName);
        try {
            file.transferTo(fileSave);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }

        String webPaths = "http://localhost:8080/stat/videos/" + newVideoName;

        course.setVideo(webPaths);
        /* 存储视频url */
        courseService.insert(course);

        /* 返回url */
        return JsonResult.strToJson(webPaths);
    }

}
