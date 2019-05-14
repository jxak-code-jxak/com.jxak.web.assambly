package com.jxak.education.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.ChangeDao;
import com.jxak.education.entity.ChangeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeService extends ServiceImpl<ChangeDao, ChangeEntity> {
    @Autowired
    ChangeDao changeDao;

    /**
     * 查询变更记录
     * @return
     */
    public List<ChangeEntity> getChangeList(int page,int limit){
        //Page<ChangeEntity> changeEntityPage=new Page<>(page,limit);

        return changeDao.getChangeList();
    }
}
