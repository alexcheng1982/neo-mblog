package com.chengfu.neomblog.model;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "FOLLOW")
public class Follow {
	@GraphId Long id;
	
	@StartNode
	User follower;
	@EndNode
	User followed;
	
	Date followingDate = new Date();

	public Follow(User follower, User followed) {
		super();
		this.follower = follower;
		this.followed = followed;
	}
}
