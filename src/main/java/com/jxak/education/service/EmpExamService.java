package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.EmpExamDao;
import com.jxak.education.entity.EmpExam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpExamService extends ServiceImpl<EmpExamDao, EmpExam> {
    /**
     * 查询企业员工判断结果
     * @return
     */
    public List<EmpExam> getEmpExamList(){
        return this.selectList(new EntityWrapper<>());
    }
}
