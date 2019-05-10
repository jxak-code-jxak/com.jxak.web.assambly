package com.jxak.education.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.dao.DeptDao;
import com.jxak.education.entity.DeptEntity;
import com.jxak.education.utils.BaseTreeGrid;
import com.jxak.education.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeptController {
    @Autowired
    DeptDao deptDao;

    /**
     * 获取树
     *
     * @return
     */
    @GetMapping(value = "/dept/getDeptTree")
    public List<BaseTreeGrid> getDeptTree() {
        List<BaseTreeGrid> baseTreeGrids = new ArrayList<>();
        List<DeptEntity> deptEntities = deptDao.selectList(new EntityWrapper<>());
        //组装树形结构
        for (int i=0;i<deptEntities.size();i++){
            DeptEntity deptEntity =deptEntities.get(i);
            BaseTreeGrid baseTreeGrid =new BaseTreeGrid();
            baseTreeGrid.setId(deptEntity.getCode());
            baseTreeGrid.setName(deptEntity.getName());
            baseTreeGrid.setParentId(deptEntity.getParentCode());
            baseTreeGrids.add(baseTreeGrid);
        }
        return TreeUtils.formatTree(baseTreeGrids,false);
    }
//    @GetMapping(value = "/dept/getUserTree")
//    public BaseTreeGrid getUserTree() {
//        String str ="";
//        return JSON.parse(str);
//    }
}
