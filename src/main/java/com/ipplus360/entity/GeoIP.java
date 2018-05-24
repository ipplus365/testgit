package com.ipplus360.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ipplus360.dto.MultiAreaDto;

/**
 * Created by 辉太郎 on 2017/2/5.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoIP {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Long minip;
    @JsonIgnore
    private Long maxip;
    private String continent;
    @JsonIgnore
    private String areaCode;
    private String country;
    private String multiarea;
    private String zipcode;
    private String timezone;
    private String accuracy;
    //@JsonIgnore
    private String scene;
    private String owner;
    private String user;
    @JsonIgnore
    private String source;
    private Integer correctness;
    private Integer consistency;
    private List<MultiAreaDto> multiAreas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMinip() {
        return minip;
    }

    public void setMinip(Long minip) {
        this.minip = minip;
    }

    public Long getMaxip() {
        return maxip;
    }

    public void setMaxip(Long maxip) {
        this.maxip = maxip;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getCorrectness() {
        return correctness;
    }

    public void setCorrectness(Integer correctness) {
        this.correctness = correctness;
    }

    public Integer getConsistency() {
        return consistency;
    }

    public void setConsistency(Integer consistency) {
        this.consistency = consistency;
    }

    public void setMultiarea(String multiarea) throws IOException {
        if (StringUtils.isEmpty(multiarea)) {
            return;
        }
        JSONArray array = JSONArray.parseArray(multiarea);
        List<MultiArea> multiAreas = JSONObject.parseArray(array.toJSONString(), MultiArea.class);
        if (multiAreas != null && multiAreas.size() > 0) {
            List<MultiAreaDto> multiAreaDtos = new ArrayList<MultiAreaDto>();
            for (MultiArea mulArea : multiAreas) {
                MultiAreaDto mulAreaDto = new MultiAreaDto();
                /*String w = mulArea.getW();
                String j = mulArea.getJ();
                String p = mulArea.getP();
                String c = mulArea.getC();
                String d = mulArea.getD();
                String r = mulArea.getR();
                mulAreaDto.setLat(mulArea.getW());
                mulAreaDto.setLng(mulArea.getJ());
                mulAreaDto.setProv(mulArea.getP());
                if (StringUtils.isNotBlank(c)) {
                    mulAreaDto.setCity(c);
                }*/
                mulAreaDto.setLat(mulArea.getW());
                mulAreaDto.setLng(mulArea.getJ());
                mulAreaDto.setProv(mulArea.getP());
                mulAreaDto.setCity(mulArea.getC());
                mulAreaDto.setDistrict(mulArea.getD());
                mulAreaDto.setRadius(mulArea.getR());
                multiAreaDtos.add(mulAreaDto);
            }
            this.setMultiareaDtos(multiAreaDtos);
        }
    }

    public List<MultiAreaDto> getMultiAreas() {

        if (multiAreas == null) {
            return null;
        }
        return multiAreas;
    }

    public void setMultiAreas(List<MultiAreaDto> multiAreas) {
        this.multiAreas = multiAreas;
    }

    public void setMultiareaDtos(List<MultiAreaDto> multiAreaDtos) {
        this.multiAreas = multiAreaDtos;
    }

    @Override
    public String toString() {
        return "GeoIP{" +
                "id=" + id +
                ", minip=" + minip +
                ", maxip=" + maxip +
                ", continent='" + continent + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", country='" + country + '\'' +
                ", multiarea='" + multiarea + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", timezone='" + timezone + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", scene='" + scene + '\'' +
                ", owner='" + owner + '\'' +
                ", user='" + user + '\'' +
                ", source='" + source + '\'' +
                ", correctness=" + correctness +
                ", consistency=" + consistency +
                ", multiAreaDtos=" + multiAreas +
                '}';
    }
}
