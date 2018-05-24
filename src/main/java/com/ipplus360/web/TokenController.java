package com.ipplus360.web;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.UserToken;
import com.ipplus360.service.TokenService;
import com.ipplus360.service.mail.MailService;
import com.ipplus360.util.IPUtil;
import com.ipplus360.util.SessionUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/token")
public class TokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MailService mailService;

    @RequestMapping("")
    public String product() {
        return "redirect:/person/user";
    }

    //@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String tokenList(HttpServletRequest request, HttpSession session, Model model) {
        //User user = (User) session.getAttribute(Constants.CURRENT_USER);
        SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
        try {
            Set<UserToken> userToken = tokenService.getByUserId(user.getId());
            if (null != userToken) {
                model.addAttribute("tokenList", userToken);
                LOGGER.info("list--{}", userToken);
                return "personalcenter/user";
            }
            model.addAttribute("msg", "余量列表为空");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询余量列表失败");
            model.addAttribute("msg", "查询余量列表失败");
            return "";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/canLocate", method = RequestMethod.GET)
    public Result canLocate(@RequestParam(value = "tokenId", required = false) Long tokenId,
                            HttpServletRequest request) {
        try {
            if (null == tokenId) {
                SessionUser sessionUser = SessionUtil.getSessionUser(request);
                Long userId = sessionUser.getId();
                Set<UserToken> tokens = tokenService.getByUserId(userId);
                for (UserToken token : tokens) {
                    if (token.canWebLocate()) {
                        return new Result(true, "");
                    }
                }
            } else {
                UserToken token = tokenService.getById(tokenId);
                if (token.canWebLocate()) {
                    return new Result(true, "");
                }
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
            return new Result(false, "获取定位信息失败");
        }
        return new Result(false, "您还没有可用于web定位的产品");
    }


    @Transactional
    @ResponseBody
    @RequestMapping(value = "/resetToken", method = RequestMethod.GET)
    public Result reset(HttpServletRequest request, HttpSession session, @RequestParam("token")String token) {
        //User user = (User) session.getAttribute(Constants.CURRENT_USER);
        SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
        String userIP = IPUtil.getUserIP(request);
        try {

            UserToken userToken = tokenService.getByToken(user.getId(),token);
            String originalToken = userToken.getToken();
            String resetToken = RandomStringUtils.randomAlphanumeric(64);
            userToken.setToken(resetToken);
            userToken.setUpdateDate(new Date());

            tokenService.update(userToken);

            mailService.sendResetTokenMail(user.getEmail());

            LOGGER.warn("用户:{} 登录IP为:{} 重置Token,TokenID为:{}--重置前Token为:{}--重置后Token为:{}",user.getEmail(),userIP,userToken.getId(),originalToken,resetToken);
            //session.setAttribute("msg","重置Token成功");
            return new Result(true, "重置成功");
        }catch (Exception e){
            LOGGER.warn("用户:{} 登录IP为:{} 重置Token出错:{}",user.getEmail(),userIP,e);
            //session.setAttribute("msg","重置Token失败");
            return new Result(false, "重置失败");
        }
        //return "redirect:/person/user";
    }
}