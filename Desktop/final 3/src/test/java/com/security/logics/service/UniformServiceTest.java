package com.security.logics.service;

import com.security.logics.AbstractTest;
import com.security.logics.dto.uniform.ShirtDTO;
import com.security.logics.dto.uniform.TieDTO;
import com.security.logics.dto.uniform.TrouserDTO;
import com.security.logics.dto.uniform.UniformDTO;
import com.security.logics.model.uniform.UniformEntity;
import com.security.logics.repository.UniformRepository;
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

public class UniformServiceTest extends AbstractTest {
    @Mock
    private UniformRepository uniformRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UniformService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveUniform() {
        //Arrange
        UniformDTO dto = new UniformDTO();
        dto.setUniformId(1L);
        dto.setShirt(new ShirtDTO());
        dto.setTie(new TieDTO());
        dto.setTrouser(new TrouserDTO());
        dto.setTotalAvailableUniforms(20L);
        UniformEntity entity = mapper.map(dto, UniformEntity.class);

        //Act
        when(modelMapper.map(dto, UniformEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, UniformDTO.class)).thenReturn(dto);
        when(uniformRepository.save(entity)).thenReturn(entity);
        UniformDTO res = this.service.saveUniform(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
        UniformDTO dto = new UniformDTO();
        dto.setUniformId(1L);
        dto.setShirt(new ShirtDTO());
        dto.setTie(new TieDTO());
        dto.setTrouser(new TrouserDTO());
        dto.setTotalAvailableUniforms(20L);
        UniformEntity entity = mapper.map(dto, UniformEntity.class);
        List<UniformEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, UniformDTO.class)).thenReturn(dto);
        when(uniformRepository.findAll()).thenReturn(list);
        List<UniformDTO> res = this.service.getAllUniforms();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getUniformByValidId() {
        //Arrange
        UniformDTO dto = new UniformDTO();
        dto.setUniformId(1L);
        dto.setShirt(new ShirtDTO());
        dto.setTie(new TieDTO());
        dto.setTrouser(new TrouserDTO());
        dto.setTotalAvailableUniforms(20L);
        UniformEntity entity = mapper.map(dto, UniformEntity.class);

        //Act
        when(modelMapper.map(entity, UniformDTO.class)).thenReturn(dto);
        when(uniformRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        UniformDTO res = this.service.getUniformById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getUniformByInValidId() {
        //Arrange
        UniformDTO dto = new UniformDTO();
        dto.setUniformId(1L);
        dto.setShirt(new ShirtDTO());
        dto.setTie(new TieDTO());
        dto.setTrouser(new TrouserDTO());
        dto.setTotalAvailableUniforms(20L);
        UniformEntity entity = mapper.map(dto, UniformEntity.class);

        //Act
        when(modelMapper.map(entity, UniformDTO.class)).thenReturn(dto);
        when(uniformRepository.findById((long) 1)).thenReturn(Optional.empty());
        UniformDTO res = this.service.getUniformById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateUniform() {
        //Arrange
        UniformDTO dto = new UniformDTO();
        dto.setUniformId(1L);
        dto.setShirt(new ShirtDTO());
        dto.setTie(new TieDTO());
        dto.setTrouser(new TrouserDTO());
        dto.setTotalAvailableUniforms(20L);
        UniformEntity entity = mapper.map(dto, UniformEntity.class);

        //Act
        when(modelMapper.map(dto, UniformEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, UniformDTO.class)).thenReturn(dto);
        when(uniformRepository.save(entity)).thenReturn(entity);
        UniformDTO res = this.service.updateUniform(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteUniform() {
        //Arrange
        UniformDTO dto = new UniformDTO();
        dto.setUniformId(1L);
        dto.setShirt(new ShirtDTO());
        dto.setTie(new TieDTO());
        dto.setTrouser(new TrouserDTO());
        dto.setTotalAvailableUniforms(20L);
        UniformEntity entity = mapper.map(dto, UniformEntity.class);

        //Act
        when(modelMapper.map(entity, UniformDTO.class)).thenReturn(dto);
        doNothing().when(uniformRepository).delete(entity);
        this.service.deleteUniform(1L);

        //Assert
        assertTrue(true);
    }
}
