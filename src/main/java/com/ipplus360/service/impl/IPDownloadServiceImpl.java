package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.IPDownLoadDao;
import com.ipplus360.entity.IPDownload;
import com.ipplus360.service.IPDownloadService;

/**
 * Created by 辉太郎 on 2017/4/14.
 */
@Service
public class IPDownloadServiceImpl implements IPDownloadService {
    @Autowired
    private IPDownLoadDao ipDownLoadDao;

    @Override
    public IPDownload getByVersionId(Long versionId){
        return ipDownLoadDao.getByVersionId(versionId);
    }

    @Override
    public IPDownload getByAvailable(boolean available){

        return ipDownLoadDao.getByAvailable(available);
    }
}
