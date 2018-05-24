package com.ipplus360.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎
 * 下载记录表
 * 创建时间2017年5月23日   
 */

@Alias("downloadorder")
public class DownloadOrder {

	private Long id;
	private long productId;
	private long userId;
	private String email;
    private String ip;
    private Date dateCreated;
    private Boolean newUser;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getNewUser() {
		return newUser;
	}

	public void setNewUser(Boolean newUser) {
		this.newUser = newUser;
	}

	@Override
	public String toString() {
		return "DownloadOrder{" +
				"id=" + id +
				", productId=" + productId +
				", userId=" + userId +
				", email='" + email + '\'' +
				", ip='" + ip + '\'' +
				", dateCreated=" + dateCreated +
				", newUser=" + newUser +
				'}';
	}
}
