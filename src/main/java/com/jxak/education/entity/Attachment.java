package com.jxak.education.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jxak.education.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;


/**
* @ClassName:：Attachment
* @Comment： 附件实体
* @author ：yangth
* @date ：2019年5月13日 上午11:21:59
*/
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "attachment_info")
@TableName("attachment_info")
public class Attachment extends BaseEntity{

	private static final long serialVersionUID = 7464307588189927927L;

	/**
	 * 附件名称
	 */
	@TableField("attr_name_")
	private String attrName;

	/**
	 *文件后缀
	 */
	@TableField("attr_suffix_")
	private String attrSuffix;

	/**
	 * 文件全路径
	 */
	@TableField("full_path_")
	private String fullPath;

}
