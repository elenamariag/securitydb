package com.security.logics.service;

import com.security.logics.dto.SecurityBadgeDTO;
import com.security.logics.exception.SecurityBadgeNotFoundException;
import com.security.logics.model.SecurityBadgeEntity;
import com.security.logics.repository.SecurityBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityBadgeService {

    private final SecurityBadgeRepository securityBadgeRepository;

    private final ModelMapper modelMapper;

    public SecurityBadgeDTO saveSecurityBadge(SecurityBadgeDTO securityBadgeDTO) {
        SecurityBadgeEntity securityBadgeEntity = securityBadgeRepository.save(modelMapper.map(securityBadgeDTO, SecurityBadgeEntity.class));
        return modelMapper.map(securityBadgeEntity, SecurityBadgeDTO.class);
    }

    public List<SecurityBadgeDTO> getAllSecurityBadges() {
        List<SecurityBadgeEntity> securityBadgeList = (List<SecurityBadgeEntity>) securityBadgeRepository.findAll();

        List<SecurityBadgeDTO> securityBadges = securityBadgeList
                .stream()
                .map(securityBadge -> modelMapper.map(securityBadge, SecurityBadgeDTO.class))
                .collect(Collectors.toList());

        return securityBadges;
    }

    public SecurityBadgeDTO getSecurityBadgeById(Long securityBadgeId) throws SecurityBadgeNotFoundException {
        Optional<SecurityBadgeEntity> securityBadge = securityBadgeRepository.findById(securityBadgeId);

        if (securityBadge.isPresent()) {
            return modelMapper.map(securityBadge.get(), SecurityBadgeDTO.class);
        } else {
            throw new SecurityBadgeNotFoundException("Security Badge Not Found: " + securityBadgeId);
        }
    }

    public SecurityBadgeDTO updateSecurityBadge(SecurityBadgeDTO securityBadgeDTO) {
        //Throw exception
        return modelMapper.map(securityBadgeRepository.save(modelMapper.map(securityBadgeDTO, SecurityBadgeEntity.class)), SecurityBadgeDTO.class);
    }


    public void deleteSecurityBadge(Long securityBadgeId) {
        //Throw exception
        securityBadgeRepository.deleteById(securityBadgeId);
    }

}
