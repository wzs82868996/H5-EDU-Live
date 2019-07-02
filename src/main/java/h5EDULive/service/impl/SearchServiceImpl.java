//package h5EDULive.service.impl;
//
//import h5EDULive.Util.QueryString;
//import h5EDULive.dao.CourseRepository;
//import h5EDULive.dao.ExamRepository;
//import h5EDULive.dao.PostRepository;
//import h5EDULive.dao.domain.Course;
//import h5EDULive.dao.domain.Exam;
//import h5EDULive.dao.domain.Post;
//import h5EDULive.service.SearchService;
//import h5EDULive.web.dto.PostSummary;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.data.domain.Pageable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class SearchServiceImpl implements SearchService {
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private ExamRepository examRepository;
//
//    @Autowired
//    private PostRepository postRepository;
//
////    @Override
////    public List<> getAll(String keywords, Pageable pageable) {
////        return null;
////    }
//
//    @Override
//    public List<Course> getCourses(String keywords, Pageable pageable) {
//        List<Course> courses = courseRepository.findByKeys(new QueryString(toKeywordsArray(keywords)).getRE(), pageable).getContent();
//
//        return courses;
//    }
//
//    @Override
//    public List<Exam> getExams(String keywords, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<PostSummary> getPosts(String keywords, Pageable pageable) {
//        List<Post> posts = postRepository.findByKeys(new QueryString(toKeywordsArray(keywords)).getRE(), pageable).getContent();
//        List<PostSummary> postSummaries = new ArrayList<>();
//        for (Post post : posts)
//            postSummaries.add(new PostSummary(
//                    post.getPostId(),
//                    post.getTitle(),
//                    post.getAuthor(),
//                    post.getHeat(),
//                    post.getPubTime(),
//                    post.getLatestTime()
//            ));
//        return postSummaries;
//    }
//
//    private String[] toKeywordsArray(String keywords) {
//        String[] keys = keywords.split(" ");
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < keys.length; i++)
//            if(!keys[i].equals(""))
//                list.add(keys[i]);
//        return (String[])list.toArray();
//    }
//}
