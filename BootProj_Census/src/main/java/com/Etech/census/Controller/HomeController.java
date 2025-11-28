package com.Etech.census.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }
    
    // AWW pages only
    @GetMapping("/aww/dashboard")
    public String showAwwDashboard() {
        return "aww_dashboard";
    }

   
}
