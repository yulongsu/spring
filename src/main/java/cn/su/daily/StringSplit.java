package cn.su.daily;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author suyulong
 * @date 2019/5/21 10:56 AM
 */
public class StringSplit {

    public static void main(String[] args) {
        String s1 = "|12";
        String s2 = "";
        String s3 = "12|34|";
        String s4 = "||";
        String s5 = "同业业务违规|人员问责|不配合监管检查|理财投资业务违规|会计核算违规|会计核算违规";

        String[] sArr1 = s1.split("\\|",-1);
        System.out.println(Arrays.toString(sArr1));

        String[] sArr2 = s2.split("\\|",-1);
        System.out.println(Arrays.toString(sArr2));
        Arrays.stream(sArr2);

        String[] sArr3 = s3.split("\\|",-1);
        System.out.println(Arrays.toString(sArr3));

        String[] sArr4 = s4.split("\\|",-1);
        System.out.println(Arrays.toString(sArr4));

        String[] sArr5 = s5.split("\\|",-1);
        System.out.println(Arrays.toString(sArr5));

        List<String> list = Arrays.stream(sArr5).distinct().collect(Collectors.toList());
        System.out.println(list);

        int x = LocalDate.now().getYear()-4;
        System.out.println(String.valueOf(x));


    }
}
