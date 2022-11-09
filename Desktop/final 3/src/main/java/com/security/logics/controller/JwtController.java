package com.security.logics.controller;

import com.security.logics.dto.JwtRequestDTO;
import com.security.logics.dto.JwtResponseDTO;
import com.security.logics.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class JwtController {

    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public JwtResponseDTO createJwtToken(@RequestBody JwtRequestDTO jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
