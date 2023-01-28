package com.security.logics.controller;

import com.security.logics.dto.SecurityGuardDTO;
import com.security.logics.exception.SecurityGuardNotFoundException;
import com.security.logics.service.SecurityGuardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("security-guard")
public class SecurityGuardController {
    private final SecurityGuardService securityGuardService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SecurityGuardDTO saveSecurityGuard(@RequestParam("file") MultipartFile file, @RequestParam("json") String securityGuardDTO) {
        return securityGuardService.saveSecurityGuard(securityGuardDTO, file);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<SecurityGuardDTO> getAllSecurities() {
        return securityGuardService.getAllSecurityGuards();
    }

    @GetMapping("/{securityGuardId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SecurityGuardDTO> getSecurityById(@PathVariable Long securityGuardId) throws SecurityGuardNotFoundException {
        SecurityGuardDTO security = securityGuardService.getSecurityGuardById(securityGuardId);
        return new ResponseEntity<>(security, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SecurityGuardDTO> updateSecurityGuard(@RequestBody SecurityGuardDTO securityGuardDTO) throws SecurityGuardNotFoundException {
        SecurityGuardDTO security = securityGuardService.getSecurityGuardById(securityGuardDTO.getSecurityGuardId());
        SecurityGuardDTO updatedSecurity = securityGuardService.updateSecurityGuard(securityGuardDTO);
        return new ResponseEntity<>(updatedSecurity, HttpStatus.OK);
    }

    @DeleteMapping("/{securityGuardId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SecurityGuardDTO> deleteSecurityGuard(@PathVariable Long securityGuardId) throws SecurityGuardNotFoundException {
        SecurityGuardDTO security = securityGuardService.getSecurityGuardById(securityGuardId);
        securityGuardService.deleteSecurityGuard(securityGuardId);
        return new ResponseEntity("Security guard is successfully deleted", HttpStatus.OK);
    }

}
