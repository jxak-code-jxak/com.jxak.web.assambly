package com.jxak.education.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jxak.education.entity.Question;

/**
 * 
 * @ClassName:：QuestionDao 
 * @Comment： 教材题库接口
 * @author ：杨廷华  
 * @date ：2019年5月16日 上午11:30:01 
 *
 */
@Repository
public interface QuestionDao extends BaseMapper<Question>{ 

}
