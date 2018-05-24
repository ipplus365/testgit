package com.ipplus360;

import com.ipplus360.util.DateUtil;

import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoField.MILLI_OF_DAY;

/**
 * Created by 辉太郎 on 2017/9/2.
 */
public class JsonTest {

    public static void main(String[] args) {
        /*String str = "{\"lastLogin\":1504316177231,\"testCount\":3,\"tokenStatus\":0,\"source\":0,\"dateCreated\":1490766404000,\"id\":12,\"email\":\"lijian@ipplus360.com\",\"loginCount\":113,\"regFrom\":\"0:0:0:0:0:0:0:1\",\"lastLoginFrom\":\"0:0:0:0:0:0:0:1\",\"status\":1}";

        String str1 = "{\"data\":{\"lastLogin\":1504316177231,\"testCount\":3,\"tokenStatus\":0,\"source\":0,\"password\":\"1000:0e71a05946ce6154174fac7cfd39689c107b038c8d479426:680a62ed976fe5011dd9d8a0ba75e1146f6c89ac3c0b663f\",\"checkStatus\":1,\"dateCreated\":1490766404000,\"checkCodeUpdated\":1495764375000,\"id\":12,\"passwordSalt\":\"23cc653bcfe549bc5eac344abf4a9b288bb6093ecc54b593\",\"email\":\"lijian@ipplus360.com\",\"loginCount\":113,\"checkCode\":207483,\"token\":\"8aa4cc864061c0edfd243524ac0fa8b9\",\"regFrom\":\"0:0:0:0:0:0:0:1\",\"lastLoginFrom\":\"0:0:0:0:0:0:0:1\",\"tokenExptime\":1490852802000,\"status\":1},\"success\":true}";
        JSON.parseObject(str, SessionUser.class);*/
        //JSON.parse(str);
        //1504454399
        /*System.err.println("sec:" + DateUtil.getSeconds(DateUtil.getEndOfDay(LocalDate.of(2017, 9, 3))));
        System.err.println("sec:" + DateUtil.getSeconds(DateUtil.getEndOfDay(null)));
        System.err.println("sec:" + DateUtil.getSeconds(DateUtil.getEndOfDay(LocalDate.of(2017, 9, 10))));
        System.err.println("end:" + DateUtil.getEndOfDay(DateUtil.date2LocalDate(null)).getLong(MILLI_OF_DAY));
        System.err.println(Charset.forName("UTF-8").name());

        Long curr = Instant.now().getLong(ChronoField.MILLI_OF_SECOND);
        Long curr1 = System.currentTimeMillis();
        System.err.println("ss1:" + Instant.now().toEpochMilli());
        System.err.println("ss2:" + System.currentTimeMillis());
        System.err.println("ss3:" + Instant.now().getLong(ChronoField.MILLI_OF_SECOND));
        System.out.println(Integer.MAX_VALUE);

        System.out.println(ChronoUnit.HOURS.ordinal());*/
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.err.println(DateUtil.date2LocalDateTime(new Date()).format(formatter));
        List<String> list = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/

        /*LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.plusYears(1L);
        System.err.println("ldt:" + ldt);

        Date now = new Date();
        ldt = DateUtil.getNextXYear(now, 3);
        System.err.println("ldt1:" + ldt);*/

        String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
        String s = "1582279708@qq.com";
        boolean matches = Pattern.matches(EMAIL_PATTERN, s);
        System.err.println("matches:" + matches);

    }
}
