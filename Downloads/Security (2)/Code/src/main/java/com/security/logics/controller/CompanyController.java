package com.security.logics.controller;

import com.security.logics.dto.CompanyDTO;
import com.security.logics.exception.CompanyNotFoundException;
import com.security.logics.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CompanyDTO saveCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.saveCompany(companyDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<CompanyDTO> getCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long companyId) throws CompanyNotFoundException {
        CompanyDTO company = companyService.getCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDTO) throws CompanyNotFoundException {
        CompanyDTO company = companyService.getCompanyById(companyDTO.getCompanyId());
        CompanyDTO updatedCompany = companyService.updateCompany(companyDTO);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable Long companyId) throws CompanyNotFoundException {
        CompanyDTO company = companyService.getCompanyById(companyId);
        companyService.deleteCompany(companyId);
        return new ResponseEntity("Company is successfully deleted", HttpStatus.OK);
    }
}