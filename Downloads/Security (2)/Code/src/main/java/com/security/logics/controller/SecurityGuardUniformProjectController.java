package com.security.logics.controller;

import com.security.logics.dto.SecurityGuardUniformProjectDTO;
import com.security.logics.exception.SecurityGuardUniformProjectNotFoundException;
import com.security.logics.service.SecurityGuardUniformProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("security-guard-uniform-project")
public class SecurityGuardUniformProjectController {
    private final SecurityGuardUniformProjectService guardUniformAndProjectService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public SecurityGuardUniformProjectDTO saveUniform(@RequestBody SecurityGuardUniformProjectDTO uniformDTO) {
        return guardUniformAndProjectService.saveGuardUniformAndProject(uniformDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<SecurityGuardUniformProjectDTO> getUniform() {
        return guardUniformAndProjectService.getAllGuardUniformsAndProjects();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<SecurityGuardUniformProjectDTO> getGuardUniformAndProjectById(@PathVariable Long id) throws SecurityGuardUniformProjectNotFoundException {
        SecurityGuardUniformProjectDTO uniform = guardUniformAndProjectService.getUniformById(id);
        return new ResponseEntity<>(uniform, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<SecurityGuardUniformProjectDTO> updateUniform(@RequestBody SecurityGuardUniformProjectDTO uniformDTO) throws SecurityGuardUniformProjectNotFoundException {
        SecurityGuardUniformProjectDTO uniform = guardUniformAndProjectService.getUniformById(uniformDTO.getId());
        SecurityGuardUniformProjectDTO updatedUniform = guardUniformAndProjectService.updateUniform(uniformDTO);
        return new ResponseEntity<>(updatedUniform, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<SecurityGuardUniformProjectDTO> deleteUniform(@PathVariable long id) throws SecurityGuardUniformProjectNotFoundException {
        SecurityGuardUniformProjectDTO uniform = guardUniformAndProjectService.getUniformById(id);
        guardUniformAndProjectService.deleteUniform(id);
        return new ResponseEntity("SecurityGuardUniformProject is successfully deleted", HttpStatus.OK);
    }

}

