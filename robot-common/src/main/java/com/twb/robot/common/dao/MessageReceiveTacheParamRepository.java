package com.twb.robot.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twb.robot.common.entity.MessageReceiveTacheParam;

public interface MessageReceiveTacheParamRepository extends JpaRepository<MessageReceiveTacheParam, Integer> {

	@Query(value= "select a.* from message_receive_tache_param a where   a.param_key=:paramKey ",nativeQuery = true)
	public List<MessageReceiveTacheParam> getMessageReceiveTacheParamList(@Param("paramKey")String paramKey); 

	@Query(value= "select a.* from message_receive_tache_param a where   a.param_key=:paramKey and a.codeb=:codeb",nativeQuery = true)
	public List<MessageReceiveTacheParam> getListByKeyCodeb(@Param("paramKey")String paramKey,@Param("codeb")String codeb); 

	
}
