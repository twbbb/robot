package com.twb.robot.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twb.robot.common.entity.MessageReceiveTache;

public interface MessageReceiveTacheRepository extends JpaRepository<MessageReceiveTache, Integer> {

	@Query(value= "select a.* from message_receive_tache a where a.state='0' ",nativeQuery = true)
	public List<MessageReceiveTache> getMessageReceiveQueueList(); 

	
}
