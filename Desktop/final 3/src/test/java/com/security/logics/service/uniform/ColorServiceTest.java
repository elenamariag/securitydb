package com.security.logics.service.uniform;

import com.security.logics.AbstractTest;
import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.service.uniform.ColorService;
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

public class ColorServiceTest extends AbstractTest {
    @Mock
    private ColorRepository colorRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ColorService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveColor() {
        //Arrange
        ColorDTO dto = new ColorDTO();
        dto.setColorId(1L);
        dto.setColorName("Pink");
        ColorEntity entity = mapper.map(dto, ColorEntity.class);

        //Act
        when(modelMapper.map(dto, ColorEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, ColorDTO.class)).thenReturn(dto);
        when(colorRepository.save(entity)).thenReturn(entity);
        ColorDTO res = this.service.saveColor(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
        ColorDTO dto = new ColorDTO();
        dto.setColorId(1L);
        dto.setColorName("Pink");
        ColorEntity entity = mapper.map(dto, ColorEntity.class);
        List<ColorEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, ColorDTO.class)).thenReturn(dto);
        when(colorRepository.findAll()).thenReturn(list);
        List<ColorDTO> res = this.service.getAllColors();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getColorByValidId() {
        //Arrange
        ColorDTO dto = new ColorDTO();
        dto.setColorId(1L);
        dto.setColorName("Pink");
        ColorEntity entity = mapper.map(dto, ColorEntity.class);

        //Act
        when(modelMapper.map(entity, ColorDTO.class)).thenReturn(dto);
        when(colorRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        ColorDTO res = this.service.getColorById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getColorByInValidId() {
        //Arrange
        ColorDTO dto = new ColorDTO();
        dto.setColorId(1L);
        dto.setColorName("Pink");
        ColorEntity entity = mapper.map(dto, ColorEntity.class);

        //Act
        when(modelMapper.map(entity, ColorDTO.class)).thenReturn(dto);
        when(colorRepository.findById((long) 1)).thenReturn(Optional.empty());
        ColorDTO res = this.service.getColorById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateColor() {
        //Arrange
        ColorDTO dto = new ColorDTO();
        dto.setColorId(1L);
        dto.setColorName("Pink");
        ColorEntity entity = mapper.map(dto, ColorEntity.class);

        //Act
        when(modelMapper.map(dto, ColorEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, ColorDTO.class)).thenReturn(dto);
        when(colorRepository.save(entity)).thenReturn(entity);
        ColorDTO res = this.service.updateColor(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteColor() {
        //Arrange
        ColorDTO dto = new ColorDTO();
        dto.setColorId(1L);
        dto.setColorName("Pink");
        ColorEntity entity = mapper.map(dto, ColorEntity.class);

        //Act
        when(modelMapper.map(entity, ColorDTO.class)).thenReturn(dto);
        doNothing().when(colorRepository).delete(entity);
        this.service.deleteColor(1L);

        //Assert
        assertTrue(true);
    }
}
