package h5EDULive.service;

import com.alibaba.fastjson.JSONObject;
import h5EDULive.web.dto.UserExam;

public interface ExamService {
    String getExamsInfo(int userId);
    String getExamInfo(int userId, int examId);
    String getExamResult(UserExam userExam);
    String addExam(JSONObject examInfo);
}
