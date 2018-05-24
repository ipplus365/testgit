package com.ipplus360.service;

import com.ipplus360.entity.OrderItem;
import com.ipplus360.exception.OrderException;

import java.util.Map;

public interface AlipayService {
	
	/**
     * 即时到账交易接口接入
     * Created by 辉太郎
     * @param payParam
     * @return payHtmlText
     */
    String payOnline(Map payParam);
    
    /**
     * 发送账单邮件
     * @param email
     */
	void sendOrderEmail(Map payResult);

    //void processSceneAPI();

    void processDistrictDownload(OrderItem orderItem, Long userId, String currVer, boolean free) throws OrderException;

    String processDistrictAPI(OrderItem orderItem, Long userId);

    String processSceneAPI(OrderItem orderItem, Long userId);

    String processFreeDistrictAPI(OrderItem orderItem, Long id);
}
