package com.ipplus360.security.encrypt;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;

/**
 * 数据库加密
 * Created by sylar on 2017/2/12.
 */
public class EncryptedProperties implements FactoryBean {

    private Properties properties;

    @Override
    public Object getObject() throws Exception {
        return getProperties();
    }

    @Override
    public Class<?> getObjectType() {
        return Properties.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Properties getProperties() {
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        //System.out.println("user:" + user + " password:" + password);

        String decryptedUser = DECEnctyptUtil.decrypt(user, "ipplus360");
        String decryptedPwd = DECEnctyptUtil.decrypt(password, "ipplus360");
        properties.put("user", decryptedPwd);
        properties.put("password", decryptedPwd);
    }
}
