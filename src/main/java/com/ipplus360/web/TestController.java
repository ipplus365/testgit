package com.ipplus360.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 辉太郎 on 2017/10/20.
 */
@Controller
@RequestMapping("/dev")
public class TestController {


    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String buy() {
        return "buy";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "info";
    }

}
