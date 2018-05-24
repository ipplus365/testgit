package com.ipplus360.service.captcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

/**
 * 自定义验证码生成类替换GenericManageableCaptchaService
 * Created by 辉太郎 on 2017/3/6.
 */
public class CustomGenericManageableCaptchaService extends GenericManageableCaptchaService {
    public CustomGenericManageableCaptchaService(CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize) {
        super(captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize);
    }

    @Override
    public Boolean validateResponseForID(String ID, Object response) throws CaptchaServiceException {
        if(!this.store.hasCaptcha(ID)) {
            throw new CaptchaServiceException("Invalid ID, could not validate unexisting or already validated captcha");
        }
        Boolean valid = this.store.getCaptcha(ID).validateResponse(response);
        //this.store.removeCaptcha(ID);

        return valid;
    }

    /**
     * 移除session中验证码信息
     * @param sessionId
     */
    public void removeCaptcha(String sessionId) {
        if (sessionId != null && this.store.hasCaptcha(sessionId)) {
            this.store.removeCaptcha(sessionId);
        }
    }
}
