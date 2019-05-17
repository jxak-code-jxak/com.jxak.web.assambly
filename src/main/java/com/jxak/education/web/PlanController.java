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
import java.util.List;
import java.util.Map;

@RestController
public class PlanController {
    @Autowired
    PlanService planService;
    @Autowired
    ChangeService changeService;
    /**
    * @Description:    查询培训计划列表
    * @Author:         liaoyuanjie
    * @CreateDate:     2019/5/14 17:31
    * @UpdateUser:     liaoyuanjie
    * @UpdateDate:     2019/5/14 17:31
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @GetMapping(value = "/getPlanList")
    public ResponseT getPlanListPage(@RequestParam int page,int limit){
        ResponseT responseT=new ResponseT<>();
        responseT.setData(planService.getPlanListPage(page,limit));
        responseT.setCount(planService.selectCount(new EntityWrapper<>()));
        responseT.setCode("0");
        responseT.setMsg("");
        return responseT;
    }
    /**
    * @Description:    查询培训变更记录列表
    * @Author:         liaoyuanjie
    * @CreateDate:     2019/5/14 17:27
    * @UpdateUser:     liaoyuanjie
    * @UpdateDate:     2019/5/14 17:27
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
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
    * @Description:    保存培训计划
    * @Author:         liaoyuanjie
    * @CreateDate:     2019/5/14 17:27
    * @UpdateUser:     liaoyuanjie
    * @UpdateDate:     2019/5/14 17:27
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @PostMapping(value = "/savePlan")
    public Map<String,Object> savePlan(@RequestBody PlanEntity planEntity){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("state",planService.savePlan(planEntity));
        return objectMap;
    }
    /**
    * @Description:    根据id数组删除培训计划
    * @Author:         liaoyuanjie
    * @CreateDate:     2019/5/14 17:27
    * @UpdateUser:     liaoyuanjie
    * @UpdateDate:     2019/5/14 17:27
    * @UpdateRemark:   修改内容
    * @Version:        1.0
    */
    @PostMapping(value = "/deletePlanByIds")
    public Map<String,Object> deletePlanById(@RequestBody List<String> ids){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("state",planService.deletePlanByIds(ids));
        return objectMap;
    }
}
