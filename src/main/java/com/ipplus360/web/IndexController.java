package com.ipplus360.web;

import com.ipplus360.entity.Comment;
import com.ipplus360.entity.Product;
import com.ipplus360.service.CommentService;
import com.ipplus360.service.ProductAndPriceService;
import com.ipplus360.util.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 辉太郎 on 2017/5/12.
 */
@Controller
public class IndexController {

    private final static Logger Logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private ProductAndPriceService productAndPriceService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = {"", "/"})
    public String index(Model model, HttpServletRequest request) {
        try {
            List<Product> listAll = productAndPriceService.listAll();

            for (int i = 0; i < listAll.size(); i++) {
                Map getcomment = new HashMap();
                getcomment.put("productid", listAll.get(i).getId());

                List<Comment> comment = commentService.getCommentByProduct(getcomment);
                listAll.get(i).setEvaluate(String.valueOf(comment.size()));
            }


            if (null != listAll) {
                model.addAttribute("productList", listAll);

                Logger.info("|{}|{}|{}|", new Object[]{IPUtil.getUserIP(request), request.getHeader("User-Agent"), request.getHeader("referer")});
                /**
                 * --------------------------------------------------------------------------------------------------------
                 * ATTENTION 大写的注意
                 * 51行以上不可增删任何代码，所有逻辑必须写在51行之后，如必须修改，同时需要修改VisitLogServiceImpl类recordAndMail方法
                 *
                 * 一定要注意，一定要注意，一定要注意
                 * 51行以上不可增删任何代码，所有逻辑必须写在51行之后，如必须修改，同时需要修改VisitLogServiceImpl类recordAndMail方法
                 *
                 * 重要的事情说三遍
                 *
                 * 51行以上不可增删任何代码，所有逻辑必须写在51行之后，如必须修改，同时需要修改VisitLogServiceImpl类recordAndMail方法
                 * by LL(lijian@ipplus360.com)
                 * --------------------------------------------------------------------------------------------------------
                 */
            }
            // model.addAttribute("msg", "服务器维护，请联系客服人员");
            return "redirect:/product/buy";
//            return "redirect:/pros/street";
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error("获取商品列表失败");
            // model.addAttribute("msg", "服务器维护，请联系客服人员");
        }
        return "redirect:/product/buy";
//        return "redirect:/pros/street";
    }

}
