package com.ipplus360.dao;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.Accuracy;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月7日   
 */

public interface AccuracyDao {
	//根据id查询定位精度
	List<Accuracy> getAccuracy(Map getType) ;
	Accuracy getAccuracyById(Long id) ;

	List<Accuracy> getAccuracyByAvailable() ;

}
