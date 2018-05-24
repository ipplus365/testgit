package com.ipplus360.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎 on 2017/4/6.
 * 对应表geoip_version
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Alias("geoIPVersion")
public class GeoIPVersion {
    private Long id;
    /*内部版本号*/
    private String privateVersion;
    /*外部版本号，用于展示给用户，暂留空*/
    private String publicVersion;
    /*历史版本所在数据库名字，现为ipplus360_data*/
    private String dbName;
    /*历史版本数据表名字*/
    private String tableName;
    /*版本生效开始时间*/
    private Date startTime;
    /*版本生效结束时间，如果为空，为当前版本*/
    private Date endTime;
    private String desc;
    /**/
    private boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivateVersion() {
        return privateVersion;
    }

    public void setPrivateVersion(String privateVersion) {
        this.privateVersion = privateVersion;
    }

    public String getPublicVersion() {
        return publicVersion;
    }

    public void setPublicVersion(String publicVersion) {
        this.publicVersion = publicVersion;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString(){
        return "GeoIPVersion{" +
                "id=" + id +
                ", privateVersion=" + privateVersion +
                ", publicVersion=" + publicVersion +
                ", dbName=" + dbName +
                ", tableName=" + tableName +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", desc=" + desc +
                ", available=" + available +
                "}";
    }
}
