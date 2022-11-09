package com.security.logics.controller;

import com.security.logics.dto.SecurityGuardDTO;
import com.security.logics.service.SecurityGuardService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class SecurityGuardController
{

  @Autowired
  private SecurityGuardService securityGuardService;

  @PostMapping("/securityGuard")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public SecurityGuardDTO saveSecurityGuard (@RequestParam("file") MultipartFile file,@RequestParam("json") String securityGuardDTO)
  {
    return securityGuardService.saveSecurityGuard(securityGuardDTO, file);
  }

  @GetMapping("/getSecurities")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public List<SecurityGuardDTO> getAllSecurities()
  {
    return securityGuardService.getAllSecurityGuards();
  }

  @GetMapping("/getSecurityById/{securityId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<SecurityGuardDTO> getSecurityById(@PathVariable Long securityId)
  {
    SecurityGuardDTO security = securityGuardService.getSecurityGuardById(securityId);

    if (security == null)
    {
      return new ResponseEntity("Security ID not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(security, HttpStatus.OK);
  }

  @PutMapping("/updateSecurity")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<SecurityGuardDTO> updateSecurityGuard(@RequestBody SecurityGuardDTO securityGuardDTO)
  {
    SecurityGuardDTO security = securityGuardService.getSecurityGuardById(securityGuardDTO.getSecurityGuardId());

    if (security == null)
    {
      return new ResponseEntity("Security Guard not found", HttpStatus.NOT_FOUND);
    }
    SecurityGuardDTO updatedSecurity = securityGuardService.updateSecurityGuard(securityGuardDTO);
    return new ResponseEntity<>(updatedSecurity, HttpStatus.OK);
  }

  @DeleteMapping("/deleteSecurityGuard/{securityGuardId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<SecurityGuardDTO> deleteSecurityGuard(@PathVariable Long securityId)
  {
    SecurityGuardDTO security = securityGuardService.getSecurityGuardById(securityId);

    if (security == null)
    {
      return new ResponseEntity("Security guard is not found", HttpStatus.NOT_FOUND);
    }
    securityGuardService.deleteSecurityGuard(securityId);
    return new ResponseEntity("Security guard is successfully deleted", HttpStatus.OK);
  }

}
