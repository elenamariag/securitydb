package com.security.logics.service;

import com.security.logics.dto.uniform.UniformDTO;
import com.security.logics.model.uniform.UniformEntity;
import com.security.logics.repository.UniformRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniformService
{

  @Autowired
  private UniformRepository uniformRepository;

  @Autowired
  private ModelMapper modelMapper;

  public UniformDTO saveUniform(UniformDTO uniformDTO)
  {
    UniformEntity uniformEntity = uniformRepository.save(modelMapper.map(uniformDTO, UniformEntity.class));
    return modelMapper.map(uniformEntity, UniformDTO.class);
  }

  public List<UniformDTO> getAllUniforms()
  {
    List<UniformEntity> uniformList = (List<UniformEntity>) uniformRepository.findAll();

    List<UniformDTO> dtos = uniformList
            .stream()
            .map(uniform -> modelMapper.map(uniform, UniformDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public UniformDTO getUniformById(Long uniformId)
  {
    Optional<UniformEntity> uniform = uniformRepository.findById(uniformId);

    if (uniform.isPresent())
    {
      return modelMapper.map(uniform.get(), UniformDTO.class);
    }
    return null;
  }

  public UniformDTO updateUniform(UniformDTO uniformDTO)
  {
    return modelMapper.map(uniformRepository.save(modelMapper.map(uniformDTO, UniformEntity.class)), UniformDTO.class);
  }


  public void deleteUniform(Long uniformId)
  {
    uniformRepository.deleteById(uniformId);
  }


}
