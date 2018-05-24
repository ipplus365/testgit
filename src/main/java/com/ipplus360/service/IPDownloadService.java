package com.ipplus360.service;

import com.ipplus360.entity.IPDownload;

/**
 * Created by 辉太郎 on 2017/4/14.
 */
public interface IPDownloadService {

    IPDownload getByVersionId(Long versionId);

    IPDownload getByAvailable(boolean available);
}
