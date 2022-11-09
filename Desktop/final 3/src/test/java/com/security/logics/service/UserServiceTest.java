package com.security.logics.service;

import com.security.logics.AbstractTest;
import com.security.logics.dto.UserDTO;
import com.security.logics.model.UserEntity;
import com.security.logics.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserServiceTest extends AbstractTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveUser() {
        //Arrange
         UserDTO dto = new UserDTO();
        dto.setUserId(1L);
        dto.setFirstName("testUserName");
        dto.setLastName("testUserName");
        dto.setAddress("testUserAddress, Street 25, London");
        dto.setEmail("testUserName@gmail.com");
        UserEntity entity = mapper.map(dto, UserEntity.class);

        //Act
        when(modelMapper.map(dto, UserEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, UserDTO.class)).thenReturn(dto);
        when(userRepository.save(entity)).thenReturn(entity);
        UserDTO res = this.service.saveUser(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
         UserDTO dto = new UserDTO();
        dto.setUserId(1L);
        dto.setFirstName("testUserName");
        dto.setLastName("testUserName");
        dto.setAddress("testUserAddress, Street 25, London");
        dto.setEmail("testUserName@gmail.com");
        UserEntity entity = mapper.map(dto, UserEntity.class);
        List<UserEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, UserDTO.class)).thenReturn(dto);
        when(userRepository.findAll()).thenReturn(list);
        List<UserDTO> res = this.service.getAllUsers();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getUserByValidId() {
        //Arrange
         UserDTO dto = new UserDTO();
        dto.setUserId(1L);
        dto.setFirstName("testUserName");
        dto.setLastName("testUserName");
        dto.setAddress("testUserAddress, Street 25, London");
        dto.setEmail("testUserName@gmail.com");
        UserEntity entity = mapper.map(dto, UserEntity.class);

        //Act
        when(modelMapper.map(entity, UserDTO.class)).thenReturn(dto);
        when(userRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        UserDTO res = this.service.getUserById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getUserByInValidId() {
        //Arrange
         UserDTO dto = new UserDTO();
        dto.setUserId(1L);
        dto.setFirstName("testUserName");
        dto.setLastName("testUserName");
        dto.setAddress("testUserAddress, Street 25, London");
        dto.setEmail("testUserName@gmail.com");
        UserEntity entity = mapper.map(dto, UserEntity.class);

        //Act
        when(modelMapper.map(entity, UserDTO.class)).thenReturn(dto);
        when(userRepository.findById((long) 1)).thenReturn(Optional.empty());
        UserDTO res = this.service.getUserById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateUser() {
        //Arrange
        UserDTO dto = new UserDTO();
        dto.setUserId(1L);
        dto.setFirstName("testUserName");
        dto.setLastName("testUserName");
        dto.setAddress("testUserAddress, Street 25, London");
        dto.setEmail("testUserName@gmail.com");
        UserEntity entity = mapper.map(dto, UserEntity.class);

        //Act
        when(modelMapper.map(dto, UserEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, UserDTO.class)).thenReturn(dto);
        when(userRepository.save(entity)).thenReturn(entity);
        UserDTO res = this.service.updateUser(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteUser() {
        //Arrange
        UserDTO dto = new UserDTO();
        dto.setUserId(1L);
        dto.setFirstName("testUserName");
        dto.setLastName("testUserName");
        dto.setAddress("testUserAddress, Street 25, London");
        dto.setEmail("testUserName@gmail.com");
        UserEntity entity = mapper.map(dto, UserEntity.class);

        //Act
        when(modelMapper.map(entity, UserDTO.class)).thenReturn(dto);
        doNothing().when(userRepository).delete(entity);
        this.service.deleteUser(1L);

        //Assert
        assertTrue(true);
    }
}
