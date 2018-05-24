package com.ipplus360.service.captcha;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.octo.captcha.image.ImageCaptcha;

/**
 * 自定义Gimpy类，实现验证码不区分大小写
 * Created by 辉太郎 on 2017/3/6.
 */
public class CustomGimpy extends ImageCaptcha implements Serializable {

    private String response;
    protected CustomGimpy(String question, BufferedImage challenge, String response) {
        super(question, challenge);
        this.response = response;
    }

    @Override
    public final Boolean validateResponse(Object response) {
        return null != response && response instanceof String?this.validateResponse((String)response):Boolean.FALSE;
    }

    private final Boolean validateResponse(String response) {
        return new Boolean(response.equalsIgnoreCase(this.response));
    }
}
