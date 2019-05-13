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
 * @ClassName:：BaseEntity 
 * @Comment： 公共字段实体
 * @author ：yangth  
 * @date ：2019年5月10日 上午11:29:13 
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 5010777754852182334L;
	
	/**
	   * 主键
	 */
	@Id
    @TableId
    @GeneratedValue
    @Column(length = 32)
    private String id; 
	
	/**
	   * 创建用户
	 */
    @TableField("create_user_")
    @Column(name = "create_user_")
    private String createUser;
    
    /**
             * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time_")
    @Column(name = "create_time_")
    private Date createTime;
    
    /**
             * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time_")
    @Column(name = "update_time_")
    private Date updateTime;
    
    /**
             * 更新用户
     */
    @TableField("update_user_")
    @Column(name = "update_user_")
    private String updateUser;
}
