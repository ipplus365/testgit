package com.ipplus360.service.impl;

import com.ipplus360.dao.ProductAttrDao;
import com.ipplus360.entity.ProductAttr;
import com.ipplus360.service.ProductAttrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Created by 辉太郎 on 2017/9/25.
 */
@Service
public class ProductAttrServiceImpl implements ProductAttrService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ProductAttrDao productAttrDao;

    @Autowired
    public ProductAttrServiceImpl(ProductAttrDao productAttrDao) {
        this.productAttrDao = productAttrDao;
    }

    @Override
    public List<ProductAttr> getTopAttrs(Long productId) {
        return productAttrDao.getTopAttrs(productId);
    }

    @Override
    public List<ProductAttr> getAttrsByParent(Integer parentId) {
        return productAttrDao.getAttrsByParent(parentId);
    }

    @Override
    public BigDecimal calculate(Integer[] ids) {

        List<ProductAttr> attrs = getAttrsByIds(ids);

        BigDecimal price = new BigDecimal(0);
        price.setScale(2);
        for (ProductAttr attr : attrs) {
            price = price.add(attr.getPrice());
        }

        return price;
    }

    @Override
    public List<ProductAttr> getAttrsByIds(Integer[] ids) {
        return productAttrDao.getAttrsByIds(ids);
    }

    @Override
    public BigDecimal calculate(List<ProductAttr> attrs) {
        BigDecimal res = new BigDecimal(0);
        boolean isPreVer = false;
        boolean isBDCoord = false;
        BigDecimal bd09Price = new BigDecimal(3680.00);
        for (ProductAttr attr : attrs) {
            if (null != attr) {
                if ("version2".equals(attr.getAttrKey())) {
                    isPreVer = true;
                }
                if ("BD09".equals(attr.getAttrValue())) {
                    isBDCoord = true;
                    bd09Price = attr.getPrice();
                }
                res = res.add(attr.getPrice());
            }
        }

        if (isPreVer && isBDCoord) {
            res = res.subtract(bd09Price);
        }

        LOGGER.info("isPreVer {}, isBDCoord {}, bd09Price {}, priceRes {}", isPreVer, isBDCoord, bd09Price.toString(), res.toString());

        return res;
    }

    @Override
    public List<ProductAttr> getPreviousVersions(Long productId) {
        return productAttrDao.getPreviousVersions(productId);
    }

    @Override
    public ProductAttr getById(Integer attrId) {
        return productAttrDao.getById(attrId);
    }
}
