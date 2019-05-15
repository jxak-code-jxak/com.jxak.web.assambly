package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jxak.education.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
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
	@TableField("op_code")
    @Column(name = "op_code")
	private String opCode;//操作流水号记录
	@TableField("plan_id")
	@Column(name = "plan_id")
	private String planId;//计划id
    @Column(name = "plan_time")
    @TableField("plan_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp planTime;//计划时间
    @Column(name = "plan_name")
    @TableField("plan_name")
    private String planName;//培训名称
    @Column(name = "edu_purpose")
    @TableField("edu_purpose")
    private String eduPurpose;//培训目的
    @Column(name = "plan_content")
    @TableField("plan_content")
    private String planContent;//培训内容
    @Column(name = "plan_object")
    @TableField("plan_object")
    private String planObject;//培训对象
    @Column(name = "plan_type")
    @TableField("plan_type")
    private Integer planType;//培训方式
	@Column(name = "change_state")
	@TableField("change_state")
	private Integer changeState;//变更状态
	private String remark;//备注
}
