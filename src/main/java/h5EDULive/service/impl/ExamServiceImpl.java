package h5EDULive.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import h5EDULive.dao.CourseRepository;
import h5EDULive.dao.ExamRepository;
import h5EDULive.dao.UserExamRepository;
import h5EDULive.service.ExamService;
import h5EDULive.web.dto.Course;
import h5EDULive.web.dto.Exam;
import h5EDULive.web.dto.UserExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository er;
    @Autowired
    private CourseRepository cr;
    @Autowired
    private UserExamRepository uer;

    @Override
    public String getExamsInfo(int userId) {
        List<UserExam> userExams = uer.findByUserId(userId).getContent();
        List<Exam> exams = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        int courseId;
        for (UserExam ue : userExams) {
            courseId = ue.getCourseId();
            exams.add(er.findByCourseId(courseId));
            courses.add(cr.findByCourseId(courseId));
        }
        Map<String, Object> examsMap = new HashMap<>();
        for (int i = 0; i < userExams.size(); i++) {
            Map<String, Object> examMap = new HashMap<>();
            examMap.put("name",courses.get(i).getName());
            examMap.put("time",exams.get(i).getTime());
            examMap.put("maxScore",exams.get(i).getMaxScore());
            examMap.put("totalScore",userExams.get(i).getTotalScore());
            examsMap.put(Integer.toString(exams.get(i).getCourseId()), new JSONObject(examMap).toJSONString());
        }
        return new JSONObject(examsMap).toJSONString();
    }

    @Override
    public String getExamInfo(int userId, int courseId) {
        Exam exam = er.findByCourseId(courseId);
        UserExam userExam = uer.findByUserIdAndCourseId(userId, courseId);
        Course course = cr.findByCourseId(courseId);
        Map<String, Object> examMap = new HashMap<>();
        examMap.put("name", course.getName());
        examMap.put("label", course.getLabel());
        examMap.put("lecture", course.getLecture());
        examMap.put("time", exam.getTime());
        examMap.put("duration", exam.getDuration());
        examMap.put("maxScore", exam.getMaxScore());
        examMap.put("subtitle", exam.getSubtitlePicUrl());
        examMap.put("subtitleScore", exam.getSubtitleScore());
        examMap.put("solutions", exam.getSolutions());
        examMap.put("answers", userExam.getAnswers());
        examMap.put("subScore", userExam.getSubScore());
        examMap.put("totalScore", userExam.getTotalScore());
        return new JSONObject(examMap).toJSONString();
    }

    @Override
    public String getExamResult(UserExam userExam) {
        Exam exam = er.findByCourseId(userExam.getCourseId());
        List<Integer> answers = userExam.getAnswers();
        List<Integer> solutions = exam.getSolutions();
        List<Integer> subtitleScore = exam.getSubtitleScore();
        List<Integer> subScore = userExam.getSubScore();
        int score, totalScore = 0;
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i) == solutions.get(i))
                score = subtitleScore.get(i);
            else
                score = 0;
            subScore.add(score);
            totalScore += score;
            map.put("q" + i, score);
        }
        userExam.setTotalScore(totalScore);
        map.put("totalScore", totalScore);
        uer.saveAndFlush(userExam);
        return new JSONObject(map).toJSONString();
    }

    @Override
    public String addExam(JSONObject examInfo) {
        Exam exam = new Exam();
        exam.setCourseId((int)examInfo.get("courseId"));
        exam.setTime((String)examInfo.get("time"));
        exam.setDuration((int)examInfo.get("duration"));
        exam.setMaxScore((int) examInfo.get("maxScore"));
        JSONArray subtitleScore = (JSONArray)examInfo.get("subtitleScore");
        exam.setSubtitleScore(Arrays.asList((Integer[])subtitleScore.toArray()));
        JSONArray solutions = (JSONArray)examInfo.get("solutions");
        exam.setSolutions(Arrays.asList((Integer[])solutions.toArray()));
        JSONArray subtitlePicUrl = (JSONArray)examInfo.get("subtitlePicUrl");
        exam.setSubtitlePicUrl(Arrays.asList((String[])subtitlePicUrl.toArray()));
        try {
            er.save(exam);
        }
        catch (Exception e){
            return "";
        }
        return "SUCCESS";
    }
}
