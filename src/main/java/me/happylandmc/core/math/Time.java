package me.happylandmc.core.math;

import java.util.Calendar;

public class Time {
    static Calendar calendar = Calendar.getInstance();
    public static int year = calendar.get(Calendar.YEAR);
    public static int month = calendar.get(Calendar.MONTH) + 1;
    public static int day = calendar.get(Calendar.DATE);
    public static int hour = calendar.get(Calendar.HOUR_OF_DAY);
    public static int minute = calendar.get(Calendar.MINUTE);
    public static int second = calendar.get(Calendar.SECOND);
    public static int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    public static int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    public static int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
}
