package com.jxak.education.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.TeaMatTypeDao;
import com.jxak.education.entity.TeaMatType;

/**
 * @ClassName:：TeaMatTypeService 
 * @Comment： 教材类型服务
 * @author ：杨廷华  
 * @date ：2019年5月15日 上午9:37:34 
 */

@Service
public class TeaMatTypeService extends ServiceImpl<TeaMatTypeDao, TeaMatType>{
	
	@Autowired
	private TeaMatTypeDao teaMatTypeDao;
	
	/**
	 * @Title：searchTeaMatType 
	 * @Comment：查询教材类型数据树
	 * @author：杨廷华
	 * @param ：@return 
	 * @return ：List<TeaMatType>
	 * @throws
	 */
	public List<TeaMatType> searchTeaMatType(){ 
		
		return teaMatTypeDao.selectList(new EntityWrapper<TeaMatType>());
	}
}
