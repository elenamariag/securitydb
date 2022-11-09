package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.TrouserDTO;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.model.uniform.TrouserEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.SizeRepository;
import com.security.logics.repository.uniform.TrouserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrouserService
{

  @Autowired
  TrouserRepository trouserRepository;

  @Autowired ColorRepository colorRepository;
  @Autowired SizeRepository sizeRepository;


  @Autowired
  private ModelMapper modelMapper;

  public TrouserDTO saveTrouser(TrouserDTO trouserDTO)
  {
    TrouserEntity trouserMapper = modelMapper.map(trouserDTO, TrouserEntity.class);

    ColorEntity colorEntity = colorRepository.findByColorName(trouserDTO.getColor().getColorName());
    SizeEntity sizeEntity = sizeRepository.findBySize(trouserDTO.getSize().getSize());

    if (colorEntity != null && sizeEntity != null)
    {
      trouserMapper.setColor(colorEntity);
      trouserMapper.setSize(sizeEntity);
    }
    
    TrouserEntity trouserEntity = trouserRepository.save(trouserMapper);
    return modelMapper.map(trouserEntity, TrouserDTO.class);
  }

  public List<TrouserDTO> getAllTrousers()
  {
    List<TrouserEntity> trouserList = (List<TrouserEntity>) trouserRepository.findAll();

    List<TrouserDTO> dtos = trouserList
            .stream()
            .map(trouser -> modelMapper.map(trouser, TrouserDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public TrouserDTO getTrouserById(Long trouserId)
  {
    Optional<TrouserEntity> trouser = trouserRepository.findById(trouserId);

    if (trouser.isPresent())
    {
      return modelMapper.map(trouser.get(), TrouserDTO.class);
    }
    return null;
  }

  public TrouserDTO updateTrouser(TrouserDTO trouserDTO)
  {
    return modelMapper.map(trouserRepository.save(modelMapper.map(trouserDTO, TrouserEntity.class)), TrouserDTO.class);
  }


  public void deleteTrouser(Long trouserId)
  {
    trouserRepository.deleteById(trouserId);
  }
}
