package com.ipplus360.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.channels.ByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件下载工具类
 * Created by 辉太郎 on 2017/9/6.
 */
public class DownloadUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(DownloadUtil.class);

    private final static String DEFAULT_PATH = "/home/mall/download";

    public static ByteChannel buildDownloadStream(String uri, String fileName) throws IOException {
        if (StringUtils.isEmpty(uri)) {
            uri = DEFAULT_PATH;
        }
        Path path = Paths.get(uri, fileName);
        LOGGER.info("path:" + path);

        if (!Files.exists(path)) {
            throw new FileNotFoundException(path.toString() + " not exist");
        }

        return Files.newByteChannel(path);
    }

    public static void setDownloadResponse(HttpServletResponse response, String fileName)
            throws UnsupportedEncodingException {
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" +
                URLEncoder.encode(fileName, Charset.forName("UTF-8").name()));
    }
}
