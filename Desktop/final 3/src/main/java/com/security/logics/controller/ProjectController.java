package com.security.logics.controller;

import com.security.logics.dto.ProjectDTO;
import com.security.logics.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController
{

  @Autowired
  private ProjectService projectService;

  @PostMapping("/projects")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ProjectDTO saveProject(@RequestBody ProjectDTO projectDTO)
  {
    return projectService.saveProject(projectDTO);
  }

  @GetMapping("/getProjects")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public List<ProjectDTO> getProjects()
  {
    return projectService.getAllProjects();
  }

  @GetMapping("/getProjectId/{projectId}")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long projectId)
  {
    ProjectDTO project = projectService.getProjectById(projectId);

    if (project == null)
    {
      return new ResponseEntity("Project ID is not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(project, HttpStatus.OK);
  }

  @PutMapping("/updateProject")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<ProjectDTO> updateProjects(@RequestBody ProjectDTO projectDTO)
  {
    ProjectDTO project = projectService.getProjectById(projectDTO.getProjectId());

    if (project == null)
    {
      return new ResponseEntity("Project is not found", HttpStatus.NOT_FOUND);
    }
    ProjectDTO updatedProject = projectService.updateProject(projectDTO);
    return new ResponseEntity<>(updatedProject, HttpStatus.OK);
  }

  @DeleteMapping("/deleteProject/{projectId}")
  @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
  public ResponseEntity<ProjectDTO> deleteProject(@PathVariable Long projectId)
  {
    ProjectDTO project = projectService.getProjectById(projectId);

    if (project == null)
    {
      return new ResponseEntity("Project is not found", HttpStatus.NOT_FOUND);
    }
    projectService.deleteProject(projectId);
    return new ResponseEntity("Project is succesfully deleted", HttpStatus.OK);
  }


}
