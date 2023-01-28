package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.exception.ColorNotFoundException;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.repository.uniform.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;

    private final ModelMapper modelMapper;

    public ColorDTO saveColor(ColorDTO colorDTO) {
        ColorEntity colorEntity = colorRepository.save(modelMapper.map(colorDTO, ColorEntity.class));
        return modelMapper.map(colorEntity, ColorDTO.class);
    }

    public List<ColorDTO> getAllColors() {
        List<ColorEntity> colorList = (List<ColorEntity>) colorRepository.findAll();

        List<ColorDTO> colors = colorList
                .stream()
                .map(color -> modelMapper.map(color, ColorDTO.class))
                .collect(Collectors.toList());

        return colors;
    }

    public ColorDTO getColorById(Long colorId) throws ColorNotFoundException {
        Optional<ColorEntity> color = colorRepository.findById(colorId);

        if (color.isPresent()) {
            return modelMapper.map(color.get(), ColorDTO.class);
        }
        else {
            throw new ColorNotFoundException("Invalid Color ID: " + colorId.toString());
        }
    }

    public ColorDTO updateColor(ColorDTO colorDTO) throws ColorNotFoundException {
        Optional<ColorEntity> color = colorRepository.findById(colorDTO.getColorId());

        if (color.isPresent()) {
            return modelMapper.map(colorRepository.save(modelMapper.map(colorDTO, ColorEntity.class)), ColorDTO.class);
        }
        else {
            throw new ColorNotFoundException("Invalid Color ID: " + colorDTO.getColorId().toString());
        }
    }


    public void deleteColor(Long colorId) throws ColorNotFoundException {
        Optional<ColorEntity> color = colorRepository.findById(colorId);
        if (color.isPresent()) {
            colorRepository.deleteById(colorId);
        }
        else {
            throw new ColorNotFoundException("Invalid Color ID: " + colorId.toString());
        }
    }


}
