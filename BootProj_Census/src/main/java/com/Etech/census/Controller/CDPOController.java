package com.Etech.census.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Etech.census.Service.CdpoService;
import com.Etech.census.entity.Cdpo;

@Controller
public class CDPOController {

    @Autowired
    private CdpoService cdpoService;

    // Show Registration Page
    @GetMapping("/cdpo/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("cdpo", new Cdpo());
        return "cdpo_register";
    }

    // Handle Registration
    @PostMapping("/cdpo/register")
    public String registerCdpo(
            @RequestParam String username,
            @RequestParam String name,
            @RequestParam String mobile,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String state,
            @RequestParam String district,
            Model model
    ) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "cdpo_register";
        }
        if (cdpoService.existsByUsername(username)) {
            model.addAttribute("error", "Username already taken!");
            return "cdpo_register";
        }
        if (cdpoService.existsByMobile(mobile)) {
            model.addAttribute("error", "Mobile number already registered!");
            return "cdpo_register";
        }

        Cdpo cdpo = Cdpo.builder()
                .username(username)
                .name(name)
                .mobile(mobile)
                .password(password)
                .state(state)
                .district(district)
                .build();

        cdpoService.registerCdpo(cdpo);
        model.addAttribute("message", "Registration Successful!");
        return "redirect:/cdpo/login";
    }

    // Show Login Page
    @GetMapping("/cdpo/login")
    public String showLoginForm() {
        return "cdpo_login";
    }

    // Handle Login
    @PostMapping("/cdpo/login")
    public String loginCdpo(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        boolean success = cdpoService.loginCdpo(username, password);
        if (success) {
            return "redirect:/cdpo/dashboard";
        } else {
            model.addAttribute("error", "Invalid Credentials!");
            return "cdpo_login";
        }
    }

    // Logout
    @GetMapping("/cdpo/logout")
    public String logout() {
        cdpoService.logout();
        return "redirect:/cdpo/login";
    }
}