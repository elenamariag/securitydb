package com.security.logics.controller;

import com.security.logics.dto.CompanyDTO;
import com.security.logics.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController
{

  @Autowired
  private CompanyService companyService;

  @PostMapping("/company")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public CompanyDTO saveCompany(@RequestBody CompanyDTO companyDTO)
  {
    return companyService.saveCompany(companyDTO);
  }

  @GetMapping("/getCompanies")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public List<CompanyDTO> getCompanies()
  {
    return companyService.getAllCompanies();
  }

  @GetMapping("/getCompanyById/{companyId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Long companyId)
  {
    CompanyDTO company = companyService.getCompanyById(companyId);

    if (company == null)
    {
      return new ResponseEntity("Company not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(company, HttpStatus.OK);
  }

  @PutMapping("/updateCompany")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDTO)
  {
    CompanyDTO company = companyService.getCompanyById(companyDTO.getCompanyId());

    if (company == null)
    {
      return new ResponseEntity("Company is not found", HttpStatus.NOT_FOUND);
    }
    CompanyDTO updatedCompany = companyService.updateCompany(companyDTO);
    return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
  }

  @DeleteMapping("/deleteCompany/{companyId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable Long companyId)
  {
    CompanyDTO company = companyService.getCompanyById(companyId);

    if (company == null)
    {
      return new ResponseEntity("Company is not found", HttpStatus.NOT_FOUND);
    }
    companyService.deleteCompany(companyId);
    return new ResponseEntity("Company is successfully deleted", HttpStatus.OK);
  }
}