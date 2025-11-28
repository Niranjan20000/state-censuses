package com.Etech.census.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Etech.census.Respository.HouseholdRepository;

@Controller
@RequestMapping("/household")
public class HouseHoldAll {

    private final HouseholdRepository householdRepository;

    public HouseHoldAll(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @GetMapping("/all")
    public String viewAllHouseholds(Model model) {
        model.addAttribute("households", householdRepository.findAll());
        return "household_list";
    }
}