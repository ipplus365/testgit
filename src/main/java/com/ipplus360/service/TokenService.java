package com.ipplus360.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ipplus360.entity.UserToken;

/**
 * Created by 辉太郎 on 2017/3/13.
 */
public interface TokenService {

    UserToken getById(Long id);

    UserToken getByKey(String key);

    UserToken getByToken(Long userId,String token);

    Set<UserToken> getByUserId(Long userId);

    int add(UserToken userToken);

    int update(UserToken userToken);

    boolean canLocate(Long userId);

	List<Map> getForPerson(Long userId);

    /**
     * 随机获取用户可用key
     * @param id 用户id
     * @return 返回一个可用key
     */
    String getValidKey(Long id);

    UserToken getByUserAndProduct(Long userId, Long productId);

    UserToken getDistrictAPIByUser(Long userId, Long productId);

	Set<UserToken> getTokensByUserId(Long id, int currPage, int pageSize);

	//获得用户token总数量
	int getAllCountByUserId(Long id);

}
