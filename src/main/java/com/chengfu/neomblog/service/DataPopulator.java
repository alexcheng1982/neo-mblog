package com.chengfu.neomblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengfu.neomblog.model.User;
import com.chengfu.neomblog.repository.UserRepository;

@Service
public class DataPopulator {
	
	@Autowired UserRepository userRepository;
	
	@Transactional
	public void populate() {
        userRepository.deleteAll();
		User user1 = new User("alex", "Alex");
		User user2 = new User("bob", "Bob");
		user1.follow(user2);
		userRepository.save(user1);
		userRepository.save(user2);
	}
}
