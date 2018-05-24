package com.ipplus360.exception;

/**token不可用异常
 * Created by 辉太郎 on 2017/2/18.
 */
public class InvalidTokenException extends AiwenException {

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
