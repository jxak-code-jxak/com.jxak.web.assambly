package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
/**
* @Description:    安全教育培训计划表
* @Author:         liaoyuanjie
* @CreateDate:     2019/5/9 9:51
* @UpdateUser:     liaoyuanjie
* @UpdateDate:     2019/5/9 9:51
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "plan_info")
@TableName("plan_info")
public class PlanEntity extends BaseEntity {
    
	
	private static final long serialVersionUID = 4778684192314097195L;
	@Column(name = "plan_code")
    @TableField("plan_code")
    private String planCode;//计划编码
    @Column(name = "plan_time")
    @TableField("plan_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp planTime;//计划时间
    @Column(name = "plan_name")
    @TableField("plan_name")
    private String planName;//计划名称
    @Column(name = "plan_object")
    @TableField("plan_object")
    private String trainObject;//培训对象
    @Column(name = "plan_type")
    @TableField("plan_type")
    private Integer trainType;//培训方式
    private Integer state;
}
