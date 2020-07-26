package com.mostafa.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mostafa.spring.domain.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

	public Project findByProjectIdentifier(String id);

	public List<Project> findAll();
}
