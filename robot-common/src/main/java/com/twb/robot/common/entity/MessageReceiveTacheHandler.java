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
	
	@Column(columnDefinition = "varchar(2)")
	private String flowType ; // 步骤处理类型，1.执行sql，2.执行java代码
	
	@Column(columnDefinition = "varchar(4000)")
	private String flowContent ; //  步骤处理内容
	
	@Column(columnDefinition = "varchar(2) default '0'")
	private String state =""; // 状态，0开启，1，关闭

	@Column(columnDefinition = "varchar(200)")
	private String remark  =""; //备注
}
