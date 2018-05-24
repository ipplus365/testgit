package com.ipplus360.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ipplus360.entity.UserToken;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 辉太郎 on 2017/3/13.
 */
public interface TokenDao {

    UserToken getById(Long id);

    UserToken getByToken(String token);

    UserToken getByUserIdAndToken(Long userId,String token);

    Set<UserToken> getByUserId(Map<String,Object> map);

    int add(UserToken userToken);

    int update(UserToken userToken);

    List<UserToken> getNotifyTokens();

    UserToken getByUserAndProduct(@Param("uid") Long userId, @Param("pid") Long productId);

    UserToken getDistrictAPIByUser(@Param("uid") Long userId, @Param("pid") Long productId);

	int getAllCountByUserId(Long id);
}
