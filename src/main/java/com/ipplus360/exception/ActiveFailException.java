package com.ipplus360.exception;

/**
 * 激活异常
 * Created by 辉太郎 on 2017/3/11.
 */
public class ActiveFailException extends AiwenException {
    public ActiveFailException() {
    }

    public ActiveFailException(String message) {
        super(message);
    }

    public ActiveFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
