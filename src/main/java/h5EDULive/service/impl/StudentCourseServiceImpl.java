package h5EDULive.service.impl;

import h5EDULive.dao.CourseRepository;
import h5EDULive.dao.StudentCourseRepository;
import h5EDULive.dao.domain.StudentCourse;
import h5EDULive.service.StudentCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Resource
    private StudentCourseRepository stuCourseRepository;

    @Resource
    private CourseRepository courseRepository;

    public StudentCourseServiceImpl(StudentCourseRepository stuCourseRepository, CourseRepository courseRepository) {
        this.stuCourseRepository = stuCourseRepository;
        this.courseRepository = courseRepository;
    }

    /* 根据学生id查找课程名称列表 */
    public List<String> getList(int id)
    {
        List<StudentCourse> courseId = stuCourseRepository.findByStuId(id);
        List<String> courses = new ArrayList<>();
        for (StudentCourse StudentCourse : courseId) {
            courses.add(courseRepository.findById(StudentCourse.getCourseId()).getName());
        }
        return courses;
    }

    /* 把课程添加到我的课程里 */
    @Override
    @Transactional
    public boolean insert(int stuId, int courseId)
    {
        StudentCourse StudentCourse = new StudentCourse();
        StudentCourse.setStuId(stuId);
        StudentCourse.setCourseId(courseId);
        return stuCourseRepository.save(StudentCourse) != null;
    }

    /* 把课程从我的课程里删除 */
    @Override
    @Transactional
    public void remove(int stuId, int courseId)
    {
        stuCourseRepository.deleteByCourseIdAndStuId(stuId, courseId);
    }

    /* 根据课程id查找课程信息 */
    public String getInfo(int id)
    {
        return courseRepository.findById(id).getDepiction();
    }

    /* 根据课程id查找课程录播 */
    public String getRecord(int id)
    {
        return courseRepository.findById(id).getVideo();
    }
}
