package com.ipplus360.domain;

/**
 * 商品属性
 * Created by 辉太郎 on 2017/9/26.
 */
public class FileProductAttr {
    private Integer id;
    private Integer coordId;
    private Integer versionId;
    private Integer frequencyId;
    private Integer dateTypeId;
    private Integer areaId;
    private Integer updateModeId;
    private Integer monthId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoordId() {
        return coordId;
    }

    public void setCoordId(Integer coordId) {
        this.coordId = coordId;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Integer frequencyId) {
        this.frequencyId = frequencyId;
    }

    public Integer getDateTypeId() {
        return dateTypeId;
    }

    public void setDateTypeId(Integer dateTypeId) {
        this.dateTypeId = dateTypeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getUpdateModeId() {
        return updateModeId;
    }

    public void setUpdateModeId(Integer updateModeId) {
        this.updateModeId = updateModeId;
    }

    public Integer getMonthId() {
        return monthId;
    }

    public void setMonthId(Integer monthId) {
        this.monthId = monthId;
    }

    public boolean isValid() {
        return this.coordId == null || this.areaId == null || this.dateTypeId == null ||
                this.frequencyId == null || this.updateModeId == null || this.monthId == null ||
                this.versionId == null;
    }

    @Override
    public String toString() {
        return "FileProductAttr{" +
                "id=" + id +
                ", coordId=" + coordId +
                ", versionId=" + versionId +
                ", frequencyId=" + frequencyId +
                ", dateTypeId=" + dateTypeId +
                ", areaId=" + areaId +
                ", updateModeId=" + updateModeId +
                ", monthId=" + monthId +
                '}';
    }
}
