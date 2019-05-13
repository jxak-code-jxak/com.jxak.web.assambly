package com.jxak.education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jxak.education.dao.FileDao;
import com.jxak.education.entity.Attachment;

/**
* @ClassName:：FileServiceImpl 
* @Comment： 公共文件类，用于文件处理
* @author ：杨廷华  
* @date ：2019年5月13日 下午4:07:41 
*/
@Service
public class FileServiceImpl {
	
	/**
	 * 注入文件操作DAO
	 */
	@Autowired
	private FileDao fileDao;
	
	/**
	 * @Title：SaveFileInfo 
	 * @Comment：保存文件附件信息
	 * @author：杨廷华
	 * @param ：@param attach 
	 * @return ：void 
	 * @throws
	 */
	public void SaveFileInfo(Attachment attachment){
		
		fileDao.insert(attachment);
	}
}
