package com.magic.dao;

import org.springframework.stereotype.Repository;

import com.magic.domain.Project;

@Repository
public interface ProjectDao {

	public Project loadProjectById(int id);

}
