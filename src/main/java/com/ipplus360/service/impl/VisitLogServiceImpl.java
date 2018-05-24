package com.ipplus360.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ipplus360.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.VisitLogDao;
import com.ipplus360.entity.VisitLog;
import com.ipplus360.guanwang.dao.VisitDataDao;
import com.ipplus360.service.LocateLogService;
import com.ipplus360.service.VisitLogService;
import com.ipplus360.service.mail.MailService;
import com.ipplus360.util.IPUtil;

/**
 * Created by 辉太郎 on 2017/7/4.
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitLogServiceImpl.class);

    @Autowired
    private VisitLogDao visitLogDao;
    @Autowired
    private VisitDataDao visitDataDao;

    @Autowired
    private LocateLogService locateLogService;
    @Autowired
    private MailService mailService;

    @Value("${mallLogPath}")
    private String mallLogPath;
    @Value("${visitAnalysisEmail}")
    private String visitAnalysisEmail;
    @Value("${wangyong}")
    private String wangyong;

    @Override
    public int add(VisitLog visitLog) {
        return visitLogDao.add(visitLog);
    }

    @Override
    public int addBatch(List<VisitLog> visitLogList) {
        return visitLogDao.addBatch(visitLogList);
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void recordAndAnalysis() {

        String visitReg = "IndexController.java:51";
        String visitReg1 = "ProductController.java:57";
        String visitExcludeReg = "360JK yunjiankong";
        String visitExcludeReg2 = "yandex.ru";
        String prefix = "mall.log.";
        String path = locateLogService.getLogFile(mallLogPath, prefix);

        BufferedReader reader = null;
        File file = new File(path);
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            List<VisitLog> visitLogList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if ((line.contains(visitReg) || line.contains(visitReg1)) &&
                        !line.contains(visitExcludeReg) && !line.contains(visitExcludeReg2)) {
                    VisitLog visitLog = parseLine(line);
                    visitLogList.add(visitLog);
                    if ((visitLogList.size() % 1000) == 0) {
                        addBatch(visitLogList);
                        visitLogList.clear();
                    }
                }
            }
            if (!visitLogList.isEmpty()) {
                addBatch(visitLogList);
            }

            analysisAndSendMail();

        } catch (Exception e) {
            LOGGER.error("首页统计异常 {}", e);
            //e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("访问日志流关闭异常 {}", e);
                    //e.printStackTrace();
                }
            }
        }
    }

    private void analysisAndSendMail() {
        Date date = getYesterday();
        String subject = "埃文官网/商城首页每日流量统计";
        Long ipCounts = visitLogDao.getIPCounts(date);
        List<Map<String, Object>> sources = visitLogDao.getSources(date);
        Long countsFromGw = null;
        Long gwCounts = null;
        List<Map<String, Object>> gwSources = null;
        List<Map<String, Object>> gwSourceUrls = null;
        try {
            countsFromGw = visitLogDao.getCountsFromGw(date);
            gwCounts = visitDataDao.getIPCounts(date);
            gwSources = visitDataDao.getSources(date);
            gwSourceUrls = visitDataDao.getGwSourceUrls(date);
        } catch (Exception e) {
            LOGGER.error("gw exception {}", e.getMessage(), e);
        }

        Map<String, String> params = new HashMap<>();
        Map<String, Object> objparams = new HashMap<>();

        params.put("to", visitAnalysisEmail);
        params.put("cc", wangyong);
        params.put("subject", subject);
        objparams.put("ipCounts", ipCounts);
        objparams.put("countsFromGw", countsFromGw);
        objparams.put("visitTime", getYesterday());
        objparams.put("visitTime", date);
        objparams.put("sources", sources);
        objparams.put("gwCounts", gwCounts);
        objparams.put("gwSources", gwSources);
        objparams.put("gwSourceUrls", gwSourceUrls);
        objparams.put("template", "visitloganalysis.html");
        mailService.sendVisitAnalysisMail(params, objparams);
    }

    private VisitLog parseLine(String line) throws ParseException {
        String[] lineArr = line.split("\\|");
        String dateStr = lineArr[0].split(",")[0];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateStr);
        String ip = lineArr[1];
        if (!IPUtil.isIPv4(ip)) {
            ip = "";
        }
        String userAgent = "";
        String source = "";
        if (lineArr.length > 3) {
            source = lineArr[3];
        }
        if (source.length() > 200) {
            source = source.substring(0, 100);
        }
        if (lineArr[2].length() > 200) {
            userAgent = lineArr[2].substring(0, 100);
        }
        VisitLog visitLog = new VisitLog();
        visitLog.setIp(ip);
        visitLog.setUserAgent(userAgent);
        visitLog.setSource(source);
        visitLog.setVisitTime(date);
        return visitLog;
    }

    private Date getYesterday() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        date = cal.getTime();
        return date;
    }
}
