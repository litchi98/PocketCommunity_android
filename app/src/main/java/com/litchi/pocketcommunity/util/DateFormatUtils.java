package com.litchi.pocketcommunity.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatUtils {

    public static String format(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(date);
    }
}
