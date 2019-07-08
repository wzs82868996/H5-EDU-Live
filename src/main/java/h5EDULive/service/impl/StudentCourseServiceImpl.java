package h5EDULive.service.impl;

import com.h5_sdu_live.demo.domain.Course;
import com.h5_sdu_live.demo.domain.StuCourseMapper;
import com.h5_sdu_live.demo.repository.CourseRepository;
import com.h5_sdu_live.demo.repository.StuCourseRepository;
import com.h5_sdu_live.demo.service.StuCourseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StuCourseService {
    private final StuCourseRepository stuCourseRepository;
    private final CourseRepository courseRepository;

    public StudentCourseServiceImpl(StuCourseRepository stuCourseRepository, CourseRepository courseRepository) {
        this.stuCourseRepository = stuCourseRepository;
        this.courseRepository = courseRepository;
    }

    /* 根据学生id查找课程名称列表 */
    public List<Course> getList(int id)
    {
        List<StuCourseMapper> courseId = stuCourseRepository.findByStuId(id);
        List<Course> cources = new ArrayList<>();
        for (StuCourseMapper stuCourseMapper : courseId) {
            cources.add(courseRepository.findById(stuCourseMapper.getCourseId()));
        }
        return cources;
    }

    /* 把课程添加到我的课程里 */
    @Override
    public boolean insert(int stuId, int courseId)
    {
        StuCourseMapper stuCourseMapper = new StuCourseMapper();
        stuCourseMapper.setStuId(stuId);
        stuCourseMapper.setCourseId(courseId);
        return stuCourseRepository.save(stuCourseMapper) != null;
    }

    /* 把课程从我的课程里删除 */
    @Transactional
    @Override
    public void remove(int stuId, int courseId)
    {
        stuCourseRepository.deleteByCourseIdAndStuId(courseId, stuId);
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
