package com.chengfu.neomblog.model;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "PUBLISH")
public class Publish implements Comparable<Publish> {
	@GraphId Long id;
	
	@Fetch @StartNode
	User user;
	
	@Fetch @EndNode
	Message message;
	
	Date publishedAt = new Date();
	
	String publishedVia = "web";
	
	public Publish() {
		
	}

	public Publish(User user, Message message) {
		super();
		this.user = user;
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}
	
	public User getUser() {
		return user;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public String getPublishedVia() {
		return publishedVia;
	}

	public int compareTo(Publish another) {
		return another.getPublishedAt().compareTo(this.publishedAt);
	}
	
	
}
