package com.jxak.education.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.TrainDao;
import com.jxak.education.entity.TrainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService extends ServiceImpl<TrainDao,TrainEntity> {
    @Autowired
    TrainDao trainDao;

    public List<TrainEntity> getTrainList(){
        return trainDao.getTrainList();
    }
    public List<TrainEntity> getTrainByDept(String deptCode){
        String str =new StringBuilder().append(deptCode).append("%").toString();
        return trainDao.getTrainByDept(str);
    }
}
