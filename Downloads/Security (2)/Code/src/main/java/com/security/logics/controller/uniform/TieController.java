package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.TieDTO;
import com.security.logics.exception.TieNotFoundException;
import com.security.logics.service.uniform.TieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tie")
public class TieController {
    private final TieService tieService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public TieDTO saveTie(@RequestBody TieDTO tieDTO) {
        return tieService.saveTie(tieDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<TieDTO> getTies() {
        return tieService.getAllTies();
    }

    @GetMapping("/{tieId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TieDTO> getTieById(@PathVariable Long tieId) throws TieNotFoundException {
        TieDTO tie = tieService.getTieById(tieId);
        return new ResponseEntity<>(tie, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TieDTO> updateTie(@RequestBody TieDTO tieDTO) throws TieNotFoundException {
        TieDTO tie = tieService.getTieById(tieDTO.getTieId());
        TieDTO updatedTie = tieService.updateTie(tieDTO);
        return new ResponseEntity<>(updatedTie, HttpStatus.OK);
    }

    @DeleteMapping("/{tieId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TieDTO> deleteTie(@PathVariable long tieId) throws TieNotFoundException {
        TieDTO tie = tieService.getTieById(tieId);
        tieService.deleteTie(tieId);
        return new ResponseEntity("Tie is successfully deleted", HttpStatus.OK);
    }
}
