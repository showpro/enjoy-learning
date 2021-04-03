import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import util.DateUtils;

/**
 * @author zhanzhan
 * @date 2021/4/3 15:32
 *
 * LocalDateTime和Date互相转换
 *
 */
public class LocalDateTimeAndDate {
    public static void main(String[] args) {

        System.out.println("当前时间：" + LocalDateTime.now());//2021-04-03T15:39:56.759
        System.out.println("当前时间：" + new Date());//Sat Apr 03 15:39:56 CST 2021

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //字符转LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse("2021-12-12 20:20:21", dateTimeFormatter);
        System.out.println("字符转LocalDateTime: "+ localDateTime);

        //LocalDateTime转字符
        String format = dateTimeFormatter.format(localDateTime);
        System.out.println("LocalDateTime转字符: " + format);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        System.out.println(instant);
        //LocalDateTime转Date
        java.util.Date date = Date.from(instant);
        System.out.println("LocalDateTime转Date: "+date);
        Instant instant2 = date.toInstant();
        //Date转LocalDateTime
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, zone);
        System.out.println("Date转LocalDateTime: "+localDateTime2);

        Date startOfDay = DateUtils.startOfDay();
        System.out.println("startOfDay:"+ startOfDay);
        Date endOfDay = DateUtils.endOfDay();
        System.out.println("endOfDay:"+ endOfDay);

        Date tommorrowZero = DateUtils.getTommorrowZero();
        System.out.println("tommorrowZero:"+ tommorrowZero);

        Date tommorrowEight = DateUtils.getTommorrowEight();
        System.out.println("tommorrowEight:"+ tommorrowEight);


        System.out.println(new Date().compareTo(tommorrowZero) );
        System.out.println(startOfDay.compareTo(tommorrowZero) );
        System.out.println(endOfDay.compareTo(tommorrowZero) );



        //当天0点整
        Date todayZeroClock = getSomeTime(0,0, 0, 0);
        //当天8点整
        Date todayEightClock = getSomeTime(0,8, 0, 0);
        //当天18点整
        Date todayEighteenClock = getSomeTime(0,18, 0, 0);
        //明天0点整
        Date tommorrowZeroClock = getSomeTime(1,0, 0, 0);
        //明天8点整
        Date tommorrowEightClock = getSomeTime(1,8, 0, 0);
        System.out.println("todayZeroClock:"+ todayZeroClock);
        System.out.println("todayEightClock:"+ todayEightClock);
        System.out.println("todayEighteenClock:"+ todayEighteenClock);
        System.out.println("tommorrowZeroClock:"+ tommorrowZeroClock);
        System.out.println("tommorrowEightClock:"+ tommorrowEightClock);

    }


    /**
     * 获取某一时间
     *
     * @param hourOfDay 时
     * @param minute 分
     * @param second 秒
     * @return
     */
    public static Date getSomeTime(int date, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, date);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

}
