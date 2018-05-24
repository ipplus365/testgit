package com.ipplus360.dao;

import com.ipplus360.entity.GeoIP;
import com.ipplus360.entity.UserToken;
import com.ipplus360.util.DateUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoField.*;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by 辉太郎 on 2017/3/14.
 */
public class RedisDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDao.class);
    private final static String DOWNLOAD_LIMIT_PREFIX = "downloadlimit_";

    private final JedisPool jedisPool;

    private RuntimeSchema<GeoIP> GeoIPSchema = RuntimeSchema.createFrom(GeoIP.class);
    private RuntimeSchema<UserToken> tokenSchema = RuntimeSchema.createFrom(UserToken.class);

    public RedisDao(String host, int port) {
        jedisPool = new JedisPool(host, port);
    }

    public UserToken getUserToken(String token) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                UserToken userToken = tokenSchema.newMessage();
                String key = "tokens:" + token;
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    ProtobufIOUtil.mergeFrom(bytes, userToken, tokenSchema);
                    return userToken;
                }
            } finally {
                jedis.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String putUserToken(UserToken userToken) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "tokens:" + userToken.getToken();
                byte[] bytes = ProtobufIOUtil.toByteArray(userToken, tokenSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //24小时缓存有效期
                jedis.setex(key.getBytes(), 60 * 60 * 24, bytes);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GeoIP getGeoIP(Long ip) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                GeoIP geoIP = GeoIPSchema.newMessage();
                String key = "GeoIP:";
                Set<byte[]> set = jedis.zrangeByScore(key.getBytes(), ip.doubleValue(), Double.MAX_VALUE, 0, 1);
                byte[] bytes = set.iterator().next();
                ProtobufIOUtil.mergeFrom(bytes, geoIP, GeoIPSchema);
                return geoIP;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long putGeoIP(GeoIP geoIP) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "GeoIP:";
                byte[] bytes = ProtobufIOUtil.toByteArray(geoIP, GeoIPSchema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                Long result = jedis.zadd(key.getBytes(), geoIP.getMinip(), bytes);
                return result;
            } finally {
                jedis.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUnLimited(String token) {
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                long ts = System.currentTimeMillis()/1000;
                String key = token + ":" + ts;

                String countStr = jedis.get(key);
                if (!isEmpty(countStr)) {
                    Long count = Long.valueOf(countStr);
                    if (count < 5) {
                        jedis.incr(key);
                        return true;
                    }
                } else {
                    Transaction transaction = jedis.multi();
                    transaction.incr(key);
                    transaction.expire(key, 1);
                    transaction.exec();
                    return true;
                }

            }  finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getInvalidToken(String token) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "invalid:" + token;
            return jedis.get(key);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     *
     * @param token
     * @param timeout 超时标识
     */
    public void putInvalidToken(String token, boolean timeout) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "invalid:" + token;
            if (timeout) {
                jedis.setex(key, 600, "invalid");
            } else {
                jedis.set(key, "invalid");
            }
            LOGGER.debug("put invalid token {} to redis", token);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }

    public void removeInvalidToken(String token) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "invalid:" + token;
            jedis.del(key);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }


    public boolean downloadLimited(String email, int limitCount) {
        String key = DOWNLOAD_LIMIT_PREFIX + email;
        try (Jedis jedis = jedisPool.getResource()) {
            String countStr = jedis.get(key);
            if (isEmpty(countStr)) {
                setExpireByDay(jedis, key, "1", DateUtil.ldt2Date(DateUtil.getEndOfDay(null)));
                return false;
            }

            Long count = Long.valueOf(countStr);
            if (count < limitCount) {
                Transaction transaction = jedis.multi();
                transaction.incr(key);
                transaction.expireAt(key, DateUtil.getSeconds(DateUtil.getEndOfDay(null)));
                transaction.exec();
                transaction.close();
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
        return true;
    }

    /**
     * key过期时间为每日23:59:59
     * @param key
     * @param value
     */
    public void setExpireByDay(String key, String value, Date date) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            expireAt(jedis, key, date);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }

    public void setExpireByDay(Jedis jedis, String key, String value, Date date) {
        jedis.set(key, value);
        expireAt(jedis, key, date);
    }

    public void setExpire(String key, String value, int timeout, TimeUnit timeUnit) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
            expire(jedis, key, timeout, timeUnit);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }

    public void put(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }

    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }

        return null;
    }

    public boolean has(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
        return false;
    }

    private void expireAt(Jedis jedis, String key, Date date) {
        LocalDateTime endOfDay = DateUtil.getEndOfDay(DateUtil.date2LocalDate(date));
        jedis.expireAt(key, DateUtil.getSeconds(endOfDay));
    }

    private void expire(Jedis jedis, String key, int timeout, TimeUnit timeUnit) {
        switch (timeUnit) {
            case MINUTES: timeout = 60 * timeout;
                System.out.println("MINUTES timeout:" + timeout);
                break;
            case HOURS: timeout = 60 * 60 * timeout;
                System.out.println("HOURS timeout:" + timeout);
                break;
            case DAYS: timeout = 24 * 60 * 60 * timeout;
                System.out.println("DAYS timeout:" + timeout);
                break;
            default:
                System.out.println("timeout:" + timeout);
                break;
        }
        jedis.expire(key, timeout);
    }

    /**
     * token调用频率是否受限
     * @param token token
     * @return boolean
     */
    public boolean islimited(String token) {
        try (Jedis jedis = jedisPool.getResource()) {
            long ts = Instant.EPOCH.getEpochSecond();
            String key = token + ":" + ts;
            String limitCountStr = jedis.get(key);

            // 1. 限制次数为空, 第一次进入
            if (StringUtils.isEmpty(limitCountStr)) {
                jedis.setex(key, 1, "1");
                return false;
            }

            // 2. 限制次数不为空, 判断次数是否大于限制次数
            long limitCount = Long.valueOf(limitCountStr);
            if (limitCount < 5) {
                Transaction transaction = jedis.multi();
                transIncr(transaction, key);
                return false;
            }

        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
        return true;
    }

    /**
     * 受限, 有超时时间
     * @param key key
     * @param expire 超时
     * @return boolean
     */
    public boolean isLimitedExpire(String key, int expire) {
        key = key + ":" + expire;
        String value = get(key);
        if (StringUtils.isEmpty(value)) {
            setExpire(key, "1", expire, TimeUnit.SECONDS);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 每小时次数限制
     * hourLimit == -1为不限速
     * @param key key
     * @return boolean
     */
    public boolean isLimitedByHour(String key, int hourLimit) {

        if (hourLimit == -1) {
            return false;
        }

        try (Jedis jedis = jedisPool.getResource()) {
            int hour = DateUtil.getHour();
            key = key + ":" + hour;
            String val = jedis.get(key);
            if (StringUtils.isEmpty(val)) {
                setExpire(key, "1", 1, TimeUnit.HOURS);
                return false;
            }

            long limitCount = Long.valueOf(val);
            if (limitCount < hourLimit) {
                Transaction transaction = jedis.multi();
                transIncr(transaction, key);
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
        return true;
    }

    /**
     * 每日查询次数已满判断
     * @param key key
     * @param dailyLimit 每日次数限制
     * @return boolean
     */
    public boolean isFull(String key, Integer dailyLimit) {

        try (Jedis jedis = jedisPool.getResource()) {
            String val = jedis.get(key);
            Date endOfDay = DateUtil.ldt2Date(DateUtil.getEndOfDay(null));

            if (StringUtils.isEmpty(val)) {
                setExpireByDay(key, "1", endOfDay);
                return false;
            }

            long limitCount = Long.valueOf(val);
            if (limitCount < dailyLimit) {
                Transaction transaction = jedis.multi();
                transIncr(transaction, key);
                return false;
            }

        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
        return true;
    }

    private void transIncr(Transaction transaction, String key) throws IOException {
        transaction.incr(key);
        transaction.exec();
        transaction.close();
    }
}