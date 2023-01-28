package com.security.logics.service;

import com.security.logics.dto.SecurityGuardUniformProjectDTO;
import com.security.logics.exception.SecurityGuardUniformProjectNotFoundException;
import com.security.logics.model.SecurityGuardUniformProjectEntity;
import com.security.logics.model.uniform.UniformEntity;
import com.security.logics.repository.SecurityGuardUniformProjectRepository;
import com.security.logics.repository.UniformRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityGuardUniformProjectService {

    private final SecurityGuardUniformProjectRepository securityGuardUniformProjectRepository;

    private final UniformRepository uniformRepository;

    private final ModelMapper modelMapper;

    public SecurityGuardUniformProjectDTO saveGuardUniformAndProject(SecurityGuardUniformProjectDTO securityGuardUniformProjectDTO) {

        Optional<UniformEntity> uniform = uniformRepository.findById(securityGuardUniformProjectDTO.getUniform().getUniformId());
        Long totalAvailableUniforms = null;
        if (uniform.isPresent()) {
            totalAvailableUniforms = uniform.get().getTotalAvailableUniforms();
        }

        Long remainingUniforms = totalAvailableUniforms - securityGuardUniformProjectDTO.getRequiredUniforms();

        uniform.get().setTotalAvailableUniforms(remainingUniforms);
        uniformRepository.save(uniform.get());

        SecurityGuardUniformProjectEntity uniformEntity = securityGuardUniformProjectRepository.save(modelMapper.map(securityGuardUniformProjectDTO, SecurityGuardUniformProjectEntity.class));

        return modelMapper.map(uniformEntity, SecurityGuardUniformProjectDTO.class);
    }

    public List<SecurityGuardUniformProjectDTO> getAllGuardUniformsAndProjects() {
        List<SecurityGuardUniformProjectEntity> uniformList = (List<SecurityGuardUniformProjectEntity>) securityGuardUniformProjectRepository.findAll();

        List<SecurityGuardUniformProjectDTO> dtos = uniformList
                .stream()
                .map(uniform -> modelMapper.map(uniform, SecurityGuardUniformProjectDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }

    public SecurityGuardUniformProjectDTO getUniformById(Long uniformId) throws SecurityGuardUniformProjectNotFoundException {
        Optional<SecurityGuardUniformProjectEntity> uniform = securityGuardUniformProjectRepository.findById(uniformId);

        if (uniform.isPresent()) {
            return modelMapper.map(uniform.get(), SecurityGuardUniformProjectDTO.class);
        } else {
            throw new SecurityGuardUniformProjectNotFoundException("SecurityGuardUniformProject Not Found: " + uniformId);
        }
    }

    public SecurityGuardUniformProjectDTO updateUniform(SecurityGuardUniformProjectDTO uniformDTO) throws SecurityGuardUniformProjectNotFoundException {
        Optional<SecurityGuardUniformProjectEntity> uniform = securityGuardUniformProjectRepository.findById(uniformDTO.getId());

        if (uniform.isPresent()) {
            return modelMapper.map(securityGuardUniformProjectRepository.save(modelMapper.map(uniformDTO, SecurityGuardUniformProjectEntity.class)), SecurityGuardUniformProjectDTO.class);
        } else {
            throw new SecurityGuardUniformProjectNotFoundException("SecurityGuardUniformProject Not Found: " + uniformDTO.getId());
        }
    }


    public void deleteUniform(Long uniformId) throws SecurityGuardUniformProjectNotFoundException {
        Optional<SecurityGuardUniformProjectEntity> uniform = securityGuardUniformProjectRepository.findById(uniformId);

        if (uniform.isPresent()) {
            securityGuardUniformProjectRepository.deleteById(uniformId);
        } else {
            throw new SecurityGuardUniformProjectNotFoundException("SecurityGuardUniformProject Not Found: " + uniformId);
        }

    }

}
