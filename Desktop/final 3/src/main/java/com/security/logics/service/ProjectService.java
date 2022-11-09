package com.security.logics.service;

import com.security.logics.dto.ProjectDTO;
import com.security.logics.dto.ProjectDTO;
import com.security.logics.model.ProjectEntity;
import com.security.logics.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProjectDTO saveProject(ProjectDTO projectDTO)
    {
        ProjectEntity projectEntity = projectRepository.save(modelMapper.map(projectDTO, ProjectEntity.class));
        return modelMapper.map(projectEntity, ProjectDTO.class);
    }

    public List<ProjectDTO> getAllProjects()
    {
        List<ProjectEntity> projectList = (List<ProjectEntity>) projectRepository.findAll();

        List<ProjectDTO> dtos = projectList
                .stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }

    public ProjectDTO getProjectById(Long projectId)
    {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);

        if (project.isPresent())
        {
            return modelMapper.map(project.get(), ProjectDTO.class);
        }
        return null;
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO)
    {
        return modelMapper.map(projectRepository.save(modelMapper.map(projectDTO, ProjectEntity.class)), ProjectDTO.class);
    }


    public void deleteProject(Long projectId)
    {
        projectRepository.deleteById(projectId);
    }
}
