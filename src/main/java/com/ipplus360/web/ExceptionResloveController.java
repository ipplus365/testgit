package com.ipplus360.web;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.User;
import com.ipplus360.util.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 辉太郎 on 2017/4/7.
 */
@Controller
public class ExceptionResloveController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/error/{errorCode}")
    public String reslover(HttpServletRequest request, HttpSession session, Model model, @PathVariable String errorCode){

        String ipAddress = IPUtil.getUserIP(request);
        String message;
        String message4XX = "服务器无法找到被请求的页面";
        String message5XX = "请求失败";
        String userEmail = null;
        //User user = (User) session.getAttribute(Constants.CURRENT_USER);
        SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
        String fileError = (String) session.getAttribute("fileError");

        if(null != user ){
            userEmail = user.getEmail();
        }

        if(null != fileError){
            logger.error("请求IP地址为:{},请求用户为:{},请求URL:{}, 请求状态码:{}",ipAddress,userEmail,fileError, errorCode);
            session.removeAttribute("fileError");
        }
        logger.error("请求IP地址为:{},请求用户为:{},请求状态码:{}",ipAddress,userEmail,errorCode);
        switch (errorCode){
            case "400":
                model.addAttribute("msg",message4XX);
                break;
            case "401":
                model.addAttribute("msg",message4XX);
                break;
            case "402":
                model.addAttribute("msg",message4XX);
                break;
            case "403":
                model.addAttribute("msg",message4XX);
                break;
            case "404":
                //model.addAttribute("msg",message4XX);
                return "redirect:/";
            case "405":
                model.addAttribute("msg",message4XX);
                break;
            case "414":
                model.addAttribute("msg",message4XX);
                break;
            case "500":
                model.addAttribute("msg",message5XX);
                break;
            case "501":
                model.addAttribute("msg",message5XX);
                break;
            case "502":
                model.addAttribute("msg",message5XX);
                break;
            case "503":
                model.addAttribute("msg",message5XX);
                break;
            case "504":
                model.addAttribute("msg",message5XX);
                break;
            case "505":
                model.addAttribute("msg",message5XX);
                break;
            case "info":
                message = (String ) session.getAttribute("msg");
                logger.error("ErrorMessage:{}",message);
                if(null != message){
                    model.addAttribute("msg",message);
                    session.removeAttribute("msg");
                }else{
                    model.addAttribute("msg",message5XX);
                }
                break;
            default:
                model.addAttribute("msg",message5XX);
                break;

        }
        return "/error/error";
    }
}