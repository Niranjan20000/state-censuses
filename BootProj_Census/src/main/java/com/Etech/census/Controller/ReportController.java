package com.Etech.census.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Etech.census.Service.HouseholdService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final HouseholdService householdService;

    // Serve the Survey Report page
    @GetMapping("/survey")
    public String surveyReport(Model model) {
        // Summary cards
        model.addAttribute("totalHouseholds", householdService.countHouseholds());
        model.addAttribute("totalMembers", householdService.countMembers());
        model.addAttribute("todaysSurveys", householdService.countTodaysSurveys());
        model.addAttribute("internetConnected", householdService.countInternetConnected());

        // Amenities counts for pie chart
        model.addAttribute("electricityAvailable", householdService.countElectricityAvailable());
        model.addAttribute("tvAvailable", householdService.countTvAvailable());
        model.addAttribute("refrigeratorAvailable", householdService.countRefrigeratorAvailable());

        // Last 7 days survey trend
        model.addAttribute("last7DaysLabels", householdService.getLast7DaysLabels());
        model.addAttribute("last7DaysCounts", householdService.getLast7DaysCounts());

        return "survey_report"; // Thymeleaf template name
    }

 // AJAX endpoint for date-based report
    @ResponseBody
    @GetMapping("/survey/by-date")
    public List<Map<String, String>> surveyByDate(@RequestParam("date") String date) {
        List<Map<String, String>> result = householdService.getHouseholdsByDate(date);
        System.out.println("Report for " + date + " → " + result.size() + " households");
        return result;
    }
}