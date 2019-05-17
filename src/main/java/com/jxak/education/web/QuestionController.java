package com.jxak.education.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jxak.education.entity.Question;
import com.jxak.education.service.QuestionService;
import com.jxak.education.utils.ResponseT;

/**
 * @ClassName:：QuestionController 
 * @Comment： 教材题库数据交互
 * @author ：杨廷华  
 * @date ：2019年5月17日 上午11:01:27 
 */
@RestController
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	/**
	 * @Title：loadQuestionByPage 
	 * @Comment：根据教材类型编号查询对应题库信息
	 * @author：杨廷华
	 * @param ：@param page
	 * @param ：@param limit
	 * @param ：@return 
	 * @return ：String 
	 * @throws
	 */
	@RequestMapping("/loadQuestionByPage") 
	public ResponseT<Question> loadQuestionByPage(Model model,@RequestParam("page") int page, @RequestParam("limit") int limit,@RequestParam("id") int id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		List<Question> list = questionService.queryQuestionByPage(page, limit, params);
		ResponseT<Question> responseT=new ResponseT<Question>();
	    responseT.setData(list);
	    responseT.setCount(5);
	    responseT.setCode("0");
	    responseT.setMsg("");
		return responseT;
	}
}
