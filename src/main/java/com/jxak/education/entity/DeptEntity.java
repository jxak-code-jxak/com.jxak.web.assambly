package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @Description:    部门表
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
@TableName("dept_info")
@Table(name = "dept_info")
public class DeptEntity extends BaseEntity{
    
	private static final long serialVersionUID = -3320744791572178697L;
	private String code;
    private String name;
    @TableField("parent_code")
    @Column(name = "parent_code")
    private String parentCode;
}
