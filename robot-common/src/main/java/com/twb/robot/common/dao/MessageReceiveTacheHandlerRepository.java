package com.twb.robot.common.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.twb.robot.common.entity.MessageReceiveTacheHandler;

public interface MessageReceiveTacheHandlerRepository extends JpaRepository<MessageReceiveTacheHandler, Integer> {

	@Query(value= "select a.* from message_receive_tache_handler a where a.state='0' and a.tache_code=:tache_code order by flow_num ",nativeQuery = true)
	 @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="false") })  
	public List<MessageReceiveTacheHandler> getMessageReceiveQueueList(@Param("tache_code")String tache_code); 

	
}
