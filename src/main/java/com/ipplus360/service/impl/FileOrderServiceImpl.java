package com.ipplus360.service.impl;

import com.alibaba.fastjson.JSON;
import com.ipplus360.dao.FileOrderDao;
import com.ipplus360.dao.GeoIPVersionDao;
import com.ipplus360.dao.ProductAttrDao;
import com.ipplus360.entity.FileOrder;
import com.ipplus360.entity.GeoIPVersion;
import com.ipplus360.entity.ProductAttr;
import com.ipplus360.service.FileOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 辉太郎 on 2017/10/14.
 */
@Service
public class FileOrderServiceImpl implements FileOrderService {

    @Autowired
    private FileOrderDao fileOrderDao;
    @Autowired
    private ProductAttrDao attrDao;
    @Autowired
    private GeoIPVersionDao versionDao;

    @Override
    public int add(FileOrder fileOrder) {
        return fileOrderDao.add(fileOrder);
    }

    @Override
    public FileOrder getById(Long id) {
        return fileOrderDao.getById(id);
    }

    @Override
    public List<FileOrder> getByUserId(Long userId, int currPage, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("currPage", (currPage - 1) * pageSize);
        map.put("pageSize", pageSize);

        return fileOrderDao.getByUserId(map);
    }

    @Override
    public List<FileOrder> getByOrderId(Long orderId) {
        return fileOrderDao.getByOrderId(orderId);
    }

    @Override
    public void processAdd(FileOrder fileOrder) {
        String attrs = fileOrder.getAttrs();
        List<ProductAttr> productAttrs = JSON.parseArray(attrs, ProductAttr.class);

        Map<String, String> params = new HashMap<>();
        GeoIPVersion geoIPVersion = versionDao.getByAvailable(true);
        for (ProductAttr attr : productAttrs) {
            String type = attr.getAttrType();
            if (type.equals("osm")) {
                params.put("coord", "osm");
            } else if (type.equals("bd")) {
                params.put("coord", "bd");
            } else if (type.equals("current")) {
                String version = geoIPVersion.getPublicVersion().substring(0, 6);
                params.put("version", version);
            }
            // 历史版本 v2.0.6
            if (type.startsWith("v")) {
                params.put("pversion", type);
            }
        }

        StringBuilder file = new StringBuilder("/district/IP");
        if (StringUtils.isNotBlank(params.get("version"))) {
            file.append("_").append(params.get("version").trim());
        } else if (StringUtils.isNotBlank(params.get("pversion"))) {
            file.append("_").append(params.get("pversion").trim());
        }

        file.append("_").append(params.get("coord")).append(".zip");
        fileOrder.setFile(file.toString());
        add(fileOrder);
    }

    @Override
    public FileOrder getByUserIdAndFileId(Long userId, Long fileId) {
        return fileOrderDao.getByUserIdAndFileId(userId, fileId);
    }

    @Override
    public FileOrder getByAttrIdsAndVersion(Long userId, String attrIds, String currVer) {
        return fileOrderDao.getByAttrIdsAndVersion(userId, attrIds, currVer);
    }

    @Override
    public int getAllCountByUserId(Long userId) {
        return fileOrderDao.getAllCountByUserId(userId);
    }
}
