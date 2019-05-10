package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.dao.TrainDao;
import com.jxak.education.entity.TrainEntity;
import com.jxak.education.entity.UserEntity;
import com.jxak.education.service.TrainService;
import com.jxak.education.service.UserService;
import com.jxak.education.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需求制度
 */
@RestController
public class TrainController {
    @Autowired
    TrainService trainService;
    @Autowired
    UserService userService;
    @GetMapping(value = "/getTrainList")
    public Map<String,Object> getTrainList(){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("data",trainService.getTrainList());
        objectMap.put("count",0);
        objectMap.put("msg","");
        objectMap.put("code",0);
        return objectMap;
    }
    @GetMapping(value = "/getTrainByDept/{deptCode}")
    public Map<String,Object> getTrainByDept(@PathVariable String deptCode){
        Map<String,Object> objectMap=new HashMap<>();
        List<TrainEntity> entities=trainService.getTrainByDept(deptCode);
        objectMap.put("data",entities);
        objectMap.put("count",entities.size());
        objectMap.put("msg","");
        objectMap.put("code",0);
        return objectMap;
    }
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/deleteTrainById/{id}")
    public Map<String,Object> deleteTrainById(@PathVariable String id){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("state",trainService.deleteById(id));
        return objectMap;
    }
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/saveTrain")
    public Map<String,Object> saveTrain(@RequestBody TrainEntity trainEntity){
        Map<String,Object> objectMap=new HashMap<>();
        UserEntity userEntity=new UserEntity();
        if (trainEntity.getId()==null||trainEntity.getId().equals("")){
            trainEntity.setId(GuidUtils.getCode());
            trainEntity.setUserId(GuidUtils.getCode());
        }
        trainEntity.setWriteTime(new Timestamp(new Date().getTime()));
        userEntity.setId(trainEntity.getUserId());
        userEntity.setName(trainEntity.getName());
        userEntity.setSex(trainEntity.getSex());
        userEntity.setHealthy(trainEntity.getHealthy());
        userEntity.setDeptCode(trainEntity.getDeptCode());
        userEntity.setWorkTime(trainEntity.getWorkTime());
        userService.insertOrUpdate(userEntity);
        trainService.insertOrUpdate(trainEntity);
        objectMap.put("state",true);
        return objectMap;
    }
}
