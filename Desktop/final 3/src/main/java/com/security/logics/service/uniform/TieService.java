package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.TieDTO;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.TieEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.TieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TieService
{

  @Autowired
  private TieRepository tieRepository;

  @Autowired ColorRepository colorRepository;

  @Autowired
  private ModelMapper modelMapper;

  public TieDTO saveTie(TieDTO tieDTO)
  {
    TieEntity tieMapper = modelMapper.map(tieDTO, TieEntity.class);
    ColorEntity colorEntity = colorRepository.findByColorName(tieDTO.getColor().getColorName());
    tieMapper.setColor(colorEntity);
    TieEntity tieEntity = tieRepository.save(tieMapper);
    return modelMapper.map(tieEntity, TieDTO.class);
  }

  public List<TieDTO> getAllTies()
  {
    List<TieEntity> tieList = (List<TieEntity>) tieRepository.findAll();

    List<TieDTO> dtos = tieList
            .stream()
            .map(tie -> modelMapper.map(tie, TieDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public TieDTO getTieById(Long tieId)
  {
    Optional<TieEntity> tie = tieRepository.findById(tieId);

    if (tie.isPresent())
    {
      return modelMapper.map(tie.get(), TieDTO.class);
    }
    return null;
  }

  public TieDTO updateTie(TieDTO tieDTO)
  {
    TieEntity tieMapper = modelMapper.map(tieDTO, TieEntity.class);
    ColorEntity colorEntity = colorRepository.findByColorName(tieDTO.getColor().getColorName());
    tieMapper.setColor(colorEntity);
    TieEntity tieEntity = tieRepository.save(tieMapper);
    return modelMapper.map(tieEntity, TieDTO.class);
  }


  public void deleteTie(Long tieId)
  {
    tieRepository.deleteById(tieId);
  }

}
