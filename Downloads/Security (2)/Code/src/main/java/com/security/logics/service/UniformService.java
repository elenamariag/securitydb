package com.security.logics.service;

import com.security.logics.dto.uniform.UniformDTO;
import com.security.logics.exception.UniformNotFoundException;
import com.security.logics.model.uniform.UniformEntity;
import com.security.logics.repository.UniformRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniformService {
    private final UniformRepository uniformRepository;

    private final ModelMapper modelMapper;

    public UniformDTO saveUniform(UniformDTO uniformDTO) {
        UniformEntity uniformEntity = uniformRepository.save(modelMapper.map(uniformDTO, UniformEntity.class));
        return modelMapper.map(uniformEntity, UniformDTO.class);
    }

    public List<UniformDTO> getAllUniforms() {
        List<UniformEntity> uniformList = (List<UniformEntity>) uniformRepository.findAll();

        List<UniformDTO> uniforms = uniformList
                .stream()
                .map(uniform -> modelMapper.map(uniform, UniformDTO.class))
                .collect(Collectors.toList());

        return uniforms;
    }

    public UniformDTO getUniformById(Long uniformId) throws UniformNotFoundException {
        Optional<UniformEntity> uniform = uniformRepository.findById(uniformId);

        if (uniform.isPresent()) {
            return modelMapper.map(uniform.get(), UniformDTO.class);
        } else {
            throw new UniformNotFoundException("Uniform Not Found: " + uniformId);
        }
    }

    public UniformDTO updateUniform(UniformDTO uniformDTO) throws UniformNotFoundException {
        Optional<UniformEntity> uniform = uniformRepository.findById(uniformDTO.getUniformId());

        if (uniform.isPresent()) {
            return modelMapper.map(uniformRepository.save(modelMapper.map(uniformDTO, UniformEntity.class)), UniformDTO.class);
        } else {
            throw new UniformNotFoundException("Uniform Not Found: " + uniformDTO.getUniformId());
        }
    }


    public void deleteUniform(Long uniformId) throws UniformNotFoundException {
        Optional<UniformEntity> uniform = uniformRepository.findById(uniformId);

        if (uniform.isPresent()) {
            uniformRepository.deleteById(uniformId);
        } else {
            throw new UniformNotFoundException("Uniform Not Found: " + uniformId);
        }
    }

}
