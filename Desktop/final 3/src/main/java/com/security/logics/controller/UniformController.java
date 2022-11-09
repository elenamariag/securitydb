package com.security.logics.controller;

import com.security.logics.dto.uniform.UniformDTO;
import com.security.logics.service.UniformService;
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
import java.util.Optional;

@RestController
public class UniformController
{

  @Autowired
  private UniformService uniformService;

  @PostMapping("/uniform")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public UniformDTO saveUniform(@RequestBody UniformDTO uniformDTO)
  {
    return uniformService.saveUniform(uniformDTO);
  }

  @GetMapping("/getUniforms")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public List<UniformDTO> getUniform()
  {
    return uniformService.getAllUniforms();
  }

  @GetMapping("/getUniformById/{uniformId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<UniformDTO> getUniformById(@PathVariable Long uniformId)
  {
    UniformDTO uniform = uniformService.getUniformById(uniformId);

    if (uniform == null)
    {
      return new ResponseEntity("Uniform ID not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(uniform, HttpStatus.OK);
  }

  @PutMapping("/updateUniform")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<UniformDTO> updateUniform(@RequestBody UniformDTO uniformDTO)
  {
    UniformDTO uniform = uniformService.getUniformById(uniformDTO.getUniformId());

    if (uniform == null)
    {
      return new ResponseEntity("Uniform ID not found", HttpStatus.NOT_FOUND);
    }
    UniformDTO updatedUniform = uniformService.updateUniform(uniformDTO);
    return new ResponseEntity<>(updatedUniform, HttpStatus.OK);
  }

  @DeleteMapping("/deleteUniform/{uniformId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<UniformDTO> deleteUniform(@PathVariable long uniformId)
  {
    UniformDTO uniform = uniformService.getUniformById(uniformId);

    if (uniform == null)
    {
      return new ResponseEntity("Uniform Id not found", HttpStatus.NOT_FOUND);
    }
    uniformService.deleteUniform(uniformId);
    return new ResponseEntity("uniform ID succesfully deleted", HttpStatus.OK);
  }

}

