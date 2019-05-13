package com.jxak.education.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName:：FTPUtils 
 * @Comment： FTP文件上传、下载工具类
 * @author ：yangth  
 * @date ：2019年5月10日 下午5:20:47 
 */
@Component
public class FTPUtils  {  
	
	private static final Logger log= LoggerFactory.getLogger(FTPUtils.class);
	
    private FTPClient ftpClient = new FTPClient();
	
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
             *主机
     */
    @Value("${ftp.host}")  
    private String ip;
    
    /**
             * 端口号
     */
    @Value("${ftp.port}")
    private int port;
 
    
    /**
     * @Comment上传文件附件到文件服务器
     * @param buffIn:上传文件流
     * @param fileName：保存文件名称
     * @param needDelete：是否同时删除
     * @return
     * @throws IOException
     */
    public boolean uploadFile(InputStream inStream, String fileName,boolean needDelete)throws FTPConnectionClosedException, IOException,Exception {
        boolean returnValue = false;
        try {
            connectToServer();
            setFileType(FTP.BINARY_FILE_TYPE);
            int reply = ftpClient.getReplyCode();   
            if(!FTPReply.isPositiveCompletion(reply)){   
                ftpClient.disconnect();   
                throw new IOException(String.format("FTP服务器连接失败，请检查主机地址[%s]是否正确！",ip));   
            }
            ftpClient.enterLocalPassiveMode();
            returnValue = ftpClient.storeFile(new String(fileName.getBytes("GBK"),"iso-8859-1"), inStream);
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
     * @Comment功能：根据文件名称下载文件流
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
                throw new IOException(String.format("FTP服务器连接失败，请检查主机地址[%s]是否正确！",ip));   
            }
            //ftpClient.changeWorkingDirectory(CURRENT_DIR);
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
     * @Comment：设置传输文件的类型
     * @author：setFileType
     * @param : fileType 
     * @return ：void 
     * @throws
     */
    private void setFileType(int fileType) {
        try {
            ftpClient.setFileType(fileType);
        } catch (Exception e) {
        	log.error("FTP设置传输文件的类型时失败！", e);
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
        	log.error("FTP连接关闭失败！", e);
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
                ftpClient.login("test","test");
                reply = ftpClient.getReplyCode(); 
                System.out.println(reply);
                if (!FTPReply.isPositiveCompletion(reply)){
                    ftpClient.disconnect(); 
                    log.info("连接FTP服务器失败，请检查连接配置！"); 
                } 
            }catch(FTPConnectionClosedException ex){
                log.error("连接FTP服务器失败！", ex);
                throw ex;
            }catch (Exception e) { 
                log.error("连接FTP服务器失败！", e); 
                throw e;
            } 
        } 
    } 
}
