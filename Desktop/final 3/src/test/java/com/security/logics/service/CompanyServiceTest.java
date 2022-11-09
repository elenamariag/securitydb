package com.security.logics.service;

import com.security.logics.AbstractTest;
import com.security.logics.dto.CompanyDTO;
import com.security.logics.model.CompanyEntity;
import com.security.logics.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CompanyServiceTest extends AbstractTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CompanyService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void saveCompany() {
        //Arrange
        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyId(1L);
        dto.setCompanyName("testCompanyName");
        dto.setCompanyAddress("testCompanyAddress, Street 25, London");
        dto.setEmail("testCompanyName@gmail.com");
        CompanyEntity entity = mapper.map(dto, CompanyEntity.class);

        //Act
        when(modelMapper.map(dto, CompanyEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, CompanyDTO.class)).thenReturn(dto);
        when(companyRepository.save(entity)).thenReturn(entity);
        CompanyDTO res = this.service.saveCompany(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void getAllCompanies() {
        //Arrange
        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyId(1L);
        dto.setCompanyName("testCompanyName");
        dto.setCompanyAddress("testCompanyAddress, Street 25, London");
        dto.setEmail("testCompanyName@gmail.com");
        CompanyEntity entity = mapper.map(dto, CompanyEntity.class);
        List<CompanyEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, CompanyDTO.class)).thenReturn(dto);
        when(companyRepository.findAll()).thenReturn(list);
        List<CompanyDTO> res = this.service.getAllCompanies();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @Test
    public void getCompanyByValidId() {
        //Arrange
        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyId(1L);
        dto.setCompanyName("testCompanyName");
        dto.setCompanyAddress("testCompanyAddress, Street 25, London");
        dto.setEmail("testCompanyName@gmail.com");
        CompanyEntity entity = mapper.map(dto, CompanyEntity.class);

        //Act
        when(modelMapper.map(entity, CompanyDTO.class)).thenReturn(dto);
        when(companyRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        CompanyDTO res = this.service.getCompanyById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void getCompanyByInValidId() {
        //Arrange
        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyId(1L);
        dto.setCompanyName("testCompanyName");
        dto.setCompanyAddress("testCompanyAddress, Street 25, London");
        dto.setEmail("testCompanyName@gmail.com");
        CompanyEntity entity = mapper.map(dto, CompanyEntity.class);

        //Act
        when(modelMapper.map(entity, CompanyDTO.class)).thenReturn(dto);
        when(companyRepository.findById((long) 1)).thenReturn(Optional.empty());
        CompanyDTO res = this.service.getCompanyById(1L);

        //Assert
        assertNull(res);
    }

    @Test
    public void updateCompany() {
        //Arrange
        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyId(1L);
        dto.setCompanyName("testCompanyName");
        dto.setCompanyAddress("testCompanyAddress, Street 25, London");
        dto.setEmail("testCompanyName@gmail.com");
        CompanyEntity entity = mapper.map(dto, CompanyEntity.class);

        //Act
        when(modelMapper.map(dto, CompanyEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, CompanyDTO.class)).thenReturn(dto);
        when(companyRepository.save(entity)).thenReturn(entity);
        CompanyDTO res = this.service.updateCompany(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteCompany() {
        //Arrange
        CompanyDTO dto = new CompanyDTO();
        dto.setCompanyId(1L);
        dto.setCompanyName("testCompanyName");
        dto.setCompanyAddress("testCompanyAddress, Street 25, London");
        dto.setEmail("testCompanyName@gmail.com");
        CompanyEntity entity = mapper.map(dto, CompanyEntity.class);

        //Act
        when(modelMapper.map(entity, CompanyDTO.class)).thenReturn(dto);
        doNothing().when(companyRepository).delete(entity);
        this.service.deleteCompany(1L);

        //Assert
        assertTrue(true);
    }
}
