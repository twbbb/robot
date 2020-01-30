package com.twb.robot.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "message_receive_tache_handler") // @Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class MessageReceiveTacheHandler
{
	



	@Id // 这是一个主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
	private Integer id;
	
	@Column(columnDefinition = "varchar(20)")
	private String tacheCode ; // 环节code
	
	@Column(columnDefinition = "int(11)")
	private Integer flowNum;//步骤id
	
	@Column(columnDefinition = "varchar(4000)")
	private String flowCheck ; //  步骤是否执行检查
	
	
	@Column(columnDefinition = "varchar(1000)")
	private String flowClass ; //  步骤处理类
	
	
	@Column(columnDefinition = "varchar(4000)")
	private String col1 ; // 
	
	@Column(columnDefinition = "varchar(1000)")
	private String col2 ; //
	
	@Column(columnDefinition = "varchar(1000)")
	private String col3 ; //
	
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

	public Integer getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(Integer flowNum) {
		this.flowNum = flowNum;
	}

	public String getFlowCheck() {
		return flowCheck;
	}

	public void setFlowCheck(String flowCheck) {
		this.flowCheck = flowCheck;
	}

	

	public String getFlowClass() {
		return flowClass;
	}

	public void setFlowClass(String flowClass) {
		this.flowClass = flowClass;
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
