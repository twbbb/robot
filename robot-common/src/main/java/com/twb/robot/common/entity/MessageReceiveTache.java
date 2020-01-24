package com.twb.robot.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "message_receive_tache") // @Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class MessageReceiveTache
{


	@Id // 这是一个主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
	private Integer id;
	
	@Column(columnDefinition = "varchar(20)")
	private String tacheCode ; // 环节code
	
	@Column(columnDefinition = "varchar(1000)")
	private String tacheDesc ; // 环节描述
	
	@Column(columnDefinition = "varchar(2)")
	private String tacheType ; // 环节处理类型
	
	@Column(columnDefinition = "varchar(4000)")
	private String tacheContent ; //  环节处理内容
	
	@Column(columnDefinition = "varchar(2) default '0'")
	private String state =""; // 状态，0开启，1，关闭

	@Column(columnDefinition = "varchar(200)")
	private String remark  =""; //备注

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

	public String getTacheDesc() {
		return tacheDesc;
	}

	public void setTacheDesc(String tacheDesc) {
		this.tacheDesc = tacheDesc;
	}

	public String getTacheType() {
		return tacheType;
	}

	public void setTacheType(String tacheType) {
		this.tacheType = tacheType;
	}

	public String getTacheContent() {
		return tacheContent;
	}

	public void setTacheContent(String tacheContent) {
		this.tacheContent = tacheContent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}
