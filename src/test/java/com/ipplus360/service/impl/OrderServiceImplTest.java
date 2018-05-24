package com.ipplus360.service.impl;

import com.ipplus360.entity.Order;
import com.ipplus360.entity.OrderItem;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.User;
import com.ipplus360.service.OrderService;
import com.ipplus360.service.UserService;
import com.ipplus360.service.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class OrderServiceImplTest {

    @Autowired private OrderService orderService;
    @Autowired private UserService userService;
    @Autowired private MailService mailService;

    @Test
    public void getAll() throws Exception {
        List<Order> orderList = orderService.getAll(13L, 0, 10);
        for (Order order : orderList) {
            List<OrderItem> items = order.getOrderItems();
            for (OrderItem item : items) {
                System.err.println("item:" + item);
            }
        }
    }

    @Test
    public void geByUserIdAndOrderSerial() {
        Order order = orderService.getByUserAndOrder(13L, "IP-1710281566609507");
        System.err.println(order);
    }

    @Test
    public void testGetAll() {
        List<Order> orders = orderService.getAllOrdersByUserid(13L, 1, 10);
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            System.err.println("length:" + orderItems.size());
            if (null != orderItems && orderItems.size() > 0) {
                for (OrderItem item : orderItems) {
                    System.err.println("item:" + item);
                    Long pid = item.getPricePackageId();
                    if (null != pid) {
                        /*PricePackage pricePackage = pricePackageService.getById(pid);
                        if (null != pricePackage && pricePackage.isAvailable()) {
                            item.setPricePackage(pricePackage);
                        }*/
                        System.err.println("pid:" + pid);
                    }
                }
            }
        }
    }

    @Test
    public void testGetUser() {
        User user = userService.getById(15L);
        Order order = orderService.getById1(128L);
        mailService.sendOrderEmail("lijian@ipplus360.com", "埃文商城发货通知", order , user , null );


    }
}