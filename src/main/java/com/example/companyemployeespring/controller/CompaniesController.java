package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Companies;
import com.example.companyemployeespring.service.CompaniesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CompaniesController {

    private final CompaniesService companiesService;

    @GetMapping("/companies")
    public String companies(ModelMap map) {
        map.addAttribute("companies", companiesService.findAll());
        return "companies";
    }

    @GetMapping("/addCompanies")
    public String addCompaniesPage() {
        return "saveCompanies";
    }

    @PostMapping("/addCompanies")
    public String addCompanies(@ModelAttribute Companies companies) {
        companiesService.save(companies);
        return "redirect:/companies";
    }

    @GetMapping("/editCompanies/{id}")
    public String editCompanies(ModelMap map, @PathVariable("id") int id) {
        map.addAttribute("companies", companiesService.findById(id));
        return "saveCompanies";
    }

    @GetMapping("/deleteCompanies/{id}")
    public String deleteCompanies(@PathVariable("id") int id) {
        companiesService.deleteById(id);
        return "redirect:/companies";
    }
}
