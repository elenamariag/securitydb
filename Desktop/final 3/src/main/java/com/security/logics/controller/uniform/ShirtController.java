package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.ShirtDTO;
import com.security.logics.service.uniform.ShirtService;
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
public class ShirtController
{

  @Autowired
  private ShirtService shirtService;

  @PostMapping("/shirt")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ShirtDTO saveShirt(@RequestBody ShirtDTO shirtDTO)
  {
    return shirtService.saveShirt(shirtDTO);
  }

  @GetMapping("/getShirts")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private List<ShirtDTO> getShirts()
  {
    return shirtService.getAllShirts();
  }

  @GetMapping("/getShirtById/{shirtId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<ShirtDTO> getShirtById(@PathVariable Long shirtId)
  {
    ShirtDTO shirt = shirtService.getShirtById(shirtId);

    if (shirt == null)
    {
      return new ResponseEntity("Shirt not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(shirt, HttpStatus.OK);
  }

  @PutMapping("/updateShirt")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<ShirtDTO> updateShirt(@RequestBody ShirtDTO shirtDTO)
  {
    ShirtDTO shirt = shirtService.getShirtById(shirtDTO.getShirtId());

    if (shirt == null)
    {
      return new ResponseEntity("Shirt is not found", HttpStatus.NOT_FOUND);
    }
    ShirtDTO updatedShirt = shirtService.updateShirt(shirtDTO);
    return new ResponseEntity<>(updatedShirt, HttpStatus.OK);
  }

  @DeleteMapping("/deleteShirt/{shirtId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<ShirtDTO> deleteShirt(@PathVariable Long shirtId)
  {
    ShirtDTO shirt = shirtService.getShirtById(shirtId);

    if (shirt == null)
    {
      return new ResponseEntity("Shirt not found", HttpStatus.NOT_FOUND);
    }
    shirtService.deleteShirt(shirtId);
    return new ResponseEntity("Shirt is succesfully deleted", HttpStatus.OK);
  }


}
