package com.ipplus360.dto;

import com.ipplus360.entity.UserToken;
import com.ipplus360.util.IPUtil;
import org.springframework.util.StringUtils;

import static com.ipplus360.common.Constants.API_LOCATE;
import static com.ipplus360.common.Constants.COORD_SUFFIX;

/**
 *
 * Created by 辉太郎 on 2017/9/19.
 */
public class LocateProps {

    public LocateProps() {
    }

    public LocateProps(String key, String locateIp, String userIp, String source, String coordSuffix) {
        this.key = key;
        this.locateIp = locateIp;
        if (!StringUtils.isEmpty(locateIp)) {
            this.locateIpL = IPUtil.ipToLong(locateIp);
        }
        this.userIp = userIp;
        if (!StringUtils.isEmpty(source)) {
            this.source = source;
        }
        if (!StringUtils.isEmpty(coordSuffix)) {
            this.coordSuffix = coordSuffix;
        }
    }

    public LocateProps(UserToken userToken, String locateIp, String userIp, String source, String coordSuffix) {
        this.userToken = userToken;
        this.locateIp = locateIp;
        if (!StringUtils.isEmpty(locateIp)) {
            this.locateIpL = IPUtil.ipToLong(locateIp);
        }
        this.userIp = userIp;
        if (!StringUtils.isEmpty(source)) {
            this.source = source;
        }
        if (!StringUtils.isEmpty(coordSuffix)) {
            this.coordSuffix = coordSuffix;
        }
    }

    private UserToken userToken;
    private String locateIp;
    private Long locateIpL;
    private String userIp;
    private String key;
    private String source = API_LOCATE;
    private String coordSuffix = COORD_SUFFIX;

    public UserToken getUserToken() {
        return userToken;
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    public String getLocateIp() {
        return locateIp;
    }

    public void setLocateIp(String locateIp) {
        this.locateIpL = IPUtil.ipToLong(locateIp);
        this.locateIp = locateIp;
    }

    public Long getLocateIpL() {
        return locateIpL;
    }

    public void setLocateIpL(Long locateIpL) {
        this.locateIpL = locateIpL;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCoordSuffix() {
        return coordSuffix;
    }

    public void setCoordSuffix(String coordSuffix) {
        this.coordSuffix = coordSuffix;
    }

    @Override
    public String toString() {
        return "LocateProps{" +
                "userToken=" + userToken +
                ", locateIp='" + locateIp + '\'' +
                ", locateIpL=" + locateIpL +
                ", userIp='" + userIp + '\'' +
                ", key='" + key + '\'' +
                ", source='" + source + '\'' +
                ", coordSuffix='" + coordSuffix + '\'' +
                '}';
    }
}
