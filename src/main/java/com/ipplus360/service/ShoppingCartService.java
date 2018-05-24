package com.ipplus360.service;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.CartItem;
import com.ipplus360.entity.ProductAttr;
import com.ipplus360.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * 购物车服务接口</br>
 * Created by 辉太郎 on 2017/3/06.</br>
 * @author wangguoyan
 */
public interface ShoppingCartService {

	/**
	 * 根据ID获取购物车
	 * @param id
	 * @return
	 */
	ShoppingCart getById(Long id);

	/**
	 * 根据用户ID获取购物车
	 * @param userId
	 * @return
	 */
	ShoppingCart getByUserId(Long userId);

	/**
	 * 获取所有购物车
	 * @return
	 */
	List<ShoppingCart> getAll();

	/**
	 * 根据条件筛选购物车
	 * @param entity
	 * @return
	 */
	List<ShoppingCart> getAll(ShoppingCart entity);

	/**
	 * 更新购物车
	 * @param entity
	 * @return
	 */
	int update(ShoppingCart entity);

//	void delete(Long[] ids);

//	int delete(Long id);
	
	/**
	 * 添加购物车
	 * @param entity
	 * @return
	 */
	int insert(ShoppingCart entity);

	Map<String,Object> insert(SessionUser user, ShoppingCart cart, Long productId, Long pricePackageId,
							  Integer accuracyId, Integer itemNum,String cartItemType, Integer freeApi);

	ShoppingCart getCountByUserId(Long id);

    void addDistrict(SessionUser sessionUser, ShoppingCart cart, Long productId, Integer coordId, Integer versionId, Integer hId);

    Map<String, Object> addSceneLibrary(SessionUser sessionUser, ShoppingCart cart, Long productId, List<Long> priceId, Integer accuracyId,
			Integer productCount);

    List<ProductAttr> getAttrsByIds(String attrIds);

	void getIPDistrictDownloadInfo(List<CartItem> items);
}
