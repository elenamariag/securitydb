package com.security.logics.controller.uniform;

import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.service.uniform.ColorService;
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
public class ColorController
{

  @Autowired
  private ColorService colorService;

  @PostMapping("/color")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ColorDTO saveColor(@RequestBody ColorDTO colorDTO)
  {
    return colorService.saveColor(colorDTO);
  }

  @GetMapping("/getColors")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private List<ColorDTO> getColors()
  {
    return colorService.getAllColors();
  }

  @GetMapping("/getColorById/{colorId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<ColorDTO> getColorById(@PathVariable Long colorId)
  {
    ColorDTO color = colorService.getColorById(colorId);

    if (color == null)
    {
      return new ResponseEntity("Color not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(color, HttpStatus.OK);
  }

  @PutMapping("/updateColor")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<ColorDTO> updateColor(@RequestBody ColorDTO colorDTO)
  {
    ColorDTO color = colorService.getColorById(colorDTO.getColorId());

    if (color == null)
    {
      return new ResponseEntity("Color is not found", HttpStatus.NOT_FOUND);
    }
    ColorDTO updatedColor = colorService.updateColor(colorDTO);
    return new ResponseEntity<>(updatedColor, HttpStatus.OK);
  }

  @DeleteMapping("/deleteColor/{colorId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  private ResponseEntity<ColorDTO> deleteColor(@PathVariable Long colorId)
  {
    ColorDTO color = colorService.getColorById(colorId);

    if (color == null)
    {
      return new ResponseEntity("Color is not found", HttpStatus.NOT_FOUND);
    }
    colorService.deleteColor(colorId);
    return new ResponseEntity("Color is successfully deleted", HttpStatus.OK);
  }
}
