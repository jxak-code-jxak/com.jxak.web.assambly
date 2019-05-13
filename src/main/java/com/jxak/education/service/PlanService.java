package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.PlanDao;
import com.jxak.education.entity.PlanEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlanService extends ServiceImpl<PlanDao, PlanEntity> {
    @Autowired
    PlanDao planDao;

    public List<PlanEntity> getPlanListPage() {
//        QueryWrapper<OauthOrganization> queryWrapper =  new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");

        Page<PlanEntity> page = new Page<>(1, 5);  // 查询第1页，每页返回5条
        return planDao.selectPage(page, new EntityWrapper<>());
    }

}
