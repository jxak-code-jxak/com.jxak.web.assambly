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
        boolean isEdit =false;
        String opCode ="";//变更记录
        if (planEntity.getId()==null||planEntity.getId().equals("")){
            planEntity.setId(GuidUtils.getCode());
        }else {
            //变更前的数据
            PlanEntity entity =this.selectById(planEntity.getId());
            //当前是编辑,需要生成变更记录
            if (entity!=null){
                isEdit =true;
                opCode =GuidUtils.getCode();
                ChangeEntity changeEntity=this.setChangeEntity(opCode,EnumState.changeState.before.getState(),entity);
                changeService.insert(changeEntity);
            }
        }
        this.insertOrUpdate(planEntity);
        if (isEdit){
            ChangeEntity changeEntity=this.setChangeEntity(opCode,EnumState.changeState.after.getState(),planEntity);
            changeService.insert(changeEntity);
        }
        return true;
    }

    /**
     * 维护变更记录
     * @param opId 操作记录业务流水id
     * @param state 当前是变更前还是变更后
     * @param entity 更改前后的数据
     * @return
     */
    private ChangeEntity setChangeEntity(String opId,Integer state,PlanEntity entity){
        ChangeEntity changeEntity=new ChangeEntity();
        changeEntity.setId(GuidUtils.getCode());
        changeEntity.setOpCode(opId);
        changeEntity.setPlanId(entity.getId());
        changeEntity.setEduPurpose(entity.getEduPurpose());
        changeEntity.setPlanContent(entity.getPlanContent());
        changeEntity.setPlanName(entity.getPlanName());
        changeEntity.setPlanObject(entity.getPlanObject());
        changeEntity.setPlanType(entity.getPlanType());
        changeEntity.setPlanBeginTime(entity.getPlanBeginTime());
        changeEntity.setPlanEndTime(entity.getPlanEndTime());
        changeEntity.setRemark(entity.getRemark());
        changeEntity.setChangeState(state);
        return changeEntity;
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
