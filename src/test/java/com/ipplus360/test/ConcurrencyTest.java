package com.ipplus360.test;

import com.ipplus360.util.IPUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

/**
 * Created by 辉太郎 on 2017/6/19.
 */
public class ConcurrencyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrencyTest.class);
    @Test
    public void testConcurrency() throws IOException {
        //System.out.println(IPUtil.ipToLong("0.0.0.0"));
        //System.out.println(IPUtil.ipToLong("255.255.255.255"));
        long min = 1;
        long max = 4294967295l;
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i=0; i<5000000; i++) {
        //for (int i=0; i<10; i++) {
            LOGGER.info("current: " + i);
            long l = min + (long)(random.nextDouble() * (max - min));
            httpGet(l);
        }
        long end = System.currentTimeMillis();
        System.err.println("耗时：" + (end - start) + "ms");
        //for (long ip=)
    }

    public void httpGet(long ipL) throws IOException {
        String ip = IPUtil.ipFromLong(ipL);
        String locateUrl = "http://192.168.1.30:8081/ip/locate/api?ip=" + ip + "&key=cKgx78xWaEEPaybXN1G3Hql64wHUbgdC2hl9ZYAK4EEYF5L92NhZx4wdPN4PPnTZ";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(locateUrl);
        httpGet.setHeader("Accept", "application/json");

        String result = httpClient.execute(httpGet, response -> {
            int statusCode = response.getStatusLine().getStatusCode();
            if ( statusCode == 200 ) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
            }
            LOGGER.error("Got statusCode {} while parsing url {}.", new Object[]{ statusCode, locateUrl });
            return null;
        });

        //System.err.println(result);
        LOGGER.info(result);
        httpClient.close();

    }
}
