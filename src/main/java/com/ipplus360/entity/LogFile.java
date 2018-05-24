package com.ipplus360.entity;

import java.util.Date;

/**
 * 记录未入库的日志文件，每天扫描
 * Created by 辉太郎 on 2017/6/22.
 */
public class LogFile {
    private Integer id;
    private String file;
    private Boolean success;
    private Integer attemptTimes;
    private Date attemptTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getAttemptTimes() {
        return attemptTimes;
    }

    public void setAttemptTimes(Integer attemptTimes) {
        this.attemptTimes = attemptTimes;
    }

    public Date getAttemptTime() {
        return attemptTime;
    }

    public void setAttemptTime(Date attemptTime) {
        this.attemptTime = attemptTime;
    }
}
