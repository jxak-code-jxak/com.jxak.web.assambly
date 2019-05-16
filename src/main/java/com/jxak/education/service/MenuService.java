package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.MenuDao;
import com.jxak.education.entity.Menu;
import com.jxak.education.utils.BaseTreeGrid;
import com.jxak.education.utils.TreeUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService extends ServiceImpl<MenuDao, Menu> {
    /**
     * @Description:    查询系统菜单树
     * @Author:         liaoyuanjie
     * @CreateDate:     2019/5/14 17:23
     * @UpdateUser:     liaoyuanjie
     * @UpdateDate:     2019/5/14 17:23
     * @UpdateRemark:   修改内容
     * @Version:        1.0
     */
    public List<BaseTreeGrid> getMenuTree(){
        List<BaseTreeGrid> baseTreeGrids=new ArrayList<>();
        List<Menu> menus = this.selectList(new EntityWrapper<>());
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
        return TreeUtils.formatTree(baseTreeGrids,false);
    }

}
