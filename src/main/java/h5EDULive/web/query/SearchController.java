package h5EDULive.web.query;

import h5EDULive.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {
    @Autowired
    private SearchService ss;

    @RequestMapping("/search/course")
    public String courses(String keywords) {
        return ss.getCourses(keywords);
    }

    @RequestMapping("/search/exam")
    public String exams(String keywords) {
        return ss.getExams(keywords);
    }

}
