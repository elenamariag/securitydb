package com.security.logics.service.uniform;

import com.security.logics.AbstractTest;
import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.repository.uniform.SizeRepository;
import com.security.logics.service.uniform.SizeService;
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

public class SizeServiceTest extends AbstractTest {
    @Mock
    private SizeRepository sizeRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SizeService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveSize() {
        //Arrange
        SizeDTO dto = new SizeDTO();
        dto.setSizeId(1L);
        dto.setSize("Large");
        SizeEntity entity = mapper.map(dto, SizeEntity.class);

        //Act
        when(modelMapper.map(dto, SizeEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, SizeDTO.class)).thenReturn(dto);
        when(sizeRepository.save(entity)).thenReturn(entity);
        SizeDTO res = this.service.saveSize(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
        SizeDTO dto = new SizeDTO();
        dto.setSizeId(1L);
        dto.setSize("Large");
        SizeEntity entity = mapper.map(dto, SizeEntity.class);
        List<SizeEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, SizeDTO.class)).thenReturn(dto);
        when(sizeRepository.findAll()).thenReturn(list);
        List<SizeDTO> res = this.service.getAllSizes();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getSizeByValidId() throws SizeNotFoundException {
        //Arrange
        SizeDTO dto = new SizeDTO();
        dto.setSizeId(1L);
        dto.setSize("Large");
        SizeEntity entity = mapper.map(dto, SizeEntity.class);

        //Act
        when(modelMapper.map(entity, SizeDTO.class)).thenReturn(dto);
        when(sizeRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        SizeDTO res = this.service.getSizeById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getSizeByInValidId() throws SizeNotFoundException {
        //Arrange
        SizeDTO dto = new SizeDTO();
        dto.setSizeId(1L);
        dto.setSize("Large");
        SizeEntity entity = mapper.map(dto, SizeEntity.class);

        //Act
        when(modelMapper.map(entity, SizeDTO.class)).thenReturn(dto);
        when(sizeRepository.findById((long) 1)).thenReturn(Optional.empty());
        SizeDTO res = this.service.getSizeById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateSize() throws SizeNotFoundException {
        //Arrange
        SizeDTO dto = new SizeDTO();
        dto.setSizeId(1L);
        dto.setSize("Large");
        SizeEntity entity = mapper.map(dto, SizeEntity.class);

        //Act
        when(modelMapper.map(dto, SizeEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, SizeDTO.class)).thenReturn(dto);
        when(sizeRepository.save(entity)).thenReturn(entity);
        SizeDTO res = this.service.updateSize(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteSize() throws SizeNotFoundException {
        //Arrange
        SizeDTO dto = new SizeDTO();
        dto.setSizeId(1L);
        dto.setSize("Large");
        SizeEntity entity = mapper.map(dto, SizeEntity.class);

        //Act
        when(modelMapper.map(entity, SizeDTO.class)).thenReturn(dto);
        doNothing().when(sizeRepository).delete(entity);
        this.service.deleteSize(1L);

        //Assert
        assertTrue(true);
    }
}
