package com.jxak.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName:：TeaMatType 
 * @Comment： 教材类型数据树
 * @author ：杨廷华  
 * @date ：2019年5月15日 上午9:28:05 
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@TableName("teamat_type")
@Table(name = "teamat_type")
public class TeaMatType extends BaseEntity{

	private static final long serialVersionUID = 6306161153208620344L; 
	
	/**
	 * 父编码
	 */
	@Column(name = "parent_id_")
	@TableField("parent_id_")
	@JsonProperty("pId")
	private String pId;
	
	/**
	 * 教材
	 * 名称
	 */
	@Column(name = "name_")
	@TableField("name_")
	private String name;
	
	/**
	 * 教材编码
	 */
	@Column(name = "code_")
	@TableField("code_")
	private String code;
	
	/**
	 * 层级编码
	 */
	@Column(name = "level_")
	@TableField("level_")
	private String level;

}
