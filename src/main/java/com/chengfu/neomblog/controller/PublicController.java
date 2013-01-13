package com.chengfu.neomblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.helpers.collection.ClosableIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chengfu.neomblog.model.User;
import com.chengfu.neomblog.repository.UserRepository;
import com.chengfu.neomblog.util.Utils;

@Controller
public class PublicController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String publicInfo(Model model) {
		ClosableIterable<User> users = userRepository.findAll();
		List<User> result = new ArrayList<User>();
		for (User user : users) {
			result.add(user);
		}
		model.addAttribute("users", result);
		User currentUser = userRepository.findByLoginName(utils.getCurrentUsername());
		model.addAttribute("currentUser", currentUser);
		return "publicInfo";
	}
}
