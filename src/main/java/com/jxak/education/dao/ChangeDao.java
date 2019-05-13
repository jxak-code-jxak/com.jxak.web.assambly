package com.jxak.education.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jxak.education.entity.ChangeEntity;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeDao extends BaseMapper<ChangeEntity> {

    List<ChangeEntity> getChangeList();
}
