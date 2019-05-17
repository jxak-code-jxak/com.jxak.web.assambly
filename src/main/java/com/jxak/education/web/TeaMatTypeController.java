package com.jxak.education.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxak.education.entity.TeaMatType;
import com.jxak.education.service.TeaMatTypeService;

/**
 * @ClassName:：TeaMatTypeController 
 * @Comment： 教材类型数据交互
 * @author ：yangth  
 * @date ：2019年5月15日 上午9:40:37 
 */
@RestController
public class TeaMatTypeController {
	
	@Autowired
	private TeaMatTypeService teaMatTypeService;
	
	/**
	 * @Title：loadTeaMatType 
	 * @Comment：查询教材类型数据树
	 * @author：杨廷华
	 * @param ：@return 
	 * @return ：List<TeaMatType> 
	 * @throws
	 */
	@PostMapping(value = "/loadTeaMatType") 
	public List<TeaMatType> loadTeaMatType(){
		List<TeaMatType> entityList = teaMatTypeService.searchTeaMatType();
		return entityList;
	}
}
