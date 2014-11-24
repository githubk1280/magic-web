package com.tmrasys.controller;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmrasys.domain.Project;
import com.tmrasys.service.DataService;

@Controller
@RequestMapping("/project")
public class DataTestController {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	DataService dataService;

	@PostConstruct
	public void init() {
		logger.info("post ----------------------" + dataService);
	}

	@RequestMapping("/load")
	public ModelAndView loadProjectById() {
		Project project = dataService.loadProjectById(1);
		ModelAndView view = new ModelAndView();
		view.addObject("project", project);
		view.setViewName("load");
		return view;

	}

}
