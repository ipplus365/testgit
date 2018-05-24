package com.ipplus360.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ipplus360.entity.User;

/**
 * Created by 辉太郎 on 2017/2/22.
 */
public interface UserDao {

    User getById(Long id);

    User getByName(String name);

    int addCheckCode(User user);

    int update(User user);

    //重置密码
    int add(User user);

    //重置密码
    int resetPwd(User user);
    int resetPwdforMobile(User user);

    int changepwd(User user);

    int updateTestCount(User user);

    int updateToken(User user);

    //增加手机号
    int updateMobile(User user);

    //登录
    User userSignIn(User user);

    //注册
    int userSignUp(User user);

    User getByEmail(String email);

    User getByMobile(String mobile);

    User getByToken(String activeToken);

    void active(String token);

    //手机验证码入库
    int insertMobileCode(String mobile, String code, Date createTime, Date expireTime, String type);

    /**
     * 获取新注册用户（只包括已经激活的）
     *
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

    void updateFreeDistrictApi(Long userId);

}
