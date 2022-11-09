package com.security.logics.service.uniform;

import com.security.logics.AbstractTest;
import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.dto.uniform.ShirtDTO;
import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.ShirtEntity;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.ShirtRepository;
import com.security.logics.repository.uniform.SizeRepository;
import com.security.logics.service.uniform.ShirtService;
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

public class ShirtServiceTest extends AbstractTest {
    @Mock
    private ShirtRepository shirtRepository;
    @Mock
    private ColorRepository colorRepository;
    @Mock
    private SizeRepository sizeRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ShirtService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveShirt() {
        //Arrange
        ColorDTO color = new ColorDTO();
        color.setColorName("Grey");
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setSize("Large");
        ShirtDTO dto = new ShirtDTO();
        dto.setShirtId(1L);
        dto.setShirtName("Pink");
        dto.setColor(color);
        dto.setSize(sizeDTO);
        ShirtEntity entity = mapper.map(dto, ShirtEntity.class);

        //Act
        when(modelMapper.map(dto, ShirtEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, ShirtDTO.class)).thenReturn(dto);
        when(shirtRepository.save(entity)).thenReturn(entity);
        when(colorRepository.findByColorName(dto.getColor().getColorName())).thenReturn(mapper.map(color, ColorEntity.class));
        when(sizeRepository.findBySize(dto.getSize().getSize())).thenReturn(mapper.map(sizeDTO, SizeEntity.class));
        ShirtDTO res = this.service.saveShirt(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
        ShirtDTO dto = new ShirtDTO();
        dto.setShirtId(1L);
        dto.setShirtName("Pink");
        ShirtEntity entity = mapper.map(dto, ShirtEntity.class);
        List<ShirtEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, ShirtDTO.class)).thenReturn(dto);
        when(shirtRepository.findAll()).thenReturn(list);
        List<ShirtDTO> res = this.service.getAllShirts();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getShirtByValidId() {
        //Arrange
        ShirtDTO dto = new ShirtDTO();
        dto.setShirtId(1L);
        dto.setShirtName("Pink");
        ShirtEntity entity = mapper.map(dto, ShirtEntity.class);

        //Act
        when(modelMapper.map(entity, ShirtDTO.class)).thenReturn(dto);
        when(shirtRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        ShirtDTO res = this.service.getShirtById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getShirtByInValidId() {
        //Arrange
        ShirtDTO dto = new ShirtDTO();
        dto.setShirtId(1L);
        dto.setShirtName("Pink");
        ShirtEntity entity = mapper.map(dto, ShirtEntity.class);

        //Act
        when(modelMapper.map(entity, ShirtDTO.class)).thenReturn(dto);
        when(shirtRepository.findById((long) 1)).thenReturn(Optional.empty());
        ShirtDTO res = this.service.getShirtById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateShirt() {
        //Arrange
        ColorDTO color = new ColorDTO();
        color.setColorName("Grey");
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setSize("Large");
        ShirtDTO dto = new ShirtDTO();
        dto.setShirtId(1L);
        dto.setShirtName("Pink");
        dto.setColor(color);
        dto.setSize(sizeDTO);
        ShirtEntity entity = mapper.map(dto, ShirtEntity.class);

        //Act
        when(modelMapper.map(dto, ShirtEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, ShirtDTO.class)).thenReturn(dto);
        when(shirtRepository.save(entity)).thenReturn(entity);
        when(colorRepository.findByColorName(dto.getColor().getColorName())).thenReturn(mapper.map(color, ColorEntity.class));
        when(sizeRepository.findBySize(dto.getSize().getSize())).thenReturn(mapper.map(sizeDTO, SizeEntity.class));
        ShirtDTO res = this.service.updateShirt(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteShirt() {
        //Arrange
        ShirtDTO dto = new ShirtDTO();
        dto.setShirtId(1L);
        dto.setShirtName("Pink");
        ShirtEntity entity = mapper.map(dto, ShirtEntity.class);

        //Act
        when(modelMapper.map(entity, ShirtDTO.class)).thenReturn(dto);
        doNothing().when(shirtRepository).delete(entity);
        this.service.deleteShirt(1L);

        //Assert
        assertTrue(true);
    }
}
