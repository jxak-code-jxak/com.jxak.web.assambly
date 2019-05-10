package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@TableName("menu")
@Table(name = "menu")
public class Menu extends BaseEntity {
    private String name;
    @TableField("parent_code")
    @Column(name = "parent_code")
    private String parentCode;
    private String url;
}