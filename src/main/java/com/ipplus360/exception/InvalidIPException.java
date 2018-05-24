package com.ipplus360.exception;

/**
 * Created by 辉太郎 on 2017/2/18.
 */
public class InvalidIPException extends AiwenException {

    public InvalidIPException(String message) {
        super(message);
    }

    public InvalidIPException(String message, Throwable cause) {
        super(message, cause);
    }
}
