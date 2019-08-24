package cn.su.daily;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suyulong
 * @date 2019/7/24 12:59 PM
 */
public class Test {
    public static void main(String[] args) {
        //List list1 = null;
        //List list2 = null;
        //System.out.println(list1 == list2);
        //
        //System.out.println(true^false);
        //System.out.println(true^true);

        System.out.println(LocalDate.now().minusYears(1).with(TemporalAdjusters.firstDayOfMonth()).toString());

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        System.out.println(LocalDate.now().format(formatters));
        System.out.println(LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        List<String> list1 = Arrays.asList("1","2","3");
        List<String> list2 = Arrays.asList("1","2","3");
        System.out.println(list1.hashCode());
        System.out.println(list2.hashCode());
    }
}
