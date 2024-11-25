package com.example.firstJobApp.company;

import com.example.firstJobApp.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompany() {
       return new ResponseEntity<>(this.companyService.getAllCompanies(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Long id) {
        Company company = companyService.getById(id);
        if (company != null)
            return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {

        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean isUpdated =  companyService.updateCompany(id, company);
        String msg = isUpdated ?  "Company updated successfully" : "Company updated unsuccessfully";
        if (isUpdated) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCompany(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompany(id);
        String msg = isDeleted ? "Company deleted successfully" : "Company deleted unsuccessfully";
        if (isDeleted) {
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }



}
