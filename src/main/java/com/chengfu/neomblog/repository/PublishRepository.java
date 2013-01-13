package com.chengfu.neomblog.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.chengfu.neomblog.model.Message;
import com.chengfu.neomblog.model.Publish;
import com.chengfu.neomblog.model.User;

public interface PublishRepository extends GraphRepository<Publish> {
	 /*@Query("start user1=node({0}) " +
	            " match user1-[r1:PUBLISH]->ownMessage, user1<-[:FOLLOW]-user2-[r2:PUBLISH]->followedMessage" +
	            " return  ownMessage, followedMessage" +
	            " order by r1.publishedAt" +
	            " limit 10") */
	@Query("start user1=node({0}) " +
            " match user1-[:FOLLOW]->user2-[r2:PUBLISH]->followedMessage" +
            " return r2 " +
            //" order by r1.publishedAt" +
            " ")
	List<Publish> getFollowingUserMessages(User user);
	
	@Query("start user=node({0}) match user-[r:PUBLISH]->message return r")
	List<Publish> getOwnMessages(User user);
}
