package com.ipplus360.security.encrypt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/3/15.
 */
public class PasswordHashTest {

    @Test
    public void createHash() throws Exception {
        String hash = PasswordHash.createHash("123123" + "dde235f640bd781e9b882f21fd2d2c196791b7ef8d5e989b");
        System.out.println(hash);
    }

    @Test
    public void validatePassword() throws Exception {
        boolean b = PasswordHash.validatePassword("123123" + "dde235f640bd781e9b882f21fd2d2c196791b7ef8d5e989b", "1000:da39b16577348178f3b01568fd9b6c574b0a388de0b0daa4:5eb4a212ad24f3b793af4595c8f26abdfcb3869bad013c6a");
        System.out.println(b);
    }

}