package com.example.firstJobApp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getById(Long id);
    void createCompany(Company company);
    boolean updateCompany(Long id, Company company);
    boolean deleteCompany(Long id);

}
