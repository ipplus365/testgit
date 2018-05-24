package com.ipplus360.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.AccuracyDao;
import com.ipplus360.entity.Accuracy;
import com.ipplus360.service.AccuracyService;

@Service
public class AccuracyServiceImpl implements AccuracyService {

	@Autowired
    private AccuracyDao accuracyDao;
	
    /**
     * 得到定位精度
     */
	@Override
	public List<Accuracy> getAccuracy(Map getType) {
		List<Accuracy> accuracy = accuracyDao.getAccuracy(getType);
		//System.err.println("accuracy-" + accuracy.size());
		return accuracy;
	}

	@Override
	public Accuracy getAccuracyById(long id) {
		return accuracyDao.getAccuracyById(id);
	}

	@Override
	public List<Accuracy> getAccuracyByAvailable() {
		return accuracyDao.getAccuracyByAvailable();
	}
}
