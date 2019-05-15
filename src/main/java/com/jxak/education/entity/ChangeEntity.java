package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* @Description:    变更记录表
* @Author:         liaoyuanjie
* @CreateDate:     2019/5/9 9:55
* @UpdateUser:     liaoyuanjie
* @UpdateDate:     2019/5/9 9:55
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "change_info")
@TableName("change_info")
public class ChangeEntity extends BaseEntity {

	private static final long serialVersionUID = 6230059061095965219L;
	@TableField("plan_id")
	@Column(name = "plan_id")
	private String planId;//计划id
	@Column(name = "change_state")
	@TableField("change_state")
	private Integer changeState;//状态
	private String remark;//备注
	/******************************/
	@Transient
	@TableField(exist = false)
	private PlanEntity planEntity;
}
