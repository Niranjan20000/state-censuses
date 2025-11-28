package com.Etech.census.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Etech.census.Service.UserService;
import com.Etech.census.entity.AwwWorker;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService awwWorkerService;

    // Show AWW Registration Page
    @GetMapping("/aww/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("awwWorker", new AwwWorker());
        return "aww_register"; // aww_register.html
    }

    // AWW Registration endpoint
    @PostMapping("/aww/register")
    public String registerAwwWorker(
            @RequestParam String username,
            @RequestParam String name,
            @RequestParam String mobile,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String state,
            @RequestParam String district,
            @RequestParam String awwCentreCode,
            Model model
    ) {

        AwwWorker awwWorker = AwwWorker.builder()
                .username(username)
                .name(name)
                .mobile(mobile)
                .password(password)
                .confirmPassword(confirmPassword)
                .state(state)
                .district(district)
                .awwCentreCode(awwCentreCode)
                .build();

        awwWorkerService.registerUser(awwWorker);
        model.addAttribute("message", "Registration Successful!");

        return "redirect:/aww/login";
    }

    // Show Login Page
    @GetMapping("/aww/login")
    public String showLoginForm() {
        return "aww_login"; // aww_login.html
    }

    // Login endpoint
    @PostMapping("/aww/login")
    public String loginAwwWorker(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        boolean success = awwWorkerService.loginUser(username, password);

        if (success) {
        	return "redirect:/aww/dashboard";
  
        } else {
            model.addAttribute("error", "Invalid Credentials!");
            return "aww_login"; 
        }
    }
    
    

    // ✅ Logout clears the session
    @GetMapping("/aww/logout")
    public String logout(HttpSession session) {
        session.invalidate();       // ✅ clears the session completely
        awwWorkerService.logout();  // optional: clear in-memory worker
        return "redirect:/aww/login"; // go back to login page
    }

}