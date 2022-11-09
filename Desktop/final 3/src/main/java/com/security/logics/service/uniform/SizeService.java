package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.repository.uniform.SizeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SizeService
{

  @Autowired
  private SizeRepository sizeRepository;

  @Autowired
  private ModelMapper modelMapper;

  public SizeDTO saveSize(SizeDTO sizeDTO)
  {
    SizeEntity sizeEntity = sizeRepository.save(modelMapper.map(sizeDTO, SizeEntity.class));
    return modelMapper.map(sizeEntity, SizeDTO.class);
  }

  public List<SizeDTO> getAllSizes()
  {
    List<SizeEntity> sizeList = (List<SizeEntity>) sizeRepository.findAll();

    List<SizeDTO> dtos = sizeList
            .stream()
            .map(size -> modelMapper.map(size, SizeDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public SizeDTO getSizeById(Long sizeId)
  {
    Optional<SizeEntity> size = sizeRepository.findById(sizeId);

    if (size.isPresent())
    {
      return modelMapper.map(size.get(), SizeDTO.class);
    }
    return null;
  }

  public SizeDTO updateSize(SizeDTO sizeDTO)
  {
    return modelMapper.map(sizeRepository.save(modelMapper.map(sizeDTO, SizeEntity.class)), SizeDTO.class);
  }


  public void deleteSize(Long sizeId)
  {
    sizeRepository.deleteById(sizeId);
  }
}
