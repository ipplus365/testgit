package com.ipplus360.service.redis;

import com.ipplus360.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by 辉太郎 on 2017/9/7.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired private RedisDao redisDao;

    @Override
    public void putExpireByDay(String key, String value, Date date) {
        redisDao.setExpireByDay(key, value, date);
    }

    @Override
    public void putExpire(String key, String value, int timeout, TimeUnit timeUnit) {
        redisDao.setExpire(key, value, timeout, timeUnit);
    }

    @Override
    public void put(String key, String value) {
        redisDao.put(key, value);
    }

    @Override
    public String get(String key) {
        return redisDao.get(key);
    }

    @Override
    public boolean has(String key) {
        return redisDao.has(key);
    }

    @Override
    public boolean downloadLimited(String email, int limitCount) {
        return redisDao.downloadLimited(email, limitCount);
    }

    @Override
    public boolean locateLimited(String key, int expire) {
        return redisDao.isLimitedExpire(key, expire);
    }

    @Override
    public boolean isFull(String key, Integer dailyLimit) {
        return redisDao.isFull(key, dailyLimit);
    }
}
