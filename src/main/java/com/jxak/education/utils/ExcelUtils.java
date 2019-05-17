package com.jxak.education.utils;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.jxak.education.entity.Question;

/**
 * @ClassName:：ExcelUtils 
 * @Comment： Excel工具类，解析Excel文件对象，将表格中的数据转换为对象输出
 * @author ：杨廷华  
 * @date ：2019年5月16日 上午10:14:25 
 */
public class ExcelUtils {
	
	    private final static String excel2003 =".xls";
	    
	    private final static String excel2007 =".xlsx";
	    
	    /**
	     * @Title：getBankListByExcel 
	     * @Comment：读取流中的数据，组装对象返回
	     * @author：杨廷华
	     * @param ：@param in
	     * @param ：@param fileName
	     * @param ：@return
	     * @param ：@throws Exception 
	     * @return ：List<List<Object>> 
	     * @throws
	     */ 
	    public static  List<Question> getDataListByExcel(InputStream in,String fileName) throws Exception{  
	    	List<Question> dataList = null;
				Workbook wb = WorkbookFactory.create(in);
				Sheet sheet = wb.getSheetAt(0);
				int rownum = sheet.getPhysicalNumberOfRows();
				Row row = sheet.getRow(0);
				dataList = new ArrayList<Question>();
				for (int i = 1; i < rownum; i++) {
					Question question = new Question();
					row = sheet.getRow(i);
					if (row != null) {
						question.setTraContent(getCellFormatValue(row.getCell(0)));
						question.setQuestion(getCellFormatValue(row.getCell(1)));
						question.setOptionA(getCellFormatValue(row.getCell(2)));
						question.setOptionB(getCellFormatValue(row.getCell(3)));
						question.setOptionC(getCellFormatValue(row.getCell(4)));
						question.setOptionD(getCellFormatValue(row.getCell(5)));
						question.setAnswer(getCellFormatValue(row.getCell(6)));
						question.setMatTypeCode(getCellFormatValue(row.getCell(7)).substring(0,getCellFormatValue(row.getCell(7)).indexOf("-")));
					} else {
						continue;
					}
					dataList.add(question);
			}
			return dataList;
		}
	    
	    public static String getCellFormatValue(Cell cell) {
			String cellValue = "";
			if (cell == null) {
				return cellValue;
			}
			switch (cell.getCellType()) {
				case NUMERIC: {
					cellValue = String.valueOf((int)cell.getNumericCellValue());
					break;
				}
				case FORMULA: {
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date d = cell.getDateCellValue();
						if (d != null) {
							cellValue = sdf.format(d);
						}
					} else {
						cellValue = String.valueOf(cell.getNumericCellValue());
					}
					break;
				}
				case STRING: {
					cellValue = cell.getRichStringCellValue().getString();
					break;
				}
			default:
				break;
			}
			return cellValue;
		}

		/**
		 * @Title：getWorkbook 
		 * @Comment：根据文件后缀，自适应上传的版本
		 * @author：杨廷华
		 * @param ：@param inStr
		 * @param ：@param fileName
		 * @param ：@return
		 * @param ：@throws Exception 
		 * @return ：Workbook 
		 * @throws
		 */
		public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
		    Workbook wb = null;  
		    String fileType = fileName.substring(fileName.lastIndexOf("."));  
		    if(excel2003.equals(fileType)){  
		        wb = new HSSFWorkbook(inStr);  
		    }else if(excel2007.equals(fileType)){  
		        wb = new XSSFWorkbook(inStr);
		    }else{  
		        throw new Exception("Excel文件格式不正确，请检查！");  
		    }  
		    return wb;  
		}  
		
		/**
		 * @Title：getCellValue 
		 * @Comment：格式化表格中的数值
		 * @author：杨廷华
		 * @param ：@param cell
		 * @param ：@return 
		 * @return ：Object 
		 * @throws
		 */
	    public static  Object getCellValue(Cell cell){  
	        Object value = null;  
	        DecimalFormat df = new DecimalFormat("0");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss"); 
	        DecimalFormat df2 = new DecimalFormat("0.00");  
	        switch (cell.getCellType()) {  
	        case STRING: 
	            value = cell.getRichStringCellValue().getString();  
	            break;  
	        case NUMERIC:  
	            if("General".equals(cell.getCellStyle().getDataFormatString())){  
	                value = df.format(cell.getNumericCellValue());  
	            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
	                value = sdf.format(cell.getDateCellValue());  
	            }else{  
	                value = df2.format(cell.getNumericCellValue());  
	            }  
	            break;  
	        case BOOLEAN:  
	            value = cell.getBooleanCellValue();  
	            break;  
	        case BLANK:  
	            value = "";  
	            break;  
	        default:  
	            break;  
	        }  
	        return value;  
	    }  
}
