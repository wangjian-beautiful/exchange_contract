package com.bijinsuo.common.utils;

import io.netty.util.internal.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nike
 * @date 2022年12月01日 14:06
 */
public class DateUtils {
    /**
     * 英文简写（默认）如：2010-12-01
     */

    public static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文最简写（默认）如：20101201
     */

    public static String FORMAT_MORE_SHORT = "yyyyMMdd";

    /**
     * 英文全称 如：22016-08-9T10:01:54Z
     */

    public static String FORMAT_LONG_UTC = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * 英文全称 如：2010-12-01 23:15:06
     */

    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 英文全称 如：2010-12-01 23:15
     */

    public static String FORMAT_CAR_DATE = "yyyy-MM-dd HH:mm";

    public static String FORMAT_MINUTE= "yyyyMMddHHmm";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */

    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写 如：2010年12月01日
     */

    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */

    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间 yyyy年MM月dd日 HH时mm分ss秒SSS毫秒
     */

    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * 精确到秒的完整中文最简时间 yyyyMMddHHmmss
     */
    public static String FORMAT_FULL_SIMPLE = "yyyyMMddHHmmss";

    /**
     * 精确到秒的完整中文最简时间 HHmmss 如：100101
     */
    public static String FORMAT_TIME_FULL = "HHmmss";

    /**
     * 精确到毫秒的完整中文最简时间 如：20150304230516221
     */
    public static String FORMAT_TIME_FULL_MILLI = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式正则表达式
     */
    public static String DATA_PATTERN = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";

    /**
     * 短格式日期变长格式日期附加字符串，用于补充开始查询时间
     */
    public static String ADDTIONAL_STR_BEGAIN = "2000-01-01 00:00:00";

    /**
     * 短格式日期变长格式日期附加字符串，用于补充结束查询时间
     */
    public static String ADDTIONAL_STR_END = "2000-01-01 23:59:59";

    /**
     * 获得默认的 date pattern
     */

    public static String getDatePattern() {
        return FORMAT_LONG;

    }

    /**
     * 获得默认的 date pattern
     */

    public static String getCarDatePattern() {
        return FORMAT_CAR_DATE;

    }

    /**
     * <p>
     * 功能描述:[方法功能中文描述]
     * </p>
     *
     * @return
     */
    public static Date getDate() {
        return parse(getNow());
    }

    public static Date getDate(String format) {
        return parse(getNow(), format);
    }

    /**
     * 根据预设格式返回当前日期
     *
     * @return
     */
    public static String getNow() {
        return format(new Date());

    }

    public static Date getMonthBeginTime(Date time) {
        return parseDate(format(time, "yyyy-MM-01 00:00:00"));
    }

    public static Date getDayBeginTime(Date time) {
        return parseDate(format(time, "yyyy-MM-dd 00:00:00"));
    }


