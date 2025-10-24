package com.seeho.service.test.service.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LocalDateTimeUtils {


    /**
     * 年月日时分秒格式 YYYY_MM_DD_HH_MM_SS
     */
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日格式
     */
    public static final String yyyyMMdd = "yyyy-MM-dd";


    //获取当前时间的LocalDateTime对象
    // LocalDateTime.now();


    /**
     * Date转换为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Date str 转换为LocalDateTime 默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDateToLDT(String date) {
        DateTimeFormatter df = DateTimeFormatterPool.getDateFormat(yyyyMMddHHmmss);
        return LocalDateTime.parse(date, df);
    }

    /**
     * Date str 转换为LocalDateTime
     *
     * @param date    时间
     * @param pattern 格式
     * @return
     */
    public static LocalDateTime convertDateToLDT(String date, String pattern) {
        DateTimeFormatter df = DateTimeFormatterPool.getDateFormat(pattern);
        return LocalDateTime.parse(date, df);
    }


    /**
     * LocalDateTime  转换为 Date str 默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime
     * @return
     */
    public static String convertLDTToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatterPool.getDateFormat(yyyyMMddHHmmss);
        return localDateTime.format(formatter);
    }

    /**
     * LocalDateTime  转换为 Date str
     *
     * @param localDateTime 时间
     * @param pattern       格式
     * @return
     */
    public static String convertLDTToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatterPool.getDateFormat(pattern);
        return localDateTime.format(formatter);
    }


    /**
     * 获取指定时间的指定格式
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatterPool.getDateFormat(pattern));
    }

    /**
     * @param pattern 格式
     * @return
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String now() {
        return formatTime(LocalDateTime.now(), yyyyMMddHHmmss);
    }


    /**
     * LocalDateTime转换为Date
     *
     * @param time
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取一天的开始时间，2017,7,22 00:00
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取一天的开始时间，2017,7,22 00:00
     *
     * @param date
     * @return
     */
    public static LocalDateTime getDayStart(Date date) {
        return convertDateToLDT(date).withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    /**
     * 获取一天的结束时间，2017,7,22 23:59:59
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59);
    }


    /**
     * 获取一天的结束时间，2017,7,22 23:59:59
     *
     * @param date
     * @return
     */
    public static LocalDateTime getDayEnd(Date date) {
        return convertDateToLDT(date).withHour(23)
                .withMinute(59)
                .withSecond(59);
    }

    /**
     * 获取两个日期有几天
     * 这通过将秒数除以 86400 返回持续时间的总天数。这是基于一天的标准定义为 24 小时
     *
     * @param start
     * @param end
     * @return
     */
    public static long betweenDays(LocalDateTime start, LocalDateTime end) {
        return between(start, end).toDays();
    }

    /**
     * Foo:
     * Duration duration = Duration.between(now,end);
     * long days = duration.toDays(); //相差的天数
     * long hours = duration.toHours();//相差的小时数
     * long minutes = duration.toMinutes();//相差的分钟数
     * long millis = duration.toMillis();//相差毫秒数
     * long nanos = duration.toNanos();//相差的纳秒数
     * <p>
     * https://blog.csdn.net/weixin_39989962/article/details/109162968
     *
     * @param start
     * @param end
     * @return
     */
    public static Duration between(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end);
    }

    public void betweenDaysGeneralized(LocalDateTime start, LocalDateTime end) {

    }


    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) return period.getYears();
        if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
        return field.between(startTime, endTime);
    }


    /**
     * 获取某天下一天的0点0分0秒
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getSomeDayNextDayStart(LocalDateTime dateTime) {
        LocalDateTime nextDay = dateTime.plusDays(1);
        return getDayStart(nextDay);
    }

    /**
     * 获取某天加上几天的的0点0分0秒
     *
     * @param dateTime
     * @param plusDay
     * @return
     */
    public static LocalDateTime getSomeDayPlusDayStart(LocalDateTime dateTime, long plusDay) {
        LocalDateTime nextDay = dateTime.plusDays(plusDay);
        return getDayStart(nextDay);
    }

    /**
     * 获取某天加上几天的的0点0分0秒
     *
     * @param date
     * @param plusDay
     * @return
     */
    public static LocalDateTime getSomeDayPlusDayStart(Date date, long plusDay) {
        LocalDateTime nextDay = convertDateToLDT(date).plusDays(plusDay);
        return getDayStart(nextDay);
    }

    /**
     * 获取某天下一天的23点59分59秒
     *
     * @param dateTime
     * @return
     */
    public static LocalDateTime getSomeDayNextDayEnd(LocalDateTime dateTime) {
        LocalDateTime nextDay = dateTime.plusDays(1);
        return getDayEnd(nextDay);
    }

    /**
     * 获取某天加上几天的的23点59分59秒
     *
     * @param dateTime
     * @param plusDay
     * @return
     */
    public static LocalDateTime getSomeDayPlusDayEnd(LocalDateTime dateTime, long plusDay) {
        LocalDateTime nextDay = dateTime.plusDays(plusDay);
        return getDayEnd(nextDay);
    }

    /**
     * 获取某天加上几天的的23点59分59秒
     *
     * @param date
     * @param plusDay
     * @return
     */
    public static LocalDateTime getSomeDayPlusDayEnd(Date date, long plusDay) {
        LocalDateTime nextDay = convertDateToLDT(date).plusDays(plusDay);
        return getDayEnd(nextDay);
    }

    public static class DateTimeFormatterPool {
        public static final Map<String, ThreadLocal<DateTimeFormatter>> DATE_TIME_FORMAT_POOL = new HashMap<String, ThreadLocal<DateTimeFormatter>>();

        static DateTimeFormatter getDateFormat(String format) {
            ThreadLocal<DateTimeFormatter> threadLocal = DATE_TIME_FORMAT_POOL.get(format);
            if (threadLocal == null)
                threadLocal = initThreadLocal(format);
            return threadLocal.get();
        }

        private static synchronized ThreadLocal<DateTimeFormatter> initThreadLocal(final String format) {
            ThreadLocal<DateTimeFormatter> threadLocal = DATE_TIME_FORMAT_POOL.get(format);
            if (threadLocal == null) {
                threadLocal = new ThreadLocal<DateTimeFormatter>() {
                    @Override
                    protected DateTimeFormatter initialValue() {
                        return DateTimeFormatter.ofPattern(format);
                    }
                };
                DATE_TIME_FORMAT_POOL.put(format, threadLocal);
            }
            return threadLocal;
        }
    }

}
