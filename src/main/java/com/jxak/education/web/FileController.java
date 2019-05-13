package com.jxak.education.web;

import com.jxak.education.entity.Attachment;
import com.jxak.education.service.FileServiceImpl;
import com.jxak.education.utils.FTPUtils;
import com.jxak.education.utils.GuidUtils;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
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
    
    /**
     * @throws Exception 
     * @throws IOException 
     * @throws FTPConnectionClosedException 
     * @Title  ：fileUpload 
     * @Comment：将文件上传至FTP服务器并保存附件信息
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
    	String path = "ftp://"+environment.getProperty("ftp.host")+":"+environment.getProperty("ftp.port")+"/test/"+file.getOriginalFilename();
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
    
    
    //文件下载相关代码
    @PostMapping("/download/{fileName}")
    public String downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        //文件下载的位置
        String targetPath =environment.getProperty("upload.path");
        if (targetPath != null) {
            //设置文件路径
            File file = new File(targetPath , fileName);
            if (file.exists()) {
                response.setContentType("multipart/form-data");
                response.setHeader("content-type", "application/octet-stream");
                try {
                    response.addHeader("Content-Disposition","attachment;fileName=" +new String(fileName.getBytes("UTF-8"),"iso-8859-1"));
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;
                    try {
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        OutputStream os = response.getOutputStream();
                        int i = bis.read(buffer);
                        while (i != -1) {
                            os.write(buffer, 0, i);
                            i = bis.read(buffer);
                        }
                        return "下载成功";
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bis != null) {
                            try {
                                bis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
    /**
     * 获取文件列表
     * @return
     */
    @GetMapping(value = "/file/getFileList")
    public Map<String,Object> getFileList(){
        //Map<String,Object> objectMap=new HashMap<>();
        //objectMap.put("data",.selectList(new EntityWrapper<>()));
        //return objectMap;
    	return null;
    }

    /**
     * 判断文件是否存在
     * @param id
     * @return
     */
    @GetMapping(value = "/fileExist/{id}")
    public Map<String,Object> fileExist(@PathVariable String id){
    	/*
        Map<String,Object> objectMap=new HashMap<>();
        Attachment fileEntity = fileUploadDao.selectById(id);
        if (fileEntity!=null){
            objectMap.put("state",true);
            objectMap.put("data",fileEntity);
        }else {
            objectMap.put("state",false);
        }
        return objectMap;
        */
    	return null;
    }
}
