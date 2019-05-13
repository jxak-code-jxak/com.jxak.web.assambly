package com.jxak.education.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName:：FTPUtils 
 * @Comment： FTP文件上传、下载工具类
 * @author ：yangth  
 * @date ：2019年5月10日 下午5:20:47 
 */
public class FTPUtils  {  

	public static final int imageCutSize=300;
	
	public static final String DIRSPLIT="/";
	
    private FTPClient ftpClient = new FTPClient();
	
    private static final Logger log= LoggerFactory.getLogger(FTPUtils.class);
    
    /**
             * 用户名
     */
    @Value("${ftp.username}")   
    private String userName;
    
    /**
             * 密码
     */
    @Value("${ftp.password}")
    private String passWord;
    
    /**
             *主机(IP地址)
     */
    @Value("${ftp.host}")  
    private String ip;
    
    /**
             * 端口号
     */
    @Value("${ftp.port}")
    private int port;
    
    /**
             * 存放路径
     */
    @Value("${ftp.filepath}")
    private String CURRENT_DIR;     
 
    
    public String getCURRENT_DIR() {
        return CURRENT_DIR;
    }
 
 
    public void setCURRENT_DIR(String cURRENTDIR) {
        CURRENT_DIR = cURRENTDIR;
    }
 
    
    /**
     * @Comment上传文件附件到文件服务器
     * @param buffIn:上传文件流
     * @param fileName：保存文件名称
     * @param needDelete：是否同时删除
     * @return
     * @throws IOException
     */
    public boolean uploadToFtp(InputStream inStream, String fileName,boolean needDelete)throws FTPConnectionClosedException, IOException,Exception {
        boolean returnValue = false;
        try {
            connectToServer();
            setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();   
            if(!FTPReply.isPositiveCompletion(reply)){   
                ftpClient.disconnect();   
                throw new IOException(String.format("FTP服务器连接失败，请检查主机地址[%s]是否正确！", ip));   
            }
            ftpClient.enterLocalPassiveMode();
            returnValue = ftpClient.storeFile(fileName, inStream);
            if(needDelete){
                ftpClient.deleteFile(fileName);
            }
            if (returnValue) {
                log.info("FTP文件上传成功！");
            } else {
            	log.info("FTP文件上传失败！");
            }
            closeConnect();
        } catch (FTPConnectionClosedException e) {
        	log.error("FTP连接被关闭！", e);
        } catch (Exception e) {
            returnValue = false;
            log.error("FTP文件上传失败！", e);
            throw e;
        } finally {
            try {
                if (inStream != null) {
                	inStream.close();
                }
            } catch (Exception e) {
            	log.error("FTP输入流关闭失败！", e);
            }
            if (ftpClient.isConnected()) {
                closeConnect();
            }            
        }
        return returnValue;
    }
    
    
    /**
     * @Comment功能：根据文件名称，下载文件流
     * @param filename
     * @return
     * @throws IOException
     */
    public InputStream  downloadFile(String filename)throws IOException {
        InputStream in=null;
        try {
            connectToServer();
            ftpClient.enterLocalPassiveMode();
            setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();   
            if(!FTPReply.isPositiveCompletion(reply)){   
                ftpClient.disconnect();
                throw new IOException("failed to connect to the FTP Server:"+ip);   
            }
            ftpClient.changeWorkingDirectory(CURRENT_DIR);
            in=ftpClient.retrieveFileStream(filename);
 
        } catch (FTPConnectionClosedException e) {
        	log.error("FTP连接被关闭！", e);
            throw e;
        } catch (Exception e) {
            log.error("ERR : upload file "+ filename+ " from ftp : failed!", e);
        }
        return in;
    }
    
    /**
     * @Title：setFileType 
     * @Comment：设置传输文件的类型[文本文件或者二进制文件]
     * @author：setFileType
     * @param : fileType 
     * @return ：void 
     * @throws
     */
    private void setFileType(int fileType) {
        try {
            ftpClient.setFileType(fileType);
        } catch (Exception e) {
        	log.error("ftp设置传输文件的类型时失败！", e);
        }
    }
    
    /**
     * @Title：closeConnect 
     * @Comment：关闭连接
     * @author：closeConnect
     * @return ：void 
     * @throws
     */
    public void closeConnect() {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (Exception e) {
        	log.error("ftp连接关闭失败！", e);
        }
    }
    
    /**
     * @Title：connectToServer 
     * @Comment：连接到FTP服务器
     * @author：connectToServer
     * @param ：@throws FTPConnectionClosedException
     * @param ：@throws Exception 
     * @return ：void 
     * @throws
     */
    private void connectToServer() throws FTPConnectionClosedException,Exception { 
        if (!ftpClient.isConnected()) { 
            int reply; 
            try { 
                ftpClient=new FTPClient();
                ftpClient.connect(ip,port);
                ftpClient.login(userName,passWord);
                reply = ftpClient.getReplyCode(); 
 
                if (!FTPReply.isPositiveCompletion(reply)) { 
                    ftpClient.disconnect(); 
                    log.info("connectToServer FTP server refused connection."); 
                } 
            
            }catch(FTPConnectionClosedException ex){
                log.error("服务器:IP："+ip+"没有连接数！there are too many connected users,please try later", ex);
                throw ex;
            }catch (Exception e) { 
                log.error("登录ftp服务器【"+ip+"】失败", e); 
                throw e;
            } 
        } 
    } 
   
    /**
     * @Title：existDirectory 
     * @Comment：检查文件目录是否存在
     * @author：existDirectory
     * @param ：path
     * @param ：@return
     * @param ：@throws IOException 
     * @return ：boolean 
     * @throws
     */
    public boolean existDirectory(String path) throws IOException {
        boolean flag = false;  
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);  
        for (FTPFile ftpFile : ftpFileArr) {  
            if (ftpFile.isDirectory()  
                    && ftpFile.getName().equalsIgnoreCase(path)) {  
                flag = true;  
                break;  
            }  
        }  
        return flag;  
    } 
    
    /**
     * @Title：createDirectory 
     * @Comment：创建文件目录
     * @author： yangtinghua 
     * @param ： pathName
     * @param ：@return
     * @param ：@throws IOException 
     * @return ：boolean 
     * @throws
     */
    public boolean createDirectory(String pathName) throws IOException { 
    	boolean isSuccess=false;
    	try{
    		isSuccess=ftpClient.makeDirectory(pathName);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return isSuccess;  
    }
    
    /**
     * @Title：getExtention 
     * @Comment：根据拓展名读取文件
     * @author：getNoPointExtention
     * @param ：fileName
     * @return ：String 
     * @throws
     */
    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }
    
    /**
     * @Title：getNoPointExtention 
     * @Comment：根据拓展名读取文件
     * @author：getNoPointExtention
     * @param ：fileName
     * @return ：String 
     * @throws
     */
    public static String getNoPointExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos+1);
    }
    
    /**
     * @Title：getDirectory 
     * @Comment：根据当前时间读取文件目录
     * @author：  yangtinghua
     * @param ：  currentDate 当前时间
     * @return ：String 
     * @throws
     */
    public static String getDirectory(Date currentDate){
        Calendar cal = Calendar.getInstance();
        if(null!=currentDate){
            cal.setTime(currentDate);
        }
        int currentYear = cal.get(Calendar.YEAR);
        int currentMouth = cal.get(Calendar.MONTH) + 1;
        int currentDay = cal.get(Calendar.DAY_OF_MONTH) ;
        return currentYear+FTPUtils.DIRSPLIT+currentMouth+FTPUtils.DIRSPLIT+currentDay;
    }
}
