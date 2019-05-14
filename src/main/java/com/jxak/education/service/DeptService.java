package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jxak.education.dao.DeptDao;
import com.jxak.education.entity.DeptEntity;
import com.jxak.education.utils.BaseTreeGrid;
import com.jxak.education.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptService {
    @Autowired
    DeptDao deptDao;
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
}
