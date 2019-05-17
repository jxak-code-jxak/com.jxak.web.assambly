package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/** @Author liaoyuanjie
 * @Description //TODO 费用台账
 * @Date 10:52 2019/5/17
 * @Param
 * @return
 **/
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@TableName("fy_tz")
@Table(name = "fy_tz")
public class FyTz extends BaseEntity {
    private static final long serialVersionUID = 625555906109445219L;
    @Column(name = "tr_xm")
    @TableField("tr_xm")
    private String trXm;//投入项目
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("tr_sj")
    @Column(name = "tr_sj")
    private Date trSj;//投入时间
    @TableField("tr_je")
    @Column(name = "tr_je")
    private BigDecimal trJe;//投入金额
    private String bz;//备注
}
