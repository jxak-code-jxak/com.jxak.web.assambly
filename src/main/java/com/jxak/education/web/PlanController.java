package com.jxak.education.web;

import com.jxak.education.entity.PlanEntity;
import com.jxak.education.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PlanController {
    @Autowired
    PlanService planService;
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
}
