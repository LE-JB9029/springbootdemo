package com.demo.common.util;

import jodd.util.StringUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {

    /**
     * 获取当前时间
     *
     * @return Date
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 获取当前时间字符串
     *
     * @param reg 指定格式
     * @return String
     */
    public static String getNowString(String reg) {
        if (StringUtil.isBlank(reg))
            reg = "yyyy-MM-dd HH:mm:ss";
        return new SimpleDateFormat(reg).format(new Date());
    }

    /**
     * 将日期字符串按照指定格式转成日期
     *
     * @param dateStr 日期字符串
     * @param reg     指定格式
     * @return Date
     */
    public static Date toDate(String dateStr, String reg) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        try {
            return new SimpleDateFormat(reg).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期按照指定格式转成日期字符串
     *
     * @param date 日期
     * @param reg  指定格式
     * @return String
     */
    public static String dateToString(Date date, String reg) {
        if (date == null)
            return "";
        if (StringUtil.isBlank(reg))
            reg = "yyyy-MM-dd";
        return new SimpleDateFormat(reg).format(date);
    }

    /**
     * 向前或向后推移指定年份数
     *
     * @param date  日期
     * @param years 年份数
     * @return Date
     */
    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 向前或向后推移指定月份数
     *
     * @param date   日期
     * @param months 月份数
     * @return Date
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 向前或向后推移指定天数
     *
     * @param date 日期
     * @param days 天数
     * @return Date
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 向前或向后推移指定小时数
     *
     * @param date  日期
     * @param hours 小时数
     * @return Date
     */
    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

    /**
     * 向前或向后推移指定分钟数
     *
     * @param date    日期
     * @param minutes 分钟数
     * @return Date
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    /**
     * 获得指定日期的月初时间
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getMonthStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期的月末时间
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getMonthEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获得指定日期的当日起始时间
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getDayStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得指定日期的当日结束时间
     *
     * @param date 指定日期
     * @return Date
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获得指定日期的星期字符串
     *
     * @param date 指定日期
     * @return String
     */
    public static String getWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return "星期" + NumberUtil.toLowerChinese(day - 1);
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (day != 0)
            return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        else if (hour != 0)
            return hour + "小时" + min + "分" + sec + "秒";
        else if (min != 0)
            return min + "分" + sec + "秒";
        else if (sec != 0)
            return sec + "秒";
        else
            return "0秒";
    }

    /**
     * 格式化秒
     *
     * @param duration 单位秒
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistinceTime(Long duration) {
        long diff = duration * 1000;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0)
            return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        else if (hour != 0)
            return hour + "小时" + min + "分" + sec + "秒";
        else if (min != 0)
            return min + "分" + sec + "秒";
        else if (sec != 0)
            return sec + "秒";
        else
            return "0秒";
    }

    /**
     * 两个时间相差多少秒
     *
     * @param one 时间参数 1 格式：1990-01-01 12:00:00
     * @param two 时间参数 2 格式：2009-01-01 12:00:00
     * @return long
     */
    public static long getDistanceTime(Date one, Date two) {
        long sec = 0;
        try {

            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            sec = ((diff / 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sec;
    }

    /**
     * 获得两日期间隔的日期字符串集合
     *
     * @param dBegin 起始日期
     * @param dEnd   结束日期
     * @return List<String>
     */
    public static List<String> findDates(Date dBegin, Date dEnd) {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        lDate.add(df.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(df.format(calBegin.getTime()));
        }
        return lDate;
    }

    /**
     * 根据开始结束时间以及间隔时间返回时刻字符串集合
     *
     * @param start     开始时间
     * @param end       结束时间
     * @param reg       返回字符串format
     * @param split_int 时间间隔，单位：min,split_int>=5,split_int必须为5的倍数
     * @return List<String>
     */
    public static List<String> getTimesByStartEndAndSplit(Date start, Date end, String reg, int split_int) {
        // if(split_int<5 || split_int%5!=0) return null;
        if (split_int == 0)
            return null;
        List<String> timeList = new ArrayList<String>();
        start = getNearestMin(start, split_int);
        Long second = getDistanceTime(start, end);
        int second_int = second.intValue();
        int size = second_int % (60 * split_int) == 0 ? second_int / (60 * split_int)
                : (second_int / (60 * split_int) + 1);
        timeList.add(dateToString(start, reg));
        for (int i = 1; i <= size; i++) {
            Date time_temp = new Date(start.getTime() + i * split_int * 60 * 1000);
            timeList.add(dateToString(time_temp, reg));
        }
        return timeList;
    }

    /**
     * 获得最近的能被split_int整除的时间
     *
     * @param date      指定日期
     * @param split_int 时间间隔，单位：min
     * @return Date
     */
    public static Date getNearestMin(Date date, int split_int) {
        long timemisecond = date.getTime();
        long split_minlong = split_int * 60 * 1000;
        long leftmisecond = timemisecond % split_minlong;
        if (leftmisecond == 0)
            return new Date(timemisecond);
        timemisecond = timemisecond + (split_minlong - leftmisecond);
        return new Date(timemisecond);
    }

    /**
     * 获取date后，时间间隔最近的时刻
     *
     * @param date      指定时刻
     * @param split_min 时间间隔
     * @return
     */
    public static Date getAfterTimeBySplit(Date date, int split_min) {
        String dateStr = dateToString(date, "yyyy-MM-dd");
        Long time = date.getTime();
        Long split_long = (long) split_min * 60 * 1000;
        long mod = time % split_long;
        if (mod == 0)
            return date;
        Date newDate = new Date(time + (split_long - mod));
        String newDateStr = dateToString(newDate, "yyyy-MM-dd");
        if (newDateStr.equals(dateStr))
            return newDate;
        Date beforeDate = new Date(time - mod);
        return beforeDate;
    }

    /**
     * 获取最接近的分钟为5的倍数的时间(向以前推) 余数小于2分钟再向前推5分钟
     *
     * @return Date
     */
    public static Date getLast5minutesTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int minute = cal.get(Calendar.MINUTE);
        if (minute % 5 <= 2)
            cal.set(Calendar.MINUTE, minute - minute % 5 - 5);
        else
            cal.set(Calendar.MINUTE, minute - minute % 5);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
