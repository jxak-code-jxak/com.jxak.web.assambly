package com.jxak.education.web;


import com.jxak.education.service.DeptService;
import com.jxak.education.utils.BaseTreeGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    /**
     * 获取树
     *
     * @return
     */
    @GetMapping(value = "/dept/getDeptTree")
    public List<BaseTreeGrid> getDeptTree() {
        return deptService.getDeptTree();
    }
}
