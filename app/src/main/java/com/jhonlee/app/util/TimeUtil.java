package com.jhonlee.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JhoneLee on 2017/2/7.
 */

public class TimeUtil {

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String time = format.format(date);
        return time;
    }
}
