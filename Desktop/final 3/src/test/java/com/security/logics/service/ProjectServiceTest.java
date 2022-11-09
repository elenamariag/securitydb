package com.security.logics.service;

import com.security.logics.AbstractTest;
import com.security.logics.dto.ProjectDTO;
import com.security.logics.model.ProjectEntity;
import com.security.logics.repository.ProjectRepository;
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

public class ProjectServiceTest extends AbstractTest {
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProjectService service;

    private ModelMapper mapper = new ModelMapper();

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void saveProject() {
        //Arrange
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1L);
        dto.setProjectName("testProjectName");
        dto.setProjectAddress("testProjectAddress, Street 25, London");
        ProjectEntity entity = mapper.map(dto, ProjectEntity.class);

        //Act
        when(modelMapper.map(dto, ProjectEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, ProjectDTO.class)).thenReturn(dto);
        when(projectRepository.save(entity)).thenReturn(entity);
        ProjectDTO res = this.service.saveProject(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void getAllCompanies() {
        //Arrange
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1L);
        dto.setProjectName("testProjectName");
        dto.setProjectAddress("testProjectAddress, Street 25, London");
        ProjectEntity entity = mapper.map(dto, ProjectEntity.class);
        List<ProjectEntity> list = Arrays.asList(entity);

        //Act
        when(modelMapper.map(entity, ProjectDTO.class)).thenReturn(dto);
        when(projectRepository.findAll()).thenReturn(list);
        List<ProjectDTO> res = this.service.getAllProjects();

        //Assert
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(dto, res.get(0));
    }

    @Test
    public void getProjectByValidId() {
        //Arrange
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1L);
        dto.setProjectName("testProjectName");
        dto.setProjectAddress("testProjectAddress, Street 25, London");
        ProjectEntity entity = mapper.map(dto, ProjectEntity.class);

        //Act
        when(modelMapper.map(entity, ProjectDTO.class)).thenReturn(dto);
        when(projectRepository.findById((long) 1)).thenReturn(Optional.of(entity));
        ProjectDTO res = this.service.getProjectById(1L);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void getProjectByInValidId() {
        //Arrange
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1L);
        dto.setProjectName("testProjectName");
        dto.setProjectAddress("testProjectAddress, Street 25, London");
        ProjectEntity entity = mapper.map(dto, ProjectEntity.class);

        //Act
        when(modelMapper.map(entity, ProjectDTO.class)).thenReturn(dto);
        when(projectRepository.findById((long) 1)).thenReturn(Optional.empty());
        ProjectDTO res = this.service.getProjectById(1L);

        //Assert
        assertNull(res);
    }

    @Test
    public void updateProject() {
        //Arrange
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1L);
        dto.setProjectName("testProjectName");
        dto.setProjectAddress("testProjectAddress, Street 25, London");
        ProjectEntity entity = mapper.map(dto, ProjectEntity.class);

        //Act
        when(modelMapper.map(dto, ProjectEntity.class)).thenReturn(entity);
        when(modelMapper.map(entity, ProjectDTO.class)).thenReturn(dto);
        when(projectRepository.save(entity)).thenReturn(entity);
        ProjectDTO res = this.service.updateProject(dto);

        //Assert
        assertEquals(dto, res);
    }

    @Test
    public void deleteProject() {
        //Arrange
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectId(1L);
        dto.setProjectName("testProjectName");
        dto.setProjectAddress("testProjectAddress, Street 25, London");
        ProjectEntity entity = mapper.map(dto, ProjectEntity.class);

        //Act
        when(modelMapper.map(entity, ProjectDTO.class)).thenReturn(dto);
        doNothing().when(projectRepository).delete(entity);
        this.service.deleteProject(1L);

        //Assert
        assertTrue(true);
    }
}
