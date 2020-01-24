package com.twb.robot.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twb.robot.common.entity.MessageReceive;

public interface MessageReceiveRepository extends JpaRepository<MessageReceive, Integer> {
}
