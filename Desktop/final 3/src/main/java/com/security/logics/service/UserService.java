package com.security.logics.service;

import com.security.logics.dto.UserDTO;
import com.security.logics.model.UserEntity;
import com.security.logics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public UserDTO saveUser(UserDTO userDTO)
  {
    UserEntity userEntity = userRepository.save(modelMapper.map(userDTO, UserEntity.class));
    return modelMapper.map(userEntity, UserDTO.class);
  }

  public List<UserDTO> getAllUsers()
  {
    List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();

    List<UserDTO> dtos = userList
            .stream()
            .map(user -> modelMapper.map(user, UserDTO.class))
            .collect(Collectors.toList());

    return dtos;
  }

  public UserDTO getUserById(Long userId)
  {
    Optional<UserEntity> user = userRepository.findById(userId);

    if (user.isPresent())
    {
      return modelMapper.map(user.get(), UserDTO.class);
    }
    return null;
  }

  public UserDTO updateUser(UserDTO userDTO)
  {
    return modelMapper.map(userRepository.save(modelMapper.map(userDTO, UserEntity.class)), UserDTO.class);
  }


  public void deleteUser(Long userId)
  {
    userRepository.deleteById(userId);
  }
}

