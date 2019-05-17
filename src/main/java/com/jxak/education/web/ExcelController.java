package com.jxak.education.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.jxak.education.entity.Question;
import com.jxak.education.entity.TeaMatType;
import com.jxak.education.service.QuestionService;
import com.jxak.education.service.TeaMatTypeService;
import com.jxak.education.utils.ExcelTempUtils;
import com.jxak.education.utils.ExcelUtils;

@Controller
public class ExcelController {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelController.class);
	
	@Autowired
	private TeaMatTypeService teaMatTypeService;
	
	@Autowired
	private QuestionService questionService;
	
	
	/**
	 * @Title：getExcelTemplate 
	 * @Comment：调用此方法获取Excel模板
	 * @author：杨廷华
	 * @param ：@param request
	 * @param ：@param response 
	 * @return ：void 
	 * @throws
	 */
    @RequestMapping("/downloadExcel") 
    public void getExcelTemplate(HttpServletRequest request, HttpServletResponse response){
    	String path = request.getSession().getServletContext().getRealPath("/temp/TeaMatExcelTem.xls");
        String[] handers = {"培训内容","题目","选项A","选项B","选项C","选项D","答案","培训类型"};
    	List<TeaMatType> list = teaMatTypeService.searchTeaMatType();
    	List<String> dataList = new ArrayList<String>();
    	for(TeaMatType item:list) {
    		dataList.add(item.getId()+"-"+item.getName());
    	}
    	String[] data = new String[dataList.size()];
    	dataList.toArray(data);
        List<String[]> downData = new ArrayList<String[]>();
        String [] downRows = {"7"};
        downData.add(data);
        try {
        	ExcelTempUtils.createExcelTemplate(path, handers, downData, downRows);
        	ExcelTempUtils.getExcel(path, "教材题库模板", response, request);
        } catch (Exception e) {
            log.error("批量导入信息异常：" + e.getMessage());
        }
    }
    
    /**
     * @throws Exception 
     * @Title：impExcel
     * @Comment：Excel批量导入题库
     * @author：杨廷华
     * @param ：@param file
     * @return ：void 
     * @throws
     */
    @RequestMapping("/loadExcel")
    public void impExcel(MultipartFile file) throws Exception {
    	List<TeaMatType> condition = null;
    	InputStream inputStream = file.getInputStream();
    	List<Question> dataList  = ExcelUtils.getDataListByExcel(inputStream, "TeaMatExcelTem.xls");//获取Excel数据源
    	if(!dataList.isEmpty()) {
    		for(Question question:dataList) {
    			String teaId = question.getMatTypeCode();//得到教材类型的编码
    			Map<String,Object> param = new HashMap<String,Object>();
    			param.put("id", teaId);
    			TeaMatType teaMatType = teaMatTypeService.selectByMap(param).get(0);
    			String level = teaMatType.getLevel();
    			Map<String,Object> params = new HashMap<String,Object>();
    			params.put("parent_id_", teaMatType.getId());
				condition = teaMatTypeService.selectByMap(params);
    			if(level.equals("1")) {
    				if(!condition.isEmpty()) {
    					for(int i=0;i<condition.size();i++) {
    						question.setId(UUID.randomUUID().toString().replace("-", ""));
    						question.setMatTypeCode(condition.get(i).getId());
    						question.setCreateTime(new Date());
    						question.setUpdateTime(new Date());
    						questionService.insert(question);
    					}
    				}
    			}
    			if(level.equals("2")) {
    				condition.add(teaMatType);
    				for(int i=0;i<condition.size();i++) {
    					question.setId(UUID.randomUUID().toString().replace("-", ""));
						question.setMatTypeCode(condition.get(i).getId());
						question.setUpdateTime(new Date());
						questionService.insert(question);
					}
    			}
        	}
    	}
    }
}
