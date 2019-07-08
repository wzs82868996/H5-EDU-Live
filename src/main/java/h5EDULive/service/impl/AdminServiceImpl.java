package h5EDULive.service.impl;

import h5EDULive.dao.CourseRepository;
import h5EDULive.dao.ExamRepository;
import h5EDULive.dao.PostRepository;
import h5EDULive.dao.UserRepository;
import h5EDULive.dao.domain.Course;
import h5EDULive.dao.domain.Exam;
import h5EDULive.dao.domain.Post;
import h5EDULive.dao.domain.User;
import h5EDULive.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    UserRepository userRepository;

    @Resource
    CourseRepository courseRepository;

    @Resource
    ExamRepository examRepository;

    @Resource
    PostRepository postRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String removeUser(int userId) {
        try{
            userRepository.deleteById(userId);
        }
        catch (Exception e){
            return "";
        }
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public String removeCourse(int courseId) {
        return null;
    }

    @Override
    public List<Exam> getAllExams() {
        return null;
    }

    @Override
    public String removeExam(int examId) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public String removePost(int postId) {
        return null;
    }
}
