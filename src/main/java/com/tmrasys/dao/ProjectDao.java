package com.tmrasys.dao;

import org.springframework.stereotype.Repository;

import com.tmrasys.domain.Project;

@Repository
public interface ProjectDao {

	public Project loadProjectById(int id);

}
