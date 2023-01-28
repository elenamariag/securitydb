package com.security.logics.service;

import com.security.logics.dto.JwtRequestDTO;
import com.security.logics.dto.JwtResponseDTO;
import com.security.logics.dto.UserDTO;
import com.security.logics.model.UserEntity;
import com.security.logics.repository.UserRepository;
import com.security.logics.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class JwtService implements UserDetailsService {

    private UserRepository userRepository;

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    @Autowired
    public void JwtService(UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponseDTO createJwtToken(JwtRequestDTO jwtRequest) throws Exception {
        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        authenticate(email, password);

        final UserDetails userDetails = loadUserByUsername(email);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        UserEntity user = userRepository.findByEmail(email);

        UserDTO responseUser = new UserDTO();
        responseUser.setUserId(user.getUserId());
        responseUser.setUserType(user.getUserType());
        responseUser.setEmail(user.getEmail());
        responseUser.setAddress(user.getAddress());
        responseUser.setDateOfBirth(user.getDateOfBirth());
        responseUser.setFirstName(user.getFirstName());
        responseUser.setLastName(user.getLastName());

        return new JwtResponseDTO(user.getUserId(), user.getEmail(), newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            return new User(user.getEmail(), user.getPassword(), getAuthorities(user));
        } else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private Set getAuthorities(UserEntity user) {
        Set authorities = new HashSet();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
        });
        return authorities;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User is disabled!");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials from user!");
        }
    }
}
