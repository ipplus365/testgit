package com.ipplus360.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipplus360.dto.Result;
import com.ipplus360.entity.TestUser;
import com.ipplus360.service.TestUserService;

/**
 * 用户提交信息，ad.jsp
 */
@Controller
@RequestMapping("/test")
public class TestUserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestUserController.class);

	
	@Autowired
	private TestUserService testUserService;
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public
    @ResponseBody
    Result testregister(@RequestParam("realname") String realname,@RequestParam("email") String email,
    		@RequestParam("mobile") String mobile,@RequestParam("orgName") String orgName,@RequestParam("description") String description) {
        try {
        	TestUser testUser = new TestUser();
        	testUser.setRealname(realname);
        	testUser.setOrgName(orgName);
        	testUser.setMobile(mobile);
        	testUser.setDescription(description);
        	testUser.setEmail(email);
        	LOGGER.info("测试用户："+testUser.toString());
        	testUserService.add(testUser);
        	
        } catch (Exception e) {
        	LOGGER.error("测试用户添加异常--{}",e);
            e.printStackTrace();
            return new Result(false, "提交失败");
        }
        return new Result(true, "提交成功");
    }
}
