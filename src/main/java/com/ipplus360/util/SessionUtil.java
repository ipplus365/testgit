package com.ipplus360.util;

import com.ipplus360.dao.UserDao;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.User;
import com.ipplus360.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 辉太郎 on 2017/8/29.
 */
public class SessionUtil {

    private final static String SESSION_USER = "session_user";
    private final static String IS_LOGIN = "is_login";

    public static SessionUser getSessionUser(HttpServletRequest request) {
        return (SessionUser) request.getSession().getAttribute(SESSION_USER);

    }

    public static void setSessionUser(HttpServletRequest request, SessionUser user) {
        request.getSession().setAttribute(SESSION_USER, user);
    }

    public static void invalidate(HttpServletRequest request) {
        setSessionUser(request, null);
    }

    public static User convertToUser(SessionUser sessionUser, UserDao userDao) {
        if (sessionUser != null && userDao != null) {
            Long id = sessionUser.getId();
            User user = userDao.getById(id);
            return user;
        }
        return null;
    }


    public static User convertToUser(SessionUser sessionUser, UserService userService) {
        if (sessionUser != null && userService != null) {
            Long id = sessionUser.getId();
            User user = userService.getById(id);
            return user;
        }
        return null;
    }

    public static SessionUser convertToSessionUser(User user) {
        if (user != null && user.getId() != null) {
            SessionUser sessionUser = new SessionUser();
            sessionUser.setId(user.getId());
            sessionUser.setEmail(user.getEmail());
            sessionUser.setUsername(user.getUsername());
            sessionUser.setLastLogin(user.getLastLogin());
            sessionUser.setLastLoginFrom(user.getLastLoginFrom());
            sessionUser.setMobile(user.getMobile());
            sessionUser.setDateCreated(user.getDateCreated());
            sessionUser.setStatus(user.getStatus());
            sessionUser.setTestCount(user.getTestCount());
            return sessionUser;
        }
        return null;
    }
}
