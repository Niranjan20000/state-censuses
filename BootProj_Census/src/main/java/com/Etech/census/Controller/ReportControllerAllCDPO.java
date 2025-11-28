package com.Etech.census.Controller;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Etech.census.Service.HouseholdService;

@Controller
@RequestMapping("/cdpo/reports")
public class ReportControllerAllCDPO {

    private final HouseholdService householdService;

    public ReportControllerAllCDPO(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public String showReportPage() {
        return "report_page";
    }

    @PostMapping("/generate")
    public String generatePdf(@RequestParam("reportDate") String reportDate, Model model) {
        List<Map<String, String>> households = householdService.getHouseholdsByDate(reportDate);
        model.addAttribute("reportDate", reportDate);
        model.addAttribute("households", households);
        return "report_pdf"; // this will be rendered as PDF
    }
}