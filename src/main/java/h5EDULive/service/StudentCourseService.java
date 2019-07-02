package h5EDULive.service;

import java.util.List;

public interface StudentCourseService {

    List<String> getList(int id);

    boolean insert(int stuId, int courseId);

    void remove(int stuId, int courseId);

    String getInfo(int id);

    String getRecord(int id);
}
