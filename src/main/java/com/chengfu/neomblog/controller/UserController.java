package com.chengfu.neomblog.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengfu.neomblog.model.Publish;
import com.chengfu.neomblog.model.User;
import com.chengfu.neomblog.repository.PublishRepository;
import com.chengfu.neomblog.repository.UserRepository;
import com.chengfu.neomblog.service.JsonOutput;
import com.chengfu.neomblog.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PublishRepository publishRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	JsonOutput jsonOutput;

	@RequestMapping(value = "/user/{loginName}", method = RequestMethod.GET)
	public String publicProfile(Model model, @PathVariable("loginName") String loginName) {
		User profiled = userRepository.findByLoginName(loginName);
		User user = userService.getUserFromSession();
		return userPage(model, profiled, user);
	}

	private String userPage(Model model, User profiled, User user) {
		model.addAttribute("profiled", profiled);
		model.addAttribute("user", user);
		model.addAttribute("canPostMessage", profiled.equals(user));
		return "userPage";
	}
	
	@RequestMapping(value = "/stream/{loginName}", produces="application/json")
	@ResponseBody
	public String userStream(@PathVariable("loginName") String loginName) {
		User profiled = userRepository.findByLoginName(loginName);
		User user = userService.getUserFromSession();
		Collection<Publish> stream = Collections.emptyList();
		if (profiled.equals(user)) {
			List<Publish> messages = new ArrayList<Publish>(publishRepository.getFollowingUserMessages(profiled));
			messages.addAll(profiled.getMessages());
			Collections.sort(messages);
			stream = messages;
		}
		else {
			stream = profiled.getMessages();
		}
		return jsonOutput.toJson(stream);
	}
	
	@RequestMapping(value = "/user/!message", method = RequestMethod.POST)
	@ResponseBody
	public String publishMessage(Model model, @RequestParam("content") String content) {
		User user = userService.getUserFromSession();
		userService.publish(user, content);
		return getResultJson(true);
	}
	
	@RequestMapping(value = "/user/!email", method = RequestMethod.POST)
	@ResponseBody
	public String saveProfileImage(Model model, @RequestParam("email") String email) {
		User user = userService.getUserFromSession();
		user.setEmail(email);
		userService.update(user);
		return getResultJson(true);
	}
	
	
	@RequestMapping(value = "/settings")
	public String settings(Model model) {
		User user = userService.getUserFromSession();
		model.addAttribute("currentUser", user);
		return "userSettings";
	}
	
	@RequestMapping(value = "/user/!follow", produces="application/json")
	@ResponseBody
	public String follow(Model model,  @RequestParam("loginName") String loginName) {
		User currentUser = userService.getUserFromSession();
		User user = userRepository.findByLoginName(loginName);
		currentUser.follow(user);
		userRepository.save(currentUser);
		return getResultJson(true);
	}
	
	@RequestMapping(value = "/user/!unfollow", produces="application/json")
	@ResponseBody
	public String unfollow(Model model,  @RequestParam("loginName") String loginName) {
		User currentUser = userService.getUserFromSession();
		User user = userRepository.findByLoginName(loginName);
		currentUser.unfollow(user);
		userRepository.save(currentUser);
		return getResultJson(true);
	}
	
	private String getResultJson(boolean success) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", success);
		try {
			return objectMapper.writeValueAsString(result);
		} catch (Exception e) {
			return "{}";
		}
	}
}
