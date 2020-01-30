package com.twb.robot.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "message_answer_queue" ) // @Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class MessageAnswerQueue
{

	@Id // 这是一个主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "int(11)")
	private int messageReceiveId;
	
	@Column(columnDefinition = "int(11)")
	private int messageAnswerId;
	
	@org.hibernate.annotations.CreationTimestamp 
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate ; // 

	@org.hibernate.annotations.UpdateTimestamp  
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate ; // 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMessageReceiveId() {
		return messageReceiveId;
	}

	public void setMessageReceiveId(int messageReceiveId) {
		this.messageReceiveId = messageReceiveId;
	}

	public int getMessageAnswerId() {
		return messageAnswerId;
	}

	public void setMessageAnswerId(int messageAnswerId) {
		this.messageAnswerId = messageAnswerId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	

	

	
}
