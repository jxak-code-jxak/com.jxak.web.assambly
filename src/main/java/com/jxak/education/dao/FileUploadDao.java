package com.jxak.education.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jxak.education.entity.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadDao extends BaseMapper<Attachment> {

}
