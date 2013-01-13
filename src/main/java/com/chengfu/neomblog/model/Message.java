package com.chengfu.neomblog.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Message {

	@GraphId Long id;
	
	String content = "";

	public Message() {
		
	}
	
	public Message(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}
}
