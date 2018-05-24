package com.ipplus360.enums;


/**
 * Created by 辉太郎 on 2017/2/17.
 */
public enum StatusCode {
    OK(200, "查询成功"),
    INVALID_KEY(403, "错误的请求key"),
    INVALID_REQUEST(404, "IP格式不正确"),
    INVALID_REQUEST_PARAM(405, "参数不正确"),
    INVALID_REQUEST_FREQUENCY(406, "接口调用太频繁"),
    INVALID_SERVICE(410, "没有可用定位产品"),
    INVALID_RESULT(411, "结果为空"),
    INTERNAL_ERROR(500, "服务异常");
    //URL上appKey参数不能为空
    //余额不足
    //超过每天限量，请明天继续
    //调用太频繁
    //用户已被禁用
    //该数据只允许企业用户调用

    private int code;
    private String msg;

    StatusCode(int code, String msg) {
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
