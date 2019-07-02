package h5EDULive.service.impl;

import h5EDULive.dao.CourseRepository;
import h5EDULive.dao.TeacherCourseRepository;
import h5EDULive.dao.domain.TeacherCourse;
import h5EDULive.service.TeacherCourseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    private final TeacherCourseRepository teacherCourseRepository;
    private final CourseRepository courseRepository;

    public TeacherCourseServiceImpl(TeacherCourseRepository teacherCourseRepository, CourseRepository courseRepository) {
        this.teacherCourseRepository = teacherCourseRepository;
        this.courseRepository = courseRepository;
    }

    /* 上传课程 */
    public boolean upload()
    {
        return true;
    }

    /* 查看课程 */
    public List<String> getList(int id)
    {
        return teacherCourseRepository.getList(id);
    }

    /* 添加课程 */
    @Override
    public boolean insert(int tchId, int courseId) {
        TeacherCourse TeacherCourse = new TeacherCourse();
        TeacherCourse.setTeacherId(tchId);
        TeacherCourse.setCourseId(courseId);
        return teacherCourseRepository.save(TeacherCourse) != null;
    }

    @Override
    public void delete(int courseId) {
        teacherCourseRepository.deleteByCourseId(courseId);
    }

    /* 修改课程信息*/
    public void modifyInfo(int courseId, String dep)
    {
        courseRepository.updateDepicitionByCourseId(courseId, dep);
    }
}
