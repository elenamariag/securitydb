package com.security.logics.controller;

import com.security.logics.dto.SecurityGuardUniformProjectDTO;
import com.security.logics.service.SecurityGuardUniformProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityGuardUniformProjectController
{

  @Autowired
  private SecurityGuardUniformProjectService guardUniformAndProjectService;

  @PostMapping("/guardUniformAndProject")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public SecurityGuardUniformProjectDTO saveUniform(@RequestBody SecurityGuardUniformProjectDTO uniformDTO)
  {
    return guardUniformAndProjectService.saveGuardUniformAndProject(uniformDTO);
  }

  @GetMapping("/getAllGuardUniformsAndProject")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public List<SecurityGuardUniformProjectDTO> getUniform()
  {
    return guardUniformAndProjectService.getAllGuardUniformsAndProjects();
  }

  @GetMapping("/getGuardUniformAndProject/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<SecurityGuardUniformProjectDTO> getGuardUniformAndProjectById(@PathVariable Long id)
  {
    SecurityGuardUniformProjectDTO uniform = guardUniformAndProjectService.getUniformById(id);

    if (uniform == null)
    {
      return new ResponseEntity("Uniform ID not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(uniform, HttpStatus.OK);
  }

  @PutMapping("/updateGuardUniformAndProject")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<SecurityGuardUniformProjectDTO> updateUniform(@RequestBody SecurityGuardUniformProjectDTO uniformDTO)
  {
    SecurityGuardUniformProjectDTO uniform = guardUniformAndProjectService.getUniformById(uniformDTO.getId());

    if (uniform == null)
    {
      return new ResponseEntity("Uniform ID not found", HttpStatus.NOT_FOUND);
    }
    SecurityGuardUniformProjectDTO updatedUniform = guardUniformAndProjectService.updateUniform(uniformDTO);
    return new ResponseEntity<>(updatedUniform, HttpStatus.OK);
  }

  @DeleteMapping("/deleteGuardUniformAndProject/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<SecurityGuardUniformProjectDTO> deleteUniform(@PathVariable long id)
  {
    SecurityGuardUniformProjectDTO uniform = guardUniformAndProjectService.getUniformById(id);

    if (uniform == null)
    {
      return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
    }
    guardUniformAndProjectService.deleteUniform(id);
    return new ResponseEntity("Successfully deleted", HttpStatus.OK);
  }

}

