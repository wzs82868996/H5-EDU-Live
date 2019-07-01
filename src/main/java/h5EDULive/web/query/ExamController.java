package h5EDULive.web.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import h5EDULive.service.ExamService;
import h5EDULive.web.dto.UserExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {
    @Autowired
    private ExamService es;
    private UserExam userExam;

    @RequestMapping("/homepage/exam")
    public String getExamsInfo(HttpServletRequest request) {
        return es.getExamsInfo((Integer)request.getSession().getAttribute("userId"));
    }

    @RequestMapping("/exam/{examId}")
    public String getExamInfo(@PathVariable int examId, HttpServletRequest request) {
        return es.getExamInfo((Integer)request.getSession().getAttribute("userId"), examId);
    }

    @RequestMapping("/exam/{examId}/submit")
    @ResponseBody
    public String getExamResult(@PathVariable int examId, @RequestBody String solutions, HttpServletRequest request) {
        userExam = new UserExam();
        userExam.setCourseId(examId);
        userExam.setUserId((Integer)request.getSession().getAttribute("userId"));
        List<Integer> answers = userExam.getAnswers();
        LinkedHashMap<String, Integer> jsonMap = JSON.parseObject(solutions, new TypeReference<LinkedHashMap<String, Integer>>() {
        });
        for (Map.Entry<String, Integer> entry : jsonMap.entrySet())
            answers.add(Integer.parseInt(entry.getKey().replace("q", "")), entry.getValue());
        return es.getExamResult(userExam);
    }

    @RequestMapping("/homepage/exam/add")
    public String addExam(@RequestBody String examInfo) {
        return es.addExam(JSONObject.parseObject(examInfo));
    }

}

