package com.ipplus360.dao;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.Comment;

public interface CommentDao {
	
	//添加评论
	int add(Map comment);
	
	//显示评论
	List<Comment> getCommentByProduct(Map getcomment);
	
	//查询评论
	List<Comment> getCommentByUser(Map getcomment);
	
	List<Comment> getComment(Map getcomment);

	List<Comment> getCommentByUserId(Long id);
	
}
