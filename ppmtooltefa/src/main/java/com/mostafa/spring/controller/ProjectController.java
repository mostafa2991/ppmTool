package com.mostafa.spring.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mostafa.spring.domain.Project;
import com.mostafa.spring.service.MapValidationErrorService;
import com.mostafa.spring.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject (@Valid @RequestBody Project project , BindingResult result ) {
		ResponseEntity<?> mapValidation = mapValidationErrorService.MapValidation(result);
		if(mapValidation!=null) return mapValidation;
		projectService.saveOrUpdateProject(project);
		return new  ResponseEntity<Project> (project,HttpStatus.OK);
		
	}
	@GetMapping("/{projectId}")
	public ResponseEntity<?> findByID (@PathVariable String projectId ) {
		Project project = projectService.findByIdIdentifier(projectId);
		return new  ResponseEntity<Project> (project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Project> findAll() {
		List<Project> all = projectService.findAll();
		return  all;
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable String id){
		projectService.deleteProject(id);
		return new  ResponseEntity<String> ("project is deleted",HttpStatus.OK);

	}
	
}
