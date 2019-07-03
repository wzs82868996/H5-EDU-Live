package h5EDULive.web.query;

import h5EDULive.service.impl.ExamServiceImpl;
import h5EDULive.web.dto.ExamDetail;
import h5EDULive.web.dto.ExamSummary;
import h5EDULive.dao.domain.UserExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExamController {
    @Autowired
    private ExamServiceImpl examService;
    private UserExam userExam;

    @RequestMapping("/homepage-exam/summaries")
    public List<ExamSummary> getExamSummaries(HttpServletRequest request) {
        return examService.getExamSummaries((int)request.getSession().getAttribute("userId"));
    }

    @RequestMapping("/exam/{courseId}")
    public ExamDetail getExamDetail(@PathVariable int courseId, HttpServletRequest request) {
        return examService.getExamDetail((int)request.getSession().getAttribute("userId"), courseId);
    }

    @RequestMapping("/exam/{courseId}/submit")
    @ResponseBody
    public List<Integer> getExamResult(@PathVariable int courseId, @RequestParam("answers") List<Integer> answers, HttpServletRequest request) throws Exception {
        userExam = new UserExam();
        userExam.setCourseId(courseId);
        userExam.setUserId((int)request.getSession().getAttribute("userId"));
        userExam.setAnswers(answers);
        return examService.getExamResult(userExam);
    }

    @RequestMapping("/homepage-exam/add")
    public String addExam(@RequestBody String examInfo) {
        return examService.addExam(examInfo);
    }

    @RequestMapping("/homepage-exam/delete")
    public String addExam(@RequestParam("courseId") int courseId) {
        return examService.removeExam(courseId);
    }
}

