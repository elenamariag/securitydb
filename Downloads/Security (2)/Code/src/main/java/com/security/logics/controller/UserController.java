package com.security.logics.controller;

import com.security.logics.dto.UserDTO;
import com.security.logics.exception.UserNotFoundException;
import com.security.logics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userService.saveUser(userDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        UserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws UserNotFoundException {
        UserDTO user = userService.getUserById(userDTO.getUserId());
        UserDTO updatedUser = userService.updateUser(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        UserDTO user = userService.getUserById(userId);
        userService.deleteUser(userId);
        return new ResponseEntity("User is successfully deleted", HttpStatus.OK);
    }

}
