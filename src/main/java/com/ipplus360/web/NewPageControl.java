package com.ipplus360.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 辉太狼  on 2017/11/25
 */


@Controller
@RequestMapping("/pros")
public class NewPageControl {

    @RequestMapping(value = "/street", method = RequestMethod.GET)
    public String product(HttpServletRequest request) {
        return "/pros/street";
    }

    @RequestMapping(value = "/ipbt", method = RequestMethod.GET)
    public String ipbt(HttpServletRequest request) {
        return "/pros/ipbt";
    }

    @RequestMapping(value = "/iptantan", method = RequestMethod.GET)
    public String iptantan(HttpServletRequest request) {
        return "/pros/iptantan";
    }

    @RequestMapping(value = "/ipwave", method = RequestMethod.GET)
    public String ipwave(HttpServletRequest request) {
        return "/pros/ipwave";
    }
}
