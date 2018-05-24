package com.ipplus360.web;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.*;
import com.ipplus360.service.*;
import com.ipplus360.service.redis.RedisService;
import com.ipplus360.util.DownloadUtil;
import com.ipplus360.util.IPUtil;
import com.ipplus360.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.util.Date;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by 辉太郎 on 2017/4/11.
 * <p>
 * 2017年9月7日 rewrite by LL（lijian@ipplus360.com）
 */
@Controller
@RequestMapping("/download")
public class DownLoadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static String FILE_SPLIT = "_";
    private final static String FILE_SPLIT_DOT = ".";

    @Autowired private IPDownloadService ipDownloadService;

    @Autowired private DownloadOrderService downloadOrderService;

    @Autowired private RedisService redisService;
    @Autowired private UserService userService;
    @Autowired private FileOrderService fileOrderService;
    @Autowired private GeoIPVersionService versionService;

    @Value("${download.dir}")
    private String downloadDir;
    
    /**
     * 判断下载是否超过限制
     * @param fileId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isDownLoad", method = RequestMethod.GET)
    public Result isDownLoad(HttpServletRequest request,HttpServletResponse response) {
    	  SessionUser sessionUser = SessionUtil.getSessionUser(request);
          if (null == sessionUser) {
              return new Result<>(false, "/user/toLogin", "请先登录");
          }
    	 if (isLimited(sessionUser, 5)) {
             return new Result(false, "今日下载次数已满,请明日再来,客服QQ：2415968308");
         }else{
        	 return new Result(true, "今日下载次数还有,可以下载");
         }	
    }
    

    /**
     * 新版下载放入个人中心已购数据栏
     * @param fileId
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public Result downloadFile(@RequestParam("fileId") Long fileId, HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            if (null == fileId) {
                return new Result(false, "参数不正确");
            }

            SessionUser sessionUser = SessionUtil.getSessionUser(request);
            if (null == sessionUser) {
                return new Result<>(false, "/user/toLogin", "请先登录");
            }
            String userIp = IPUtil.getUserIP(request);
            User user = SessionUtil.convertToUser(sessionUser, userService);
            if (StringUtils.isEmpty(user.getMobile())) {
                return new Result(false, "您必须完善手机号之后才能下载");
            }

            Long userId = user.getId();

            FileOrder fileOrder = fileOrderService.getByUserIdAndFileId(userId, fileId);

            if (null == fileOrder) {
                return new Result(false, "您没有下载此文件的权限");
            }

            String version = versionService.getCurrentVersion().getPublicVersion();
            if (fileOrder.isFree()) {
                if (!version.equals(fileOrder.getVersion())) {
                    return new Result(false, "请下载当前免费版本");
                }
            }

            if (isLimited(sessionUser, 3)) {
                return new Result(false, "今日下载次数已满,请明日再来,客服QQ：2415968308");
            }
            String filePath = fileOrder.getFile();
            String fileName = filePath;
            if (!StringUtils.isEmpty(filePath) && filePath.contains("/")) {
                fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
            }
            try (ByteChannel channel = DownloadUtil.buildDownloadStream(downloadDir, filePath)) {
                DownloadUtil.setDownloadResponse(response, fileName);
                resDownload(response, channel);

                // 记录用户下载记录
                recordDownload(sessionUser, userIp);
                return new Result(true, "下载成功");
            } catch (IOException e) {
                logger.error("{}", e.getMessage(), e);
                return new Result(false, "数据更新中, 请稍候再试!");
            }

        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
            return new Result(false, "下载失败");
        }
    }

    /***
     * 下载记录
     * @param user  用户
     * @param ip  用户IP
     */
    private void recordDownload(SessionUser user, String ip) {
        DownloadOrder downloadOrder = new DownloadOrder();
        downloadOrder.setProductId(5L);
        downloadOrder.setUserId(user.getId());
        downloadOrder.setDateCreated(new Date());
        downloadOrder.setIp(ip);
        downloadOrder.setEmail(user.getEmail());
        downloadOrderService.add(downloadOrder);

        logger.info("{}", downloadOrder);
    }

    /**
     * 下载
     *
     * @param response 获取输出流
     * @param channel  文件Channel
     */
    private void resDownload(HttpServletResponse response, ByteChannel channel) {
        try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            ByteBuffer buffer = ByteBuffer.allocate(4096);

            while (channel.read(buffer) > 0) {
                buffer.flip();
                bos.write(buffer.array());
                buffer.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断用户当日下载次数是否已满
     *
     * @param user       用户
     * @param limitCount 限制次数
     * @return 是否受限
     */
    private boolean isLimited(SessionUser user, int limitCount) {
        return redisService.downloadLimited(user.getEmail(), limitCount);
    }

    /**
     * 生成所要下载IP库文件名
     *
     * @param coord 坐标系
     * @return 文件名
     */
    private String buildFileName(String coord) {
        String suffix = "osm";
        if ("BD09".equalsIgnoreCase(coord)) {
            suffix = "bd";
        }
        IPDownload ipDownload = ipDownloadService.getByAvailable(true);
        String type = ipDownload.getFileType();
        if (isEmpty(type)) {
            type = "zip";
        }
        String fileName = ipDownload.getFileName() + FILE_SPLIT + suffix + FILE_SPLIT_DOT + type;

        return fileName;
    }

        /*@ResponseBody
    @RequestMapping(value = "/ip/district", method = RequestMethod.GET)
    public Result download(@RequestParam("coord") String coord, @RequestParam("type") String type,
                           HttpServletRequest request, HttpServletResponse response) {

        String ip = IPUtil.getUserIP(request);
        SessionUser user = SessionUtil.getSessionUser(request);
        // 1. 受限用户不能下载，直接返回
        if (isLimited(user, 3)) {
            return new Result(false, "今日下载次数已满,请明日再来,客服QQ：2415968308");
        }

        // 2. 根据用户选择版本生成IP库名称
        String fileName = buildFileName(coord);

        // 3. 创建下载流, 开始下载
        try (ByteChannel channel = DownloadUtil.buildDownloadStream(downloadDir, fileName)) {
            DownloadUtil.setDownloadResponse(response, fileName);
            resDownload(response, channel);

            // 4. 记录用户下载记录
            recordDownload(user, ip);
            return new Result(true, "下载成功");
        } catch (IOException e) {
            logger.error("{}", e);
            return new Result(false, "数据更新中, 请稍候再试!");
        }

    }*/

}