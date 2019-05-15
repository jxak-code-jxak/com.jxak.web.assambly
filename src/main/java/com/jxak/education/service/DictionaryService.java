package com.jxak.education.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jxak.education.dao.DictionaryDao;
import com.jxak.education.entity.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

/** @Author liaoyuanjie
 * @Description //TODO 数据字典服务层
 * @Date 16:24 2019/5/15
 * @Param
 * @return
 **/
@Service
public class DictionaryService extends ServiceImpl<DictionaryDao,Dictionary> {
    /** @Author liaoyuanjie
     * @Description //TODO 根据数据字段类型编码查询数据
     * @Date 16:30 2019/5/15
     * @Param
     * @return
     **/
    public List<Dictionary> getDictionaryKeyType(String keyType){
        return this.selectList(new EntityWrapper<Dictionary>().eq("key_type",keyType));
    }
}
