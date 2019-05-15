package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.ChangeDao;
import com.jxak.education.entity.ChangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChangeService extends ServiceImpl<ChangeDao, ChangeEntity> {
    @Autowired
    ChangeDao changeDao;

    /**
    * @Description:    查询培训变更记录列表
    * @Author:         liaoyuanjie
    * @CreateDate:     2019/5/14 17:22
    * @UpdateUser:     liaoyuanjie
    * @UpdateDate:     2019/5/14 17:22
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    public List<ChangeEntity> getChangeList(int page,int limit){
        Page<ChangeEntity> changeEntityList =this.selectPage(new Page<>(page,limit),new EntityWrapper<>());
        return changeEntityList.getRecords();
    }
    /**
    * @Description:    删除培训变更记录
    * @Author:         liaoyuanjie
    * @CreateDate:     2019/5/14 17:21
    * @UpdateUser:     liaoyuanjie
    * @UpdateDate:     2019/5/14 17:21
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteChangeByIds(List<String> ids){
        this.deleteBatchIds(ids);
        return true;
    }
}
