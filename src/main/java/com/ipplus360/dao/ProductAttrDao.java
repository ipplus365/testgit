package com.ipplus360.dao;

import com.ipplus360.entity.ProductAttr;

import java.util.List;

/**
 *
 * Created by 辉太郎 on 2017/9/25.
 */
public interface ProductAttrDao {

    List<ProductAttr> getTopAttrs(Long productId);

    List<ProductAttr> getTopValidAttrs(Long productId);

    List<ProductAttr> getAttrsByParent(Integer parentId);

    ProductAttr getById(Integer id);

    List<ProductAttr> getAttrsByIds(Integer[] ids);

    List<ProductAttr> getPreviousVersions(Long productId);
}
