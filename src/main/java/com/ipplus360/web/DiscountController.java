package com.ipplus360.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipplus360.dto.Result;
import com.ipplus360.entity.Discount;
import com.ipplus360.service.DiscountService;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月9日   
 */

@Controller
@RequestMapping("/discount")
@Deprecated()
public class DiscountController {

	private final static Logger Logger = LoggerFactory.getLogger(DiscountController.class);
	
    @Autowired
    private DiscountService discountService;

    //得到商品列表
  	@RequestMapping(value = "/", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<Discount> discount() {
		try{	
			
			//Map packagenumber = new HashMap();
  			//Discount discount=discountService.getDiscount(packagenumber);
            Discount discount= null;
			if(null == discount){
				return new Result<>(false, "查询结果为空");
			}
			
			Result<Discount> result = new Result<>(true, discount, "查询成功");
			//System.out.println("amountList[0]="+result.getData().indexOf(0));
			return result;
		}catch (Exception e) {
			Logger.error(e.getMessage());
			return new Result<>(false, "查询失败");
		}
    }

}
