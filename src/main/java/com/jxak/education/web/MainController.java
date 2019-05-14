package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.entity.DeptEntity;
import com.jxak.education.entity.Menu;
import com.jxak.education.service.MainService;
import com.jxak.education.service.MenuService;
import com.jxak.education.utils.BaseTreeGrid;
import com.sun.prism.impl.BaseGraphics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    MainService mainService;

    /**
     * 获取左侧菜单树
     * @return
     */
    @GetMapping(value = "/getMenuTree")
    public Map<String,Object> getMenuTree(){
        Map<String,Object> objectMap =new HashMap<>();
        objectMap.put("data",mainService.getMenuTree());
        return objectMap;
    }
}
