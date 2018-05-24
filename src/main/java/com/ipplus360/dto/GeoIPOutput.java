package com.ipplus360.dto;

/**
 * Created by 辉太郎 on 2017/3/15.
 */
public class GeoIPOutput {
    private String continent;
    private String country;
    private String province;
    private String city;
    private String district;
    private String longitude;
    private String latitude;
    private String zipcode;
    private String timezone;
    private Double r1;
    private Double r2;
    private String accuracy;
    private String scene;
    private String owner;
    private String user;
    private String source;
    private String LED;
    private String LED_longitude;
    private String LED_latitude;
    private int correctness;
    private int precision;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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

    public Double getR1() {
        return r1;
    }

    public void setR1(Double r1) {
        this.r1 = r1;
    }

    public Double getR2() {
        return r2;
    }

    public void setR2(Double r2) {
        this.r2 = r2;
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

    public String getLED() {
        return LED;
    }

    public void setLED(String LED) {
        this.LED = LED;
    }

    public String getLED_longitude() {
        return LED_longitude;
    }

    public void setLED_longitude(String LED_longitude) {
        this.LED_longitude = LED_longitude;
    }

    public String getLED_latitude() {
        return LED_latitude;
    }

    public void setLED_latitude(String LED_latitude) {
        this.LED_latitude = LED_latitude;
    }

    public int getCorrectness() {
        return correctness;
    }

    public void setCorrectness(int correctness) {
        this.correctness = correctness;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"continent\":\"")
                .append(continent).append('\"');
        sb.append(",\"country\":\"")
                .append(country).append('\"');
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append(",\"city\":\"")
                .append(city).append('\"');
        sb.append(",\"district\":\"")
                .append(district).append('\"');
        sb.append(",\"longitude\":\"")
                .append(longitude).append('\"');
        sb.append(",\"latitude\":\"")
                .append(latitude).append('\"');
        sb.append(",\"zipcode\":\"")
                .append(zipcode).append('\"');
        sb.append(",\"timezone\":\"")
                .append(timezone).append('\"');
        sb.append(",\"r1\":")
                .append(r1);
        sb.append(",\"r2\":")
                .append(r2);
        sb.append(",\"accuracy\":\"")
                .append(accuracy).append('\"');
        sb.append(",\"scene\":\"")
                .append(scene).append('\"');
        sb.append(",\"owner\":\"")
                .append(owner).append('\"');
        sb.append(",\"user\":\"")
                .append(user).append('\"');
        sb.append(",\"source\":\"")
                .append(source).append('\"');
        sb.append(",\"LED\":\"")
                .append(LED).append('\"');
        sb.append(",\"LED_longitude\":\"")
                .append(LED_longitude).append('\"');
        sb.append(",\"LED_latitude\":\"")
                .append(LED_latitude).append('\"');
        sb.append(",\"correctness\":")
                .append(correctness);
        sb.append(",\"precision\":")
                .append(precision);
        sb.append('}');
        return sb.toString();
    }
}
