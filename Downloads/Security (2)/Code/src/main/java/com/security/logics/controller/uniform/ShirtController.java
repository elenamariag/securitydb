package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.ShirtDTO;
import com.security.logics.exception.ColorNotFoundException;
import com.security.logics.exception.ShirtNotFoundException;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.service.uniform.ShirtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("shirt")
public class ShirtController {
    private final ShirtService shirtService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ShirtDTO saveShirt(@RequestBody ShirtDTO shirtDTO) throws ColorNotFoundException, SizeNotFoundException {
        return shirtService.saveShirt(shirtDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<ShirtDTO> getShirts() {
        return shirtService.getAllShirts();
    }

    @GetMapping("/{shirtId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ShirtDTO> getShirtById(@PathVariable Long shirtId) throws ShirtNotFoundException {
        ShirtDTO shirt = shirtService.getShirtById(shirtId);
        return new ResponseEntity<>(shirt, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ShirtDTO> updateShirt(@RequestBody ShirtDTO shirtDTO) throws ShirtNotFoundException {
        ShirtDTO shirt = shirtService.getShirtById(shirtDTO.getShirtId());
        ShirtDTO updatedShirt = shirtService.updateShirt(shirtDTO);
        return new ResponseEntity<>(updatedShirt, HttpStatus.OK);
    }

    @DeleteMapping("/{shirtId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ShirtDTO> deleteShirt(@PathVariable Long shirtId) throws ShirtNotFoundException {
        ShirtDTO shirt = shirtService.getShirtById(shirtId);
        shirtService.deleteShirt(shirtId);
        return new ResponseEntity("Shirt is successfully deleted", HttpStatus.OK);
    }


}
