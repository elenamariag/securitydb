package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.TieDTO;
import com.security.logics.exception.TieNotFoundException;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.TieEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.TieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TieService {

    private final TieRepository tieRepository;

    private final ColorRepository colorRepository;

    private final ModelMapper modelMapper;

    public TieDTO saveTie(TieDTO tieDTO) {
        TieEntity tieMapper = modelMapper.map(tieDTO, TieEntity.class);
        ColorEntity colorEntity = colorRepository.findByColorName(tieDTO.getColor().getColorName());
        tieMapper.setColor(colorEntity);
        TieEntity tieEntity = tieRepository.save(tieMapper);
        return modelMapper.map(tieEntity, TieDTO.class);
    }

    public List<TieDTO> getAllTies() {
        List<TieEntity> tieList = (List<TieEntity>) tieRepository.findAll();

        List<TieDTO> ties = tieList
                .stream()
                .map(tie -> modelMapper.map(tie, TieDTO.class))
                .collect(Collectors.toList());

        return ties;
    }

    public TieDTO getTieById(Long tieId) throws TieNotFoundException {
        Optional<TieEntity> tie = tieRepository.findById(tieId);

        if (tie.isPresent()) {
            return modelMapper.map(tie.get(), TieDTO.class);
        } else {
            throw new TieNotFoundException("Tie Not Found: " + tieId);
        }
    }

    public TieDTO updateTie(TieDTO tieDTO) throws TieNotFoundException {

        Optional<TieEntity> tie = tieRepository.findById(tieDTO.getTieId());

        if (tie.isPresent()) {
            TieEntity tieMapper = modelMapper.map(tieDTO, TieEntity.class);
            ColorEntity colorEntity = colorRepository.findByColorName(tieDTO.getColor().getColorName());
            tieMapper.setColor(colorEntity);
            TieEntity tieEntity = tieRepository.save(tieMapper);
            return modelMapper.map(tieEntity, TieDTO.class);
        } else {
            throw new TieNotFoundException("Tie Not Found: " + tieDTO.getTieId());
        }

    }


    public void deleteTie(Long tieId) throws TieNotFoundException {
        Optional<TieEntity> tie = tieRepository.findById(tieId);

        if (tie.isPresent()) {
            tieRepository.deleteById(tieId);
        } else {
            throw new TieNotFoundException("Tie Not Found: " + tieId);
        }
    }

}
