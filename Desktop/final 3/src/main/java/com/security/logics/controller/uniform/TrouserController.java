package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.TrouserDTO;
import com.security.logics.service.uniform.TrouserService;
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
public class TrouserController
{

  @Autowired
  private TrouserService trouserService;

  @PostMapping("/trouser")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private TrouserDTO saveTrouser(@RequestBody TrouserDTO trouserDTO)
  {
    return trouserService.saveTrouser(trouserDTO);
  }

  @GetMapping("/getTrousers")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private List<TrouserDTO> getTrousers()
  {
    return trouserService.getAllTrousers();
  }

  @GetMapping("/getTrouserById/{trouserId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<TrouserDTO> getTrouserById(@PathVariable Long trouserId)
  {
    TrouserDTO trouser = trouserService.getTrouserById(trouserId);

    if (trouser == null)
    {
      return new ResponseEntity("Trouser is not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(trouser, HttpStatus.OK);
  }

  @PutMapping("/updateTrouser")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<TrouserDTO> updateTrouser(@RequestBody TrouserDTO trouserDTO)
  {
    TrouserDTO trouser = trouserService.getTrouserById(trouserDTO.getTrouserId());

    if (trouser == null)
    {
      return new ResponseEntity("Trouser is not found", HttpStatus.NOT_FOUND);
    }
    TrouserDTO updatedTrouser = trouserService.updateTrouser(trouserDTO);
    return new ResponseEntity<>(updatedTrouser, HttpStatus.OK);
  }

  @DeleteMapping("/deleteTrouser")
  private ResponseEntity<TrouserDTO> deleteTrouser(@PathVariable Long trouserId)
  {
    TrouserDTO trouser = trouserService.getTrouserById(trouserId);

    if (trouser == null)
    {
      return new ResponseEntity("Trouser is not found", HttpStatus.NOT_FOUND);
    }
    trouserService.deleteTrouser(trouserId);
    return new ResponseEntity("Trouser is succesfully deleted", HttpStatus.OK);

  }

}
