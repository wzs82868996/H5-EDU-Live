//package h5EDULive.web.query;
//
//import h5EDULive.dao.domain.Post;
//import h5EDULive.service.impl.CommunityServiceImpl;
//import h5EDULive.web.dto.PostDetail;
//import h5EDULive.web.dto.PostSummary;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@Controller
//public class CommunityController {
//    @Autowired
//    private CommunityServiceImpl communityService;
//
//    @RequestMapping("/community")
//    public List<PostSummary> getPostSummaries(@RequestParam("page") int page) {
//		Sort sort = new Sort(Sort.Direction.DESC, "postId");
//		Pageable pageable = PageRequest.of(page, 15, sort);
//		return communityService.getPostsSummaries(pageable);
//	}
//
//    @RequestMapping("/community/{postId}")
//    public PostDetail getPostDetail(@PathVariable int postId, @RequestParam("page") int page) {
//        Sort sort = new Sort(Sort.Direction.DESC, "postId");
//		Pageable pageable = PageRequest.of(page, 15, sort);
//        return communityService.getPostDetail(postId, pageable);
//    }
//
//    @RequestMapping("/community/add")
//    public String addPost(@RequestBody String postInfo, HttpServletRequest request) {
//        return communityService.addPost((int)request.getSession().getAttribute("userId"), postInfo);
//    }
//
//    @RequestMapping("/community/respond")
//    public String addResponse(@RequestBody String responseInfo, HttpServletRequest request) {
//        return communityService.addResponse((int)request.getSession().getAttribute("userId"), responseInfo);
//    }
//}
