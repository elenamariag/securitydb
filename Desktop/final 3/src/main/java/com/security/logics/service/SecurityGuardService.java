package com.security.logics.service;

import com.google.gson.Gson;
import com.security.logics.dto.SecurityGuardDTO;
import com.security.logics.dto.SecurityGuardDTO;
import com.security.logics.model.SecurityGuardEntity;
import com.security.logics.repository.SecurityGuardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityGuardService
{

  @Autowired
  private SecurityGuardRepository securityGuardRepository;

  @Autowired
  private ModelMapper modelMapper;

  public SecurityGuardDTO saveSecurityGuard(String jsonRequest, MultipartFile diploma)
  {
    Gson gson = new Gson();
    SecurityGuardEntity securityGuardEntity = gson.fromJson(jsonRequest, SecurityGuardEntity.class);

    try
    {
      securityGuardEntity.setDiploma(diploma.getBytes());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }


    return modelMapper.map(securityGuardRepository.save(securityGuardEntity), SecurityGuardDTO.class);
  }

  public List<SecurityGuardDTO> getAllSecurityGuards()
  {
    List<SecurityGuardEntity> securityGuardList = (List<SecurityGuardEntity>) securityGuardRepository.findAll();

    List<SecurityGuardDTO> dtos = securityGuardList
            .stream()
            .map(securityGuard -> modelMapper.map(securityGuard, SecurityGuardDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public SecurityGuardDTO getSecurityGuardById(Long securityGuardId)
  {
    Optional<SecurityGuardEntity> securityGuard = securityGuardRepository.findById(securityGuardId);

    if (securityGuard.isPresent())
    {
      return modelMapper.map(securityGuard.get(), SecurityGuardDTO.class);
    }
    return null;
  }

  public SecurityGuardDTO updateSecurityGuard(SecurityGuardDTO securityGuardDTO)
  {
    return modelMapper.map(securityGuardRepository.save(modelMapper.map(securityGuardDTO, SecurityGuardEntity.class)), SecurityGuardDTO.class);
  }


  public void deleteSecurityGuard(Long securityGuardId)
  {
    securityGuardRepository.deleteById(securityGuardId);
  }


}
