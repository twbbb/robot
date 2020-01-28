package com.twb.robot.common.entity;

import java.util.Date;

import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "message_send_his") 
public class MessageSendHis
{

	@Id // 这是一个主键
	private Integer id;


	@Column(columnDefinition = "varchar(200)")
	private String toUserId =""; // 微信群组发送者id
	@Column(columnDefinition = "varchar(200)")
	private String toUserName =""; // '微信群组发送者名称'
	
	@Column(columnDefinition = "varchar(200)")
	private String toGroupId ; // 微信群组id
	@Column(columnDefinition = "varchar(200)")
	private String toGroupName ; // 微信群组名称

	@Column(columnDefinition = "varchar(100)")
	private String localRobotId  =""; // 当前账号id
	
	@Column(columnDefinition = "varchar(10)")
	private String msgType  =""; // 消息类型
	
	@Column(columnDefinition = "varchar(10)")
	private String msgSubType  =""; // 消息子类型表，1文本，2语音，3图片4链接
	@Column(columnDefinition = "varchar(4000)")
	private String message  =""; // 消息内容,文本，语音转换的文本,链接标题',
	@Column(columnDefinition = "varchar(1000)")
	private String col1="";
	@Column(columnDefinition = "varchar(1000)")
	private String col2="";
	@Column(columnDefinition = "varchar(1000)")
	private String col3="";
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate ; // 消息时间戳
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate ; // 消息时间戳
	
	@Column(columnDefinition = "varchar(2)")
	private String sendState  =""; //  
	@Column(columnDefinition = "varchar(1000)")
	private String sendStateMsg  =""; //  
	
	
	@Column(columnDefinition = "varchar(20)")
	private String busCode  =""; //  消息对应的业务类型
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getToGroupId() {
		return toGroupId;
	}
	public void setToGroupId(String toGroupId) {
		this.toGroupId = toGroupId;
	}
	public String getToGroupName() {
		return toGroupName;
	}
	public void setToGroupName(String toGroupName) {
		this.toGroupName = toGroupName;
	}
	public String getLocalRobotId() {
		return localRobotId;
	}
	public void setLocalRobotId(String localRobotId) {
		this.localRobotId = localRobotId;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgSubType() {
		return msgSubType;
	}
	public void setMsgSubType(String msgSubType) {
		this.msgSubType = msgSubType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCol1() {
		return col1;
	}
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public String getCol3() {
		return col3;
	}
	public void setCol3(String col3) {
		this.col3 = col3;
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
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	public String getSendStateMsg() {
		return sendStateMsg;
	}
	public void setSendStateMsg(String sendStateMsg) {
		this.sendStateMsg = sendStateMsg;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	
	

}
