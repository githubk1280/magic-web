package com.tmrasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmrasys.dao.ProjectDao;
import com.tmrasys.domain.Project;

@Service
public class DataService {
	@Autowired
	ProjectDao projectDao;

	public Project loadProjectById(int projectId) {
		return projectDao.loadProjectById(projectId);
	}
	
}
