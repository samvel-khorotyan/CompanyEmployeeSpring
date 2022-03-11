package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Companies;
import com.example.companyemployeespring.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompaniesService {

    private final CompaniesRepository companiesRepository;

    public Companies save(Companies companies) {
        return companiesRepository.save(companies);
    }

    public void deleteById(int id) {
        companiesRepository.deleteById(id);
    }

    public Companies findById(int id) {
        return companiesRepository.getById(id);
    }

    public List<Companies> findAll() {
        return companiesRepository.findAll();
    }
}
