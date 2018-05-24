package com.ipplus360.entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎 on 2017/4/14.
 */
@Alias("download")
public class IPDownload {
    private Long id;
    private Long versionId;
    private String fileName;
    private String fileType;
    private boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString(){
        return "IPDownload{" +
                "id=" + id +
                ", versionId=" + versionId +
                ", fileName=" + fileName +
                ", fileType=" + fileType +
                ", available=" + available +
                "}";
    }
}
