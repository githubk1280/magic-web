package com.magic.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.magic.exceptions.test.NotFoundException;

@Controller
@RequestMapping(value = "/ex")
public class TestController {
	Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/http/{num}")
	public String httpStatusCodeTest(@PathVariable int num) {
		if (num < 1)
			throw new NotFoundException("num less than 1 :" + num);
		return "ex";
	}

}
