package com.jxak.education.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * @Title:  FilePreviewController.java
 * @Package com.jxak.education.web
 * @Description: 将传入的文件转为PDF格式文件实现在线预览
 * @author: yangth
 * @date:   2019年5月8日 下午1:40:53
 * @version V1.0
 */
@RestController
public class FilePreviewController {

	/**
	 * DOC文件存放路径
	 */
	private static final String DOC_FILE_PATH = "";

	/**
	 * PDF文件存放路径
	 */
	private static final String PDF_FILE_PATH = "";

	/**
	 * @Title: fileConvert
	 * @Description: 文件格式转换
	 * @param: @param response
	 * @return: void
	 * @throws
	 */
	public void fileConvert(HttpServletResponse response){

		 docFileConvert(DOC_FILE_PATH, PDF_FILE_PATH);
	}

	/**
	 * @Title: docFileConvert
	 * @Description: 将doc文件转换为pdf文件
	 * @author 杨廷华
	 * @param: orginalFilePath
	 * @param: pdfFilePath
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	@GetMapping("/file/convert")
	public boolean docFileConvert(String orginalFilePath,String pdfFilePath) {
		ActiveXComponent active = new ActiveXComponent("Word.Application");
		try {
			active.setProperty("Visible", false);
            Dispatch docs = active.getProperty("Documents").toDispatch();
            Dispatch doc = Dispatch.call(docs, "Open", new Object[]{orginalFilePath, false, true}).toDispatch();
            Dispatch.call(doc, "ExportAsFixedFormat", new Object[]{pdfFilePath,17});
            Dispatch.call(doc, "Close", new Object[]{false});
            active.invoke("Quit", 0);
            return true;
        } catch (Exception var6) {
        	active.invoke("Quit", 0);
            return false;
        }
	}

	/**
	 * @Title: previewPdfFile
	 * @Comment: 预览PDF文件
	 * @author 杨廷华
	 * @param: response
	 * @return: void
	 * @throws
	 */
	@GetMapping("/preview")
	public void previewPdfFile(HttpServletResponse response,@RequestParam("pdfFilePath")String pdfFilePath) {
        try {
            File file = new File(pdfFilePath);
            if (!file.exists()) {
                response.getWriter().write("文档生成PDF失败,请下载文档查看!");
                return;
            }
            InputStream fis = new FileInputStream(pdfFilePath);
            byte[] buffer = new byte[1024];
            response.reset();
            response.addHeader("Content-Disposition", "inline;filename="+URLEncoder.encode(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/pdf");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            int nbytes = 0;
            while ((nbytes = fis.read(buffer)) != -1) {
                toClient.write(buffer, 0, nbytes);
                toClient.flush();
            }
            toClient.flush();
            toClient.close();
            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
