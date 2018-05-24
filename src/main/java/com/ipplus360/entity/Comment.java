package com.ipplus360.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月9日   
 */

@Alias("comment")
public class Comment {
    private Long id;
    /*评论所属产品*/
    private Long productId;
    /*评论所属用户*/
    private String userId;
    /*评论状态*/
    private String status;
    /*评论内容*/
    private String content;
    /*评论时间*/
    private Date commentDate;
    /*用户邮箱*/
    private String email;
    /*用户名字*/
    private String username;
    
    private Product product;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String toString(){
		return  "Comment [id="+ id +
				", productId="+ productId +
				", product="+ product +
				", userId="+ userId +
				", status="+ status +
				", content="+ content +
				", commentDate="+ commentDate +
				", email="+ email +
				", username="+ username +
				"]";
	}
    
}
