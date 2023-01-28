package com.security.logics.controller;

import com.security.logics.dto.uniform.UniformDTO;
import com.security.logics.exception.UniformNotFoundException;
import com.security.logics.service.UniformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("uniform")
public class UniformController {
    private final UniformService uniformService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UniformDTO saveUniform(@RequestBody UniformDTO uniformDTO) {
        return uniformService.saveUniform(uniformDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UniformDTO> getUniform() {
        return uniformService.getAllUniforms();
    }

    @GetMapping("/{uniformId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UniformDTO> getUniformById(@PathVariable Long uniformId) throws UniformNotFoundException {
        UniformDTO uniform = uniformService.getUniformById(uniformId);
        return new ResponseEntity<>(uniform, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UniformDTO> updateUniform(@RequestBody UniformDTO uniformDTO) throws UniformNotFoundException {
        UniformDTO uniform = uniformService.getUniformById(uniformDTO.getUniformId());
        UniformDTO updatedUniform = uniformService.updateUniform(uniformDTO);
        return new ResponseEntity<>(updatedUniform, HttpStatus.OK);
    }

    @DeleteMapping("/{uniformId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UniformDTO> deleteUniform(@PathVariable long uniformId) throws UniformNotFoundException {
        UniformDTO uniform = uniformService.getUniformById(uniformId);
        uniformService.deleteUniform(uniformId);
        return new ResponseEntity("Uniform successfully deleted", HttpStatus.OK);
    }

}

