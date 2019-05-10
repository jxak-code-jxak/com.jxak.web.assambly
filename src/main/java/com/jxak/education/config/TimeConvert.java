package com.jxak.education.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
* @Description:     TIMESTAMP类型转换器
* @Author:         liaoyuanjie
* @CreateDate:     2019/5/10 10:56
* @UpdateUser:     liaoyuanjie
* @UpdateDate:     2019/5/10 10:56
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Component
public class TimeConvert implements Converter<String,Timestamp> {
    private static final List<String> formarts = new ArrayList<>(4);
    static{
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Timestamp convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if(source.matches("^\\d{4}-\\d{1,2}$")){
            return parseDate(source, formarts.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, formarts.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(2));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(3));
        }else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 格式化日期
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    public  Timestamp parseDate(String dateStr, String format) {
        Timestamp timestamp=null;
        try {
            timestamp = Timestamp.valueOf(dateStr);
        } catch (Exception e) {

        }
        return timestamp;
    }
}
