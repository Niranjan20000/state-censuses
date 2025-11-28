package com.Etech.census.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Etech.census.DTO.HouseholdDTO;
import com.Etech.census.Service.HouseholdService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/aww/household")
public class HouseholdController {

    private final HouseholdService householdService;

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("householdDto", new HouseholdDTO());
        return "household_form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute HouseholdDTO householdDto, Model model) {
        // ensure timestamp set (frontend populates surveyTimestamp too)
        if (householdDto.getSurveyTimestamp() == null) {
            householdDto.setSurveyTimestamp(java.time.LocalDateTime.now().toString());
        }
        householdService.saveFromDto(householdDto);
        model.addAttribute("message", "Household saved");
        return "redirect:/aww/dashboard";
    }
    
   

}
