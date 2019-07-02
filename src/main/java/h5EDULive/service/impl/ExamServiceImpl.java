package h5EDULive.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import h5EDULive.dao.CourseRepository;
import h5EDULive.dao.ExamRepository;
import h5EDULive.dao.UserExamRepository;
import h5EDULive.service.ExamService;
import h5EDULive.dao.domain.Course;
import h5EDULive.dao.domain.Exam;
import h5EDULive.web.dto.ExamDetail;
import h5EDULive.web.dto.ExamSummary;
import h5EDULive.dao.domain.UserExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserExamRepository userExamRepository;

    @Override
    public List<ExamSummary> getExamSummaries(int userId) {
        List<UserExam> userExams = userExamRepository.findByUserId(userId).getContent();
        List<Exam> exams = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        int courseId;
        for (UserExam ue : userExams) {
            courseId = ue.getCourseId();
            exams.add(examRepository.findByCourseId(courseId));
            courses.add(courseRepository.findById(courseId));
        }
        List<ExamSummary> summaries = new ArrayList<>();
        for (int i = 0; i < userExams.size(); i++)
            summaries.add(new ExamSummary(
                    exams.get(i).getCourseId(),
                    courses.get(i).getName(),
                    courses.get(i).getDepiction(),
                    exams.get(i).getTime(),
                    exams.get(i).getMaxScore(),
                    userExams.get(i).getTotalScore()
            ));
        return summaries;
    }

    @Override
    public ExamDetail getExamDetail(int userId, int courseId) {
        Exam exam = examRepository.findByCourseId(courseId);
        UserExam userExam = userExamRepository.findByUserIdAndCourseId(userId, courseId);
        Course course = courseRepository.findById(courseId);
        ExamDetail examDetail = new ExamDetail(
                course.getName(),
                course.getLabel(),
                course.getLecture(),
                exam.getTime(),
                exam.getDuration(),
                exam.getMaxScore(),
                exam.getSubtitlePicUrl(),
                exam.getSubtitleScore(),
                exam.getSolutions(),
                userExam.getAnswers(),
                userExam.getSubScore(),
                userExam.getTotalScore()
        );
        return examDetail;
    }

    @Override
    public List<Integer> getExamResult(UserExam userExam) {
        Exam exam = examRepository.findByCourseId(userExam.getCourseId());
        List<Integer> answers = userExam.getAnswers();
        List<Integer> solutions = exam.getSolutions();
        List<Integer> subtitleScore = exam.getSubtitleScore();
        List<Integer> subScore = userExam.getSubScore();
        int score, totalScore = 0;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i) == solutions.get(i))
                score = subtitleScore.get(i);
            else
                score = 0;
            subScore.add(score);
            totalScore += score;
        }
        List<Integer> result = new ArrayList<>();
        result.addAll(subScore);
        result.add(totalScore);
        userExam.setTotalScore(totalScore);
        userExamRepository.saveAndFlush(userExam);
        return result;
    }

    @Override
    @Transactional
    public String addExam(String examInfo) {
        JSONObject jobj = JSON.parseObject(examInfo);
        Exam exam = new Exam();
        exam.setCourseId((int)jobj.get("courseId"));
        exam.setDuration((int)jobj.get("duration"));
        exam.setMaxScore((int)jobj.get("maxScore"));
        exam.setSubtitleScore((List<Integer>)jobj.get("subtitleScore"));
        exam.setSolutions((List<Integer>)jobj.get("solutions"));
        // TODO
        try {
            examRepository.saveAndFlush(exam);
        } catch (Exception e) {
            return "";
        }
        return "SUCCESS";
    }

    @Override
    public String removeExam(int courseId) {
        try {
            examRepository.deleteByCourseId(courseId);
            userExamRepository.deleteByCourseId(courseId);
        } catch (Exception e) {
            return "";
        }
        return "SUCCESS";
    }
}
