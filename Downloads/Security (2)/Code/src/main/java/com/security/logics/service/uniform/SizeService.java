package com.security.logics.service.uniform;

import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.repository.uniform.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeService {

    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;

    public SizeDTO saveSize(SizeDTO sizeDTO) {
        SizeEntity sizeEntity = sizeRepository.save(modelMapper.map(sizeDTO, SizeEntity.class));
        return modelMapper.map(sizeEntity, SizeDTO.class);
    }

    public List<SizeDTO> getAllSizes() {
        List<SizeEntity> sizeList = (List<SizeEntity>) sizeRepository.findAll();

        List<SizeDTO> sizes = sizeList
                .stream()
                .map(size -> modelMapper.map(size, SizeDTO.class))
                .collect(Collectors.toList());

        return sizes;
    }

    public SizeDTO getSizeById(Long sizeId) throws SizeNotFoundException {
        Optional<SizeEntity> size = sizeRepository.findById(sizeId);

        if (size.isPresent()) {
            return modelMapper.map(size.get(), SizeDTO.class);
        } else {
            throw new SizeNotFoundException("Size Not Found: " + sizeId);
        }
    }

    public SizeDTO updateSize(SizeDTO sizeDTO) throws SizeNotFoundException {
        Optional<SizeEntity> size = sizeRepository.findById(sizeDTO.getSizeId());

        if (size.isPresent()) {
            return modelMapper.map(sizeRepository.save(modelMapper.map(sizeDTO, SizeEntity.class)), SizeDTO.class);
        } else {
            throw new SizeNotFoundException("Size Not Found: " + sizeDTO.getSizeId());
        }
    }

    public void deleteSize(Long sizeId) throws SizeNotFoundException {
        Optional<SizeEntity> size = sizeRepository.findById(sizeId);

        if (size.isPresent()) {
            sizeRepository.deleteById(sizeId);
        } else {
            throw new SizeNotFoundException("Size Not Found: " + sizeId);
        }
    }
}
