package com.security.logics.controller;

import com.security.logics.AbstractTest;
import com.security.logics.dto.CompanyDTO;
import com.security.logics.model.CompanyEntity;
import com.security.logics.service.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CompanyControllerTest extends AbstractTest {

    @MockBean
    private CompanyService companyService;

    private CompanyDTO dto;
    private CompanyEntity entity;
    ModelMapper mapper;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        mapper = new ModelMapper();
        dto = new CompanyDTO();
        dto.setCompanyId(1L);
        dto.setCompanyName("testCompanyName");
        dto.setCompanyAddress("testCompanyAddress, Street 25, London");
        dto.setEmail("testCompanyName@gmail.com");
        entity = mapper.map(dto, CompanyEntity.class);
    }

    @Test
    public void getCompanyDTOsList() throws Exception {
        //Arrange
        String uri = "/getCompanies";
        List<CompanyDTO> list = Arrays.asList(dto);
        when(companyService.getAllCompanies()).thenReturn(list);

        //act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();

        //Assert
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CompanyDTO[] companylist = super.mapFromJson(content, CompanyDTO[].class);
        assertTrue(companylist.length > 0);
    }


    @Test
    public void getCompanyByIdValid() throws Exception {

        String uri = "/getCompanyById/1";
        when(companyService.getCompanyById(dto.getCompanyId())).thenReturn(dto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CompanyDTO res = super.mapFromJson(content, CompanyDTO.class);
        assertEquals(dto.getCompanyName(), res.getCompanyName());
        assertEquals(dto.getCompanyAddress(), res.getCompanyAddress());
        assertEquals(dto.getEmail(), res.getEmail());
    }
    @Test
    public void getCompanyByIdInValid() throws Exception {

        String uri = "/getCompanyById/1";
        when(companyService.getCompanyById(dto.getCompanyId())).thenReturn(null);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void createCompanyDTO() throws Exception {
        String uri = "/company";
        CompanyDTO company = new CompanyDTO();
        company.setCompanyId(1L);
        company.setCompanyName("testCompanyName");
        company.setCompanyAddress("testCompanyAddress, Street 25, London");
        company.setEmail("testCompanyName@gmail.com");
        String inputJson = super.mapToJson(company);
        when(companyService.saveCompany(dto)).thenReturn(dto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CompanyDTO res = mapFromJson(content, CompanyDTO.class);
        assertEquals(dto.getCompanyName(), res.getCompanyName());
        assertEquals(dto.getCompanyAddress(), res.getCompanyAddress());
        assertEquals(dto.getEmail(), res.getEmail());
    }

    @Test
    public void updateCompanyDTOInvalid() throws Exception {
        String uri = "/updateCompany";
        String inputJson = super.mapToJson(dto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void updateCompanyDTOValid() throws Exception {
        String uri = "/updateCompany";
        String inputJson = super.mapToJson(dto);
        when(companyService.getCompanyById(dto.getCompanyId())).thenReturn(dto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteCompanyDTOValid() throws Exception {

        String uri = "/deleteCompany/1";
        when(companyService.getCompanyById(dto.getCompanyId())).thenReturn(dto);
        doNothing().when(companyService).deleteCompany(1L);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals("Company is successfully deleted", content);
    }

    @Test
    public void deleteCompanyDTOInValid() throws Exception {

        String uri = "/deleteCompany/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

}
