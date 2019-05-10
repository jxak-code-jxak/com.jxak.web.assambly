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
 * @Description:    文件管理表
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
@TableName("file_upload")
@Table(name = "file_upload")
public class FileEntity extends BaseEntity{
    private String filename;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("upload_time")
    @Column(name = "upload_time")
    private Timestamp uploadTime;
    @TableField("create_user")
    @Column(name = "create_user")
    private String createUser;
}
