package com.ipplus360.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultiAreaDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 *   {[
	 *     {"w": "22.282719", "j": "114.137402", "r": "0.1096","p": "香港特别行政区", "c": "香港岛", "d": "中西区"}, 
	 *     {"lat": "22.281126", "lng": "114.132142", "radius": "0.029", "prov": "香港特别行政区", "city": "香港岛", "district": "中西区"}, 
	 *     {"lat": "22.283001", "lng": "114.135033", "radius": "0.0296","prov": "香港特别行政区", "city": "香港岛", "district": "中西区"}, 
	 *     {"lat": "22.283686", "lng": "114.134665", "radius": "0.0215","prov": "香港特别行政区", "city": "香港岛", "district": "中西区"}
	 *    ]}
	 */
	
	/*纬度*/
	private String lat;
	/*经度*/
	private String lng;
	/*radius*/
	private String radius;
	/*省*/
	private String prov;
	/*市*/
	private String city;
	/*区县*/
	private String district;
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"lat\":\"")
				.append(lat).append('\"');
		sb.append(",\"lng\":\"")
				.append(lng).append('\"');
		sb.append(",\"radius\":\"")
				.append(radius).append('\"');
		sb.append(",\"prov\":\"")
				.append(prov).append('\"');
		sb.append(",\"city\":\"")
				.append(city).append('\"');
		sb.append(",\"district\":\"")
				.append(district).append('\"');
		sb.append('}');
		return sb.toString();
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}

}
