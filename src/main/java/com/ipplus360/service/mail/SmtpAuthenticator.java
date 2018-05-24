package com.ipplus360.service.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * smtp认证
 * Created by 辉太郎 on 2017/3/10.
 */
public class SmtpAuthenticator extends Authenticator {
    private String username;
    private String password;

    public SmtpAuthenticator() {
        super();
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
