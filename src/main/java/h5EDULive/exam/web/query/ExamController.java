package h5EDULive.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExamController {
    @RequestMapping("/studentExam")
    public String showUserMessage(@ModelAttribute User user) {
        System.out.println(user.getName() + "\n" + user.getPasswd());
        return "index";
    }


}

