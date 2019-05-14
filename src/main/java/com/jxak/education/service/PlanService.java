package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.PlanDao;
import com.jxak.education.entity.PlanEntity;
import com.jxak.education.utils.GuidUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PlanService extends ServiceImpl<PlanDao, PlanEntity> {
    @Autowired
    PlanDao planDao;

    public List<PlanEntity> getPlanListPage(int page,int limit) {
        Page<PlanEntity> planEntityPage = new Page<>(page, limit);  // 查询第1页，每页返回5条
        return planDao.selectPage(planEntityPage, new EntityWrapper<>());
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean savePlan(PlanEntity planEntity){
        if (planEntity.getId()==null||planEntity.getId().equals("")){
            planEntity.setId(GuidUtils.getCode());
        }
        planEntity.setPlanTime(new Timestamp(new Date().getTime()));
        this.insertOrUpdate(planEntity);
        return true;
    }
    public boolean deletePlanById(String id){
        this.deleteById(id);
        return true;
    }

}
