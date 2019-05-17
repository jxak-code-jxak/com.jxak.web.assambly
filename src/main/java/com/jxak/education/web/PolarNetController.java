package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.entity.EmpExam;
import com.jxak.education.entity.EnterpriseExam;
import com.jxak.education.service.EmpExamService;
import com.jxak.education.service.EnterpriseExamService;
import com.jxak.education.utils.ResponseT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** @Author liaoyuanjie
 * @Description //TODO 极卷网络
 * @Date 9:07 2019/5/17
 * @Param
 * @return
 **/
@RestController
public class PolarNetController {
    @Autowired
    EmpExamService empExamService;
    @Autowired
    EnterpriseExamService enterpriseExamService;

    /**
     * 分页查询企业员工审核结果
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/getEmpExamPage")
    public ResponseT getEmpExamPage(@RequestParam int page, @RequestParam int limit){
        ResponseT responseT=new ResponseT();
        responseT.setData(empExamService.getEmpExamList());
        responseT.setCode("0");
        responseT.setMsg("");
        responseT.setCount(empExamService.selectCount(new EntityWrapper<>()));
        return responseT;
    }

    /**
     * 分页查询企业管控审核结果
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/getEnterpriseExamPage")
    public ResponseT  getEnterpriseExamPage(@RequestParam int page,int limit){
        ResponseT responseT=new ResponseT();
        responseT.setData(enterpriseExamService.getEnterpriseExamList());
        responseT.setCount(enterpriseExamService.selectCount(new EntityWrapper<>()));
        responseT.setMsg("");
        responseT.setCode("0");
        return responseT;
    }
}
