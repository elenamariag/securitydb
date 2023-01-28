package com.security.logics.controller;

import com.security.logics.dto.ProjectDTO;
import com.security.logics.exception.ProjectNotFoundException;
import com.security.logics.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ProjectDTO saveProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.saveProject(projectDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<ProjectDTO> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("{projectId}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long projectId) throws ProjectNotFoundException {
        ProjectDTO project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<ProjectDTO> updateProjects(@RequestBody ProjectDTO projectDTO) throws ProjectNotFoundException {
        ProjectDTO project = projectService.getProjectById(projectDTO.getProjectId());
        ProjectDTO updatedProject = projectService.updateProject(projectDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public ResponseEntity<ProjectDTO> deleteProject(@PathVariable Long projectId) throws ProjectNotFoundException {
        ProjectDTO project = projectService.getProjectById(projectId);
        projectService.deleteProject(projectId);
        return new ResponseEntity("Project is successfully deleted", HttpStatus.OK);
    }


}
