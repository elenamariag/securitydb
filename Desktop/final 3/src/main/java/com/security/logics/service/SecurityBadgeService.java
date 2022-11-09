package com.security.logics.service;

import com.security.logics.dto.SecurityBadgeDTO;
import com.security.logics.model.SecurityBadgeEntity;
import com.security.logics.repository.SecurityBadgeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SecurityBadgeService
{

  @Autowired
  private SecurityBadgeRepository securityBadgeRepository;

  @Autowired
  private ModelMapper modelMapper;

  public SecurityBadgeDTO saveSecurityBadge(SecurityBadgeDTO securityBadgeDTO)
  {
    SecurityBadgeEntity securityBadgeEntity = securityBadgeRepository.save(modelMapper.map(securityBadgeDTO, SecurityBadgeEntity.class));
    return modelMapper.map(securityBadgeEntity, SecurityBadgeDTO.class);
  }

  public List<SecurityBadgeDTO> getAllSecurityBadges()
  {
    List<SecurityBadgeEntity> securityBadgeList = (List<SecurityBadgeEntity>) securityBadgeRepository.findAll();

    List<SecurityBadgeDTO> dtos = securityBadgeList
            .stream()
            .map(securityBadge -> modelMapper.map(securityBadge, SecurityBadgeDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public SecurityBadgeDTO getSecurityBadgeById(Long securityBadgeId)
  {
    Optional<SecurityBadgeEntity> securityBadge = securityBadgeRepository.findById(securityBadgeId);

    if (securityBadge.isPresent())
    {
      return modelMapper.map(securityBadge.get(), SecurityBadgeDTO.class);
    }
    return null;
  }

  public SecurityBadgeDTO updateSecurityBadge(SecurityBadgeDTO securityBadgeDTO)
  {
    return modelMapper.map(securityBadgeRepository.save(modelMapper.map(securityBadgeDTO, SecurityBadgeEntity.class)), SecurityBadgeDTO.class);
  }


  public void deleteSecurityBadge(Long securityBadgeId)
  {
    securityBadgeRepository.deleteById(securityBadgeId);
  }

}
