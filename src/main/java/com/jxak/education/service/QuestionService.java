package com.jxak.education.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.QuestionDao;
import com.jxak.education.entity.Question;

/**
 * @ClassName:：QuestionService 
 * @Comment： 教材题库服务
 * @author ：杨廷华  
 * @date ：2019年5月16日 上午11:31:37 
 */
@Service
public class QuestionService extends ServiceImpl<QuestionDao, Question>{
	
	@Autowired
	private QuestionDao questionDao;
	
	/**
	 * 
	* @Title：queryQuestionByPage 
	* @Comment：自定义条件分页查询教材题库
	* @author：杨廷华
	* @param ：@param page
	* @param ：@param limit
	* @param ：@return 
	* @return ：List<Question>  
	* @throws
	 */
	public List<Question> queryQuestionByPage(int page,int limit,Map<String,Object> params){
		Page<Question> pages = new Page<Question>(page,limit);
		EntityWrapper<Question> wrapper = new EntityWrapper<Question>();
		wrapper.like("mat_type_code_", String.valueOf(params.get("id")));
		List<Question> entityList = questionDao.selectPage(pages,  wrapper);
		return entityList;
	}
}
