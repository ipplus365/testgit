package com.ipplus360.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.CommentDao;
import com.ipplus360.entity.Comment;
import com.ipplus360.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
    private CommentDao commentDao;

	@Override
	public int add(Map comment) {
		return commentDao.add(comment);
	}

	@Override
	public List<Comment> getCommentByProduct(Map getcomment) {
		return commentDao.getCommentByProduct(getcomment);
	}

	@Override
	public List<Comment> getCommentByUser(Map getcomment) {
		return commentDao.getCommentByUser(getcomment);
	}
	
	@Override
	public List<Comment> getComment(Map getcomment) {
		return commentDao.getComment(getcomment);
	}

	@Override
	public List<Comment> getCommentByUserId(Long id) {
		return commentDao.getCommentByUserId(id);
	}

}
