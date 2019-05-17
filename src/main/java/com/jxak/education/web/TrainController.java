package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.entity.TrainEntity;
import com.jxak.education.service.TrainService;
import com.jxak.education.service.UserService;
import com.jxak.education.utils.ResponseT;
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
    public ResponseT getTrainList(){
        ResponseT responseT =new ResponseT<>();
        responseT.setData(trainService.getTrainList());
        responseT.setMsg("");
        responseT.setCode("0");
        responseT.setCount(trainService.selectCount(new EntityWrapper<>()));
        return responseT;
    }
    @GetMapping(value = "/getTrainByDept/{deptCode}")
    public ResponseT getTrainByDept(@PathVariable String deptCode){
        ResponseT responseT =new ResponseT<>();
        responseT.setData(trainService.getTrainByDept(deptCode));
        responseT.setMsg("");
        responseT.setCount(trainService.selectCount(new EntityWrapper<>()));
        responseT.setCode("0");
        return responseT;
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
