package com.ipplus360.service.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ipplus360.entity.DownloadOrder;
import com.ipplus360.entity.Order;
import com.ipplus360.entity.OrderItem;
import com.ipplus360.entity.OrderUser;
import com.ipplus360.entity.Product;
import com.ipplus360.entity.User;
import com.ipplus360.entity.UserToken;
import com.ipplus360.pay.util.UtilDate;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 发送邮件
 * Created by 辉太郎 on 2017/3/10.
 */
@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${emailList}")
    private String emailList;
    private String fromEmail;

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.freeMarkerConfigurer = freeMarkerConfigurer;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    @Deprecated
    public void sendMail(User user, String subject, String path) {
       /* StringBuffer sb = new StringBuffer();
        sb.append(toEmail);
        sb.append("，您好！\\n\\n感谢您在埃文IP定位商城注册，您的帐号已成功创建，但在使用之前须先激活您的帐号。\\n要激活帐号请直接点击以下链接，或把它复制到浏览器中打开：\\n\\n");
        sb.append("<a href='http://192.168.1.234:9000/user/active?action=active&activation='");
        sb.append(HeaderTokenizer.Token);
        sb.append("\\n\\n帐号激活后即可开始登陆使用。");*/

        String toEmail = user.getEmail();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(getMailContent(user, path), true);

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("Sending email to {} failed: Excpetion occured: {}", toEmail, e.getMessage());
            e.printStackTrace();
        }

    }

    @Deprecated
    public void sendCheckMail(String email, String subject, int checkCode) {
        String toEmail = email;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(getCheckMailContent(email, checkCode), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("Sending email to {} failed: Excpetion occured: {}", toEmail, e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 发送重置密码链接
     *
     * @param email   用户邮箱
     * @param subject
     * @param token   重置密码token
     */
    @Deprecated
    public void sendResetEmail(String email, String subject, String token, String path) {
        String toEmail = email;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(getResetEmailContent(email, token, path), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("Sending email to {} failed: Excpetion occured: {}", toEmail, e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 购买成功发送账单信息
     */
    @Deprecated
    public void sendOrderEmail(String email, String subject, Order order, User user, Map payResult) {
        String toEmail = email;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(getOrderEmailContent(email, order, user, payResult), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("Sending email to {} failed: Excpetion occured: {}", toEmail, e.getMessage());
            e.printStackTrace();
        }

    }

    @Deprecated
    public void sendWarningMail(String email, String subject, Map payResult) {
        String toEmail = email;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(getWarmingMailContent(email, payResult), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("Sending email to {} failed: Excpetion occured: {}", toEmail, e.getMessage());
            e.printStackTrace();
        }

    }

    @Deprecated
    public void sendDownloadMail(String email, String subject, Map downloadResult) {
        String toEmail = email;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(getDownloadMailContent(email, downloadResult), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("Sending email to {} failed: Excpetion occured: {}", toEmail, e.getMessage());
            e.printStackTrace();
        }

    }

    @Deprecated
    public void sendResetTokenMail(String email) {
        String toEmail = email;
        String subject = "埃文商城Token重置提醒";
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(getResetTokenContent(email), true);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            LOGGER.error("Sending email to {} failed: Excpetion occured: {}", toEmail, e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 获取邮箱验证码邮件模版
     *
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @Deprecated
    private String getCheckMailContent(String email, int checkCode) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("checkMail.html");
        Map params = new HashMap<>();

        params.put("email", email);
        params.put("checkCode", String.valueOf(checkCode));

        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    /**
     * 获取邮件模版
     *
     * @param user
     * @param path web应用访问路径
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @Deprecated
    private String getMailContent(User user, String path) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("regMail.html");
        Map<String, String> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("token", user.getToken());
        params.put("path", path);
        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    @Deprecated
    private String getResetEmailContent(String emial, String token, String path) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("resetPwd.html");
        Map<String, String> params = new HashMap<>();
        params.put("email", emial);
        params.put("token", token);
        params.put("path", path);
        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    /**
     * 购买成功发送账单邮件模版
     */
    @Deprecated
    private String getOrderEmailContent(String emial, Order order, User user, Map payResult) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("orderMail.jsp");

        List<OrderItem> orderItems = order.getOrderItems();

        Map params = new HashMap<>();
        params.put("orderItems", orderItems);
        params.put("order", order);
        params.put("user", user);
        params.put("payResult", payResult);
        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    /**
     * 获取邮箱验证码邮件模版
     *
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @Deprecated
    private String getWarmingMailContent(String email, Map payResult) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("warmingMail.html");
        Map<String, String> params = new HashMap<>();
        params.put("subject", (String) payResult.get("subject"));
        params.put("out_trade_no", (String) payResult.get("out_trade_no"));
        params.put("trade_no", (String) payResult.get("trade_no"));
        params.put("trade_status", (String) payResult.get("trade_status"));
        params.put("total_fee", (String) payResult.get("total_fee"));
        params.put("body", (String) payResult.get("body"));
        params.put("id", (String) payResult.get("id"));
        params.put("buyer_email", (String) payResult.get("buyer_email"));
        params.put("gmt_payment", (String) payResult.get("gmt_payment"));
        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    /**
     * 获取邮箱验证码邮件模版
     *
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @Deprecated
    private String getDownloadMailContent(String email, Map downloadResult) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("downloadMail.html");
        Map<String, String> params = new HashMap<>();
        params.put("email", (String) downloadResult.get("email"));
        params.put("ipAddress", (String) downloadResult.get("ipAddress"));
        params.put("downloadTime", (String) downloadResult.get("downloadTime"));
        params.put("fileName", (String) downloadResult.get("fileName"));
        params.put("stats", (String) downloadResult.get("stats"));
        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    /**
     * 获取每日下载和购买订单模版
     *
     * @param downloadOrders
     * @param orders
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @Deprecated
    private String getDownloadAndBuyEmailContent(List<DownloadOrder> downloadOrders, List<OrderUser> orders, int newUsers) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("orderStatistics.html");

        Map params = new HashMap<>();
        params.put("downloadOrders", downloadOrders);
        params.put("orders", orders);
        params.put("newUsers", newUsers);
        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    private String getResetTokenContent(String email) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("resetToken.html");
        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
    }


    /**
     * 发送每日下载和购买订单的邮件
     *
     * @param downloadOrders
     * @param orders
     */
    @Deprecated
    public void sendDownloadAndBuyEmail(List<DownloadOrder> downloadOrders, List<OrderUser> orders, String downloadAndBuyEmail, int newUsers) {
        String subject = "埃文商城每日免费库下载和购买订单统计信息";
        try {
            Map<String, String> params = new HashMap<>();
            params.put("from", fromEmail);
            params.put("to", downloadAndBuyEmail);
            params.put("cc", emailList);
            params.put("subject", subject);
            params.put("content", getDownloadAndBuyEmailContent(downloadOrders, orders, newUsers));

            mailSender.send(createHelper(params));

        } catch (Exception e) {
            LOGGER.error("每日统计邮件发送失败", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 余量提醒邮件
     *
     * @param user
     * @param token
     * @param product
     */
    @Deprecated
    public void sendBalanceNotifyMail(User user, UserToken token, Product product) {
        String subject = "埃文商城余量温馨提醒";
        String email = user.getEmail();
        try {
            Map<String, Object> contentParams = new HashMap<>();
            contentParams.put("token", token);
            contentParams.put("email", email);
            contentParams.put("product", product);
            contentParams.put("date", UtilDate.getDateFormatter());
            contentParams.put("template", "balancenotify.html");
            String mailHtml = getEmailContent(contentParams);

            Map<String, String> params = new HashMap<>();
            params.put("from", fromEmail);
            params.put("to", email);
            params.put("subject", subject);
            params.put("content", mailHtml);

            mailSender.send(createHelper(params));
        } catch (Exception e) {
            LOGGER.error("余量邮件发送失败{} {}", new Object[]{email, token.getToken()});
            e.printStackTrace();
        }
    }

    /**
     * 商城首页每日访问统计邮件
     *
     * @param objParams
     */
    @Deprecated
    public void sendVisitAnalysisMail(Map<String, String> params, Map<String, Object> objParams) {
        try {
            String mailHtml = getEmailContent(objParams);
            params.put("from", fromEmail);
            params.put("content", mailHtml);
            mailSender.send(createHelper(params));
        } catch (Exception e) {
            LOGGER.error("商城首页每日统计邮件发送失败");
            e.printStackTrace();
        }
    }

    public void sendEmail(Map<String, String> params, Map<String, Object> objparams) {
        String subject = params.get("subject");

        try {
            String mailHtml = getEmailContent(objparams);
            params.put("from", fromEmail);
            params.put("content", mailHtml);
            mailSender.send(createHelper(params));

        } catch (MessagingException | IOException e) {
            LOGGER.error("{} 发送失败", subject, e);
        } catch (TemplateException e) {
            LOGGER.error("{} 模版异常，发送失败", subject, e);
        }
    }

    private String getEmailContent(Map<String, Object> params) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate((String) params.get("template"));

        String mailHtml = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        return mailHtml;
    }

    public MimeMessage createHelper(Map<String, String> params) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        String cc = params.get("cc");

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom(params.get("from"));
        messageHelper.setTo(params.get("to"));
        if (StringUtils.isNotBlank(cc)) {
            messageHelper.setCc(cc.split(","));
        }
        messageHelper.setSubject(params.get("subject"));
        messageHelper.setText(params.get("content"), true);
        return mimeMessage;
    }
}
