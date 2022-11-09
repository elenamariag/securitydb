package com.security.logics.dto;

import com.security.logics.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;
  private String address;
  private String dateOfBirth;
  private String userType;
  private CompanyDTO company;
  private String password;
  private Set<Role> role;

}
