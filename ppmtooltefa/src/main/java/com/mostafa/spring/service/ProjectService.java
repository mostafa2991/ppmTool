package com.mostafa.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mostafa.spring.domain.Project;
import com.mostafa.spring.exceptions.ProjectIdException;
import com.mostafa.spring.repo.ProjectRepo;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepo ProjectRepo;

	public Project saveOrUpdateProject(Project project) {

		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return ProjectRepo.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project ID is already exisit");
		}
	}

	public Project findByIdIdentifier(String id) {
		Project project = ProjectRepo.findByProjectIdentifier(id.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Project ID is not exisit");
		}

		return project;
	}
	
	public List<Project> findAll(){
		return ProjectRepo.findAll();
	}
	public void deleteProject(String id){
		Project project = ProjectRepo.findByProjectIdentifier(id.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Project ID is not exisit");
		}
		ProjectRepo.delete(project);
			}

}