package h5EDULive.web.query;

import h5EDULive.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @RequestMapping("/admin/removeUser")
    public String removeUser(int userId) {
        return adminService.removeUser(userId);
    }
}
