package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.service.CompaniesService;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompaniesService companiesService;

    @GetMapping("/employee")
    public String employee(ModelMap map) {
        map.addAttribute("employees", employeeService.findAll());
        return "employee";
    }

    @GetMapping("/addEmployee")
    public String addEmployeePage(ModelMap map) {
        map.addAttribute("companies", companiesService.findAll());
        return "saveEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee, @RequestParam("picture") MultipartFile uploadedFile) throws IOException {
        employeeService.addEmployee(employee, uploadedFile);
        return "redirect:/employee";
    }

    @GetMapping("/getImage")
    public @ResponseBody
    byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        return employeeService.getImage(picName);
    }

    @GetMapping("/editEmployee/{id}")
    public String editEmployee(ModelMap map, @PathVariable("id") int id) {
        map.addAttribute("employee", employeeService.findById(id));
        map.addAttribute("companies", companiesService.findAll());
        return "saveEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteById(id);
        return "redirect:/employee";
    }
}
