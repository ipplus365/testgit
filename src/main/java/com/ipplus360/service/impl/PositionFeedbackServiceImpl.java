package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.PositionFeedbackDao;
import com.ipplus360.entity.PositionFeedback;
import com.ipplus360.service.PositionFeedbackService;

@Service
public class PositionFeedbackServiceImpl implements PositionFeedbackService {

	@Autowired
	private PositionFeedbackDao positionFeedbackDao;

	@Override
	public int insert(PositionFeedback positionFeedback) {

		return positionFeedbackDao.insert(positionFeedback);
	}

}
