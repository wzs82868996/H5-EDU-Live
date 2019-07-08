package h5EDULive.service.impl;

import h5EDULive.dao.CourseRepository;
import h5EDULive.dao.domain.Course;
import h5EDULive.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseRepository courseRepository;
    @Override
    public boolean insert(Course course) {
        return courseRepository.save(course) != null;
    }
}
