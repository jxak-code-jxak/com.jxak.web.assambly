package com.jxak.education.web;

import com.jxak.education.entity.TrainEntity;
import com.jxak.education.service.TrainService;
import com.jxak.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping(value = "/deleteTrainById/{id}")
    public Map<String,Object> deleteTrainById(@PathVariable String id){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("state",trainService.deleteById(id));
        return objectMap;
    }
    @PostMapping(value = "/saveTrain")
    public Map<String,Object> saveTrain(@RequestBody TrainEntity trainEntity){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("state",trainService.saveTrain(trainEntity));
        return objectMap;
    }
}
