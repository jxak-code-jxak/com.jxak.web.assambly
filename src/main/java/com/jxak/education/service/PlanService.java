package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.PlanDao;
import com.jxak.education.entity.ChangeEntity;
import com.jxak.education.entity.PlanEntity;
import com.jxak.education.utils.EnumState;
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
    @Autowired
    ChangeService changeService;

    /**
     * 查询培训计划
     * @param page
     * @param limit
     * @return
     */
    public List<PlanEntity> getPlanListPage(int page,int limit) {
        Page<PlanEntity> planEntityPage = new Page<>(page, limit);  // 查询第1页，每页返回5条
        return planDao.selectPage(planEntityPage, new EntityWrapper<>());
    }

    /**
     * 保存培训计划，并生成变更记录
     * @param planEntity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean savePlan(PlanEntity planEntity){
        if (planEntity.getId()==null||planEntity.getId().equals("")){
            planEntity.setId(GuidUtils.getCode());
        }else {
            PlanEntity entity =this.selectById(planEntity.getId());
            //当前是编辑,需要生成变更记录
            if (entity!=null){
                ChangeEntity changeEntity =new ChangeEntity();
                changeEntity.setPlanId(planEntity.getId());
                changeEntity.setId(GuidUtils.getCode());
                //将之前的记录更新为变更前
                changeEntity.setChangeState(EnumState.changeState.before.getState());
                changeService.update(changeEntity,new EntityWrapper<ChangeEntity>().eq("plan_id",entity.getId()));
                //生成最新记录为变更后
                changeEntity.setChangeState(EnumState.changeState.after.getState());
                changeService.insert(changeEntity);
            }
        }
        planEntity.setPlanTime(new Timestamp(new Date().getTime()));
        this.insertOrUpdate(planEntity);
        return true;
    }

    /**
     *  删除培训计划
     * @param ids
     * @return
     */
    public boolean deletePlanByIds(List<String> ids){
        this.deleteBatchIds(ids);
        return true;
    }

}
