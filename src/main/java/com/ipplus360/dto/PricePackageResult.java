package com.ipplus360.dto;

import java.util.List;

import com.ipplus360.entity.Accuracy;

/**
 * Created by 辉太郎 on 2017/3/15.
 */
public class PricePackageResult<T> {

    private boolean success;
    private List<Accuracy> accuracy;
    private T data;
    private String msg;


    public PricePackageResult() {
    }

    public PricePackageResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public PricePackageResult(boolean success, T data, List<Accuracy> accuracy, String msg) {
        this.success = success;
        this.data = data;
        this.accuracy = accuracy;
        this.msg = msg;

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Accuracy> getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(List<Accuracy> accuracy) {
        this.accuracy = accuracy;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
