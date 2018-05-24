package com.ipplus360.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ipplus360.entity.FileOrder;

/**
 * Created by 辉太郎 on 2017/10/14.
 */
public interface FileOrderDao {

    int add(FileOrder fileOrder);

    FileOrder getById(Long id);

    List<FileOrder> getByUserId(Map<String,Object> map);

    List<FileOrder> getByOrderId(Long orderId);

    FileOrder getByUserIdAndFileId(@Param("userId") Long userId, @Param("fileId") Long fileId);

    FileOrder getByAttrIdsAndVersion(@Param("userId") Long userId, @Param("attrIds") String attrIds, @Param("currVer") String currVer);

	int getAllCountByUserId(Long userId);

}
