package com.ipplus360.exception;

/**
 * Created by 辉太郎 on 2017/8/29.
 */
public class ServiceException extends RuntimeException {

    public static final String MESSAGE = "业务逻辑异常";

    public ServiceException() {
        super(MESSAGE);
    }

    public ServiceException(String message) {
        super(message);
    }
}
