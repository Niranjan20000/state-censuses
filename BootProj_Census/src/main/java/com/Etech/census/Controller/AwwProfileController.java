package com.Etech.census.Controller;

import com.Etech.census.Service.UserService;
import com.Etech.census.entity.AwwWorker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/aww/profile")
public class AwwProfileController {

    private final UserService awwWorkerService;

    public AwwProfileController(UserService awwWorkerService) {
        this.awwWorkerService = awwWorkerService;
    }

    // Show profile page (protected)
    @GetMapping
    public String showProfile(Model model) {
        AwwWorker worker = awwWorkerService.getLoggedInWorker();
        if (worker == null) {
            // not logged in → redirect to login
            return "redirect:/aww/login";
        }
        model.addAttribute("awwWorker", worker);
        return "aww_profile"; // resolves to aww_profile.html
    }

    // Update phone number
    @PostMapping("/update")
    public String updatePhone(@RequestParam("mobile") String phone,
                              RedirectAttributes redirectAttributes) {
        AwwWorker worker = awwWorkerService.getLoggedInWorker();
        if (worker == null) {
            // not logged in → redirect to login
            return "redirect:/aww/login";
        }

        // update phone via service
        awwWorkerService.updatePhone(phone);

        redirectAttributes.addFlashAttribute("success", "Phone number updated successfully!");
        return "redirect:/aww/profile"; // back to profile page
    }
}