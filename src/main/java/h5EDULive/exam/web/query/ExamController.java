package h5EDULive.exam.web.query;

import h5EDULive.exam.service.ExamService;
import h5EDULive.exam.web.dto.Student;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExamController {
    private ExamService es;

    @RequestMapping(value = "/fileTest", method = RequestMethod.POST)
    public String showUserMessage(HttpServletRequest request, MultipartFile icon) {
        System.out.println(icon.getOriginalFilename());
        return "exam.html";
    }

    @RequestMapping("/studentExam")
    public String sendExamInfo(@ModelAttribute Student student) {
        System.out.println(student.getIcon());
//        JSONObject obj = es.getExamInfo(student);
        return "index.html";
    }

}

