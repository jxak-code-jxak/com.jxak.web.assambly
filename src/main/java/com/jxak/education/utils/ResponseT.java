package com.jxak.education.utils;

import lombok.Data;

import java.util.List;
/**
* @Description:    返回结果响应
* @Author:         liaoyuanjie
* @CreateDate:     2019/5/14 17:00
* @UpdateUser:     liaoyuanjie
* @UpdateDate:     2019/5/14 17:00
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Data
public class ResponseT<T> {
    private String code;
    private List<T> data;
    private String msg;
    private Integer count;
}
