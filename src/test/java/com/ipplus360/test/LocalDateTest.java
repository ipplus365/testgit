package com.ipplus360.test;

import com.ipplus360.util.DateUtil;

import java.io.*;
import java.time.*;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Date;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * Created by 辉太郎 on 2017/8/4.
 */
public class LocalDateTest {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\ll\\Desktop\\456.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write("a");
        writer.write("\r\n");
        writer.write("b");
        writer.write('\r');
        writer.write("c");
        writer.write('\n');
        writer.write("d");
        writer.flush();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("line:" + line);
        }
    }


}
