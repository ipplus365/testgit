package com.ipplus360.service;

import com.ipplus360.entity.FileOrder;

import java.util.List;

/**
 * Created by 辉太郎 on 2017/10/14.
 */
public interface FileOrderService {

    int add(FileOrder fileOrder);

    FileOrder getById(Long id);

    List<FileOrder> getByUserId(Long userId,int currPage,int pageSize);

    List<FileOrder> getByOrderId(Long orderId);

    /**
     * 生成区县库下载文件路径
     * @param fileOrder
     */
    void processAdd(FileOrder fileOrder);

    FileOrder getByUserIdAndFileId(Long userId, Long fileId);

    FileOrder getByAttrIdsAndVersion(Long userId, String attrIds, String currVer);

	int getAllCountByUserId(Long userId);
}
