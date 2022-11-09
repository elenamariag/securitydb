package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.service.uniform.SizeService;
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
public class SizeController
{

  @Autowired
  private SizeService sizeService;

  @PostMapping("/size")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private SizeDTO saveSize(@RequestBody SizeDTO sizeDTO)
  {
    return sizeService.saveSize(sizeDTO);
  }

  @GetMapping("/getSizes")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private List<SizeDTO> getSizes()
  {
    return sizeService.getAllSizes();
  }

  @GetMapping("/getSizeById/{sizeId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<SizeDTO> getSizeById(@PathVariable Long sizeId)
  {
    SizeDTO size = sizeService.getSizeById(sizeId);

    if (size == null)
    {
      return new ResponseEntity("Size not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(size, HttpStatus.OK);
  }

  @PutMapping("/updateSize")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<SizeDTO> updateSize(@RequestBody SizeDTO sizeDTO)
  {
    SizeDTO size = sizeService.getSizeById(sizeDTO.getSizeId());

    if (size == null)
    {
      return new ResponseEntity("Size not found", HttpStatus.NOT_FOUND);
    }
    SizeDTO updatedSize = sizeService.updateSize(sizeDTO);
    return new ResponseEntity<>(updatedSize, HttpStatus.OK);
  }

  @DeleteMapping("/deleteSize/{sizeId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<SizeDTO> deleteSize(@PathVariable Long sizeId)
  {
    SizeDTO size = sizeService.getSizeById(sizeId);

    if (size == null)
    {
      return new ResponseEntity("Size not found", HttpStatus.NOT_FOUND);
    }
    sizeService.deleteSize(sizeId);
    return new ResponseEntity("Size is succesfully deleted", HttpStatus.OK);

  }
}
