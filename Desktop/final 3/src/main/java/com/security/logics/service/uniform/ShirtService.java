package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.ShirtDTO;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.ShirtEntity;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.ShirtRepository;
import com.security.logics.repository.uniform.SizeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShirtService
{

  @Autowired
  ShirtRepository shirtRepository;

  @Autowired ColorRepository colorRepository;
  @Autowired SizeRepository sizeRepository;


  @Autowired
  private ModelMapper modelMapper;

  public ShirtDTO saveShirt(ShirtDTO shirtDTO)
  {
    ShirtEntity shirtMapper = modelMapper.map(shirtDTO, ShirtEntity.class);

    ColorEntity colorEntity = colorRepository.findByColorName(shirtDTO.getColor().getColorName());
    SizeEntity sizeEntity = sizeRepository.findBySize(shirtDTO.getSize().getSize());

    if (colorEntity != null && sizeEntity != null)
    {
      shirtMapper.setColor(colorEntity);
      shirtMapper.setSize(sizeEntity);
    }
    
    ShirtEntity shirtEntity = shirtRepository.save(shirtMapper);
    return modelMapper.map(shirtEntity, ShirtDTO.class);
  }

  public List<ShirtDTO> getAllShirts()
  {
    List<ShirtEntity> ShirtList = (List<ShirtEntity>) shirtRepository.findAll();

    List<ShirtDTO> dtos = ShirtList
            .stream()
            .map(Shirt -> modelMapper.map(Shirt, ShirtDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public ShirtDTO getShirtById(Long ShirtId)
  {
    Optional<ShirtEntity> Shirt = shirtRepository.findById(ShirtId);

    if (Shirt.isPresent())
    {
      return modelMapper.map(Shirt.get(), ShirtDTO.class);
    }
    return null;
  }

  public ShirtDTO updateShirt(ShirtDTO shirtDTO)
  {
    return modelMapper.map(shirtRepository.save(modelMapper.map(shirtDTO, ShirtEntity.class)), ShirtDTO.class);
  }


  public void deleteShirt(Long ShirtId)
  {
    shirtRepository.deleteById(ShirtId);
  }
}
