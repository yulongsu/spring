package cn.su.daily;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;

/**
 * @author suyulong
 * @date 2019/7/24 12:59 PM
 */
public class Test {
    public static void main(String[] args) {

        int randomElementIndex
            = ThreadLocalRandom.current().nextInt(5);
        System.out.println(randomElementIndex);

        System.out.println(parseDateTime("2020-01-20 18:00:00"));

        String str = StringUtils.leftPad("11745", 6, "0");
        System.out.println(str);

        System.out.println(str.replaceAll("^(0+)", ""));

        //
        //LocalDate now = LocalDate.now();
        //int currWeek = now.get(WeekFields.of(DayOfWeek.MONDAY, 4).weekOfWeekBasedYear());
        //System.out.println(currWeek);

        //System.out.println(LocalDateTime.of(2019, 1, 1, 0, 0, 0).get(WeekFields.of(DayOfWeek.MONDAY, 4).weekOfYear
        // ()));
        LocalDate now = LocalDate.now();
        LocalDate reportDefineDate = LocalDate.parse("2019-08-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(reportDefineDate);


        System.out.println(reportDefineDate.get(WeekFields.of(DayOfWeek.MONDAY, 4).weekOfWeekBasedYear()));

        //上一周的周日
        System.out.println(LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY)
            .format(DateTimeFormatter.ofPattern("MM.dd")));
        System.out.println(LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY)
            .format(DateTimeFormatter.ofPattern("MM.dd")));

        //List list1 = null;
        //List list2 = null;
        //System.out.println(list1 == list2);
        //
        //System.out.println(true^false);
        //System.out.println(true^true);

        //System.out.println(LocalDate.now().minusYears(1).with(TemporalAdjusters.firstDayOfMonth()).toString());
        //
        //DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        //System.out.println(LocalDate.now().format(formatters));
        //System.out.println(LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        //
        //List<String> list1 = Arrays.asList("1","2","3");
        //List<String> list2 = Arrays.asList("1","2","3");
        //System.out.println(list1.hashCode());
        //System.out.println(list2.hashCode());

       /* Long curr,pre;
        curr = 133123123L;
        pre = 111L;

        System.out.println(new BigDecimal((double)curr/10000).setScale(2, BigDecimal.ROUND_HALF_UP));


        //System.out.println(new BigDecimal((double)(curr-pre)*100/pre).setScale(2,BigDecimal.ROUND_HALF_UP).toString
        ());

        System.out.println(LocalDate.now().getDayOfMonth());

        System.out.println(LocalDate.now().getDayOfWeek().getValue());*/
    }


    public static Date parseDateTime(String dateTime) {
        if (org.springframework.util.StringUtils.isEmpty(dateTime)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime parse = LocalDateTime.parse(dateTime, df);
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = parse.atZone(zone).toInstant();
            return new Date(instant.toEpochMilli());
        } catch (Exception e) {
            return null;
        }

    }

}
