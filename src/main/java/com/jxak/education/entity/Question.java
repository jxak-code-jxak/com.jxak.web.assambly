package com.jxak.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @ClassName:：Question 
* @Comment： 教材题库
* @author ：杨廷华  
* @date ：2019年5月16日 上午9:39:53 
*/
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@TableName("question_")
@Table(name = "question_")
public class Question extends BaseEntity {
	
	private static final long serialVersionUID = 9033849258251187520L; 

	/**
	  * 培训内容
	 */
	@Column(name = "tar_content_")
	@TableField("tar_content_")
	private String traContent;
	
	/**
	  * 题目
	 */
	@Column(name = "question_")
	@TableField("question_")
	private String question;
	
	/**
	 * 选项A
	 */
	@Column(name = "option_a_")
	@TableField("option_a_")
	private String optionA;
	
	/**
	 * 选项B
	 */
	@Column(name = "option_b_")
	@TableField("option_b_")
	private String optionB;
	
	/**
	 * 选项C
	 */
	@Column(name = "option_c_")
	@TableField("option_c_")
	private String optionC;
	
	/**
	 * 选项D
	 */
	@Column(name = "option_d_")
	@TableField("option_d_")
	private String optionD;
	
	/**
	 *  答案
	 */
	@Column(name = "answer_")
	@TableField("answer_")
	private String answer;
	
	/**
	 * 培训类型代码
	 */
	@Column(name = "mat_type_code_")
	@TableField("mat_type_code_")
	private String matTypeCode;
}
