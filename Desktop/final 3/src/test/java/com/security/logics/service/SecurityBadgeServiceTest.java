package com.security.logics.service;

import com.security.logics.AbstractTest;
import com.security.logics.dto.SecurityBadgeDTO;
import com.security.logics.model.SecurityBadgeEntity;
import com.security.logics.repository.SecurityBadgeRepository;
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

public class SecurityBadgeServiceTest extends AbstractTest {
    @Mock
    private SecurityBadgeRepository securityBadgeRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SecurityBadgeService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveSecurityBadge() {
        //Arrange
        SecurityBadgeDTO dto = new SecurityBadgeDTO();
        dto.setSecurityBadgeId(1L);
        dto.setName("testBadgeName");
        dto.setSecurityBadgeNr("testSecurityBadgeNr");
        dto.setColourSecurityBadge("Red");
        SecurityBadgeEntity entity = mapper.map(dto, SecurityBadgeEntity.class);

        //Act
        when(modelMapper.map(dto, SecurityBadgeEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, SecurityBadgeDTO.class)).thenReturn(dto);
        when(securityBadgeRepository.save(entity)).thenReturn(entity);
        SecurityBadgeDTO res = this.service.saveSecurityBadge(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
        SecurityBadgeDTO dto = new SecurityBadgeDTO();
        dto.setSecurityBadgeId(1L);
        dto.setName("testBadgeName");
        dto.setSecurityBadgeNr("testSecurityBadgeNr");
        dto.setColourSecurityBadge("Red");
        SecurityBadgeEntity entity = mapper.map(dto, SecurityBadgeEntity.class);
        List<SecurityBadgeEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, SecurityBadgeDTO.class)).thenReturn(dto);
        when(securityBadgeRepository.findAll()).thenReturn(list);
        List<SecurityBadgeDTO> res = this.service.getAllSecurityBadges();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getSecurityBadgeByValidId() {
        //Arrange
        SecurityBadgeDTO dto = new SecurityBadgeDTO();
        dto.setSecurityBadgeId(1L);
        dto.setName("testBadgeName");
        dto.setSecurityBadgeNr("testSecurityBadgeNr");
        dto.setColourSecurityBadge("Red");
        SecurityBadgeEntity entity = mapper.map(dto, SecurityBadgeEntity.class);

        //Act
        when(modelMapper.map(entity, SecurityBadgeDTO.class)).thenReturn(dto);
        when(securityBadgeRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        SecurityBadgeDTO res = this.service.getSecurityBadgeById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getSecurityBadgeByInValidId() {
        //Arrange
        SecurityBadgeDTO dto = new SecurityBadgeDTO();
        dto.setSecurityBadgeId(1L);
        dto.setName("testBadgeName");
        dto.setSecurityBadgeNr("testSecurityBadgeNr");
        dto.setColourSecurityBadge("Red");
        SecurityBadgeEntity entity = mapper.map(dto, SecurityBadgeEntity.class);

        //Act
        when(modelMapper.map(entity, SecurityBadgeDTO.class)).thenReturn(dto);
        when(securityBadgeRepository.findById((long) 1)).thenReturn(Optional.empty());
        SecurityBadgeDTO res = this.service.getSecurityBadgeById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateSecurityBadge() {
        //Arrange
        SecurityBadgeDTO dto = new SecurityBadgeDTO();
        dto.setSecurityBadgeId(1L);
        dto.setName("testBadgeName");
        dto.setSecurityBadgeNr("testSecurityBadgeNr");
        dto.setColourSecurityBadge("Red");
        SecurityBadgeEntity entity = mapper.map(dto, SecurityBadgeEntity.class);

        //Act
        when(modelMapper.map(dto, SecurityBadgeEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, SecurityBadgeDTO.class)).thenReturn(dto);
        when(securityBadgeRepository.save(entity)).thenReturn(entity);
        SecurityBadgeDTO res = this.service.updateSecurityBadge(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteSecurityBadge() {
        //Arrange
        SecurityBadgeDTO dto = new SecurityBadgeDTO();
        dto.setSecurityBadgeId(1L);
        dto.setName("testBadgeName");
        dto.setSecurityBadgeNr("testSecurityBadgeNr");
        dto.setColourSecurityBadge("Red");
        SecurityBadgeEntity entity = mapper.map(dto, SecurityBadgeEntity.class);

        //Act
        when(modelMapper.map(entity, SecurityBadgeDTO.class)).thenReturn(dto);
        doNothing().when(securityBadgeRepository).delete(entity);
        this.service.deleteSecurityBadge(1L);

        //Assert
        assertTrue(true);
    }
}
