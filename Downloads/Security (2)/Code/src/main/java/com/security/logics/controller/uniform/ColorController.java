package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.exception.ColorNotFoundException;
import com.security.logics.service.uniform.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("color")
public class ColorController {
    private final ColorService colorService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ColorDTO saveColor(@RequestBody ColorDTO colorDTO) {
        return colorService.saveColor(colorDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<ColorDTO> getColors() {
        return colorService.getAllColors();
    }

    @GetMapping("/{colorId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ColorDTO> getColorById(@PathVariable Long colorId) throws ColorNotFoundException {
        ColorDTO color = colorService.getColorById(colorId);
        return new ResponseEntity<>(color, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ColorDTO> updateColor(@RequestBody ColorDTO colorDTO) throws ColorNotFoundException {
        ColorDTO color = colorService.getColorById(colorDTO.getColorId());
        ColorDTO updatedColor = colorService.updateColor(colorDTO);
        return new ResponseEntity<>(updatedColor, HttpStatus.OK);
    }

    @DeleteMapping("/{colorId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ColorDTO> deleteColor(@PathVariable Long colorId) throws ColorNotFoundException {
        ColorDTO color = colorService.getColorById(colorId);
        colorService.deleteColor(colorId);
        return new ResponseEntity("Color is successfully deleted", HttpStatus.OK);
    }
}
