package com.ipplus360.dao;

import com.ipplus360.entity.IPDownload;

/**
 * Created by 辉太郎 on 2017/4/14.
 */
public interface IPDownLoadDao {
    IPDownload getByVersionId(Long id);

    IPDownload getByAvailable(boolean available);
}
