package com.ipplus360.entity;

import java.util.Date;

public class PositionFeedback {

	private int id;
	private long ip;
	private long userIp;
	private String lat;
	private String lon;
	private Date createdTime;

	public PositionFeedback(long ip, long userIp, String lat, String lon, Date createdTime) {
		this.ip = ip;
		this.userIp = userIp;
		this.lat = lat;
		this.lon = lon;
		this.createdTime = createdTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getIp() {
		return ip;
	}

	public void setIp(long ip) {
		this.ip = ip;
	}

	public long getUserIp() {
		return userIp;
	}

	public void setUserIp(long userIp) {
		this.userIp = userIp;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}
