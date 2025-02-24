package com.app.delicare.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static String convertDateToStringFormat(String patternFormat ){
        try {

            if (patternFormat == null || patternFormat.isEmpty()){
                patternFormat = "yyyy-mm-dd hh:mm:ss";
            }
            DateFormat dateFormat = new SimpleDateFormat(patternFormat);
            Date today= Calendar.getInstance().getTime();
            return dateFormat.format(today);
        }catch (Exception e){
            return null;
        }
    }
}
