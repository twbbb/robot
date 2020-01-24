package com.twb.robot.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twb.robot.common.entity.MessageReceiveTache;
import com.twb.robot.common.entity.MessageReceiveTacheFlow;

public interface MessageReceiveTacheFlowRepository extends JpaRepository<MessageReceiveTacheFlow, Integer> {

	@Query(value= "select a.* from message_receive_tache_flow a where a.state='0' and a.tache_code=:tache_code limit 0,1000",nativeQuery = true)
	public List<MessageReceiveTache> getMessageReceiveQueueList(@Param("tache_code")String tache_code); 
	


	
}
