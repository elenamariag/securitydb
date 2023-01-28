package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.TrouserDTO;
import com.security.logics.exception.ColorNotFoundException;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.exception.TrouserNotFoundException;
import com.security.logics.service.uniform.TrouserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("trouser")
public class TrouserController {
    private final TrouserService trouserService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public TrouserDTO saveTrouser(@RequestBody TrouserDTO trouserDTO) throws ColorNotFoundException, SizeNotFoundException {
        return trouserService.saveTrouser(trouserDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<TrouserDTO> getTrousers() {
        return trouserService.getAllTrousers();
    }

    @GetMapping("/{trouserId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TrouserDTO> getTrouserById(@PathVariable Long trouserId) throws TrouserNotFoundException {
        TrouserDTO trouser = trouserService.getTrouserById(trouserId);
        return new ResponseEntity<>(trouser, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TrouserDTO> updateTrouser(@RequestBody TrouserDTO trouserDTO) throws TrouserNotFoundException {
        TrouserDTO trouser = trouserService.getTrouserById(trouserDTO.getTrouserId());
        TrouserDTO updatedTrouser = trouserService.updateTrouser(trouserDTO);
        return new ResponseEntity<>(updatedTrouser, HttpStatus.OK);
    }

    @DeleteMapping("/{trouserId}")
    public ResponseEntity<TrouserDTO> deleteTrouser(@PathVariable Long trouserId) throws TrouserNotFoundException {
        TrouserDTO trouser = trouserService.getTrouserById(trouserId);
        trouserService.deleteTrouser(trouserId);
        return new ResponseEntity("Trouser is successfully deleted", HttpStatus.OK);

    }

}
