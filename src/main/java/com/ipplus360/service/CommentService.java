package com.ipplus360.service;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.Comment;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月9日   
 */

public interface CommentService {
	
	//添加评论
	int add(Map getcomment);
	
	//显示评论
	List<Comment> getCommentByProduct(Map getcomment);
	
	//查询评论
	List<Comment> getCommentByUser(Map getcomment);

	List<Comment> getCommentByUserId(Long id);
	
	List<Comment> getComment(Map getcomment);

}
