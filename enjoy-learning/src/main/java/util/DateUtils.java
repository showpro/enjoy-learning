package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * yinquan 时间工具类
 */
public class DateUtils {
    public static final String DATE_TIME_TIMEZONE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSX";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MONTH_PATTERN_ONLY = "MM";
    public static final String DAY_PATTERN = "dd";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";
    public static final String NORMAL = "yyyyMMdd";
    public static final String NORMAL_MONTH = "yyyyMM";
    public static final String BIRTH_DAY = "MMdd";

    /**
     * 日期相加减天数
     *
     * @param date        如果为Null，则为当前时间
     * @param days        加减天数
     * @param includeTime 是否包括时分秒,true表示包含
     * @return
     * @throws ParseException
     */
    public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException {
        if (date == null) {
            date = new Date();
        }
        if (!includeTime) {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN);
            date = sdf.parse(sdf.format(date));
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 时间格式化成字符串
     *
     * @param date    Date
     * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN，
     *                如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String dateFormat(Date date, String pattern) throws ParseException {
        if (date == null) {
            return null;
        }
        if (StrUtil.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 时间格式化成字符串(不throw ParseException)
     *
     * @param date    Date
     * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN，
     *                如果为空，则为yyyy-MM-dd
     * @return
     */
    public static String dateFormat2(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if (StrUtil.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串解析成时间对象
     *
     * @param dateTimeString String
     * @param pattern        StrUtils.DATE_TIME_PATTERN ||
     *                       StrUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
        if (StrUtil.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        dateTimeString = dateTimeString.replace("/", "-");
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateTimeString);
    }

    /**
     * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化）
     *
     * @param dateTime Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateString(Date dateTime) throws ParseException {
        String dateTimeString = DateUtils.dateFormat(dateTime, DateUtils.DATE_TIME_PATTERN);
        return dateTimeString.substring(0, 10);
    }

    /**
     * 当时、分、秒为00:00:00时，将日期时间格式成只有日期的字符串， 当时、分、秒不为00:00:00时，直接返回
     *
     * @param dateTime Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateStringIfTimeEndZero(Date dateTime) throws ParseException {
        String dateTimeString = DateUtils.dateFormat(dateTime, DateUtils.DATE_TIME_PATTERN);
        if (dateTimeString.endsWith("00:00:00")) {
            return dateTimeString.substring(0, 10);
        } else {
            return dateTimeString;
        }
    }

    /**
     * 将日期时间格式成日期对象，和dateParse互用
     *
     * @param dateTime Date
     * @return Date
     * @throws ParseException
     */
    public static Date dateTimeToDate(Date dateTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 计算两个日期相差的秒数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long calLastedTime(Date startDate, Date endDate) {
        long a = endDate.getTime();
        long b = startDate.getTime();
        Long c = ((a - b) / 1000);
        return c;
    }

    /**
     * 时间加减小时
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param hours     加减的小时
     * @return Date
     */
    public static Date dateAddHours(Date startDate, int hours) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return c.getTime();
    }

    /**
     * 时间加减分钟
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param minutes   加减的分钟
     * @return
     */
    public static Date dateAddMinutes(Date startDate, int minutes) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return c.getTime();
    }

    /**
     * 时间加减秒数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param seconds   加减的秒数
     * @return
     */
    public static Date dateAddSeconds(Date startDate, int seconds) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
        return c.getTime();
    }

    /**
     * 时间加减天数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param days      加减的天数
     * @return Date
     */
    public static Date dateAddDays(Date startDate, int days) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /**
     * 时间加减月数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param months    加减的月数
     * @return Date
     */
    public static Date dateAddMonths(Date startDate, int months) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }

    /**
     * 时间加减年数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param years     加减的年数
     * @return Date
     */
    public static Date dateAddYears(Date startDate, int years) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.YEAR, years);
        return c.getTime();
    }

    /**
     * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0）
     *
     * @param myDate      时间
     * @param compareDate 要比较的时间
     * @return int
     */
    public static int dateCompare(Date myDate, Date compareDate) {
        Calendar myCal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        myCal.setTime(myDate);
        compareCal.setTime(compareDate);
        return myCal.compareTo(compareCal);
    }

    /**
     * 获取两个时间中最小的一个时间
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMin(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate)) {
            return compareDate;
        } else if (-1 == dateCompare(date, compareDate)) {
            return date;
        }
        return date;
    }

    /**
     * 获取两个时间中最大的一个时间
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMax(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate)) {
            return date;
        } else if (-1 == dateCompare(date, compareDate)) {
            return compareDate;
        }
        return date;
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetween(Date startDate, Date endDate) throws ParseException {
        Date dateStart = dateParse(dateFormat(startDate, DATE_PATTERN), DATE_PATTERN);
        Date dateEnd = dateParse(dateFormat(endDate, DATE_PATTERN), DATE_PATTERN);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，包含今天
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException {
        return dateBetween(startDate, endDate) + 1;
    }

    /**
     * 获取两个日期月份差
     *
     * @param str1
     * @param str2
     * @return
     * @throws ParseException
     */
    public static int monthBetweenIncludeThis(String str1, String str2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(MONTH_PATTERN);
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int surplus = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        surplus = surplus <= 0 ? 1 : 0;
        return Math.abs(month + result) + surplus;
    }

    /**
     * 获取日期时间的年份，如2017-02-13，返回2017
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期时间的月份，如2017年2月13日，返回2
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期时间的第几天（即返回日期的dd），如2017-02-13，返回13
     *
     * @param date
     * @return
     */
    public static int getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取日期时间当年的总天数，如2017-02-13，返回2017年的总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 根据时间获取当月最大的日期
     * <li>2017-02-13，返回2017-02-28</li>
     * <li>2016-02-13，返回2016-02-29</li>
     * <li>2016-01-11，返回2016-01-31</li>
     *
     * @param date Date
     * @return
     * @throws Exception
     */
    public static Date maxDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }

    /**
     * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象
     *
     * @param date Date
     * @return
     * @throws Exception
     */
    public static Date minDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }

    /**
     * 获取本周第一天
     *
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getThisWeekMonday(String dateStr, String format) throws ParseException {
        Date date = dateParse(dateStr, format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();

    }

    /**
     * 获取下周第一天
     *
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getNextWeekMonday(String dateStr, String format) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(dateStr, format));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    /**
     * 获取本月第一天
     *
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfThisMonth(String dateStr, String format) throws ParseException {
        Date date = dateParse(dateStr, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfThisMonth(String dateStr, String format) throws ParseException {
        Date date = dateParse(dateStr, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        int lastDay = calendar.getActualMaximum(Calendar.DATE);
        // 设置日历中月份的最大天数
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        return calendar.getTime();
    }

    /**
     * 获取下月第一天
     *
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfNextMonth(String dateStr, String format) throws ParseException {
        Date date = dateParse(dateStr, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getSysYear() {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getSysMonth() {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.MONTH) + 1;
        return year;
    }

    /**
     * 获取当前周
     *
     * @return
     */
    public static int getSysWeek() {
//        Calendar cal = Calendar.getInstance();
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
//        cal.setMinimalDaysInFirstWeek(7);
//        try {
//            cal.setTime(new Date());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cal.get(Calendar.WEEK_OF_YEAR);
        //return DateUtil.weekOfYear(new Date());
        return -1;
    }

    /**
     *
     */
    public static int getSysAuarter() {
        Integer sysMonth = getSysMonth();
        int quarter = 0;
        if (sysMonth >= 1 && sysMonth <= 3) {
            quarter = 1;
        }
        if (sysMonth >= 4 && sysMonth <= 6) {
            quarter = 2;
        }
        if (sysMonth >= 7 && sysMonth <= 9) {
            quarter = 3;
        }
        if (sysMonth >= 10 && sysMonth <= 12) {
            quarter = 4;
        }
        return quarter;

    }

    public static void main(String[] args) throws Exception {
        /*
         * System.out.println(dateTimeToDate(new Date()));
         * System.out.println(dateParse("2017-02-04 14:58:20", null));
         * System.out.println(dateTimeToDateStringIfTimeEndZero(new Date()));
         * System.out.println(dateTimeToDateStringIfTimeEndZero(dateTimeToDate(new
         * Date())));
         */
        // System.out.println(dateBetween(dateParse("2017-01-30", null),
        // dateParse("2017-02-01", null)));
        // System.out.println(dateBetweenIncludeToday(dateParse("2017-01-30", null),
        // dateParse("2017-02-01", null)));
//        System.out.println(getDate(dateParse("2017-01-17", null)));
        /*
         * System.out.println(getDaysOfMonth(dateParse("2017-02-01", null)));
         * System.out.println(getDaysOfYear(dateParse("2017-01-30", null)));
         */
        // System.out.println(dateFormat(dateAddMonths(dateParse("2017-02-07",
        // StrUtils.MONTH_PATTERN), -12), StrUtils.MONTH_PATTERN));
        /*
         * System.out.println(dateFormat(maxDateOfMonth(dateParse("2016-02",
         * "yyyy-MM")), null));
         * System.out.println(dateFormat(minDateOfMonth(dateParse("2016-03-31", null)),
         * null));
         */
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("今天是" + sdf.format(getThisWeekMonday("2018-10-12", "yyyy-MM-dd")));
//		System.out.println("今天是" + sdf.format(getNextWeekMonday("2018-10-12", "yyyy-MM-dd")));
//		System.out.println(dateFormat(getFirstDayOfNextMonth("2018-10-12", "yyyy-MM-dd"), "yyyy-MM-dd"));
//		System.out.println(dateFormat(getFirstDayOfThisMonth("2018-10-12", "yyyy-MM-dd"), "yyyy-MM-dd"));
//		System.out.println(dateFormat(getLastDayOfThisMonth("2019-03-03", "yyyy-MM-dd"), "yyy-MM-dd"));
//        System.out.println(dateFormat(getLastDayOfThisMonth("2019-11", "yyy-MM"), "yyy-MM-dd"));
//        System.out.println(DateUtils.dateBetweenIncludeToday());
//        int k = DateUtils.monthBetweenIncludeThis("2020-06-01", "2020-08-29");
//        for (int i = 0; i < k; i++) {
//            Date date = DateUtils.dateAddMonths(DateUtils.dateParse("2020-06-01", DateUtils.DATE_PATTERN), i);
//            System.out.println(date);
//        }
//        Map<String, Object> map = weekToDayFormat(2020, 1);
//        System.out.println(map);
//        System.out.println();

    }

    /**
     * 获取当前时刻
     *
     * @return String
     */
    public static Date getToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 获取昨天
     *
     * @return String
     */
    public static Date getYestoday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static Date getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @return String
     **/
    public static Date getMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public static Date getWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        return cal.getTime();
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static Date getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        return cal.getTime();
    }

    /**
     * 获取本年的第一天
     *
     * @return String
     **/
    public static String getYearStart() {
        return new SimpleDateFormat("yyyy").format(new Date()) + "-01-01";
    }

    /**
     * 获取本年的最后一天
     *
     * @return String
     **/
    public static String getYearEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast);
    }

    /**
     * 获取当前天数
     *
     * @return
     */
    public static Date getTodayZero() {
        try {
            String dateString = dateTimeToDateStringIfTimeEndZero(new Date());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据年月获取对应第一天和最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Map<String, Object> queryDayByYearAndMonth(int year, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        Date firstDay = null;
        Date lastDay = null;
        try {
            firstDay = DateUtils.minDateOfMonth(calendar.getTime());
            lastDay = DateUtils.maxDateOfMonth(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //添加返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("firstDay", sdf.format(firstDay));
        map.put("lastDay", sdf.format(lastDay));
        return map;
    }

    /**
     * 通过第几周获取日期
     *
     * @param year
     * @param week
     * @param targetNum
     * @return
     */
    public static Map<String, Object> weekToDayFormat(int year, int week, int targetNum) {
        // 计算目标周数
        if (week + targetNum > 52) {
            year++;
            week += targetNum - 52;
        } else if (week + targetNum <= 0) {
            year--;
            week += targetNum + 52;
        } else {
            week += targetNum;
        }
        Calendar cal = Calendar.getInstance();
        // 设置每周的开始日期
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date startDate = dateAddDays(cal.getTime(), 1);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date endDate = dateAddDays(cal.getTime(), 1);
        Map<String, Object> m = new HashMap<>();
        try {
            m.put("startDate", dateParse(dateFormat(startDate, DateUtils.DATE_PATTERN), DateUtils.DATE_PATTERN));
            m.put("endDate", dateParse(dateFormat(endDate, DateUtils.DATE_PATTERN), DateUtils.DATE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return m;
    }

    /**
     * 通过日期获取星期
     *
     * @param date
     * @return
     */
    public static String dateToWeek(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 通过日期获取对应的年月日
     *
     * @param date
     * @return
     */
    public static Map<String, Object> dateToYearMonWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setMinimalDaysInFirstWeek(7);
        try {
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //年份数值
        int year = cal.get(Calendar.YEAR);
        //第几个月
        int month = cal.get(Calendar.MONTH) + 1;
        //一年中的第几周
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        //如果月份是12月，且求出来的周数是第一周，说明该日期实质上是这一年的第53周，也是下一年的第一周
        if (cal.get(Calendar.MONTH) >= 11 && week <= 1) {
            week += 52;
        }
//        return week;
        Map<String, Object> m = new HashMap<>();
        m.put("year", year);
        m.put("month", month);
        m.put("week", week);
        return m;
    }

    /**
     * 获取一天中剩余的时间（秒数）
     */
    public static long getDayRemainingTime(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        return ChronoUnit.SECONDS.between(currentDateTime, midnight);
    }

    public static Map<String, Object> getWeekDays(int year, int week, int targetNum) {
        // 计算目标周数
        if (week + targetNum > 52) {
            year++;
            week += targetNum - 52;
        } else if (week + targetNum <= 0) {
            year--;
            week += targetNum + 52;
        } else {
            week += targetNum;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        // 设置每周的开始日期
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String startDate = sdf.format(dateAddDays(cal.getTime(), 1));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        String endDate = sdf.format(dateAddDays(cal.getTime(), 1));
        Map<String, Object> m = new HashMap<>();
        m.put("startDate", startDate);
        m.put("endDate", endDate);
        return m;
    }
    /**
     * 获取明天的0点0时0分0秒
     * @return
     */
    public static Date getTommorrowZero() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取明天的8点0时0分0秒
     * @return
     */
    public static Date getTommorrowEight() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     * @return
     */
    public static Date startOfDay() {
        Calendar todayStart = Calendar.getInstance();
        //todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取当天结束时间
     * @return
     */
    public static Date endOfDay() {
        Calendar todayEnd = Calendar.getInstance();
        //todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获得当天零时零分零秒/当天的开始时间
     * @return 当天零时零分零秒的Date
     */
    public static Date getToDayInitial(){
        //带着时区(默认时区,本地日期)
        ZonedDateTime zonedDateTime = ZonedDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        //1.zonedDateTime.toInstant()转换成时间戳(因为zonedDateTime包含了时区和时间信息,可以转换为GMT的时间戳(时间+时区))2.Date.from会使用默认时区转换时间戳为本地时间(默认时区)
        Date date = Date.from(zonedDateTime.toInstant());
        System.out.println(date);
        return date;
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        //Combines this date-time with a time-zone to create a  ZonedDateTime.
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        //Tue Mar 27 14:17:17 CST 2018
        return Date.from(zdt.toInstant());
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public LocalDateTime date2LocalDateTime(Date date){
        //An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        Instant instant = date.toInstant();
        //A time-zone ID, such as {@code Europe/Paris}.(时区)
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return localDateTime;
    }

}