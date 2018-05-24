package com.ipplus360.service;

import com.ipplus360.entity.ProductAttr;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Created by 辉太郎 on 2017/9/25.
 */
public interface ProductAttrService {

    List<ProductAttr> getTopAttrs(Long productId);

    List<ProductAttr> getAttrsByParent(Integer parentId);

    BigDecimal calculate(Integer[] ids);

    List<ProductAttr> getAttrsByIds(Integer[] ids);

    BigDecimal calculate(List<ProductAttr> attrs);

    List<ProductAttr> getPreviousVersions(Long productId);

    ProductAttr getById(Integer attrId);
}
