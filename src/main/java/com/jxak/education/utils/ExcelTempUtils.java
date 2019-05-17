package com.jxak.education.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @ClassName:：CreateExcelTemplate 
 * @Comment： 创建带动态数据下拉选的Excel模板
 * @author ：杨廷华
 * @date ：2019年5月15日 上午10:46:54
 */
public class ExcelTempUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelTempUtils.class);
	
	private static HSSFWorkbook wb;
	
	/**
	 * @Title：createExcelTemplate 
	 * @Comment：动态创建Excel数据导入模板
	 * @author：杨廷华
	 * @param ：@param filePath
	 * @param ：@param handers
	 * @param ：@param downData
	 * @param ：@param downRows  
	 * @return ：void 
	 * @throws
	 */
	public static void createExcelTemplate(String filePath, String[] handers, List<String[]> downData,String[] downRows) {
		wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("微软雅黑");
		fontStyle.setFontHeightInPoints((short) 12);
		fontStyle.setBold(true);
		style.setFont(fontStyle);
		HSSFSheet sheet1 = wb.createSheet("Sheet1");
		HSSFSheet sheet2 = wb.createSheet("Sheet2");
		HSSFRow rowFirst = sheet1.createRow(0);
		for (int i = 0; i < handers.length; i++) {
			HSSFCell cell = rowFirst.createCell(i);
			sheet1.setColumnWidth(i, 4000);
			cell.setCellStyle(style);
			cell.setCellValue(handers[i]);
		}
		String[] arr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S","T", "U", "V", "W", "X", "Y", "Z" };
		int index = 0;
		HSSFRow row = null;
		for (int r = 0; r < downRows.length; r++) {
			String[] dlData = downData.get(r);
			int rownum = Integer.parseInt(downRows[r]);
			if (dlData.length < 5) {
				sheet1.addValidationData(setDataValidation(sheet1, dlData, 1, 50000, rownum, rownum));
			} else {
				String strFormula = "Sheet2!$" + arr[index] + "$1:$" + arr[index] + "$5000";
				sheet2.setColumnWidth(r, 4000);
				sheet1.addValidationData(SetDataValidation(strFormula, 1, 50000, rownum, rownum)); 
				for (int j = 0; j < dlData.length; j++) {
					if (index == 0) {
						row = sheet2.createRow(j);
						sheet2.setColumnWidth(j, 4000);
						row.createCell(0).setCellValue(dlData[j]);
					} else {
						int rowCount = sheet2.getLastRowNum();
						if (j <= rowCount) {
							sheet2.getRow(j).createCell(index).setCellValue(dlData[j]);
						} else {
							sheet2.setColumnWidth(j, 4000);
							sheet2.createRow(j).createCell(index).setCellValue(dlData[j]);
						}
					}
				}
				index++;
			}
		}
		try {
			File f = new File(filePath);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(f);
			out.flush();
			wb.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title：SetDataValidation 
	 * @Comment：设置数据有效性
	 * @author：杨廷华
	 * @param ：@param strFormula
	 * @param ：@param firstRow
	 * @param ：@param endRow
	 * @param ：@param firstCol
	 * @param ：@param endCol
	 * @param ：@return 
	 * @return ：HSSFDataValidation 
	 * @throws
	 */
    private static HSSFDataValidation SetDataValidation(String strFormula,int firstRow, int endRow, int firstCol, int endCol) {
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(strFormula);
        HSSFDataValidation dataValidation = new HSSFDataValidation(regions,constraint);
        dataValidation.createErrorBox("Error", "Error");
        dataValidation.createPromptBox("", null);
        return dataValidation;
    }
    
    
   /**
    * @Title：setDataValidation 
    * @Comment：设置数据有效性
    * @author：杨廷华
    * @param ：@param sheet
    * @param ：@param textList
    * @param ：@param firstRow
    * @param ：@param endRow
    * @param ：@param firstCol
    * @param ：@param endCol
    * @param ：@return 
    * @return ：DataValidation 
    * @throws
    */
    private static DataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        constraint.setExplicitListValues(textList);
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
        DataValidation data_validation = helper.createValidation(constraint, regions);
        return data_validation;
    }
    
    /**
     * @Title: getExcel 
     * @Description: 下载指定路径的Excel文件 
     * @param @param url 文件路径
     * @param @param fileName  文件名
     * @param @param response
     * @return void
     * @throws
     */
    public static void getExcel(String url, String fileName, HttpServletResponse response,HttpServletRequest request){
         try {  
            response.setContentType("multipart/form-data");  
            response.setHeader("Content-disposition", "attachment; filename=\""+ encodeChineseDownloadFileName(request, fileName+".xls") +"\"");
            File file = new File(url);  
            FileInputStream in = new FileInputStream(file);  
            OutputStream out = new BufferedOutputStream(response.getOutputStream());  
            int b = 0;  
            byte[] buffer = new byte[2048];  
            while ((b=in.read(buffer)) != -1){  
                out.write(buffer,0,b);
            }  
            in.close();
            out.flush(); 
            out.close();  
        } catch (IOException e) {  
            log.error("下载Excel模板异常", e);  
        }  
    }

    /**
     * @Title：encodeChineseDownloadFileName 
     * @author：杨廷华
     * @param ：@param request
     * @param ：@param pFileName
     * @param ：@return
     * @param ：@throws UnsupportedEncodingException 
     * @return ：String 
     * @throws
     */
    private static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName)throws UnsupportedEncodingException {
        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent) {
            if (-1 != agent.indexOf("Firefox")) {  
                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8")))) + "?=";
            } else if (-1 != agent.indexOf("Chrome")) {
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                filename = StringUtils.replace(filename, "+", "%20");
            }
        } else {
            filename = pFileName;
        }
        return filename;
    }
}