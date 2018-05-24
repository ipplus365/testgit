package com.ipplus360.enums;

/**
 * 用户注册方式
 * Created by 辉太郎 on 2017/2/21.
 */
public enum RegSource {
    FRONT(0, "前台注册"),       //用户自己注册
    BACKGROUND(1, "后台注册"),  //运营人员为用户注册
    OTHER(2, "其他方式");

    private int code;
    private String message;

    RegSource(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
