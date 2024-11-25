package com.example.firstJobApp.company.impl;

import com.example.firstJobApp.company.Company;
import com.example.firstJobApp.company.CompanyRepository;
import com.example.firstJobApp.company.CompanyService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company newCompany) {
        Optional<Company> existingCompany = companyRepository.findById(id);
        if (existingCompany.isPresent()) {
            Company company = existingCompany.get();
            updateIfNotNull(company::setName, newCompany.getName());
            updateIfNotNull(company::setDescription, newCompany.getDescription());
            updateIfNotNull(company::setReviews, newCompany.getReviews());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
    private <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
    @Override
    public boolean deleteCompany(Long id) {
            if (companyRepository.existsById(id)) {
                companyRepository.deleteById(id);
                return true;
            }
            return false;
    }
}
