package util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description Java8时间转换(LocalDateTime)
 * @Author zhanzhan
 * @Date 2021/2/8 13:45
 */
public class DateUtil {

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     *
     * @param localDateTime
     * @param format yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     *
     * @param time "2021-12-12 20:20:21"
     * @param format yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public Date localDateTime2Date( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        //Combines this date-time with a time-zone to create a  ZonedDateTime.
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        //Tue Mar 27 14:17:17 CST 2021
        System.out.println(date.toString());
        return date;
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

        //2018-03-27T14:07:32.668
        System.out.println(localDateTime.toString());
        //2018-03-27 14:48:57.453
        System.out.println(localDateTime.toLocalDate() + " " +localDateTime.toLocalTime());

        //This class is immutable and thread-safe.@since 1.8
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //2018-03-27 14:52:57
        System.out.println(dateTimeFormatter.format(localDateTime));

        return localDateTime;
    }

}
