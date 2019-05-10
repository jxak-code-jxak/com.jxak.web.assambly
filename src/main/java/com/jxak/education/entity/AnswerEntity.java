package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;

/**
 * @Description:    需求问题表
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
@TableName("answer_info")
@Table(name = "answer_info")
public class AnswerEntity  extends BaseEntity{
    
	private static final long serialVersionUID = 973178612894288964L;
	@TableField("train_id")
    @Column(name = "train_id")
    private Integer trainId;//需求表ID
    private String problem;//问题
    private String answer;//答案
    private Integer problemType;//问题类型
}
