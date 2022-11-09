package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.repository.uniform.ColorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorService
{

  @Autowired
  private ColorRepository colorRepository;

  @Autowired
  private ModelMapper modelMapper;

  public ColorDTO saveColor(ColorDTO colorDTO)
  {
    ColorEntity colorEntity = colorRepository.save(modelMapper.map(colorDTO, ColorEntity.class));
    return modelMapper.map(colorEntity, ColorDTO.class);
  }

  public List<ColorDTO> getAllColors()
  {
    List<ColorEntity> colorList = (List<ColorEntity>) colorRepository.findAll();

    List<ColorDTO> dtos = colorList
            .stream()
            .map(color -> modelMapper.map(color, ColorDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public ColorDTO getColorById(Long colorId)
  {
    Optional<ColorEntity> color = colorRepository.findById(colorId);

    if (color.isPresent())
    {
      return modelMapper.map(color.get(), ColorDTO.class);
    }
    return null;
  }

  public ColorDTO updateColor(ColorDTO colorDTO)
  {
    return modelMapper.map(colorRepository.save(modelMapper.map(colorDTO, ColorEntity.class)), ColorDTO.class);
  }


  public void deleteColor(Long colorId)
  {
    colorRepository.deleteById(colorId);
  }


}
