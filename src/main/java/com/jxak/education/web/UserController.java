package com.jxak.education.web;


import com.jxak.education.entity.UserEntity;
import com.jxak.education.service.UserService;
import com.jxak.education.utils.ResponseT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseT<UserEntity> getUserByDeptCode(@PathVariable String deptCode){
        ResponseT<UserEntity> response=new ResponseT<>();
        response.setData(userService.getDeptUser(deptCode));
        response.setCode("0");
        response.setMsg("");
        return response;
    }
}
