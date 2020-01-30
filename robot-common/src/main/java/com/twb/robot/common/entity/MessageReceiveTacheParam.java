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

import org.hibernate.annotations.CreationTimestamp;

//使用JPA注解配置映射关系
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "message_receive_tache_param") // @Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class MessageReceiveTacheParam
{


	@Id // 这是一个主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
	private Integer id;
	
	@Column(columnDefinition = "varchar(20)")
	private String paramKey ; // 键
	
	@Column(columnDefinition = "varchar(1000)")
	private String coeda ; // 值a
	
	@Column(columnDefinition = "varchar(1000)")
	private String coedb ; // 值b
	
	
	@Column(columnDefinition = "varchar(1000)")
	private String coedc ; // 值c
	
	@Column(columnDefinition = "varchar(1000)")
	private String coedd ; // 值d
	
	
	@Column(columnDefinition = "varchar(200)")
	private String remark  =""; //备注
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	private Date createDate ; //创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}



	public String getCoeda() {
		return coeda;
	}

	public void setCoeda(String coeda) {
		this.coeda = coeda;
	}

	public String getCoedb() {
		return coedb;
	}

	public void setCoedb(String coedb) {
		this.coedb = coedb;
	}

	public String getCoedc() {
		return coedc;
	}

	public void setCoedc(String coedc) {
		this.coedc = coedc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCoedd() {
		return coedd;
	}

	public void setCoedd(String coedd) {
		this.coedd = coedd;
	}

	
	
	
	

}
