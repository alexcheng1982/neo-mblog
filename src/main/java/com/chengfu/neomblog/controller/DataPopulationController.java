package com.chengfu.neomblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chengfu.neomblog.service.DataPopulator;

@Controller
public class DataPopulationController {
	
	@Autowired
	DataPopulator dataPopulator;
	
	@RequestMapping(value = "/populate", method = RequestMethod.GET)
	public void populate() {
		dataPopulator.populate();
	}
}
