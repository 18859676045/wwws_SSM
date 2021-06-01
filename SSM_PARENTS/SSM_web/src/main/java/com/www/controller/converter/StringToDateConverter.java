package com.www.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date>{


    @Override
    public Date convert(String source) {
        if (source==null){
            throw new RuntimeException("参数不能为空");
        }

        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = df.parse(source);
            return  date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("输入的格式有错");
        }
    }
}
