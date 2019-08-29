package cn.su.daily;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * @author suyulong
 * @date 2019/8/29 3:24 PM
 */
public class CSVProcess {

    public Map<String, String> read() {
        String filePath = this.getClass().getClassLoader().getResource("param.csv").getPath();
        try (CSVReader csvReader = new CSVReaderBuilder(
            new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "utf-8")))
            .withCSVParser(new CSVParserBuilder().withSeparator('#').build()).build()) {
            Iterator<String[]> iterator = csvReader.iterator();
            Map<String, String> map = new HashMap<>();
            //skip title
            iterator.next();
            while (iterator.hasNext()) {
                String[] strArr = iterator.next();
                map.put(strArr[0], strArr[1]);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void main(String[] args) {
        CSVProcess process = new CSVProcess();
        System.out.println(process.read());
    }

}
