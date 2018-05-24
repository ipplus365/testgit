package com.ipplus360.web;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.*;
import com.ipplus360.service.*;
import com.ipplus360.util.IPUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductAndPriceService productAndPriceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccuracyService accuracyService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductDescService productDescService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PricePackageService pricePackageService;
    @Autowired private ProductAttrService productAttrService;


    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String product(HttpServletRequest request) {
        logger.info("|{}|{}|{}|", new Object[] {IPUtil.getUserIP(request), request.getHeader("User-Agent"), request.getHeader("referer")});

        /**
         * --------------------------------------------------------------------------------------------------------
         * ATTENTION 大写的注意
         * 57行以上不可增删任何代码，所有逻辑必须写在57行之后，如必须修改，同时需要修改VisitLogServiceImpl类recordAndMail方法
         *
         * 一定要注意，一定要注意，一定要注意
         * 57行以上不可增删任何代码，所有逻辑必须写在57行之后，如必须修改，同时需要修改VisitLogServiceImpl类recordAndMail方法
         *
         * 重要的事情说三遍
         *
         * 57行以上不可增删任何代码，所有逻辑必须写在57行之后，如必须修改，同时需要修改VisitLogServiceImpl类recordAndMail方法
         * by LL(lijian@ipplus360.com)
         * --------------------------------------------------------------------------------------------------------
         */
        return "buy";
    }

    /**
     * 获取商品详情 包含价格包
     * @param productId  id
     * @return 商品
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Result detail(@RequestParam("pid") Long productId) {
        try {
            Product product = productService.getOne(productId);
            return new Result<>(true, product, "SUCCESS");
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
            return new Result(false, "获取商品详情失败");
        }
    }

    /**
     * 获取商品标价
     * @param productId productId
     * @return 价格包
     */
    @ResponseBody
    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public Result getPrices(@RequestParam("pid") Long productId) {
        try {
            if (productId == null) {
                return new Result(false, "参数不能为空");
            }

            List<PricePackage> pricePackages = pricePackageService.getPackagesByProduct(productId);
            return new Result<>(true, pricePackages, "查询成功");
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
            return new Result(false, "查询失败");
        }
    }

    /**
     * 获取历史版本
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPreVers", method = RequestMethod.GET)
    public Result getPreviousVers(@RequestParam("pid") Long productId) {
        try {
            if (null == productId) {
                return new Result(false, "参数不正确");
            }
            List<ProductAttr> pVers = productAttrService.getPreviousVersions(productId);

            return new Result<>(true, pVers, "");
        } catch (Exception e) {
            logger.error("{}", e.getMessage(), e);
            return new Result(false, "历史版本获取失败");
        }
    }

    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    public String ad() {
        return "old/ad";
    }

    //根据选择套餐规格得到价格
    //@RequestMapping(value = "/priceTest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    /*public PricePackageResult getAll(@RequestParam(required = false,name ="productId") Long productId,@RequestParam(required = false,name ="accuracyId") Long accuracyId) {
        try {
            if(null == accuracyId){
                accuracyId = 1L;
                //return new PricePackageResult<>(false, "参数为空");
            }
            if(null == productId){
                productId = 1L;
            }

            logger.debug("ProductId={},accuracyId={}",productId,accuracyId);

            List<PricePackage> productPrice = pricePackageService.getAll(productId,accuracyId);
            List<PricePackageOutput> pricePackageOutputs = new ArrayList<>();

            if(null == productPrice || productPrice.isEmpty()){
                PricePackageOutput packageOutput = new PricePackageOutput();
                Accuracy accuracy = accuracyService.getAccuracyById(accuracyId);
                if(5 == productId){
                    //PricePackage pricePackage = new PricePackage();
                    //productPrice.add(pricePackage);
                    packageOutput.setTotal("免费");
                    packageOutput.setTips("每日限2次下载，如另有需要，请加官方QQ群：160902194");
                    pricePackageOutputs.add(packageOutput);
                   *//* List<Accuracy> acc = new ArrayList<>();
                    acc.add(accuracy);*//*

                *//*Map priceResult = new HashMap();
                priceResult.put("total", "免费");
                priceResult.put("tips", "每日限2次下载，如另有需要，请加官方QQ群：160902194");*//*
                    //return new PricePackageResult<>(true, pricePackageOutputs,"查询成功");
                }else{
                    packageOutput.setTotal("即将上线");
                    pricePackageOutputs.add(packageOutput);
                    *//*List<Accuracy> acc = new ArrayList<>();
                    acc.add(accuracy);*//*
                    //return new PricePackageResult<>(true, pricePackageOutputs, acc,"查询成功");
                }
                return new PricePackageResult<>(true,pricePackageOutputs,"查询列表为空");
            }

            for (PricePackage pkg : productPrice) {
                if(null !=pkg)
                pricePackageOutputs.add(new PricePackageOutput(pkg));
            }

            List<Accuracy> acc = accuracyService.getAccuracyByAvailable();

            *//*Map<String ,List<PricePackageOutput>> map = new LinkedHashMap<>();

            groupB(pricePackageOutputs,map);*//*
            *//*List<Map<String,String>> a = new ArrayList<>();
            for (String key : map.keySet()) {
                Map<String,String> b = new LinkedHashMap<>();
                b.put("acc",key);
                a.add(b);
            }*//*

            return new PricePackageResult<>(true,pricePackageOutputs,acc,"查询成功");

        }catch (Exception e){
            e.printStackTrace();
        }

        return new PricePackageResult<>(false,"查询失败");
    }*/

    /*private  void groupB(List<PricePackageOutput> list, Map<String, List<PricePackageOutput>> map) {
        if (null == list || null == map) {
            return;
        }

        String key;
        List<PricePackageOutput> listTmp;
        for (PricePackageOutput val : list) {
            key = val.getAccuracy();
            listTmp = map.get(key);
            if (null == listTmp) {
                listTmp = new ArrayList<>();
                map.put(key, listTmp);
            }
            listTmp.add(val);
        }
    }*/

    //根据id得到商品详情
    @RequestMapping(value = "/detail1", method = RequestMethod.GET)
    public String productInfo(Long productId, HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
        try {

            SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);

            Product productInfo = productService.getForToken(productId);
            if (null == productInfo) {
                return "redirect:/";
            }


            if (null != user) {

                ShoppingCart cart = shoppingCartService.getCountByUserId(user.getId());
                if (null != cart) {
                    List<CartItem> cartItems = cart.getCartItems();
                    if (null != cartItems && !cartItems.isEmpty() && 0 < cartItems.size()) {
                        model.addAttribute("cartSize", cartItems.size());
                    }
                }
            }
            ProductDesc productDesc = productDescService.getByProductId(productId);


            List<Product> listAll = productAndPriceService.listAll();

            //updateVisit(request, response, session, productId);
            String msg = (String) session.getAttribute("msg");
            if (!StringUtils.isEmpty(msg)) {
                model.addAttribute("msg", msg);
                session.removeAttribute("msg");
            }
            //显示商品详情操作
            model.addAttribute("productInfo", productInfo);
            model.addAttribute("productList", listAll);
            model.addAttribute("productDesc", productDesc);
            return "old/detail";
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            logger.error("获取商品详细信息错误");
            return "";
        }
    }

    //得到套餐数量列表
    @RequestMapping(value = "/amount", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<List<PricePackage>> amountList(@RequestParam("productId") Long productId, @RequestParam("accuracy") Integer accuracy) {
        try {
            if (null == productId && null == accuracy) {
                return new Result(false, "参数为空");
            }
            Map getAmount = new HashMap();
            getAmount.put("nowtime", new Date());
            getAmount.put("id", productId);
            getAmount.put("accuracy", accuracy);
            List<PricePackage> amount = productAndPriceService.getAmountList(getAmount);
            if (null == amount) {
                return new Result(false, "查询结果为空");
            }
            Result<List<PricePackage>> result = new Result<>(true, amount, "查询成功");
            //System.out.println("amountList[0]="+result.getData().indexOf(0));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(false, "查询失败");
        }
    }

    //得到套餐类型列表
    @RequestMapping(value = "/packagetype", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<List<Accuracy>> typeList(@RequestParam("productId") Long productId) {
        try {
            if (null == productId) {
                return new Result(false, "参数id为空");
            }
            Map getType = new HashMap();
            getType.put("nowtime", new Date());
            getType.put("id", productId);
            //getAccuracy
            List<Accuracy> typeList = accuracyService.getAccuracy(getType);
            if (null == typeList) {
                return new Result(false, "查询失败");
            }
            if (typeList.size() == 0) {
                return new Result(false, "商品场景列表为空");
            }
            Result<List<Accuracy>> result = new Result<>(true, typeList, "查询成功");
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(false, "查询失败");
        }
    }

    /**
     * 查询精度
     * by LL 2017年3月8日下午6:53:31
     */
    @RequestMapping(value = "/accuracy", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<Accuracy> getAccuracy(@RequestParam("productId") Long productId) {
        try {
            if (null == productId) {
                return new Result(false, "参数id为空");
            }
            //getAccuracy
            Accuracy accuracy = accuracyService.getAccuracyById(productId);
            if (null == accuracy) {
                return new Result(false, "查询失败");
            }
            return new Result<>(true, accuracy, "查询成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Result(false, "查询失败");
        }
    }

    /**
     * 查询商品订单
     * by fcf 2017年3月13日下午6:53:31
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<Map> getOrder(@RequestParam("productId") Long productId, @RequestParam("curPage") int curPage) {
        try {
            if (null == productId) {
                return new Result(false, "参数id为空");
            }

            //分页
            int PAGESIZE = 5;
            int pageCount;
            //int curPage = 0;

            List<Map> orderItemInfo = orderItemService.getAllByProductId(productId);
            if (null == orderItemInfo) {
                return new Result(false, "订单查询为空");
            }

            //分页
            if (0 == curPage) {
                curPage = 1;
            }
            int size = orderItemInfo.size();
            pageCount = (size % PAGESIZE == 0) ? (size / PAGESIZE) : (size / PAGESIZE + 1);
            //取每页相应的评论
            List<Map> orderItemList = new ArrayList<Map>();
            if (curPage * 5 < size) {
                orderItemList.addAll(orderItemInfo.subList((curPage - 1) * 5, curPage * 5));
            } else {
                orderItemList.addAll(orderItemInfo.subList((curPage - 1) * 5, size));
            }

            for (int i = 0; i < orderItemList.size(); i++) {
                String email = (String) orderItemList.get(i).get("email");
                if (null == email || "" == email) {
                    logger.error("订单记录email为空，记录查询失败");
                    return new Result(false, "记录查询失败");
                }
                String resultemail = email.substring(0, 2) + "*****" + email.substring(email.length() - 1, email.length());
                orderItemList.get(i).put("email", resultemail);
            }

            Map orderItemResult = new HashMap();
            orderItemResult.put("comment", orderItemList);
            orderItemResult.put("curPage", curPage);
            orderItemResult.put("pageCount", pageCount);
            Result<Map> result = new Result<>(true, orderItemResult, "查询成功");
            return result;
            //return new Result<>(true, orderItemInfo, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new Result(false, "查询失败");
        }
    }


    @Deprecated
    //得到商品列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String productList(Model model, HttpServletRequest request) {
        try {
            List<Product> listAll = productAndPriceService.listAll();

            if (null != listAll) {
                model.addAttribute("productList", listAll);
                logger.debug("{} {}", new Object[]{IPUtil.getUserIP(request), listAll});
                return "old/list";
            }
            model.addAttribute("msg", "查询列表为空");
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取商品列表失败");
            model.addAttribute("msg", "查询列表失败");
            return "";
        }
    }

    private void addCookie(HttpServletResponse response, HttpSession session, Long productId) {
        Cookie addCookie = new Cookie("product_" + productId, session.getId());
        productAndPriceService.updatePageviews(productId);
        response.addCookie(addCookie);
    }

    private void updateVisit(HttpServletRequest request, HttpServletResponse response, HttpSession session, Long productId) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            int count = 0;
            for (Cookie ck : cookies) {
                if (StringUtils.equals(ck.getName(), "product_" + productId) && StringUtils.equals(session.getId(), ck.getValue())) {
                    //logger.debug("已存在Cookie:{}|{}|{}",ck.getName(),ck.getValue(),ck.getMaxAge());
                    break;
                }
                count++;
                if (count == cookies.length) {
                    addCookie(response, session, productId);
                }
            }
        } else {
            addCookie(response, session, productId);
        }
    }



    /*//根据选择套餐规格得到价格
    @RequestMapping(value = "/price", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    Result<Map> productPrice(@RequestParam("productId") Long productId, @RequestParam("accuracy") Integer accuracy, @RequestParam(required = false, name = "amountStr") String amountStr) {
        try {
            if (null == productId || null == accuracy) {
                return new Result(false, "参数为空");
            }
            if (StringUtils.isBlank(amountStr)) {
                PricePackage pricePackage = pricePackageService.getById(productId);//productId
                if (null != pricePackage) {
                    amountStr = pricePackage.getAmountStr();
                    logger.debug("amountStr:{}", pricePackage);
                    //return new Result<>(false,"查询套餐结果为空");
                }

            }

            Map getPrice = new HashMap();
            getPrice.put("amount_str", amountStr);
            getPrice.put("id", productId);
            getPrice.put("accuracy", accuracy);
            PricePackage productPrice = productAndPriceService.getProductPrice(getPrice);
            if (null == productPrice) {
                if (5 == productId) {
                    Map priceResult = new HashMap();
                    priceResult.put("total", "免费");
                    priceResult.put("tips", "每日限2次下载，如另有需要，请加客服QQ：2415968308");
                    return new Result(false, priceResult, "查询套餐结果为空");
                } else {
                    Map priceResult = new HashMap();
                    priceResult.put("total", "即将上线");
                    return new Result(false, priceResult, "查询套餐结果为空");
                }
            }


            BigDecimal amount_int = new BigDecimal(productPrice.getAmount());
            BigDecimal price = new BigDecimal(productPrice.getPrice());
            BigDecimal total = amount_int.multiply(price).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);

            Map priceResult = new HashMap();
            priceResult.put("id", productPrice.getId());
            priceResult.put("price", productPrice.getPrice());
            priceResult.put("total", total.doubleValue());
            priceResult.put("unit", productPrice.getUnit());
            priceResult.put("product_id", productPrice.getProductId());
            priceResult.put("accuracy", productPrice.getAccuracy());
            priceResult.put("amount", productPrice.getAmount());
            priceResult.put("tips", productPrice.getTips());
            Result<Map> result = new Result<>(true, priceResult, "查询成功");

            //System.out.println("amountList[0]="+result.getData().indexOf(0));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("错误信息:{}", e);
            return new Result(false, "查询失败");
        }
    }*/
}