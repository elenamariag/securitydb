package com.security.logics.controller;

import com.security.logics.dto.SecurityBadgeDTO;
import com.security.logics.service.SecurityBadgeService;
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
public class SecurityBadgeController
{

  @Autowired
  private SecurityBadgeService securityBadgeService;

  @PostMapping("/securityBadge")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public SecurityBadgeDTO saveSecurityBadge(@RequestBody SecurityBadgeDTO securityBadgeDTO)
  {
    return securityBadgeService.saveSecurityBadge(securityBadgeDTO);
  }

  @GetMapping("/getSecurityBadges")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public List<SecurityBadgeDTO> getSecurityBadges()
  {
    return securityBadgeService.getAllSecurityBadges();
  }

  @GetMapping("/getSecurityBadgeById/{securityBadgeId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<SecurityBadgeDTO> getSecurityBadgeById(@PathVariable Long securityBadgeId)
  {
    SecurityBadgeDTO security = securityBadgeService.getSecurityBadgeById(securityBadgeId);

    if (security == null)
    {
      return new ResponseEntity("Security badge ID is not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(security, HttpStatus.OK);
  }

  @PutMapping("/updateSecurityBadge")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<SecurityBadgeDTO> updateSecurityBadge(@RequestBody SecurityBadgeDTO securityBadgeDTO)
  {
    SecurityBadgeDTO security = securityBadgeService.getSecurityBadgeById(securityBadgeDTO.getSecurityBadgeId());

    if (security == null)
    {
      return new ResponseEntity("Security badge by ID is not found", HttpStatus.NOT_FOUND);
    }
    SecurityBadgeDTO updatedSecurityBadge = securityBadgeService.updateSecurityBadge(securityBadgeDTO);
    return new ResponseEntity<>(updatedSecurityBadge, HttpStatus.OK);
  }

  @DeleteMapping("/deleteSecurityBadge/{securityBadgeId}")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<SecurityBadgeDTO> deleteSecurityBadge(@PathVariable Long securityBadgeId)
  {
    SecurityBadgeDTO security = securityBadgeService.getSecurityBadgeById(securityBadgeId);

    if (security == null)
    {
      return new ResponseEntity("Security badge is not found", HttpStatus.NOT_FOUND);
    }
    securityBadgeService.deleteSecurityBadge(securityBadgeId);
    return new ResponseEntity("Security badge is succesfully deleted", HttpStatus.OK);
  }
}
