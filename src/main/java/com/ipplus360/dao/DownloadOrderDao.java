package com.ipplus360.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ipplus360.entity.DownloadOrder;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年5月24日   
 */

public interface DownloadOrderDao {
	
	List<DownloadOrder> getAll(Map getDownloadOrder);

	int add(DownloadOrder downloadOrder);

    List<DownloadOrder> getByDay(Date date);

    int getCountsByDay(Date date);

    /**
     * 统计上周免费库下载量
     */
    int downloadStatisticsWeekly(LocalDate date);

    /**
     * 统计上个月免费库下载量
     */
    int downloadStatisticsMonthly(LocalDate date);
}
