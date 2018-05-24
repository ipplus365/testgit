package com.ipplus360.service.schedule;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.DownloadOrderDao;
import com.ipplus360.dao.OrderDao;
import com.ipplus360.dao.OrderItemDao;
import com.ipplus360.dao.ProductDao;
import com.ipplus360.dao.TokenDao;
import com.ipplus360.entity.DownloadOrder;
import com.ipplus360.entity.OrderUser;
import com.ipplus360.entity.Product;
import com.ipplus360.entity.User;
import com.ipplus360.entity.UserToken;
import com.ipplus360.pay.util.UtilDate;
import com.ipplus360.service.UserService;
import com.ipplus360.service.mail.MailService;
import com.ipplus360.util.DateUtil;

/**
 * 邮件定时任务
 * Created by 辉太郎 on 2017/6/22.
 */
@Service
public class MailTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailTask.class);

    @Autowired
    private DownloadOrderDao downloadOrderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @Value("${downloadAndBuyEmail}")
    private String downloadAndBuyEmail;
    @Value("${wangyong}")
    private String wangyong;

    @Scheduled(cron = "0 30 1 * * ?")
    public void userDownloadAndBuyEmailNotify() {

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        date = cal.getTime();
        List<User> newUsers = userService.getNewUsers(date);
        List<DownloadOrder> downloadOrders = downloadOrderDao.getByDay(date);
        List<OrderUser> orders = orderItemDao.getOrdersByDay(date);

        for (DownloadOrder downloadOrder : downloadOrders) {
            User user = userService.getByEmail(downloadOrder.getEmail());
            if (newUsers.contains(user)) {
                downloadOrder.setNewUser(true);
            } else {
                downloadOrder.setNewUser(false);
            }
        }

        for (OrderUser orderUser : orders) {
            User user = userService.getByEmail(orderUser.getEmail());
            orderUser.setDateCreated(user.getDateCreated());
            if (newUsers.contains(user)) {
                orderUser.setNewUser(true);
            } else {
                orderUser.setNewUser(false);
            }
        }


        int newUserCounts = 0;
        if (newUsers != null) {
            newUserCounts = newUsers.size();
        }
        LOGGER.info("task: downloadAndBuy downloadOrders size {}", downloadOrders.size());
        LOGGER.info("task: downloadAndBuy orders size {}", orders.size());
        mailService.sendDownloadAndBuyEmail(downloadOrders, orders, downloadAndBuyEmail, newUserCounts);
    }

    /**
     * 余额邮件提醒，凌晨两点
     * 测试包小于500次，正式包小于100000次
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void balanceNotify() {
        List<UserToken> tokens = tokenDao.getNotifyTokens();
        for (UserToken token : tokens) {
            //List<Product> products = productDao.getByIds(token.getProductIds());
            Product product = productDao.getById(token.getProductId());
            User user = userService.getById(token.getUserId());
            mailService.sendBalanceNotifyMail(user, token, product);
            token.setNotified(true);
            tokenDao.update(token);
        }

        LOGGER.info("task: balance notify {} balance notify email has been sent successfully at {}", new Object[] {tokens.size(), UtilDate.getDateFormatter()});
    }

    /**
     * 每周统计
     * 每周一统计上周新注册用户量,免费库下载量,订单,定位数据
     */
    @Scheduled(cron = "0 0 3 ? * MON")
    public void statisticsWeekly() {
        LocalDate now = LocalDate.now();
        LocalDate lastMonday = DateUtil.getLastMonday(now);
        int week = DateUtil.getWeekOfYear(now);
        String subject = "商城每周数据统计";
        int userCount = userService.userStatisticsWeekly(lastMonday);
        int downloadCount = downloadOrderDao.downloadStatisticsWeekly(lastMonday);
        Map<String, Object> orderStatistics = orderDao.orderStatisticsWeekly(lastMonday);

        Map<String, String> params = new HashMap<>();
        Map<String, Object> objparams = new HashMap<>();

        params.put("to", downloadAndBuyEmail);
        params.put("cc", wangyong);
        params.put("subject", subject);
        objparams.put("userCount", userCount);
        objparams.put("downloadCount", downloadCount);
        objparams.put("statistics", orderStatistics);
        objparams.put("monday", lastMonday);
        objparams.put("sunday", DateUtil.getLastSunday(now));
        objparams.put("week", week);
        objparams.put("template", "weekly.html");

        mailService.sendEmail(params, objparams);

        LOGGER.info("task: weekly statistics of {} week email has been sent successfully at {}", week, DateUtil.getNow());
    }

    /**
     * 每月统计
     * 每月一号统计上个月新注册用户量,免费库下载量,订单,定位数据
     */
    @Scheduled(cron = "0 0 3 1 * *")
    public void statisticsMonthly() {

        LocalDate now = LocalDate.now();
        LocalDate start = DateUtil.getLastMonthStart(now);
        int month = DateUtil.getMonth(start);
        String subject = "商城每月数据统计";
        int userCount = userService.userStatisticsMonthly(start);
        int downloadCount = downloadOrderDao.downloadStatisticsMonthly(start);
        Map<String, Object> orderStatistics = orderDao.orderStatisticsMonthly(start);

        Map<String, String> params = new HashMap<>();
        Map<String, Object> objparams = new HashMap<>();

        params.put("to", downloadAndBuyEmail);
        params.put("cc", wangyong);
        params.put("subject", subject);
        objparams.put("userCount", userCount);
        objparams.put("downloadCount", downloadCount);
        objparams.put("statistics", orderStatistics);
        objparams.put("start", start);
        objparams.put("end", DateUtil.getLastMonthEnd(now));
        objparams.put("month", month);
        objparams.put("template", "monthly.html");

        mailService.sendEmail(params, objparams);

        LOGGER.info("task: monthly statistics of {} month email has been sent successfully at {}", month, DateUtil.getNow());
    }

}
