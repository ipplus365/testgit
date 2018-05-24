package com.ipplus360.dao;

import java.util.List;

import com.ipplus360.entity.MobileCode;

public interface MobileCodeDao {

	int add(MobileCode mobileCode);

	List<MobileCode> getByPhoneNum(String phoneNum);


}
