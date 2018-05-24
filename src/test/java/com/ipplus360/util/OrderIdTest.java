package com.ipplus360.util;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class OrderIdTest {

	@Test
	public void test() {

/*//		String orderHead = "IP-";
		Runnable task2 = () -> { 
//			getNowDate();
//			Integer uuid =UUID.randomUUID().toString().hashCode();
			System.out.println(OrderUtil.getOrderId()); };
		for (int i =0;i<101;)	new Thread(task2).start();*/
//		Double a = 3.0;
//		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
//		String aa = decimalFormat.format(a);
//		System.out.println(aa);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		Calendar cd = Calendar.getInstance();
		cd.setTime(now);
		cd.add(Calendar.YEAR, 1);
		System.out.println("Now:"+sdf.format(now) + " Now+1:"+sdf.format(cd.getTime()));
		
	}
	public static String getNowDate() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		   String dateString = formatter.format(currentTime);
		   /*ParsePosition pos = new ParsePosition(8);
		   Date currentTime_2 = formatter.parse(dateString, pos);*/
		   return dateString;
	}
	
	public static char[] generateRandomArray(int num) {  
        String chars = "0123456789";  
        char[] rands = new char[num];  
        for (int i = 0; i < num; i++) {  
            int rand = (int) (Math.random() * 10);  
            rands[i] = chars.charAt(rand);  
        }  
        return rands;  
    }
	
	
	
}
