package com.ipplus360.service.redis;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis
 * Created by 辉太郎 on 2017/9/7.
 */
public interface RedisService {

    void putExpireByDay(String key, String value, Date date);

    void putExpire(String key, String value, int timeout, TimeUnit timeUnit);

    void put(String key, String value);

    String get(String key);

    boolean has(String key);

    boolean downloadLimited(String email, int limitCount);

    /**
     * 是否受限
     * @param key key
     * @param expire 超时时间
     * @return boolean
     */
    boolean locateLimited(String key, int expire);

    boolean isFull(String key, Integer dailyLimit);
}
