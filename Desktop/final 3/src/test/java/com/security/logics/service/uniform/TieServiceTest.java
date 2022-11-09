package com.security.logics.service.uniform;

import com.security.logics.AbstractTest;
import com.security.logics.dto.uniform.ColorDTO;
import com.security.logics.dto.uniform.SizeDTO;
import com.security.logics.dto.uniform.TieDTO;
import com.security.logics.model.uniform.ColorEntity;
import com.security.logics.model.uniform.TieEntity;
import com.security.logics.repository.uniform.ColorRepository;
import com.security.logics.repository.uniform.TieRepository;
import com.security.logics.service.uniform.TieService;
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

public class TieServiceTest extends AbstractTest {
    @Mock
    private TieRepository tieRepository;
    @Mock
    private ColorRepository colorRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TieService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @org.junit.Test
    public void saveTie() {
        //Arrange
        ColorEntity color = new ColorEntity();
        color.setColorName("Grey");
        TieDTO dto = new TieDTO();
        dto.setTieId(1L);
        dto.setName("Bow");
        dto.setColor(color);
        TieEntity entity = mapper.map(dto, TieEntity.class);

        //Act
        when(modelMapper.map(dto, TieEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, TieDTO.class)).thenReturn(dto);
        when(colorRepository.findByColorName(dto.getColor().getColorName())).thenReturn(mapper.map(color, ColorEntity.class));
        when(tieRepository.save(entity)).thenReturn(entity);
        TieDTO res = this.service.saveTie(dto);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getAllCompanies() {
        //Arrange
        TieDTO dto = new TieDTO();
        dto.setTieId(1L);
        dto.setName("Bow");
        dto.setColor(new ColorEntity());
        TieEntity entity = mapper.map(dto, TieEntity.class);
        List<TieEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, TieDTO.class)).thenReturn(dto);
        when(tieRepository.findAll()).thenReturn(list);
        List<TieDTO> res = this.service.getAllTies();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @org.junit.Test
    public void getTieByValidId() {
        //Arrange
        TieDTO dto = new TieDTO();
        dto.setTieId(1L);
        dto.setName("Bow");
        dto.setColor(new ColorEntity());
        TieEntity entity = mapper.map(dto, TieEntity.class);

        //Act
        when(modelMapper.map(entity, TieDTO.class)).thenReturn(dto);
        when(tieRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        TieDTO res = this.service.getTieById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @org.junit.Test
    public void getTieByInValidId() {
        //Arrange
        TieDTO dto = new TieDTO();
        dto.setTieId(1L);
        dto.setName("Bow");
        dto.setColor(new ColorEntity());
        TieEntity entity = mapper.map(dto, TieEntity.class);

        //Act
        when(modelMapper.map(entity, TieDTO.class)).thenReturn(dto);
        when(tieRepository.findById((long) 1)).thenReturn(Optional.empty());
        TieDTO res = this.service.getTieById(1L);

        //Assert
        assertNull(res);
    }

    @org.junit.Test
    public void updateTie() {
        //Arrange
        ColorEntity color = new ColorEntity();
        color.setColorName("Grey");
        TieDTO dto = new TieDTO();
        dto.setTieId(1L);
        dto.setName("Bow");
        dto.setColor(color);
        TieEntity entity = mapper.map(dto, TieEntity.class);

        //Act
        when(modelMapper.map(dto, TieEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, TieDTO.class)).thenReturn(dto);
        when(colorRepository.findByColorName(dto.getColor().getColorName())).thenReturn(mapper.map(color, ColorEntity.class));
        when(tieRepository.save(entity)).thenReturn(entity);
        TieDTO res = this.service.updateTie(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteTie() {
        //Arrange
        TieDTO dto = new TieDTO();
        dto.setTieId(1L);
        dto.setName("Bow");
        dto.setColor(new ColorEntity());
        TieEntity entity = mapper.map(dto, TieEntity.class);

        //Act
        when(modelMapper.map(entity, TieDTO.class)).thenReturn(dto);
        doNothing().when(tieRepository).delete(entity);
        this.service.deleteTie(1L);

        //Assert
        assertTrue(true);
    }
}
