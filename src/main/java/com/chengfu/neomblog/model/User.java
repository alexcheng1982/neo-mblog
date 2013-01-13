package com.chengfu.neomblog.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class User {
	@GraphId Long id;
	
	@Indexed
	String loginName;
	String displayName;
	String email;
	
	public User() {
		
	}
	
	public User(String loginName) {
		this.loginName = loginName;
	}
	
	public User(String loginName, String displayName) {
		super();
		this.loginName = loginName;
		this.displayName = displayName;
	}

	@RelatedTo(type = "FOLLOW", direction = Direction.INCOMING)
	@Fetch Set<User> followers = new HashSet<User>();
	
	@RelatedTo(type = "FOLLOW", direction = Direction.OUTGOING)
	@Fetch Set<User> followed = new HashSet<User>();
	
	@RelatedToVia(type = "PUBLISH")
	Set<Publish> messages = new HashSet<Publish>();
	
	public Follow follow(User user) {
		Follow follow = new Follow(this, user);
		this.followed.add(user);
		user.followers.add(this);
		return follow;
	}
	
	public Publish publish(Message message) {
		Publish publish = new Publish(this, message);
		this.messages.add(publish);
		return publish;
	}
	
	public void unfollow(User user) {
		this.followed.remove(user);
		user.followers.remove(this);
	}
	
	public String getLoginName() {
		return loginName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getFollowerCount() {
		return followers.size();
	}
	
	public int getFollowedCount() {
		return followed.size();
	}
	
	public Set<Publish> getMessages() {
		return messages;
	}
	
	public int getMessageCount() {
		return messages.size();
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public Set<User> getFollowed() {
		return followed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((loginName == null) ? 0 : loginName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		return true;
	}

}
