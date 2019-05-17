package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.FyTzDao;
import com.jxak.education.entity.FyTz;
import com.jxak.education.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FyTzService extends ServiceImpl<FyTzDao, FyTz> {
    @Autowired
    FyTzDao fyTzDao;
    /**
     * 分页查询费用台账
     * @param page
     * @param limit
     * @return
     */
    public List<FyTz> getFyTzList(int page,int limit){
        Page<FyTz> fyTzPage=new Page<>(page,limit);
        return fyTzDao.selectPage(fyTzPage,new EntityWrapper<>());
    }

    /**
     * 保存费用台账
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveFyTz(FyTz fyTz){
        if (fyTz.getId()==null||fyTz.getId().equals("")){
            fyTz.setId(GuidUtils.getCode());
        }
        this.insertOrUpdate(fyTz);
        return true;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFyTz(List<String> ids){
        this.deleteBatchIds(ids);
        return true;
    }
}
