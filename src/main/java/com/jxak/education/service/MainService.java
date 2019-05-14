package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.entity.Menu;
import com.jxak.education.utils.BaseTreeGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    @Autowired
    MenuService menuService;
    public List<BaseTreeGrid> getMenuTree(){
        List<BaseTreeGrid> baseTreeGrids=new ArrayList<>();        List<Menu> menus = menuService.selectList(new EntityWrapper<>());
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
        return baseTreeGrids;
    }
}
