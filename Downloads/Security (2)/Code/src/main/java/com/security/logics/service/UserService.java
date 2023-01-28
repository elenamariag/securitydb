package com.security.logics.service;

import com.security.logics.dto.UserDTO;
import com.security.logics.exception.UserNotFoundException;
import com.security.logics.model.UserEntity;
import com.security.logics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.save(modelMapper.map(userDTO, UserEntity.class));
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();

        List<UserDTO> dtos = userList
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }

    public UserDTO getUserById(Long userId) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDTO.class);
        } else {
            throw new UserNotFoundException("User Not Found: " + userId);
        }
    }

    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(userDTO.getUserId());

        if (user.isPresent()) {
            return modelMapper.map(userRepository.save(modelMapper.map(userDTO, UserEntity.class)), UserDTO.class);
        } else {
            throw new UserNotFoundException("User Not Found: " + userDTO.getUserId());
        }

    }


    public void deleteUser(Long userId) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(userId);

        if (user.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException("User Not Found: " + userId);
        }
    }
}

