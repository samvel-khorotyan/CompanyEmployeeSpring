package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Value("${myItems.upload.path}")
    private String imagePath;

    private final EmployeeRepository employeeRepository;

    public Employee save(Employee item) {
        return employeeRepository.save(item);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee findById(int id) {
        return employeeRepository.getById(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee, MultipartFile uploadedFile) throws IOException {
        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imagePath + fileName);
            uploadedFile.transferTo(newFile);
            employee.setPicUrl(fileName);
        }
        employeeRepository.save(employee);
    }

    public byte[] getImage(String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);
    }
}
