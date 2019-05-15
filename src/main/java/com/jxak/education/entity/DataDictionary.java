package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/** @Author liaoyuanjie
 * @Description 数据字典
 * @Date 15:35 2019/5/15
 * @Param
 * @return
 **/
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@TableName("data_dictionary")
@Table(name = "data_dictionary")
public class DataDictionary extends BaseEntity {
    @TableField("key_type")
    @Column(name = "key_type")
    private String keyType;//数据项编码
    @TableField("key_word")
    @Column(name = "key_word")
    private String keyWord;//数据类型名称
    @TableField("data_code")
    @Column(name = "data_code")
    private String dataCode;//数据项编号
    @TableField("data_value")
    @Column(name = "data_value")
    private String dataValue;//数据项值
    private String remark;//描述
}
