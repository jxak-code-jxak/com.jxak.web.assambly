package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.dao.FileUploadDao;
import com.jxak.education.entity.FileEntity;
import com.jxak.education.service.UserService;
import com.jxak.education.utils.FileUtils;
import com.jxak.education.utils.GuidUtils;
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
public class UploadController {
    @Autowired
    Environment environment;
    @Autowired
    FileUploadDao fileUploadDao;
    /**
     * 上传文件
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/file/upload")
    public Map<String,Object> fileUpload(@RequestParam("file") MultipartFile file){
        Map<String,Object> objectMap=new HashMap<>();
        String fileName = file.getOriginalFilename();
        FileEntity fileUpload=new FileEntity();
        fileUpload.setId(GuidUtils.getCode());
        fileUpload.setFilename(fileName);
        fileUpload.setUploadTime(new Timestamp(new Date().getTime()));
        fileUpload.setCreateUser("admin");
        fileUploadDao.insert(fileUpload);
        //文件上传的位置
        String targetPath =environment.getProperty("upload.path");
        try {
            FileUtils.uploadFile(file.getBytes(),targetPath,fileName);
            objectMap.put("state",true);
        } catch (Exception e) {
            e.printStackTrace();
            objectMap.put("state",false);
        }
        return objectMap;
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
        FileEntity fileEntity = fileUploadDao.selectById(id);
        if (fileEntity!=null){
            objectMap.put("state",true);
            objectMap.put("data",fileEntity);
        }else {
            objectMap.put("state",false);
        }
        return objectMap;
    }
}
