package com.ipplus360.enums;

/**
 * Created by 辉太郎 on 2017/2/21.
 */
public enum UserStatus {
    //TODO
    /**
     * 0 未激活
     * 1 已激活
     */
    INACTIVE(0, "未激活"),
    ACTIVE(1, "已激活");

    private int code;
    private String msg;

    UserStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
