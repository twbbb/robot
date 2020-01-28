package com.twb.robot.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twb.robot.common.entity.MessageReceiveTache;
import com.twb.robot.common.entity.MessageReceiveTacheHandler;

public interface MessageReceiveTacheHandlerRepository extends JpaRepository<MessageReceiveTacheHandler, Integer> {

	@Query(value= "select a.* from message_receive_tache a where a.state='0' ",nativeQuery = true)
	public List<MessageReceiveTache> getMessageReceiveQueueList(); 

	
}
