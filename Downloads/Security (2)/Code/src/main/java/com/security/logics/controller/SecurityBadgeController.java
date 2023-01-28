package com.security.logics.controller;

import com.security.logics.dto.SecurityBadgeDTO;
import com.security.logics.exception.SecurityBadgeNotFoundException;
import com.security.logics.service.SecurityBadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("security-badge")
public class SecurityBadgeController {
    private final SecurityBadgeService securityBadgeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SecurityBadgeDTO saveSecurityBadge(@RequestBody SecurityBadgeDTO securityBadgeDTO) {
        return securityBadgeService.saveSecurityBadge(securityBadgeDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<SecurityBadgeDTO> getSecurityBadges() {
        return securityBadgeService.getAllSecurityBadges();
    }

    @GetMapping("/{securityBadgeId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SecurityBadgeDTO> getSecurityBadgeById(@PathVariable Long securityBadgeId) throws SecurityBadgeNotFoundException {
        SecurityBadgeDTO security = securityBadgeService.getSecurityBadgeById(securityBadgeId);
        return new ResponseEntity<>(security, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SecurityBadgeDTO> updateSecurityBadge(@RequestBody SecurityBadgeDTO securityBadgeDTO) throws SecurityBadgeNotFoundException {
        SecurityBadgeDTO security = securityBadgeService.getSecurityBadgeById(securityBadgeDTO.getSecurityBadgeId());
        SecurityBadgeDTO updatedSecurityBadge = securityBadgeService.updateSecurityBadge(securityBadgeDTO);
        return new ResponseEntity<>(updatedSecurityBadge, HttpStatus.OK);
    }

    @DeleteMapping("/{securityBadgeId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SecurityBadgeDTO> deleteSecurityBadge(@PathVariable Long securityBadgeId) throws SecurityBadgeNotFoundException {
        SecurityBadgeDTO security = securityBadgeService.getSecurityBadgeById(securityBadgeId);
        securityBadgeService.deleteSecurityBadge(securityBadgeId);
        return new ResponseEntity("Security badge is successfully deleted", HttpStatus.OK);
    }
}
