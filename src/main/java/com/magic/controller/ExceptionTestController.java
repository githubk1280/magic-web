package com.magic.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.magic.exceptions.test.NotFoundException;

@ControllerAdvice
public class ExceptionTestController {
	Logger logger = Logger.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("ex");
		return mav;
	}

	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFonndError(HttpServletRequest req, Exception exception) throws Exception {
		logger.error("Request handleNotFonndError: " + req.getRequestURL() + " raised " + exception);
//		if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null)
//			throw exception;

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("ex");
		return mav;
	}
}
