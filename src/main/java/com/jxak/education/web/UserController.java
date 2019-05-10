package com.jxak.education.web;

import com.jxak.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 根据部门编码,查询当前部门下的所有人员
     * @param deptCode
     * @return
     */
    @GetMapping(value = "/getUserByDeptCode/{deptCode}")
    public Map<String, Object> getUserByDeptCode(@PathVariable String deptCode){
        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("data",userService.getDeptUser(deptCode));
        return objectMap;
    }
}
