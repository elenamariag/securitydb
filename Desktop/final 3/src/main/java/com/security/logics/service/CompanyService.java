package com.security.logics.service;

import com.security.logics.dto.CompanyDTO;
import com.security.logics.dto.CompanyDTO;
import com.security.logics.model.CompanyEntity;
import com.security.logics.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class CompanyService
{

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private ModelMapper modelMapper;

  public CompanyDTO saveCompany(CompanyDTO companyDTO)
  {
    CompanyEntity companyEntity = companyRepository.save(modelMapper.map(companyDTO, CompanyEntity.class));
    return modelMapper.map(companyEntity, CompanyDTO.class);
  }

  public List<CompanyDTO> getAllCompanies()
  {
    List<CompanyEntity> companyList = (List<CompanyEntity>) companyRepository.findAll();

    List<CompanyDTO> dtos = companyList
            .stream()
            .map(company -> modelMapper.map(company, CompanyDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public CompanyDTO getCompanyById(Long companyId)
  {
    Optional<CompanyEntity> company = companyRepository.findById(companyId);

    if (company.isPresent())
    {
      return modelMapper.map(company.get(), CompanyDTO.class);
    }
    return null;
  }

  public CompanyDTO updateCompany(CompanyDTO companyDTO)
  {
    return modelMapper.map(companyRepository.save(modelMapper.map(companyDTO, CompanyEntity.class)), CompanyDTO.class);
  }


  public void deleteCompany(Long companyId)
  {
    companyRepository.deleteById(companyId);
  }

}
