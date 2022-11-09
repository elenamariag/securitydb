package com.security.logics.controller;

import com.security.logics.dto.UserDTO;
import com.security.logics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class UserController
{

  @Autowired
  private final PasswordEncoder passwordEncoder;

  @Autowired
  private final UserService userService;


  @PostMapping("/user")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public UserDTO saveUser(@RequestBody UserDTO userDTO)
  {
    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    return userService.saveUser(userDTO);
  }

  @GetMapping("/getUsers")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public List<UserDTO> getUsers()
  {
    return userService.getAllUsers();
  }

  @GetMapping("/getUserById/{userId}")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId)
  {
    UserDTO user = userService.getUserById(userId);

    if (user == null)
    {
      return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);

  }

  @PutMapping("/updateUser")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO)
  {
    UserDTO user = userService.getUserById(userDTO.getUserId());

    if (user == null)
    {
      return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }
    UserDTO updatedUser = userService.updateUser(userDTO);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);

  }

  @DeleteMapping("/deleteUser/{userId}")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<UserDTO> deleteUser(@PathVariable Long userId)
  {
    UserDTO user = userService.getUserById(userId);

    if (user == null)
    {
      return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }
    userService.deleteUser(userId);
    return new ResponseEntity("User is succesfully deleted", HttpStatus.OK);
  }

}
