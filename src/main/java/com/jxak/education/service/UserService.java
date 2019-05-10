package com.jxak.education.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.UserDao;
import com.jxak.education.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService extends ServiceImpl<UserDao,UserEntity> {
    @Autowired
    UserDao userDao;

    public List<UserEntity> getDeptUser(String deptCode){
        String str =new StringBuilder().append(deptCode).append("%").toString();
        return userDao.getDeptUser(str);
    }
}
