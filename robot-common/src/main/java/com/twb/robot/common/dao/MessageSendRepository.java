package com.twb.robot.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twb.robot.common.entity.MessageSend;

public interface MessageSendRepository extends JpaRepository<MessageSend, Integer> {
}
