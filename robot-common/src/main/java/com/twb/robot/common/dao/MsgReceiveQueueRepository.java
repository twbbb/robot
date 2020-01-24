package com.twb.robot.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twb.robot.common.entity.MessageReceiveQueue;

public interface MsgReceiveQueueRepository extends JpaRepository<MessageReceiveQueue, Integer> {

	@Query(value= "select b.* from (select a.* from message_receive_queue a order by message_receive_id ) b limit 0,1000",nativeQuery = true)
	public List<MessageReceiveQueue> getMessageReceiveQueueList(); 

	
}
