package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Companies;
import com.example.companyemployeespring.repository.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CompaniesController {

    @Autowired
    private CompaniesRepository companiesRepository;

    @GetMapping("/companies")
    public String main(ModelMap map){

        List<Companies> companies = companiesRepository.findAll();

        map.addAttribute("companies",companies);

        return "companies";
    }

    @GetMapping("/addCompanies")
    public String addCompaniesPage(){

        return "saveCompanies";

    }

    @PostMapping("/addCompanies")
    public String addCompanies(@ModelAttribute Companies companies){

        companiesRepository.save(companies);

        return "redirect:/companies";

    }

    @GetMapping("/editCompanies/{id}")
    public String editCompanies(ModelMap map, @PathVariable("id") int id) {

        Optional<Companies> companies = companiesRepository.findById(id);

        if (companies.isPresent()) {

            map.addAttribute("companies", companies.get());

            return "saveCompanies";

        } else {

            return "redirect:/companies";

        }
    }

    @GetMapping("/deleteCompanies/{id}")
    public String deleteCompanies(@PathVariable("id") int id) {
        companiesRepository.deleteById(id);
        return "redirect:/companies";

    }
}
