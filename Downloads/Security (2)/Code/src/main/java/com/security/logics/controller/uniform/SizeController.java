package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.service.uniform.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("size")
public class SizeController {
    private final SizeService sizeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SizeDTO saveSize(@RequestBody SizeDTO sizeDTO) {
        return sizeService.saveSize(sizeDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<SizeDTO> getSizes() {
        return sizeService.getAllSizes();
    }

    @GetMapping("/{sizeId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SizeDTO> getSizeById(@PathVariable Long sizeId) throws SizeNotFoundException {
        SizeDTO size = sizeService.getSizeById(sizeId);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SizeDTO> updateSize(@RequestBody SizeDTO sizeDTO) throws SizeNotFoundException {
        SizeDTO size = sizeService.getSizeById(sizeDTO.getSizeId());
        SizeDTO updatedSize = sizeService.updateSize(sizeDTO);
        return new ResponseEntity<>(updatedSize, HttpStatus.OK);
    }

    @DeleteMapping("/{sizeId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SizeDTO> deleteSize(@PathVariable Long sizeId) throws SizeNotFoundException {
        SizeDTO size = sizeService.getSizeById(sizeId);
        sizeService.deleteSize(sizeId);
        return new ResponseEntity("Size is successfully deleted", HttpStatus.OK);

    }
}
