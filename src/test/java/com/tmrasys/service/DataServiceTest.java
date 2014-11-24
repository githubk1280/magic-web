package com.tmrasys.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmrasys.base.AbstractBaseTestConfig;

public class DataServiceTest extends AbstractBaseTestConfig {

	@Autowired
	DataService service;
	
	@Test
	public void testLoadProjectById() {
		System.out.println(service.loadProjectById(2));
	}

}
