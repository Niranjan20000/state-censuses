package com.Etech.census.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Etech.census.Service.CdpoService;
import com.Etech.census.Service.HouseholdService;
import com.Etech.census.Service.MemberService;
import com.Etech.census.entity.Cdpo;

@Controller
@RequestMapping("/cdpo/dashboard")
public class CdpoDashboardController {

    private final HouseholdService householdService;
    private final MemberService memberService;
    private final CdpoService cdpoService;

    public CdpoDashboardController(HouseholdService householdService,
                                   MemberService memberService,
                                   CdpoService cdpoService) {
        this.householdService = householdService;
        this.memberService = memberService;
        this.cdpoService = cdpoService;
    }

    @GetMapping
    public String showDashboard(Model model) {
        Cdpo officer = cdpoService.getLoggedInCdpo();
        if (officer == null) {
            return "redirect:/cdpo/login";
        }

        model.addAttribute("totalHouseholds", householdService.countAll());
        model.addAttribute("totalMembers", memberService.countAll());
        model.addAttribute("todaysRegistrations", householdService.countToday());
        model.addAttribute("recentHouseholds", householdService.findRecent(3));
        model.addAttribute("recentMembers", memberService.findRecent(3));

        return "cdpo_dashboard";
    }
}