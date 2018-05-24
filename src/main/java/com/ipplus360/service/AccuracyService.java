package com.ipplus360.service;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.Accuracy;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月1日   
 */

public interface AccuracyService {
	//得到定位精度
	List<Accuracy> getAccuracy(Map getType);
	
	
	Accuracy getAccuracyById(long id);

	List<Accuracy> getAccuracyByAvailable() ;
}
