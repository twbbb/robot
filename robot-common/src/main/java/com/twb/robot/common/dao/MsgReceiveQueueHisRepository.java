package com.twb.robot.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twb.robot.common.entity.MessageReceiveQueueHis;

public interface MsgReceiveQueueHisRepository extends JpaRepository<MessageReceiveQueueHis, Integer> {
}
