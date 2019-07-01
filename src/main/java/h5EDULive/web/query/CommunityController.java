package h5EDULive.web.query;

import h5EDULive.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {
    @Autowired
    private CommunityService cs;

    @RequestMapping("/community")
    public String postsInfo() {
        return cs.getPostsInfo();
    }

    @RequestMapping("/community/{postId}")
    public String postInfo(@PathVariable int postId) {
        return cs.getPostInfo(postId);
    }

}
