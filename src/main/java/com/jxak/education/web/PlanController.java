package com.jxak.education.web;

import com.jxak.education.entity.ChangeEntity;
import com.jxak.education.entity.PlanEntity;
import com.jxak.education.service.ChangeService;
import com.jxak.education.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public Map<String,Object> getPlanListPage(){
        Map<String,Object> objectMap=new HashMap<>();
        List<PlanEntity> planEntities =planService.getPlanListPage();
        objectMap.put("data",planEntities);
        objectMap.put("count",planEntities.size());
        objectMap.put("msg","");
        objectMap.put("code",0);
        return objectMap;
    }
    @GetMapping(value = "/getChangeList")
    public Map<String,Object> getChangeListPage(){
        Map<String,Object> objectMap=new HashMap<>();
        List<ChangeEntity> changeEntities=changeService.getChangeList();
        objectMap.put("data",changeEntities);
        objectMap.put("count",changeEntities.size());
        objectMap.put("code",0);
        objectMap.put("msg","");
        return objectMap;
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
