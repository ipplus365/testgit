package com.ipplus360.rpc;

import com.alibaba.fastjson.JSONObject;
import com.ipplus360.dto.IPResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by 辉太郎 on 2017/5/24.
 */
public class LocateTest {

    @Test
    public void testRpc() throws IOException {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) < 5000) {
            getResult();
        }
    }

    public IPResult getResult() throws IOException {
        String url = "http://192.168.1.100:9000/ip/locate/api?ip=1.4.141.0&key=3EeuOI307Hd6A9eoSoJFLDIxiPhqDplkt5ZjYaLs3kx8GXcKH1WMlPA8iZRg4dXQ";
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");

        String result = httpClient.execute(httpGet, response -> {
            int statusCode = response.getStatusLine().getStatusCode();
            if ( statusCode == 200 ) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, "UTF-8") : null;
            }
            return null;
        });
        if ( StringUtils.isNotBlank(result) ) {
            //LOGGER.info("resolveAddressResult {}", jsonObj);
            //System.err.println("cost: " + (System.currentTimeMillis() - start));
            IPResult ipResult = JSONObject.parseObject(result, IPResult.class);

            System.err.println("ipResult:" + ipResult);
            return ipResult;
        }

        return null;
    }
}
