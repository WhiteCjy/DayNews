package com.group.daynews.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtil {

    /**
     * 格式化传入的毫秒值类型的时间
     *
     * @param t
     * @return
     */
    public static String formatTime(long t) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = format.format(new Date(t));
        return date;
    }

    public static String format2Time(long t) {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String date = format.format(new Date(t));
        return date;
    }
}
