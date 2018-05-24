package com.ipplus360.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.NotOrgEmail;
import com.ipplus360.entity.User;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月1日   
 */

public interface UserService {

	/**
	 * 登录
	 * @author ll
	 * @param username
	 * @param password
	 * @return
	 */
	SessionUser login(HttpServletRequest request, String username, String password);

	User getById(Long id);

	User getByEmail(String email);

	/**
	 * 注册
	 * @author ll
	 * @param user
	 * @param confirmPwd
	 */
	void register(User user, String confirmPwd, String path);

	/**
	 * 激活
	 * @author ll
	 * @param task
	 * @param activeToken
	 * @param currentTime
	 */
	void active(String task, String activeToken, long currentTime);

	/**
	 * 修改密码
	 * @param user
	 * @param newPwd
	 */
	void changepwd(User user,String password,  String newPwd,String confirmPwd);

	/**
	 * 更新测试包计数
	 * @param user
	 */
	void updateTestCount(User user);

	/**
	 * 发送邮箱校验码
	 * @param email
	 */
	void sendEmailCheck(String email);

	/**
	 * 重置密码
	 * @param email
	 * @param newPwd
	 */
	void resetPwd(String email,  String newPwd,String confirmPwd);

	/**
	 * 验证邮箱验证码
	 * @param email
	 * @param checkCode
	 */
	void checkEmailCheck(String email, int checkCode);

	void checkEmailCheck(String email, String  token);

	/**
	 * 发送重置链接
	 * @param email
	 */
	void sendResetEmail(String email,String path);
	void sendResetMobile(String account, String path);

	/**
	 * 获取新注册用户（只包括已经激活的）
	 * @param date
	 * @return
	 */
	List<User> getNewUsers(Date date);

	/**
	 * 统计上周新注册用户数
	 */
	int userStatisticsWeekly(LocalDate date);

	/**
	 * 统计上个月新注册用户数
	 */
	int userStatisticsMonthly(LocalDate date);


	void register(User user, String confirmPwd, String path,String orgName,String orgPersonName);

	/**
	 * 增加手机验证码
	 *
	 */
	int insertMobileCode(String mobile,String code,Date createTime,Date expireTime,String type);

	void checkMobileCodeCheck(String phoneNum, String code);
	
	
	/**
	 * 增加手机号
	 * 
	 */
	void updateMobile(User user);

	void updateFreeDistrictApi(Long userId);
	//判断手机号是否只有一个
	User getByMobile(String mobile);

	void resetPwdforMobile(String mobile, String newPwd, String confirmPwd);

	//判断是否为企业邮箱
	NotOrgEmail getNotOrgEmailByEmail(String emailForOrg);

	
}