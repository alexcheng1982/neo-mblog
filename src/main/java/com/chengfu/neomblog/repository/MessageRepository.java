package com.chengfu.neomblog.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.chengfu.neomblog.model.Message;

public interface MessageRepository extends GraphRepository<Message> {
	
}
