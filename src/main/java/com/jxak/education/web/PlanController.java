package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
    public Map<String,Object> getPlanListPage(@RequestParam int page,@RequestParam int limit){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("data",planService.getPlanListPage(page,limit));
        objectMap.put("count",planService.selectCount(new EntityWrapper<>()));
        objectMap.put("msg","");
        objectMap.put("code",0);
        return objectMap;
    }
    @GetMapping(value = "/getChangeList")
    public Map<String,Object> getChangeListPage(){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("data",changeService.getChangeList());
        objectMap.put("count",changeService.selectCount(new EntityWrapper<>()));
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
