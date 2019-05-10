package com.jxak.education.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jxak.education.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<UserEntity> {
    /**
     * 查询当前部门下的人员
     * @return
     */
    @Select("select * from user_info t1 where t1.dept_code like #{deptCode}")
    List<UserEntity> getDeptUser(@Param("deptCode")String deptCode);
}
