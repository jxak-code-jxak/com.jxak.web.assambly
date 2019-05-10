package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

/**
 * @Description:    用户表
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
@Alias("user")
@TableName("user_info")
@Table(name = "user_info")
public class UserEntity extends BaseEntity {
    
	private static final long serialVersionUID = 2351204035678361012L;
	private String username;
    private String password;
    private String sex;
    private String name;//姓名
    @Column(name = "work_time")
    @TableField("work_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")//出参
    private Date workTime;//入职时间
    private Integer healthy;//健康状态
    @TableField("dept_code")
    @Column(name = "dept_code")
    private String deptCode;//所属部门
}
