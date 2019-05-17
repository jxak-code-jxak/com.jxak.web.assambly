package com.jxak.education.web;

import com.jxak.education.entity.Dictionary;
import com.jxak.education.service.DictionaryService;
import com.jxak.education.utils.ResponseT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
/** @Author liaoyuanjie
 * @Description //TODO 数据字典
 * @Date 16:39 2019/5/15
 * @Param 
 * @return 
 **/
@RestController
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;
    @GetMapping(value = "/getDictionaryKeyType/{keyType}")
    public ResponseT getDictionaryKeyType(@PathVariable String keyType){
        ResponseT responseT=new ResponseT<>();
        responseT.setData(dictionaryService.getDictionaryKeyType(keyType));
        return responseT;
    }
}
