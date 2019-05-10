package com.jxak.education.entity.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description:    公有字段实体
 * @Author:         liaoyuanjie
 * @CreateDate:     2019/5/9 9:51
 * @UpdateUser:     liaoyuanjie
 * @UpdateDate:     2019/5/9 9:51
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Data
@MappedSuperclass//定义公有父类字段,当前类的字段会追加到所有继承当前类的子类中,并生成库表映射关系
public class BaseEntity implements Serializable {
    @Id
    @TableId
    @GeneratedValue
    @Column(length = 32)//mysql主键长度不能超过768,所以必须指定长度
    private String id;
    @TableField("create_user")
    @Column(name = "create_user")
    private String createUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")//出参
    @TableField("create_time")
    @Column(name = "create_time")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    @Column(name = "update_time")
    private Date updateTime;
    @TableField("update_user")
    @Column(name = "update_user")
    private String updateUser;
}
