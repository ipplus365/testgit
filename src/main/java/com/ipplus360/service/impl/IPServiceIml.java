package com.ipplus360.service.impl;

import com.ipplus360.dao.GeoIPDao;
import com.ipplus360.dao.GeoIPVersionDao;
import com.ipplus360.dao.RedisDao;
import com.ipplus360.dao.TokenDao;
import com.ipplus360.domain.LoginLocation;
import com.ipplus360.dto.LocateProps;
import com.ipplus360.entity.GeoIP;
import com.ipplus360.entity.UserToken;
import com.ipplus360.exception.InvalidTokenException;
import com.ipplus360.exception.RequestFrequentlyException;
import com.ipplus360.service.IPService;
import com.ipplus360.service.TokenService;
import com.ipplus360.service.redis.RedisService;
import com.ipplus360.util.DateUtil;
import com.ipplus360.util.IPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 辉太郎 on 2017/3/13.
 */
@Service
public class IPServiceIml implements IPService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPServiceIml.class);
    private static final Logger RECORD_LOGGER = LoggerFactory.getLogger("record");
    private static final Logger DISTRICT_LOGGER = LoggerFactory.getLogger("district");
    private static final Logger SCENE_LOGGER = LoggerFactory.getLogger("scene");

    private final static String REDIS_DISTRICT_KEY_PREFIX = "district_key";

    @Autowired private GeoIPDao geoIPDao;
    @Autowired private TokenDao tokenDao;
    @Autowired private RedisDao redisDao;
    @Autowired private GeoIPVersionDao versionDao;

    @Autowired private RedisService redisService;
    @Autowired private TokenService tokenService;



    @Override
    public GeoIP locate(String ip, String key, String user_ip, String source, String coordSuffix, Boolean hasScene) {
        // 1. 判断token是否有效
        UserToken token = isValidToken(key);

        // 2. 使用token进行定位
        return getIPInfo(token, ip, user_ip, source, coordSuffix, hasScene);
    }

    /**
     * 区县库定位
     * @param props 定位参数
     * @return 结果
     */
    @Override
    public GeoIP districtLocate(LocateProps props) {
        UserToken token = isValidDistrictToken(props.getKey());
        props.setUserToken(token);

        return getDistrictIPInfo(props);
    }


    /**
     * 获取区县库定位信息
     * @param props 定位属性
     * @return 定位结果
     */
    private GeoIP getDistrictIPInfo(LocateProps props) {
        final String logId = generateLogId();
        final long start = DateUtil.getNowMillis();

        UserToken token = props.getUserToken();
        GeoIP geo_ip;

        try {
            if (token.isTest()) {
                geo_ip = geoIPDao.getDistrictInfoNormal(props);
            } else {
                geo_ip = geoIPDao.getDistrictInfo(props);
            }

            DISTRICT_LOGGER.info("|{}|district|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|",
                                /*查询方式*/props.getSource(), /*此次查询唯一标识*/logId, token.getUserId(), /*客户端IP*/props.getUserIp(), token.getToken(),
                    geo_ip.getId(), /*用户查询IP*/props.getLocateIp(), /*1为IP定位这款产品的productId*/token.getProductId(),
                /*数据版本*/versionDao.getByAvailable(true).getPrivateVersion(), /*查询new Date(),*/
                /*耗时*/(Instant.now().toEpochMilli() - start), /*坐标系*/props.getCoordSuffix()
            );
            return geo_ip;
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }

        return null;
    }


    /**
     * 查询场景
     * @param props 查询参数
     * @return 场景
     */
    @Override
    public GeoIP getScene(LocateProps props) {
        UserToken token = isValidSceneToken(props.getKey());
        props.setUserToken(token);

        return getSceneInfo(props);
    }

    /**
     * 获取场景信息
     * @param props 查询参数
     * @return 场景
     */
    private GeoIP getSceneInfo(LocateProps props) {
        final String logId = generateLogId();
        final long start = DateUtil.getNowMillis();

        UserToken token = props.getUserToken();

        try {
            GeoIP geo_ip = geoIPDao.getSceneInfo(props);
            SCENE_LOGGER.info("|{}|scene|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|",
                                /*查询方式*/props.getSource(), /*此次查询唯一标识*/logId, token.getUserId(), /*客户端IP*/props.getUserIp(), token.getToken(),
                    geo_ip.getId(), /*用户查询IP*/props.getLocateIp(), /*2为IP场景这款产品的productId*/token.getProductId(),
                /*数据版本*/versionDao.getByAvailable(true).getPrivateVersion(), /*查询new Date(),*/
                /*耗时*/(Instant.now().toEpochMilli() - start), /*坐标系*/props.getCoordSuffix()
            );

            geo_ip.setMultiAreas(null);
            return geo_ip;
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }

        return null;
    }



    /**
     * 场景库token校验
     * @param key key
     * @return UserToken
     */
    private UserToken isValidSceneToken(String key) {
        // 1. token是否在黑名单里
        if (redisDao.getInvalidToken(key) != null) {
            throw new InvalidTokenException("Token不可用");
        }

        UserToken token = tokenService.getByKey(key);

        // 2. token有效性验证
        verifyToken(token, key);

        // 3. 调用频率限制 TODO

        // 4. token剩余次数
        if (!token.sceneCountValid()) {
            putInvalidToken(key, true);
            throw new InvalidTokenException("此token余量已用完");
        }

        return token;
    }


    /**
     * 区县库token校验
     * @param key key
     * @return UserToken
     */
    private UserToken isValidDistrictToken(String key) {

        // 1. token是否在黑名单里
        if (redisDao.getInvalidToken(key) != null) {
            throw new InvalidTokenException("Token不可用");
        }

        UserToken token = tokenService.getByKey(key);
        String redisKey = REDIS_DISTRICT_KEY_PREFIX + ":" + key;

        // 2. token有效性验证
        verifyToken(token, redisKey);

        // 3. 判断是否有区县定位
        if (!token.hasDistrictLocate()) {
            throw new InvalidTokenException("您还没有购买区县定位产品");
        }

        Integer dailyLimit = token.getDailyLimit();
        LOGGER.debug("token dailyLimit: {}", dailyLimit);

        if (null == dailyLimit) {
            throw new InvalidTokenException("token不可用");
        }

        int hourLimit = buildHourLimit(dailyLimit);

        // 4. 限速判断
        if (redisDao.isLimitedByHour(redisKey, hourLimit)) {
            throw new RequestFrequentlyException("查询速度过快");
        }

        // 5. 每日查询次数是否已满
        if (redisService.isFull(redisKey, dailyLimit)) {
            throw new RequestFrequentlyException("今日查询次数已满");
        }

        return token;
    }

    /**
     * 根据每日限制次数返回每小时限制
     * @param dailyLimit 每日限制
     * @return 每小时限制
     */
    private int buildHourLimit(Integer dailyLimit) {
        int hourLimit = -1;

        if (dailyLimit == 30000) {
            hourLimit = 3000;
        } else if (dailyLimit == 800000) {
            hourLimit = 80000;
        }

        return hourLimit;
    }

    /**
     * token有效性验证
     * @param key token
     */
    private UserToken isValidToken(String key) {

        // 1. token是否在黑名单里
        if (redisDao.getInvalidToken(key) != null) {
            throw new InvalidTokenException("Token不可用");
        }

        UserToken token = tokenDao.getByToken(key);

        // 2. token有效性验证
        verifyToken(token, key);

        // 3. 调用频率限制
        if (token.isTest()) {
            if (isLimited(key)) {
                throw new RequestFrequentlyException("测试包调用太频繁");
            }
        }

        // 4. token剩余次数
        if (token.getProductId() == 1 && (!token.testCountValid() && !token.countValid())) {
            putInvalidToken(key, true);
            throw new InvalidTokenException("此token余量已用完");
        }

        if (token.getProductId() == 3 && (!token.sceneCountValid())) {
            putInvalidToken(key, true);
            throw new InvalidTokenException("此token余量已用完");
        }

        return token;
    }

    private GeoIP getIPInfo(UserToken userToken, String ip, String user_ip, String source, String coordSuffix, Boolean hasScene)
            throws RequestFrequentlyException, InvalidTokenException {

        if (StringUtils.isBlank(coordSuffix)) {
            coordSuffix = "osm";
        }

        GeoIP geo_ip = null;
        final String logId = UUID.randomUUID().toString().replace("-", "");
        long start = Instant.now().toEpochMilli();
        Map<String, Object> params = new HashMap<>();
        params.put("coord", coordSuffix);

        if (userToken.getProductId() == 1L) {    //IP定位产品ID为1
            if (userToken.testCountValid()) {

                if (!redisDao.isUnLimited(userToken.getToken())) {
                    throw new RequestFrequentlyException("测试包调用太频繁");
                }
                params.put("minip", IPUtil.ipToLong(ip));
                params.put("token", userToken.getToken());

                if (hasScene) {
                    Long userId = userToken.getUserId();
                    UserToken sceneToken = tokenService.getByUserAndProduct(userId, 3L);
                    if (null != sceneToken) {
                        if (sceneToken.getSceneCounts() > 0) {
                            params.put("sceneToken", sceneToken.getToken());
                            geo_ip = geoIPDao.getByMinIPTestWithScene(params);
                        } else {
                            throw new InvalidTokenException("您的场景次数已用完");
                        }
                    }
                } else {
                    geo_ip = geoIPDao.getByMinIPTest(params);
                    geo_ip.setScene(null);
                }

                /*用户查询IP日志记录,需严格按照此格式写入日志,与数据库ipplus360_log的locate_log表字段严格对应*/
                RECORD_LOGGER.info("|{}|test|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|",
                                /*查询方式*/source, /*此次查询唯一标识*/logId, userToken.getUserId(), /*客户端IP*/user_ip, userToken.getToken(),
                        geo_ip.getId(), /*用户查询IP*/ip, /*1为IP定位这款产品的productId*/userToken.getProductId(),
                        /*数据版本*/versionDao.getByAvailable(true).getPrivateVersion(), /*查询new Date(),*/
                        /*耗时*/(Instant.now().toEpochMilli() - start), /*坐标系*/coordSuffix
                );

            } else if (userToken.countValid()) {
                params.put("minip", IPUtil.ipToLong(ip));
                params.put("token", userToken.getToken());

                if (hasScene) {
                    Long userId = userToken.getUserId();
                    UserToken sceneToken = tokenService.getByUserAndProduct(userId, 3L);
                    if (null != sceneToken) {
                        if (sceneToken.getSceneCounts() > 0) {
                            params.put("sceneToken", sceneToken.getToken());
                            geo_ip = geoIPDao.getByMinIPWithScene(params);
                        } else {
                            throw new InvalidTokenException("您的场景次数已用完");
                        }
                    } else {
                        throw new InvalidTokenException("请先购买场景后使用");
                    }
                } else {
                    geo_ip = geoIPDao.getByMinIP(params);
                    geo_ip.setScene(null);
                }

                /*用户查询IP日志记录,需严格按照此格式写入日志,与数据库ipplus360_log的locate_log表字段严格对应*/
                RECORD_LOGGER.info("|{}|normal|{}|{}|{}|{}|{}|{}|{}|{}|{}|",
                                /*查询方式*/source, /*此次查询唯一标识*/logId, userToken.getUserId(), /*客户端IP*/user_ip, userToken.getToken(),
                        geo_ip.getId(), /*用户查询IP*/ip, /*1为IP定位这款产品的productId*/userToken.getProductId(),
                        /*数据版本*/versionDao.getByAvailable(true).getPrivateVersion(), /*查询new Date(),*/
                        /*耗时*/(Instant.now().toEpochMilli() - start), /*坐标系*/coordSuffix
                );
            }
            return geo_ip;
        }
        return null;
    }


    @Override
    public String getLocation(String ip) {
        try {
            LoginLocation location = geoIPDao.getLocationByIp(IPUtil.ipToLong(ip));
            return location.getLocation();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 把无效token放入redis
     * @param token
     * @param timeout 是否设置超时
     */
    private void putInvalidToken(String token, boolean timeout) {
        if (redisDao.getInvalidToken(token) == null) {
            redisDao.putInvalidToken(token, timeout);
        }
    }

    /**
     *
     * @param key token
     * @return boolean
     */
    private boolean isLimited(String key) {
        return redisDao.islimited(key);
    }


    /**
     * token有效性验证
     * @param token UserToken
     */
    private void verifyToken(UserToken token, String key) {
        // 1. token为空
        if (token == null) {
            putInvalidToken(key, false);
            throw new InvalidTokenException("token不能为空");
        }

        // 2. token不可用
        if (!token.isAvailable()) {
            putInvalidToken(key, true);
            throw new InvalidTokenException("token不可用");
        }

        // 3. token不在有效期内
        if (!token.notExpired()) {
            throw new InvalidTokenException("token不在有效期内");
        }
    }

    private String generateLogId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}