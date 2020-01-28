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
@Table(name = "message_receive_tache_flow_his") // @Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class MessageReceiveTacheFlowHis
{


	@Id // 这是一个主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "int(11)")
	private int messageReceiveId;
	
	@Column(nullable = false,columnDefinition = "varchar(20)")
	private String tacheCode =""; // 环节code
	
	@Column(columnDefinition = "varchar(100)")
	private String localRobotId  ; // 当前账号id 
	
	@Column(columnDefinition = "varchar(2) default '0'")
	private String state ; // 状态，0待处理，1，成功2.失败

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate ; // 

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate ; // 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTacheCode() {
		return tacheCode;
	}

	public void setTacheCode(String tacheCode) {
		this.tacheCode = tacheCode;
	}

	public int getMessageReceiveId() {
		return messageReceiveId;
	}

	public void setMessageReceiveId(int messageReceiveId) {
		this.messageReceiveId = messageReceiveId;
	}

	public String getLocalRobotId() {
		return localRobotId;
	}

	public void setLocalRobotId(String localRobotId) {
		this.localRobotId = localRobotId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
