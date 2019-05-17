package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.EnterpriseExamDao;
import com.jxak.education.entity.EnterpriseExam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseExamService extends ServiceImpl<EnterpriseExamDao, EnterpriseExam> {
    public List<EnterpriseExam> getEnterpriseExamList(){
        return this.selectList(new EntityWrapper<>());
    }
}
