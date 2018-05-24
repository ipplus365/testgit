package com.ipplus360;

import com.ipplus360.entity.UserLog;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 辉太郎 on 2017/3/10.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ParseException {
        long time = 1452924402422L;
        Date date = new Date(time);
        System.out.println(date);

        String s = "13000.00";
        BigDecimal b = new BigDecimal(s);
        b.setScale(2);
        /*System.out.println("1:" + (new BigDecimal(1.2).compareTo(new BigDecimal("1.20")) == 0));
        System.out.println((new BigDecimal(1.2).equals(new BigDecimal("1.20"))));
        System.out.println((new BigDecimal(1.2).equals(new BigDecimal(1.20)))); //输出是?
        System.out.println((new BigDecimal(1.2).compareTo(new BigDecimal(1.20)) == 0));//输出是?*/


        /*String d = "2017-05-26 18:31:47";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        Date dd = sdf.parse(d);
        System.err.println(dd.getTime());*/
        /*int i = 0;
        while(i<1000) {
            System.err.println(System.currentTimeMillis() / 1000);
            i++;
            Thread.sleep(1000);
        }*/


        String logS = "2017-05-27 16:57:21,211 [http-bio-8080-exec-9] INFO record - |WEB|test:|4e0ed763958d4131acf9e8f7c3050636|12|1.192.90.168" +
                "|20H2hpgJJVUZ3n5Ju1bH4uFkcD3V0P9fuu6DOWLVcBcfQ6KpYefcc2gIXVhUQw21|127246|19181766|1|3_3_3|Sat May 27 16:57:21 CST 2017\n";

        String logS1 = "2017-05-31 10:03:18,179 [qtp126288666-36] INFO  record - |WEB|test:|878bde90bdeb4c3b9eb293406bb139a2|12|0:0:0:0:0:0:0:1" +
                "|20H2hpgJJVUZ3n5Ju1bH4uFkcD3V0P9fuu6DOWLVcBcfQ6KpYefcc2gIXVhUQw21|127246|19181766|1|3_3_3|Wed May 31 10:03:18 CST 2017\n";

        String logS2 = "2017-05-31 10:09:41,798 [qtp126288666-44] INFO  record - |WEB|test:|613fff49160a42028ff910d87798ae4e|12|192.168.1.100" +
                "|20H2hpgJJVUZ3n5Ju1bH4uFkcD3V0P9fuu6DOWLVcBcfQ6KpYefcc2gIXVhUQw21|127246|19181766|1|3_3_3|Wed May 31 10:09:41 CST 2017";

        String[] split = logS2.split("\\|");

        String ss1 = split[0].split(",")[0];
        //System.err.println("ss1:" + ss1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        UserLog userLog = new UserLog();

        userLog.setLogDate(sdf.parse(ss1));
        userLog.setSource(split[1]);
        userLog.setTest(split[2]);
        userLog.setLogId(split[3]);
        userLog.setUserId(Long.parseLong(split[4]));
        userLog.setUserIp(split[5]);
        userLog.setToken(split[6]);
  //      userLog.setDataId(split[7]);
    //    userLog.setIp(split[8]);
        userLog.setProductId(split[9]);
        userLog.setVersion(split[10]);

        System.out.println(userLog);
    }
}
