package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.TieDTO;
import com.security.logics.service.uniform.TieService;
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
public class TieController
{

  @Autowired
  private TieService tieService;

  @PostMapping("/tie")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private TieDTO saveTie(@RequestBody TieDTO tieDTO)
  {
    return tieService.saveTie(tieDTO);
  }

  @GetMapping("/getTies")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private List<TieDTO> getTies()
  {
    return tieService.getAllTies();
  }

  @GetMapping("/getTieById/{tieId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<TieDTO> getTieById(@PathVariable Long tieId)
  {
    TieDTO tie = tieService.getTieById(tieId);

    if (tie == null)
    {
      return new ResponseEntity("Tie not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(tie, HttpStatus.OK);
  }

  @PutMapping("/updateTie")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<TieDTO> updateTie(@RequestBody TieDTO tieDTO)
  {
    TieDTO tie = tieService.getTieById(tieDTO.getTieId());

    if (tie == null)
    {
      return new ResponseEntity("Tie not found", HttpStatus.NOT_FOUND);
    }
    TieDTO updatedTie = tieService.updateTie(tieDTO);
    return new ResponseEntity<>(updatedTie, HttpStatus.OK);
  }

  @DeleteMapping("/deleteTie")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<TieDTO> deleteTie(@PathVariable long tieId)
  {
    TieDTO tie = tieService.getTieById(tieId);

    if (tie == null)
    {
      return new ResponseEntity("Tie not found", HttpStatus.NOT_FOUND);
    }
    tieService.deleteTie(tieId);
    return new ResponseEntity("Tie is succesfully deleted", HttpStatus.OK);
  }
}
