package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.entity.FyTz;
import com.jxak.education.service.FyTzService;
import com.jxak.education.utils.ResponseT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FyTzController {
    @Autowired
    FyTzService fyTzService;

    /**
     * 查询费用台账
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/getFyTzPage")
    public ResponseT getFyTzPage(@RequestParam int page,int limit){
        ResponseT responseT=new ResponseT();
        responseT.setData(fyTzService.getFyTzList(page,limit));
        responseT.setCount(fyTzService.selectCount(new EntityWrapper<>()));
        responseT.setCode("0");
        responseT.setMsg("");
        return responseT;
    }

    /**
     * 保存费用台账
     * @param fyTz
     * @return
     */
    @PostMapping(value = "/saveFyTz")
    public ResponseT saveFyTz(@RequestBody FyTz fyTz){
        ResponseT responseT=new ResponseT();
        responseT.setState(fyTzService.saveFyTz(fyTz));
        return responseT;
    }
    @PostMapping(value = "/deleteFyTzByIds")
    public ResponseT deleteFyTz(@RequestBody List<String> ids){
        ResponseT responseT=new ResponseT();
        responseT.setState(fyTzService.deleteFyTz(ids));
        return responseT;
    }
}
