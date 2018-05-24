package com.ipplus360.web;

import com.ipplus360.dto.Result;
import com.ipplus360.entity.GeoIPVersion;
import com.ipplus360.service.GeoIPVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 版本
 * Created by 辉太郎 on 2017/9/6.
 */
@Controller()
@RequestMapping("/version")
public class VersionController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired private GeoIPVersionService versionService;

    @ResponseBody
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Result currentVersion() {
        try {
            GeoIPVersion version = versionService.getCurrentVersion();
            return new Result<>(true, version, "");
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
            return new Result(false, "获取版本信息失败");
        }
    }
}
