package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/** @Author liaoyuanjie
 * @Description //TODO 极卷网络企业管控审核
 * @Date 8:44 2019/5/17
 * @Param 
 * @return 
 **/
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "enterprise_exam")
@TableName("enterprise_exam")
public class EnterpriseExam extends BaseEntity {
    private static final long serialVersionUID = 6230059061095965219L;
    @Column(name = "upload_file")
    @TableField("upload_file")
    private String uploadFile;//上传文件
    @Column(name = "upload_time")
    @TableField("upload_time")
    private Timestamp uploadTime;//上传时间
    @Column(name = "upload_user")
    @TableField("upload_user")
    private String uploadUser;//上传用户
    @Column(name = "exam_user")
    @TableField("exam_user")
    private String examUser;//审核人
    @Column(name = "comprehensive_result")
    @TableField("comprehensive_result")
    private String comprehensiveResult;//综合判断结果
}
