package com.security.logics.service;

import com.security.logics.dto.CompanyDTO;
import com.security.logics.exception.CompanyNotFoundException;
import com.security.logics.model.CompanyEntity;
import com.security.logics.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final ModelMapper modelMapper;

    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        CompanyEntity companyEntity = companyRepository.save(modelMapper.map(companyDTO, CompanyEntity.class));
        return modelMapper.map(companyEntity, CompanyDTO.class);
    }

    public List<CompanyDTO> getAllCompanies() {
        List<CompanyEntity> companyList = (List<CompanyEntity>) companyRepository.findAll();

        List<CompanyDTO> companies = companyList
                .stream()
                .map(company -> modelMapper.map(company, CompanyDTO.class))
                .collect(Collectors.toList());

        return companies;
    }

    public CompanyDTO getCompanyById(Long companyId) throws CompanyNotFoundException {
        Optional<CompanyEntity> company = companyRepository.findById(companyId);

        if (company.isPresent()) {
            return modelMapper.map(company.get(), CompanyDTO.class);
        } else {
            throw new CompanyNotFoundException("Company Not Found: " + companyId);
        }
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO) throws CompanyNotFoundException {
        Optional<CompanyEntity> company = companyRepository.findById(companyDTO.getCompanyId());

        if (company.isPresent()) {
            return modelMapper.map(companyRepository.save(modelMapper.map(companyDTO, CompanyEntity.class)), CompanyDTO.class);
        } else {
            throw new CompanyNotFoundException("Company Not Found: " + companyDTO.getCompanyId());
        }
    }


    public void deleteCompany(Long companyId) throws CompanyNotFoundException {
        Optional<CompanyEntity> company = companyRepository.findById(companyId);

        if (company.isPresent()) {
            companyRepository.deleteById(companyId);
        } else {
            throw new CompanyNotFoundException("Company Not Found: " + companyId);
        }

    }

}
