package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.dao.FileUploadDao;
import com.jxak.education.entity.Attachment;
import com.jxak.education.service.FileServiceImpl;
import com.jxak.education.utils.FTPUtils;
import com.jxak.education.utils.GuidUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController extends FTPUtils {
	
    @Autowired
    Environment environment;
    
    @Autowired
    FileServiceImpl fileServiceImpl;
    
    @Autowired
    FileUploadDao fileUploadDao;
    
    /**
     * @throws Exception 
     * @throws IOException 
     * @throws FTPConnectionClosedException 
     * @Title  ：fileUpload 
     * @Comment：文件上传至FTP服务器并保存附件信息
     * @author ：杨廷华
     * @param  ：@param file
     * @param  ：@return 
     * @return ：Map<String,Object> 
     * @throws
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/file/upload")
    public Map<String,Object> fileUpload(@RequestParam("file") MultipartFile file) throws FTPConnectionClosedException, IOException, Exception{
    	String fileName = file.getOriginalFilename();
    	String path = "ftp://"+environment.getProperty("ftp.host")+":"+environment.getProperty("ftp.port")+"/"+file.getOriginalFilename();
    	Map<String,Object> params = new HashMap<String,Object>();
    	boolean result = uploadFile(file.getInputStream(),fileName, false);
    	if(result) {
    		Attachment attachment=new Attachment();
    		attachment.setId(GuidUtils.getCode());
    		attachment.setAttrName(file.getOriginalFilename());
    		attachment.setCreateTime(new Timestamp(new Date().getTime()));
    		attachment.setCreateUser("admin");
    		attachment.setAttrSuffix(fileName.substring(fileName.indexOf(".")));
    		attachment.setFullPath(path);
    		fileServiceImpl.SaveFileInfo(attachment);
    		params.put("state", true);
    	}else {
    		params.put("state", false);
    	}
		return params;
    }
    
    
    /**
     * @throws Exception 
     * @Title：downloadFile 
     * @Comment：从FTP服务器下载文件
     * @author：杨廷华
     * @param ：attrName
     * @param ：response
     * @param ：@return 
     * @return ：void 
     * @throws
    */
    @PostMapping("/download")
    public void downloadFile(@RequestParam("attrName") String attrName,HttpServletRequest request) throws Exception {
    	try {
			downloadFtpFile(attrName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    /**
     * 获取文件列表
     * @return
     */
    @GetMapping(value = "/file/getFileList")
    public Map<String,Object> getFileList(){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("data",fileUploadDao.selectList(new EntityWrapper<>()));
        return objectMap;
    }

    /**
     * 判断文件是否存在
     * @param id
     * @return
     */
    @GetMapping(value = "/fileExist/{id}")
    public Map<String,Object> fileExist(@PathVariable String id){
        Map<String,Object> objectMap=new HashMap<>();
        Attachment fileEntity = fileUploadDao.selectById(id);
        if (fileEntity!=null){
            objectMap.put("state",true);
            objectMap.put("data",fileEntity);
        }else {
            objectMap.put("state",false);
        }
        return objectMap;
    }
}
