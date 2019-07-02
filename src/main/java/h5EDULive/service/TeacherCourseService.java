package h5EDULive.service;

import java.util.List;

public interface TeacherCourseService {
    List<String> getList(int id);

    boolean insert(int tchId, int courseId);

    void delete(int courseId);

    void modifyInfo(int courseId, String dep);

    boolean upload();
}
