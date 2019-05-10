package com.jxak.education.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jxak.education.entity.TrainEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainDao extends BaseMapper<TrainEntity> {
    @Select("select t2.name,t2.sex,t2.work_time as workTime,t2.healthy,t1.id,t1.user_id as userId,t1.performance,t1.skilled,t1.train,t1.write_time as writeTime from train_info t1 left join user_info t2 on t1.user_id=t2.id order by t1.id desc")
    List<TrainEntity> getTrainList();
    @Select("select t2.name,t2.sex,t2.work_time as workTime,t2.healthy,t1.id,t1.user_id as userId,t1.performance,t1.skilled,t1.train,t1.write_time as writeTime from train_info t1 left join user_info t2 on t1.user_id=t2.id where t2.dept_code like #{deptCode} order by t1.id desc")
    List<TrainEntity> getTrainByDept(@Param("deptCode")String deptCode);
}
