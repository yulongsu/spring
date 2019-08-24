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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author suyulong
 * @date 2019/1/24 2:35 PM
 */
public class Main {
    public <T extends Comparable<T>> List<T> pickDiff(List<T> list1, List<T> list2){
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        String dateStr = "2019-02-28";
        //System.out.println(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1).toString());


        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, 1);
            System.out.println(format.format(c.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

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

        byte[] secretBytes = null;
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
        System.out.println(md5code);


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
