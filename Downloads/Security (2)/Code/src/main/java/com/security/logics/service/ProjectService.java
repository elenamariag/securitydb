package com.security.logics.service;

import com.security.logics.dto.ProjectDTO;
import com.security.logics.exception.ProjectNotFoundException;
import com.security.logics.model.ProjectEntity;
import com.security.logics.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = projectRepository.save(modelMapper.map(projectDTO, ProjectEntity.class));
        return modelMapper.map(projectEntity, ProjectDTO.class);
    }

    public List<ProjectDTO> getAllProjects() {
        List<ProjectEntity> projectList = (List<ProjectEntity>) projectRepository.findAll();

        List<ProjectDTO> projects = projectList
                .stream()
                .map(project -> modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());

        return projects;
    }

    public ProjectDTO getProjectById(Long projectId) throws ProjectNotFoundException {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);

        if (project.isPresent()) {
            return modelMapper.map(project.get(), ProjectDTO.class);
        } else {
            throw new ProjectNotFoundException("Project Not Found: " + projectId);
        }
    }

    public ProjectDTO updateProject(ProjectDTO projectDTO) throws ProjectNotFoundException {
        Optional<ProjectEntity> project = projectRepository.findById(projectDTO.getProjectId());

        if (project.isPresent()) {
            return modelMapper.map(projectRepository.save(modelMapper.map(projectDTO, ProjectEntity.class)), ProjectDTO.class);
        } else {
            throw new ProjectNotFoundException("Project Not Found: " + projectDTO.getProjectId());
        }
    }


    public void deleteProject(Long projectId) throws ProjectNotFoundException {
        Optional<ProjectEntity> project = projectRepository.findById(projectId);

        if (project.isPresent()) {
            projectRepository.deleteById(projectId);
        } else {
            throw new ProjectNotFoundException("Project Not Found: " + projectId);
        }

    }
}
