package com.example.ainurbayanova.myappfortomorrow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Utils {
    public static String getData(long date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(date);
    }
    public static String getTime(long time){
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm");
        return timeFormat.format(time);
    }
}