    public static Long convertDate(String strDate) {

        try {
            String fmt = "yyyy-MM-dd HH:mm:ss";
            strDate = strDate.replace("T", " ");
            strDate = strDate.replace("Z", "");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(fmt);

            return  parse(dateTimeFormatter.format(dateTimeFormatter.parse(strDate))).getTime();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户格式返回当前日期
     *
     * @param format
     * @return
     */
    public static String getNow(String format) {
        return format(new Date(), format);

    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */

    public static String format(Date date) {

        return format(date, getDatePattern());

    }

    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return
     */

    public static String carDateFormat(Date date) {

        return format(date, getCarDatePattern());

    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

//"yyyy-MM-dd HH:mm:ss"

    /**
     * 日期字符串转date
     * @param dateTime
     * @param pattern
     * @return
     */
    public static Date StringToDate(String dateTime,String pattern){
        //创建SimpleDateFormat对象实例并定义好转换格式
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static Date stampDate(Long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Date(s);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parseDate(String strDate) {
        return parse(strDate, getCarDatePattern());
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        if (StringUtil.isNullOrEmpty(strDate)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parseUTC(String strDate) {
        if (StringUtil.isNullOrEmpty(strDate)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_LONG_UTC);
        try {
            return df.parse(strDate.replace("Z", " UTC"));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    public static String addDay(Date date, int i, String format) {
        Date result = addDay(date, i);
        return format(result, format);
    }

    /**
     * 在当前日期上添加小时数
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, Double hour) {
        Long time = date.getTime();
        Double times = time + hour * 60 * 60 * 1000;
        return new Date(Math.round(times));
    }

    public static Date subtractionHour(Date date, Double hour) {
        Long time = date.getTime();
        Double times = time - hour * 60 * 60 * 1000;
        return new Date(Math.round(times));
    }

    /**
     * 在当前日期上添加分钟数
     *
     * @param date
     * @param
     * @return
     */
    public static Date addMinute(Date date, Double mins) {
        Long time = date.getTime();
        Double times = time + mins * 60 * 1000;
        return new Date(Math.round(times));
    }

    /**
     * 在当前日期上添加分钟数
     *
     * @param date
     * @param
     * @return
     */
    public static Date addMinute(Date date, int mins) {
        Long time = date.getTime();
        Double times = time + Double.valueOf(mins) * 60 * 1000;
        return new Date(Math.round(times));
    }

    public static Date addSecond(Date date,int second){
        long time = date.getTime();
        double times = time + (double) second *  1000;
        return new Date(Math.round(times));
    }



    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 获取yyyy-MM-dd格式日期
     *
     * @param date
     * @return
     */
    public static String getShortDay(Date date) {
        return format(date, FORMAT_SHORT);
    }

    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) ((t / 1000 - t1 / 1000) / 3600 / 24);
    }

    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) ((t / 1000 - t1 / 1000) / 3600 / 24);
    }

    /**
     * 计算两个日期的间隔天数
     *
     * @return
     */
    public static int countDays(Date start, Date end) {
        long t1 = start.getTime();
        long t2 = end.getTime();
        return (int) ((t2 / 1000 - t1 / 1000) / 3600 / 24);
    }

    /**
     * 计算两个日期格式为yyyy-MM-dd HH:mm:ss的月份数
     *
     * @param start
     * @param end
     * @return
     */
    public static int countMonth(String start, String end) {
        int month = 0;
        int year = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(parse(start));
        cal2.setTime(parse(end));
        if (cal1.after(cal2)) {
            Calendar temp = cal1;
            cal1 = cal2;
            cal2 = temp;
        }
        year = Math.abs(cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR));
        month = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        return year * 12 + month;
    }

    /**
     * 计算两个格式为yyyy-MM-dd日期的月份数
     *
     * @param start
     * @param end
     * @return
     */
    public static int countMonth(Date start, Date end) {
        int month = 0;
        int year = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(start);
        cal2.setTime(end);
        if (cal1.after(cal2)) {
            Calendar temp = cal1;
            cal1 = cal2;
            cal2 = temp;
        }
        year = Math.abs(cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR));
        month = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
        return year * 12 + month;
    }

    /**
     * 忽略年份和月份，只计算天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int countDaysIngoreMonthAndYear(String start, String end) {
        int result = 0;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(DateUtils.parse(start));
        cal2.setTime(DateUtils.parse(end));
        result = cal2.get(Calendar.DATE) - cal1.get(Calendar.DATE);
        return result;
    }

    /**
     * 计算两个日期间隔天数
     *
     * @param start  开始日期
     * @param end    结束日期
     * @param isCeil 是否向上取整
     */
    public static int countDays(Date start, Date end, boolean isCeil) {
        if (!isCeil) {// 不满一天则不计算在内
            return countDays(start, end);
        }
        int mins = countMins(start, end);
        double hours = mins / 60.0 / 24;// 算出double类型的小时数
        int intHours = (int) hours;
        if (hours > intHours) {
            intHours++;
        }
        return intHours;
    }

    /**
     * 计算间隔小时数 -带半小时(不足半小时按照半小时 超过半小时按照一个小时算)
     *
     * @return
     */
    public static double countHour(Date start, Date end) {
        long t1 = start.getTime();
        long t2 = end.getTime();
        int hour = (int) ((t2 / 1000 - t1 / 1000) / 3600);
        double halfHour = 0;
        if ((countMins(start, end) - hour * 60) > 30) {
            halfHour = 1;
        } else if ((countMins(start, end) - hour * 60) <= 30 && (countMins(start, end) - hour * 60) > 0) {
            halfHour = 0.5;
        }
        return hour + halfHour;
    }

    /**
     * 计算两个日期的间隔分钟数
     *
     * @return
     */
    public static int countMins(Date start, Date end) {
        start = parse(format(start, FORMAT_CAR_DATE) + ":00", FORMAT_LONG);
        end = parse(format(end, FORMAT_CAR_DATE) + ":00", FORMAT_LONG);
        long t1 = start.getTime();
        long t2 = end.getTime();
        return (int) ((t2 - t1) / 1000 / 60);
    }

    /**
     * 计算两个日期的间隔秒数
     *
     * @return
     */
    public static int countSecond(Date start, Date end) {
        long t1 = start.getTime();
        long t2 = end.getTime();
        return ((int) ((t2 - t1) / 1000 / 60) - 4)*60;
    }
    /**
     * 得到昨天0点
     */
    public static Date getYester0Date() {
        return parse(format(addDay(new Date(), -1), FORMAT_SHORT), FORMAT_SHORT);
    }

    /**
     * 得到昨天24点
     */
    public static Date getYester24Date() {
        return parse(format(new Date(), FORMAT_SHORT), FORMAT_SHORT);
    }

    /**
     * 判断是否是长短租
     *
     * @param startDate
     * @param enDate
     * @return
     */
    public static boolean isMoreThenMonth(Date startDate, Date enDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.MONTH, 1);
        if (enDate.getTime() - c.getTimeInMillis() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算两个日期之间的时长
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String countDateLength(Date endDate, Date startDate) {

        long diff = endDate.getTime() - startDate.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
        String hireTimeStr = days + "天" + hours + "小时" + minutes + "分";
        return hireTimeStr;
    }

    public static String countDateLength(String endDate, String startDate, String pattern) {
        return countDateLength(parse(endDate, pattern), parse(startDate, pattern));
    }

    /**
     * 根据分钟得到小时数，不足一小时按一小时
     *
     * @param minute 分钟数
     * @param isCeil 是否自动填满
     * @return
     */
    public static int countHours(int minute, boolean isCeil) {
        int hours = 0;
        if (minute == 0) {
            return hours;
        }
        if (isCeil) {
            if (minute % 60 == 0) {
                hours = minute / 60;
            } else {
                hours = minute / 60 + 1;
            }
        } else {
            hours = minute / 60;
        }
        return hours;
    }

    /**
     * 根据分钟得到天数，不足一天按一天算
     *
     * @param minute 分钟数
     * @return
     */
    public static int countDays(int minute) {
        int days = 0;
        if (minute == 0) {
            return days;
        }
        if (minute % 1440 == 0) {
            days = minute / 1440;
        } else {
            days = minute / 1440 + 1;
        }
        return days;
    }

    /**
     * 设置时间的秒为0
     *
     * @param
     * @return
     */
    public static Date setSecondZero(Date date) {
        String time = format(date, "yyyyMMddHHmm");
        date = parse(time, "yyyyMMddHHmm");
        return date;
    }

    /**
     * 验证字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDateFormat(String strDate) {
        if (strDate == null)
            return Boolean.FALSE;
        Pattern pattern = Pattern.compile(DATA_PATTERN);
        Matcher matcher = pattern.matcher(strDate);
        return matcher.matches();
    }

    /**
     * 验证字符串是否为日期格式
     *
     * @param
     * @return
     */
    public static boolean isDateTimeFormat(String dateTime, String patternStr) {
        if (StringUtil.isNullOrEmpty(dateTime) || StringUtil.isNullOrEmpty(patternStr))
            return Boolean.FALSE;

        SimpleDateFormat dfs = new SimpleDateFormat(patternStr);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
            dfs.setLenient(false);
            dfs.parse(dateTime);
            return Boolean.TRUE;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return Boolean.FALSE;
        }
    }

    /**
     * 获取生成长格式日期字符串，用于日期型数据的查询条件
     *
     * @param strDate       日期格式字符串，支持yyyy-MM-dd、....、yyyy-MM-dd HH:mm:ss之时的格式
     * @param additionalStr 用于补充strDate以满足 yyyy-MM-dd HH:mm:ss 格式<br/> 详见ADDTIONAL_STR_BEGAIN，ADDTIONAL_STR_END
     * @return
     */
    public static String getLongFormatDateStr(String strDate, String additionalStr) {
        // 验证输入参数
        if (strDate == null || strDate.trim().length() < 10 || additionalStr == null
                || additionalStr.trim().length() != 19)
            return null;

        return strDate + additionalStr.substring(strDate.length());
    }

    /**
     * 根据一个date时间 获取 day天前的日期 如：2016-6-17 HH:mm:ss 前30天 2016-5-18 HH:mm:ss
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
        return calendar.getTime();
    }

    /**
     * 根据一个date时间 获取 day天后的日期 如：2016-5-18 HH:mm:ss 后30天 2016-6-17 HH:mm:ss
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        return calendar.getTime();
    }

    /**
     * 计算两个日期时间相差几天,几小时
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param format    格式
     * @param tag       比较返回的格式，D表示为天；H表示为小时，M表示为分钟，S表示为秒
     * @return
     */
    public static String dateDiff(String startTime, String endTime, String format, String tag) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        // 获得两个时间的毫秒时间差异
        long diff = 0;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        long day = diff / nd;// 计算差多少天
        long hour = diff % nd / nh;// 计算差多少小时
        long min = diff % nd % nh / nm;// 计算差多少分钟
        StringBuilder diffs = new StringBuilder();

        if (day != 0) {
            diffs.append(day + "天");
        }
        if (hour != 0) {
            diffs.append(hour + "小时");
        }
        if (min != 0) {
            diffs.append(min + "分钟");
        }
        return diffs.toString();
    }

    /**
     * 两个时间点的时间差(秒)（目前微信使用）
     *
     * @return
     * @throws ParseException
     * @param: mintime  最小时间
     * @param: maxtime  最大时间
     */
    public static int diffSecond(String mintime, String maxtime) throws ParseException {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = dfs.parse(mintime);
        Date end = dfs.parse(maxtime);
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        return new Long(between).intValue();
    }

    /**
     * 时格式时间真是时间
     */
    public static Date formatWorldTimeToGMTTime(String worldTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return sdf.parse(worldTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取指定天的0:0:0
     *
     * @param day
     * @return
     */
    public static Date getYesterFastDate(Date day) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(day);
        cl.set(Calendar.HOUR_OF_DAY, 0);
        cl.set(Calendar.MINUTE, 0);
        cl.set(Calendar.SECOND, 0);
        cl.set(Calendar.MILLISECOND, 0);
        return cl.getTime();
    }

    /**
     * 获取指定天数的00:00:00
     *
     * @param day
     * @return
     */
    public static Date getFirstDate(Date day) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(day);
        cl.set(Calendar.HOUR_OF_DAY, 00);
        cl.set(Calendar.MINUTE, 00);
        cl.set(Calendar.SECOND, 00);
        cl.set(Calendar.MILLISECOND, 000);
        return cl.getTime();
    }

    /**
     * 获取指定天数的23:59:59.0
     *
     * @param day
     * @return
     */
    public static Date getDayLastDate(Date day) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(day);
        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.SECOND, 59);
        cl.set(Calendar.MILLISECOND, 0);
        return cl.getTime();
    }


    /**
     *   时间戳转换日期
     * @return
     */
    public static Date getTimeToDate(long time){
        String dateTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        long timeLong = Long.valueOf(time);
        dateTime = simpleDateFormat.format(new Date(time * 1000L));
        return StringToDate(dateTime,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将本地时间转化为RFC3339时间
     * @param time
     * @return
     */
    public static String getTimeAndZone(long time) {
        DateTimeFormatter firstShotFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");
        String zoneTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).format(firstShotFormatter);

        return zoneTime;
    }

    public static Date getBeforeDayByParam(Date date, int beforDay){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, - beforDay);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取几天前的开始时间
     * @param date 时间
     * @param beforDay 几天前
     * @return 当天的开始时间
     */
    public static Date getBeforDayByParam(Date date, int beforDay){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, - beforDay);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


    public static Integer getRemainSecondsOneDay(Date currentDate) {
        //使用plusDays加传入的时间加1天，将时分秒设置成0
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                        ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        //使用ChronoUnit.SECONDS.between方法，传入两个LocalDateTime对象即可得到相差的秒数
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }
}
