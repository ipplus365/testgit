package com.ipplus360.domain;

import com.alibaba.fastjson.JSON;
import com.ipplus360.entity.MultiArea;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * 登录地
 * Created by 辉太郎 on 2017/9/13.
 */
public class LoginLocation {

    private String country;
    private String prov;
    private String city;
    private String multiarea;
    private List<MultiArea> multiAreas;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMultiarea() {
        return multiarea;
    }

    public void setMultiarea(String multiarea) {
        if (StringUtils.isEmpty(multiarea)) {
            this.multiAreas = Collections.emptyList();
        }
        List<MultiArea> multiAreas = JSON.parseArray(multiarea, MultiArea.class);
        this.multiAreas = multiAreas;
    }

    public List<MultiArea> getMultiAreas() {
        return multiAreas;
    }

    public void setMultiAreas(List<MultiArea> multiAreas) {
        this.multiAreas = multiAreas;
    }

    public String getLocation() {
        try {
            String city = this.multiAreas.get(0).getC();
            String prov = this.multiAreas.get(0).getP();
            if (!StringUtils.isEmpty(city)) {
                return city;
            }
            if (!StringUtils.isEmpty(prov)) {
                return prov;
            }
        } catch (Exception e) {
            return country;
        }
        return country;
    }

    @Override
    public String toString() {
        return "LoginLocation{" +
                "country='" + country + '\'' +
                ", prov='" + prov + '\'' +
                ", city='" + city + '\'' +
                ", multiAreas=" + multiAreas +
                '}';
    }
}
