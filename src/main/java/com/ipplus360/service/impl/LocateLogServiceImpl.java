package com.ipplus360.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ipplus360.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.LogFileDao;
import com.ipplus360.dao.UserDao;
import com.ipplus360.dao.UserLogDao;
import com.ipplus360.entity.LogFile;
import com.ipplus360.entity.UserLog;
import com.ipplus360.service.IPService;
import com.ipplus360.service.LocateLogService;

/**
 * 读取定位日志文件，写入数据库
 * Created by 辉太郎 on 2017/6/16.
 */
@Service
public class LocateLogServiceImpl implements LocateLogService {

    public static final Logger LOGGER = LoggerFactory.getLogger(LocateLogServiceImpl.class);

    @Autowired
    private UserLogDao userLogDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private LogFileDao logFileDao;

    @Autowired
    private IPService ipService;

    @Value("${logPath}")
    private String logPath;   /*日志文件路径*/

    @Override
    public int add(UserLog userLog) {
        return userLogDao.add(userLog);
    }

    /**
     * 每天凌晨00：30执行
     * 从数据库查找已存在未入库的日志入库
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void getUnRecordLogFiles() {
        String token = "cKgx78xWaEEPaybXN1G3Hql64wHUbgdC2hl9ZYAK4EEYF5L92NhZx4wdPN4PPnTZ";
        try {
            ipService.locate("0.0.0.0", token, "127.0.0.1", "innerTest", "", false);
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }
        List<LogFile> logFiles = logFileDao.getUnRecord();
        LOGGER.info("log unrecorded file");
        for (LogFile logFile : logFiles) {
            String filePath = logFile.getFile();
            File file = new File(filePath);
            if (!file.exists()) {
                continue;
            }
            log2DB(file);
            logFileDao.update(logFile);
            LOGGER.info("record to db of {}", filePath);
            break;
        }

    }

    /**
     * 每天凌晨1：30执行
     */
    @Scheduled(cron = "0 30 1 * * ?")
    public void record() {
        String prefix = "record.log.";
        String filePath = getLogFile(logPath, prefix);
        File file = new File(filePath);
        if (!file.exists()) {
            LogFile logFile = new LogFile();
            logFile.setFile(filePath);
            logFile.setAttemptTime(new Date());
            logFile.setSuccess(false);
            logFileDao.add(logFile);
            LOGGER.info("{} not exist, log to db", filePath);
            return;
        }

        log2DB(file);
    }

    /**
     * 写入数据库
     * @param file
     */
    public void log2DB(File file) {
        BufferedReader reader = null;
        try {
            String prefix = "record.log.";
            LOGGER.info("import locate record to db at {} from {}", new Object[] {new Date(), getLogFile(logPath, prefix)});
            reader = new BufferedReader(new FileReader(file));
            String line;
            List<UserLog> userLogList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {

                String[] lineArr = line.split(",");
                String dateStr = lineArr[0];
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
                Date date = sdf.parse(dateStr);

                String locateLogLine = line.split("\\|", 2)[1];
                String[] locateArr = locateLogLine.split("\\|");

                UserLog userLog = parseArr(locateArr);
                userLog.setLogDate(date);
                userLogList.add(userLog);

                if ((userLogList.size() % 1000) == 0) {
                    userLogDao.addBatch(userLogList);
                    userLogList.clear();
                }
            }
            userLogDao.addBatch(userLogList);
            LOGGER.info("import locate record to db OK at {}", new Date());

        } catch (IOException |ParseException e) {
            LOGGER.error("{} 文件不存在 {}", new Object[] {file.getAbsolutePath(), e});
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("流关闭异常 {}", e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取前一天日志文件(绝对路径)
     * @return
     */
    @Override
    public String getLogFile(String path, String prefix) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = prefix + sdf.format(date);

        return path + fileName;
    }

    /**
     * 解析日志记录
     * @param locateArr
     * @return
     */
    public UserLog parseArr(String[] locateArr) {
        UserLog userLog = new UserLog();

        Long userId = Long.parseLong(locateArr[3]);
        User user = userDao.getById(userId);
        if (null == user) {
            userLog.setEmail("not exist");
        } else {
            userLog.setEmail(userDao.getById(userId).getEmail());
        }
        userLog.setSource(locateArr[0]);
        userLog.setTest(locateArr[1]);
        userLog.setLogId(locateArr[2]);
        userLog.setUserId(userId);
        userLog.setUserIp(locateArr[4]);
        userLog.setToken(locateArr[5]);
        userLog.setIpId(locateArr[6]);
        userLog.setLocateIp(locateArr[7]);
        userLog.setProductId(locateArr[8]);
        userLog.setVersion(locateArr[9]);
        userLog.setCost(Integer.parseInt(locateArr[10]));
        return userLog;
    }
}
