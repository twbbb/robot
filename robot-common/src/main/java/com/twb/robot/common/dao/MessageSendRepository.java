package com.twb.robot.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twb.robot.common.entity.MessageSend;

public interface MessageSendRepository extends JpaRepository<MessageSend, Integer> {

	@Query(value= "select a.* from message_send a where a.send_state='0' limit 0,1000",nativeQuery = true)
	public List<MessageSend> getMessageSendList(); 
}
