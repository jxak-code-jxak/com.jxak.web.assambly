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
import java.util.Date;

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
    @Column(name = "plan_name")
    @TableField("plan_name")
    private String planName;//培训名称
    @Column(name = "plan_object")
    @TableField("plan_object")
    private String planObject;//培训对象
    @Column(name = "plan_content")
    @TableField("plan_content")
    private String planContent;//培训内容
    @Column(name = "plan_type")
    @TableField("plan_type")
    private String planType;//培训方式
    @Column(name = "plan_type_name")
    @TableField("plan_type_name")
    private String planTypeName;//培训方式名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "plan_begin_time")
    @TableField("plan_begin_time")
    private Date planBeginTime;//培训开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "plan_end_time")
    @TableField("plan_end_time")
    private Date planEndTime;//培训结束日期
    @Column(name = "edu_purpose")
    @TableField("edu_purpose")
    private String eduPurpose;//培训目的
    private String remark;//备注
    private Integer state;
}
