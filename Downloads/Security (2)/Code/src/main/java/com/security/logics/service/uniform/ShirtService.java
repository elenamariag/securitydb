package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.ShirtDTO;
import com.security.logics.exception.ColorNotFoundException;
import com.security.logics.exception.ShirtNotFoundException;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.ShirtEntity;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.ShirtRepository;
import com.security.logics.repository.uniform.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShirtService {

    private final ShirtRepository shirtRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;

    public ShirtDTO saveShirt(ShirtDTO shirtDTO) throws ColorNotFoundException, SizeNotFoundException {
        ShirtEntity shirtMapper = modelMapper.map(shirtDTO, ShirtEntity.class);

        ColorEntity colorEntity = colorRepository.findByColorName(shirtDTO.getColor().getColorName());

        if(colorEntity == null){
            throw new ColorNotFoundException("Color Not Found: " + shirtDTO.getColor().getColorName());
        }

        SizeEntity sizeEntity = sizeRepository.findBySize(shirtDTO.getSize().getSize());

        if(sizeEntity == null){
            throw new SizeNotFoundException("Size Not Found: " + shirtDTO.getSize().getSize());
        }

        ShirtEntity shirtEntity = shirtRepository.save(shirtMapper);
        return modelMapper.map(shirtEntity, ShirtDTO.class);
    }

    public List<ShirtDTO> getAllShirts() {
        List<ShirtEntity> ShirtList = (List<ShirtEntity>) shirtRepository.findAll();

        List<ShirtDTO> shirts = ShirtList
                .stream()
                .map(Shirt -> modelMapper.map(Shirt, ShirtDTO.class))
                .collect(Collectors.toList());

        return shirts;
    }

    public ShirtDTO getShirtById(Long shirtId) throws ShirtNotFoundException {
        Optional<ShirtEntity> shirt = shirtRepository.findById(shirtId);

        if (shirt.isPresent()) {
            return modelMapper.map(shirt.get(), ShirtDTO.class);
        }
        else {
            throw new ShirtNotFoundException("Shirt Not Found: " + shirtId);
        }
    }

    public ShirtDTO updateShirt(ShirtDTO shirtDTO) throws ShirtNotFoundException {

        Optional<ShirtEntity> Shirt = shirtRepository.findById(shirtDTO.getShirtId());

        if (Shirt.isPresent()) {
            return modelMapper.map(shirtRepository.save(modelMapper.map(shirtDTO, ShirtEntity.class)), ShirtDTO.class);
        }
        else {
            throw new ShirtNotFoundException("Shirt Not Found: " + shirtDTO.getShirtId());
        }

    }


    public void deleteShirt(Long shirtId) throws ShirtNotFoundException {
        Optional<ShirtEntity> Shirt = shirtRepository.findById(shirtId);

        if (Shirt.isPresent()) {
            shirtRepository.deleteById(shirtId);
        }
        else {
            throw new ShirtNotFoundException("Shirt Not Found: " + shirtId);
        }

    }
}
