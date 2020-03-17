package com.lang.blog.component;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringToDateFormatter implements Formatter<Date> {
    // 日期格式化对象
    private SimpleDateFormat dateFormat;

    public StringToDateFormatter(SimpleDateFormat format) {
        this.dateFormat = format;
    }

    public Date parse(String source, Locale locale) throws ParseException {
        try {
            return dateFormat.parse(source);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    public String print(Date date, Locale locale) {
        return this.dateFormat.format(date);
    }
}
