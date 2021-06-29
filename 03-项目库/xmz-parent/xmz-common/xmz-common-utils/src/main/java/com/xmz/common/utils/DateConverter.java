package com.xmz.common.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateConverter implements Converter<String,Date> {

    @Override
    public Date convert(String s) {
        //yyyy-MM-ddw
        //yyyy-MM-dd hh:mm
        //yyyy-MM-dd hh:mm:ss

        Matcher dateMac = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}").matcher(s);
        Matcher timeMac = Pattern.compile("\\d{2}:\\d{2}(:\\d{2})?$").matcher(s);

        StringBuilder format = new StringBuilder();
        StringBuilder text = new StringBuilder();

        if(dateMac.find(0)){
            format.append("yyyy-MM-dd");
            text.append(dateMac.group());
        }
        if(timeMac.find(0)){
            if(format.length()>0) {
                format.append(" ");
                text.append(" ");
            }
            format.append("hh:mm");
            text.append(timeMac.group());
            if(timeMac.group().length()>5) format.append(":ss");
        }
        try {
            return new SimpleDateFormat(format.toString()).parse(text.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

}
