package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.TrouserDTO;
import com.security.logics.exception.ColorNotFoundException;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.exception.TrouserNotFoundException;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.model.uniform.TrouserEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.SizeRepository;
import com.security.logics.repository.uniform.TrouserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrouserService {


    private final TrouserRepository trouserRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;

    public TrouserDTO saveTrouser(TrouserDTO trouserDTO) throws ColorNotFoundException, SizeNotFoundException {
        TrouserEntity trouserMapper = modelMapper.map(trouserDTO, TrouserEntity.class);

        ColorEntity colorEntity = colorRepository.findByColorName(trouserDTO.getColor().getColorName());

        SizeEntity sizeEntity = sizeRepository.findBySize(trouserDTO.getSize().getSize());

        if(colorEntity == null){
            throw new ColorNotFoundException("Color Not Found: " + trouserDTO.getColor().getColorName());
        }

        if(sizeEntity == null){
            throw new SizeNotFoundException("Size Not Found: " + trouserDTO.getSize().getSize());
        }

        TrouserEntity trouserEntity = trouserRepository.save(trouserMapper);
        return modelMapper.map(trouserEntity, TrouserDTO.class);
    }

    public List<TrouserDTO> getAllTrousers() {
        List<TrouserEntity> trouserList = (List<TrouserEntity>) trouserRepository.findAll();

        List<TrouserDTO> trousers = trouserList
                .stream()
                .map(trouser -> modelMapper.map(trouser, TrouserDTO.class))
                .collect(Collectors.toList());

        return trousers;
    }

    public TrouserDTO getTrouserById(Long trouserId) throws TrouserNotFoundException {
        Optional<TrouserEntity> trouser = trouserRepository.findById(trouserId);

        if (trouser.isPresent()) {
            return modelMapper.map(trouser.get(), TrouserDTO.class);
        } else {
            throw new TrouserNotFoundException("Trouser Not Found: " + trouserId);
        }
    }

    public TrouserDTO updateTrouser(TrouserDTO trouserDTO) throws TrouserNotFoundException {
        Optional<TrouserEntity> trouser = trouserRepository.findById(trouserDTO.getTrouserId());

        if (trouser.isPresent()) {
            return modelMapper.map(trouserRepository.save(modelMapper.map(trouserDTO, TrouserEntity.class)), TrouserDTO.class);
        } else {
            throw new TrouserNotFoundException("Trouser Not Found: " + trouserDTO.getTrouserId());
        }

    }


    public void deleteTrouser(Long trouserId) throws TrouserNotFoundException {
        Optional<TrouserEntity> trouser = trouserRepository.findById(trouserId);

        if (trouser.isPresent()) {
            trouserRepository.deleteById(trouserId);
        } else {
            throw new TrouserNotFoundException("Trouser Not Found: " + trouserId);
        }
    }


}
