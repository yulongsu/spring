package cn.su.daily;

import cn.su.exam.BizException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author suyulong
 * @date 2019/1/24 2:35 PM
 */
public class Main {
    public <T extends Comparable<T>> List<T> pickDiff(List<T> list1, List<T> list2){
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        list = Arrays.asList("321");
        System.out.println(list);


       /* while (true){
            System.out.println(Random.nextInt(10));
        }*/


        /*System.out.println(String.format("%06d", Integer.valueOf(133)));
        System.out.println(String.format("%06d", Integer.valueOf(1331231)));

        String s = "原标题：时政新闻眼丨习近平再次在京考察 强调这件大事须臾不可放松\n"
            + "\n"
            + "[[+_+]]\n"
            + "\n"
            + "新冠肺炎疫情是一场大考。赢得这次大考，既要立足当前，抓好当务之急，又要放眼长远，谋划未来之策。3月2"
            + "日，习近平继上月调研指导疫情防控之后，再次在北京考察。作为战“疫”总指挥的他，在此行中重点关注了什么，做了哪些重要部署，释放了哪些重要信息？\n"
            + "\n"
            + "[[+_+]]\n";
        System.out.println(s.replaceAll("\\[\\[\\+_\\+\\]\\]\\n",""));

        System.out.println(s.replaceAll("\\[\\[\\+_\\+\\]\\]",""));*/


        //LocalDate now = LocalDate.now();
        //String dateStr = "2020-02-23";
        //LocalDate now = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        //计算本周第一天
        //DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate firstDayOfNow = now.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        //System.out.println(firstDayOfNow.format(fmt));
        //int currWeek = now.get(WeekFields.of(DayOfWeek.MONDAY, 4).weekOfWeekBasedYear());
        //System.out.println(currWeek);
        //
        ////上一周的周数
        //int preWeek = now.minusDays(7).get(WeekFields.of(DayOfWeek.MONDAY, 4).weekOfWeekBasedYear());
        //System.out.println(preWeek);
        //
        ////计算3周以前的第一天
        //System.out.println(firstDayOfNow.minusDays(21).format(fmt));
        //
        //System.out.println(now.minusMonths(11).with(TemporalAdjusters.firstDayOfMonth())
        //    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        /*for (int i = 11; i > -1; i--) {
            System.out.println((now.minusMonths(i).format(
                DateTimeFormatter.ofPattern("yyyy-MM"))));
        }*/

        /*for (int i = 4; i > -1; i--) {
            System.out.println((String.valueOf(now.getYear() - i)));
        }*/


        /*for (int i = 3 * 7; i > -1; i-=7) {
            LocalDate ld = now.minusDays(i);
            String m = String.valueOf(ld.get(WeekFields.of(DayOfWeek.MONDAY, 4).weekOfWeekBasedYear()));
            System.out.println(ld.with(DayOfWeek.MONDAY).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
            System.out.println(ld.with(DayOfWeek.SUNDAY).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        }*/
        //System.out.println(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1).toString());


        /*DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println(format.format(c.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        //String memo = "����ɨ��";
        ////org.apache.commons.lang3.StringEscapeUtils.escapeJava(memo);
        ////memo.replaceAll("\uFFFD", "");
        //try {
        //    while (true){
        //        stripNonCharCodepoints(new String(memo.getBytes(),"GBK"));
        //    }
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}

        //int i = 30;
        ////while (i++<10){
        ////    System.out.println(IdGen.INSTANCE.nextId());
        ////}
        //System.out.println(String.format("%04d", i));

        //LocalDate indexStartDate = LocalDate.now().minusDays(10);
        //System.out.println(indexStartDate);

        //System.out.println(Integer.valueOf("dim3".substring(3,4)));

        //2500
        /*int h = 17;
        String s;
        for (int i = 0; i < 13; i++) {
            h = 30*h + calcHash();
        }
        System.out.println(h);*/

        //calcHash("中国人民");

       /* byte[] secretBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update("中国人民".getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0

        //md5(中国人民,16)
        System.out.println(md5code.substring(8, 24));
        //md5(中国人民,32)
        System.out.println(md5code);*/


        /*md5(中国人民,32) = b17e0c5017c6c6eea21db187fbcadd4a
        md5(中国人民,16) = 17c6c6eea21db187*/
    }

    public static String stripNonCharCodepoints(String input) {
        StringBuilder retval = new StringBuilder();
        char ch;

        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);

            // Strip all non-characters http://unicode.org/cldr/utility/list-unicodeset.jsp?a=[:Noncharacter_Code_Point=True:]
            // and non-printable control characters except tabulator, new line and carriage return
            if (ch % 0x10000 != 0xffff && // 0xffff - 0x10ffff range step 0x10000
                ch % 0x10000 != 0xfffe && // 0xfffe - 0x10fffe range
                (ch <= 0xfdd0 || ch >= 0xfdef) && // 0xfdd0 - 0xfdef
                (ch > 0x1F || ch == 0x9 || ch == 0xa || ch == 0xd)) {

                retval.append(ch);
            }
        }

        return retval.toString();
    }

    private static int calcHash(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        return h;
    }

    public static int calcHash() {
        int h = 0;
        for (int i = 0; i < 256; i++) {
            h = 31 * h + 128;
        }
        return h;
    }

    public static void throwBizException() throws BizException {
        //throwException();
        System.out.println(9 / 0);
    }

    public void throwException() throws Exception {
        throw new Exception();
    }
}
