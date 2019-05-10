package com.jxak.education.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.entity.DeptEntity;
import com.jxak.education.entity.Menu;
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
    MenuService menuService;
    @GetMapping(value = "/getMenuTree")
    public Map<String,Object> getMenuTree(){
        Map<String,Object> objectMap =new HashMap<>();
        List<BaseTreeGrid> baseTreeGrids=new ArrayList<>();
        List<Menu> menus = menuService.selectList(new EntityWrapper<>());
        //组装树形结构
        for (int i=0;i<menus.size();i++){
            Menu menu =menus.get(i);
            BaseTreeGrid baseTreeGrid =new BaseTreeGrid();
            baseTreeGrid.setId(menu.getId());
            baseTreeGrid.setName(menu.getName());
            baseTreeGrid.setUrl(menu.getUrl());
            baseTreeGrid.setParentId(menu.getParentCode());
            baseTreeGrids.add(baseTreeGrid);
        }
        objectMap.put("data",baseTreeGrids);
        return objectMap;
    }
}
