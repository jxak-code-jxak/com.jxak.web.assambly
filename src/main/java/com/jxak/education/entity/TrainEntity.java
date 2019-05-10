package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Description:    培训需求表
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
@TableName("train_info")
@Table(name = "train_info")
public class TrainEntity extends BaseEntity{
    @TableField("user_id")
    @Column(name = "user_id")
    private String userId;
    private Integer performance;//工作表现
    private Integer skilled;//工作熟练度
    private Integer train;//培训状态
    @TableField("write_time")
    @Column(name = "write_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")//出参
    private Timestamp writeTime;//填表时间
    /*****************************************/
    @Transient
    @TableField(exist = false)
    private String name;//姓名
    @Transient
    @TableField(exist = false)
    private String sex;//性别
    @Transient
    @TableField(exist = false,value = "work_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")//出参
    private Timestamp workTime;//入职时间
    @Transient
    @TableField(exist = false)
    private Integer healthy;//健康状态
    @Transient
    @TableField(exist = false,value = "dept_code")
    private String deptCode;
}
