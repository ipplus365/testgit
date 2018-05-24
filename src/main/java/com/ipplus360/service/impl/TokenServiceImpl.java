package com.ipplus360.service.impl;

import com.ipplus360.dao.RedisDao;
import com.ipplus360.dao.TokenDao;
import com.ipplus360.entity.Product;
import com.ipplus360.entity.UserToken;
import com.ipplus360.service.ProductService;
import com.ipplus360.service.TokenService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 辉太郎 on 2017/3/13.
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenDao;
    
    @Autowired private RedisDao redisDao;
    @Autowired
    private ProductService productService;

    @Override
    public UserToken getById(Long id) {
        return tokenDao.getById(id);
    }

    @Override
    public UserToken getByKey(String key) {

		UserToken token = redisDao.getUserToken(key);
		if (null == token) {
			token = tokenDao.getByToken(key);
			if (null != token) {
				redisDao.putUserToken(token);
			}
		}

		return token;
    }

    @Override
    public int add(UserToken userToken) {
        return tokenDao.add(userToken);
    }

    @Override
    public int update(UserToken userToken) {
        return tokenDao.update(userToken);
    }

	@Override
	public Set<UserToken> getByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("currPage", null);
		map.put("pageSize", null);
		return tokenDao.getByUserId(map);
	}

    @Override
    public boolean canLocate(Long userId) {
        Set<UserToken> tokens = getByUserId(userId);
        Long counts = 0L;
        for (UserToken token : tokens) {
            if(token.isAvailable() && token.canWebLocate()) {
                if (token.getTestCount() != null && token.getTestCount()>0) {
                    counts += token.getTestCount();
                }
                if (token.getCounts() != null && token.getCounts() > 0) {
                    counts += token.getCounts();
                }
            }
        }
        if (counts > 0) {
            return true;
        }
        return false;
    }

	@Override
	public List<Map> getForPerson(Long userId) {
		List<Map> tokenList=new ArrayList<Map>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		Set<UserToken> tokenSet= tokenDao.getByUserId(map);
		Iterator<UserToken> iter = tokenSet.iterator(); 
		while (iter.hasNext()) { 
			UserToken token = iter.next(); 
			Map tokenResult = new HashMap();
			tokenResult.put("id", token.getId());
			tokenResult.put("token", token.getToken());
			tokenResult.put("userId", token.getUserId());
			tokenResult.put("createdDate", token.getCreatedDate());
			tokenResult.put("updateDate", token.getUpdateDate());
			tokenResult.put("effectiveDate", token.getEffectiveDate());
			tokenResult.put("expireDate", token.getExpireDate());
			tokenResult.put("counts", token.getCounts());
			tokenResult.put("testCount", token.getTestCount());
			tokenResult.put("description", token.getDescription());
			tokenResult.put("isTest", token.isTest());

			tokenResult.put("productIds", token.getProductId());
			if(token.isAvailable()){
				tokenResult.put("available", "可用");
			}else{
				tokenResult.put("available", "不可用");
			}
			if(token.isTest()){
				tokenResult.put("test", "是");
			}else{
				tokenResult.put("test", "否");
			}
			tokenList.add(tokenResult);
		} 
		return tokenList;
	}

	@Override
	public UserToken getByToken(Long userId, String token) {
		return tokenDao.getByUserIdAndToken(userId,token);
	}

	@Override
	public String getValidKey(Long id) {
        Set<UserToken> tokens = getByUserId(id);
        for (UserToken token : tokens) {
            if (token.canWebLocate() && token.valid()) {
                return token.getToken();
            }
        }
        return null;
	}

	@Override
	public UserToken getByUserAndProduct(Long userId, Long productId) {
		return tokenDao.getByUserAndProduct(userId, productId);
	}

	@Override
	public UserToken getDistrictAPIByUser(Long userId, Long productId) {
		return tokenDao.getDistrictAPIByUser(userId, productId);
	}

	@Override
	public Set<UserToken> getTokensByUserId(Long id, int currPage, int pageSize) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("userId", id);
		map.put("currPage",(currPage-1)*pageSize);
		map.put("pageSize", pageSize);
		
		return tokenDao.getByUserId(map);
		
	}

	@Override
	public int getAllCountByUserId(Long id) {
		
		return tokenDao.getAllCountByUserId(id);
	}
}
