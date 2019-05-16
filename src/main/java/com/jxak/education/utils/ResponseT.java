package com.jxak.education.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class ResponseT<T> {
    private String code;//返回状态编码
    private List<T> data;//返回数据
    private String msg;//返回消息
    private Integer count;//返回数据条数
    private boolean state;//返回状态
}
