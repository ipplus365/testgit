package com.ipplus360.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 辉太狼  on 2017/11/25
 */

@Controller
@RequestMapping("/include")
public class IncludePageContrller {

    @RequestMapping(value = "/leftNav", method = RequestMethod.GET)
    public String product(HttpServletRequest request) {
        System.out.println("i am 王彤辉，我非常帅！！！！");
        return "/include/leftNav";
    }
}
