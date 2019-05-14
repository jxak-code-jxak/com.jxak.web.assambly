package com.jxak.education.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.TrainDao;
import com.jxak.education.dao.UserDao;
import com.jxak.education.entity.TrainEntity;
import com.jxak.education.entity.UserEntity;
import com.jxak.education.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TrainService extends ServiceImpl<TrainDao,TrainEntity> {
    @Autowired
    TrainDao trainDao;
    @Autowired
    UserService userService;

    public List<TrainEntity> getTrainList(){
        return trainDao.getTrainList();
    }
    public List<TrainEntity> getTrainByDept(String deptCode){
        String str =new StringBuilder().append(deptCode).append("%").toString();
        return trainDao.getTrainByDept(str);
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean saveTrain(TrainEntity trainEntity){
        UserEntity userEntity =trainEntity.getUserEntity();
        if (trainEntity.getId()==null||trainEntity.getId().equals("")){
            trainEntity.setId(GuidUtils.getCode());
            trainEntity.setUserId(GuidUtils.getCode());
        }
        userEntity.setId(trainEntity.getUserId());
        trainEntity.setWriteTime(new Timestamp(new Date().getTime()));
        this.insertOrUpdate(trainEntity);
        userService.insertOrUpdate(trainEntity.getUserEntity());
        return true;
    }
}
