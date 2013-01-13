package com.chengfu.neomblog.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.chengfu.neomblog.model.User;

public interface UserRepository extends GraphRepository<User> {
	User findByLoginName(String loginName);
}
