package com.security.logics.service.uniform;

import com.security.logics.AbstractTest;
import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.dto.uniform.ShirtDTO;
import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.dto.uniform.TrouserDTO;
import com.security.logics.exception.ColorNotFoundException;
import com.security.logics.exception.SizeNotFoundException;
import com.security.logics.exception.TrouserNotFoundException;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.ShirtEntity;
import com.security.logics.model.uniform.SizeEntity;
import com.security.logics.model.uniform.TrouserEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.SizeRepository;
import com.security.logics.repository.uniform.TrouserRepository;
import com.security.logics.service.uniform.TrouserService;
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

public class TrouserServiceTest extends AbstractTest {
    @Mock
    private TrouserRepository trouserRepository;
    @Mock
    private ColorRepository colorRepository;
    @Mock
    private SizeRepository sizeRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TrouserService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveTrouser() throws ColorNotFoundException, SizeNotFoundException {
        //Arrange
        ColorDTO color = new ColorDTO();
        color.setColorName("Grey");
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setSize("Large");
        TrouserDTO dto = new TrouserDTO();
        dto.setTrouserId(1L);
        dto.setTrouserName("Pink");
        dto.setColor(color);
        dto.setSize(sizeDTO);
        TrouserEntity entity = mapper.map(dto, TrouserEntity.class);

        //Act
        when(modelMapper.map(dto, TrouserEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, TrouserDTO.class)).thenReturn(dto);
        when(colorRepository.findByColorName(dto.getColor().getColorName())).thenReturn(mapper.map(color, ColorEntity.class));
        when(sizeRepository.findBySize(dto.getSize().getSize())).thenReturn(mapper.map(sizeDTO, SizeEntity.class));
        when(trouserRepository.save(entity)).thenReturn(entity);
        TrouserDTO res = this.service.saveTrouser(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
        TrouserDTO dto = new TrouserDTO();
        dto.setTrouserId(1L);
        dto.setTrouserName("Pink");
        TrouserEntity entity = mapper.map(dto, TrouserEntity.class);
        List<TrouserEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, TrouserDTO.class)).thenReturn(dto);
        when(trouserRepository.findAll()).thenReturn(list);
        List<TrouserDTO> res = this.service.getAllTrousers();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getTrouserByValidId() throws TrouserNotFoundException {
        //Arrange
        TrouserDTO dto = new TrouserDTO();
        dto.setTrouserId(1L);
        dto.setTrouserName("Pink");
        TrouserEntity entity = mapper.map(dto, TrouserEntity.class);

        //Act
        when(modelMapper.map(entity, TrouserDTO.class)).thenReturn(dto);
        when(trouserRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        TrouserDTO res = this.service.getTrouserById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getTrouserByInValidId() throws TrouserNotFoundException {
        //Arrange
        TrouserDTO dto = new TrouserDTO();
        dto.setTrouserId(1L);
        dto.setTrouserName("Pink");
        TrouserEntity entity = mapper.map(dto, TrouserEntity.class);

        //Act
        when(modelMapper.map(entity, TrouserDTO.class)).thenReturn(dto);
        when(trouserRepository.findById((long) 1)).thenReturn(Optional.empty());
        TrouserDTO res = this.service.getTrouserById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateTrouser() throws TrouserNotFoundException {
        //Arrange
        TrouserDTO dto = new TrouserDTO();
        dto.setTrouserId(1L);
        dto.setTrouserName("Pink");
        TrouserEntity entity = mapper.map(dto, TrouserEntity.class);

        //Act
        when(modelMapper.map(dto, TrouserEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, TrouserDTO.class)).thenReturn(dto);
        when(trouserRepository.save(entity)).thenReturn(entity);
        TrouserDTO res = this.service.updateTrouser(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteTrouser() throws TrouserNotFoundException {
        //Arrange
        TrouserDTO dto = new TrouserDTO();
        dto.setTrouserId(1L);
        dto.setTrouserName("Pink");
        TrouserEntity entity = mapper.map(dto, TrouserEntity.class);

        //Act
        when(modelMapper.map(entity, TrouserDTO.class)).thenReturn(dto);
        doNothing().when(trouserRepository).delete(entity);
        this.service.deleteTrouser(1L);

        //Assert
        assertTrue(true);
    }
}
