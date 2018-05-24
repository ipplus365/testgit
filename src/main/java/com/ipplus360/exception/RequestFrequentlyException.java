package com.ipplus360.exception;

/**
 * Created by 辉太郎 on 2017/5/24.
 */
public class RequestFrequentlyException extends AiwenException {
    public RequestFrequentlyException(String message) {
        super(message);
    }

    public RequestFrequentlyException(String message, Throwable cause) {
        super(message, cause);
    }
}
