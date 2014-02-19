package com.chengfu.neomblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chengfu.neomblog.service.DataPopulator;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataPopulationController {
	
	@Autowired
	DataPopulator dataPopulator;
	
	@RequestMapping(value = "/populate", method = RequestMethod.GET)
    @ResponseBody
	public String populate() {
		dataPopulator.populate();
        return "Sample data is populated.";
	}
}
