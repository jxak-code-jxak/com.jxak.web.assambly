package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.entity.ChangeEntity;
import com.jxak.education.entity.PlanEntity;
import com.jxak.education.service.ChangeService;
import com.jxak.education.service.PlanService;
import com.jxak.education.utils.ResponseT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PlanController {
    @Autowired
    PlanService planService;
    @Autowired
    ChangeService changeService;
    /**
     * 培训计划列表
     * @return
     */
    @GetMapping(value = "/getPlanList")
    public ResponseT<PlanEntity> getPlanListPage(@RequestParam int page, @RequestParam int limit){
        ResponseT<PlanEntity> responseT=new ResponseT<>();
        responseT.setData(planService.getPlanListPage(page,limit));
        responseT.setCount(planService.selectCount(new EntityWrapper<>()));
        responseT.setCode("0");
        responseT.setMsg("");
        return responseT;
    }
    @GetMapping(value = "/getChangeList")
    public ResponseT<ChangeEntity> getChangeListPage(@RequestParam int page, @RequestParam int limit){
        ResponseT<ChangeEntity> responseT=new ResponseT<>();
        responseT.setData(changeService.getChangeList(page,limit));
        responseT.setCount(changeService.selectCount(new EntityWrapper<>()));
        responseT.setCode("0");
        responseT.setMsg("");
        return responseT;
    }
    /**
     * 保存培训计划
     * @param planEntity
     * @return
     */
    @PostMapping(value = "/savePlan")
    public Map<String,Object> savePlan(@RequestBody PlanEntity planEntity){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("state",planService.savePlan(planEntity));
        return objectMap;
    }
    @PostMapping(value = "/deletePlanById/{id}")
    public Map<String,Object> deletePlanById(@PathVariable String id){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("state",planService.deletePlanById(id));
        return objectMap;
    }
}
