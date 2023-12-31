package vn.edu.iuh.fit.www_lab_week5.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model){
        return "home";
    }

    @GetMapping("/viewjobpostings")
    public String viewjobpostings(Model model){
        return "jobskill/viewjobpostings";
    }

    @GetMapping("/createjobposting")
    public String createjobposting(Model model){
        return "jobskill/createjobposting";
    }

}
