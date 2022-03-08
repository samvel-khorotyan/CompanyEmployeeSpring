package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Companies;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompaniesRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompaniesRepository companiesRepository;

    @Value("${myItems.upload.path}")
    private String imagePath;

    @GetMapping("/employee")
    public String employee(ModelMap map){

        List<Employee> employees = employeeRepository.findAll();

        map.addAttribute("employees",employees);

        return "employee";

    }

    @GetMapping("/addEmployee")
    public String addEmployeePage(ModelMap map){

        List<Companies> companies = companiesRepository.findAll();

        map.addAttribute("companies",companies);

        return "saveEmployee";

    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute Employee employee, @RequestParam("picture") MultipartFile uploadedFile) throws IOException {

        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imagePath + fileName);
            uploadedFile.transferTo(newFile);

            employee.setPicUrl(fileName);
        }

        employeeRepository.save(employee);

        return "redirect:/employee";
    }

    @GetMapping("/getImage")
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream=new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }

    @GetMapping("/editEmployee/{id}")
    public String editEmployee(ModelMap map, @PathVariable("id") int id) {

        Optional<Employee> employee = employeeRepository.findById(id);
        List<Companies> companies = companiesRepository.findAll();

        if (employee.isPresent()) {

            map.addAttribute("employee", employee.get());
            map.addAttribute("companies",companies);

            return "saveEmployee";

        } else {

            return "redirect:/employee";

        }
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {

        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }
}
