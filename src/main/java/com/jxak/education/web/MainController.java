package com.jxak.education.web;


import com.jxak.education.service.MenuService;
import com.jxak.education.utils.BaseTreeGrid;
import com.jxak.education.utils.ResponseT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    MenuService menuService;

    /**
     * 获取左侧菜单树
     * @return
     */
    @GetMapping(value = "/getMenuTree")
    public ResponseT getMenuTree(){
        ResponseT response=new ResponseT<>();
        response.setData(menuService.getMenuTree());
        response.setMsg("");
        response.setCode("0");
        return response;
    }
}
