package com.ipplus360.exception;

/**
 * IP定位业务异常
 * Created by 辉太郎 on 2017/2/18.
 */
public class AiwenException extends RuntimeException {
    public AiwenException() {
        super();
    }

    public AiwenException(String message) {
        super(message);
    }

    public AiwenException(String message, Throwable cause) {
        super(message, cause);
    }

}
