package com.chengfu.neomblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengfu.neomblog.model.Message;
import com.chengfu.neomblog.model.User;
import com.chengfu.neomblog.repository.MessageRepository;
import com.chengfu.neomblog.repository.UserRepository;
import com.chengfu.neomblog.util.Utils;

@Service
public class UserService {
	@Autowired
	MessageRepository messageRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Transactional
	public void publish(User user, String content) {
		Message message = new Message(content);
		messageRepository.save(message);
		user.publish(message);
		userRepository.save(user);
	}

	public User getUserFromSession() {
		String name = utils.getCurrentUsername();
		return userRepository.findByLoginName(name);
	}

	@Transactional
	public void update(User user) {
		userRepository.save(user);
	}
}
