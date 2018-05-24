package com.ipplus360.service.impl;

import com.ipplus360.entity.FileOrder;
import com.ipplus360.entity.ProductAttr;
import com.ipplus360.service.FileOrderService;
import com.ipplus360.service.ProductAttrService;
import com.ipplus360.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 辉太郎 on 2017/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class FileOrderServiceImplTest {

    @Autowired
    private FileOrderService orderService;
    @Autowired private UserService userService;
    @Autowired private ProductAttrService attrService;

    @Test
    public void processAdd() throws Exception {
        FileOrder fileOrder = new FileOrder();
        fileOrder.setAttrs("test");
        orderService.processAdd(fileOrder);
    }

    @Test
    public void getUser() throws Exception {
        System.err.println(userService.getById(12L));
    }

    @Test
    public void getByUserIdAndFileId() throws Exception {
        FileOrder fileOrder = orderService.getByUserIdAndFileId(3L, 3L);
        System.err.println("fileOrder:" + fileOrder);
    }

    @Test
    public void test() {
        FileOrder fileOrder = orderService.getByUserIdAndFileId(12L, 9L);
        fileOrder.getVersion();
    }

    @Test
    public void getAttr() {
        Integer[] ids = new Integer[] { 1, 4, 16 };

        String idsStr = StringUtils.join(ids, ",");
        List<ProductAttr> attrs = attrService.getAttrsByIds(ids);
        for (ProductAttr attr : attrs) {
            System.err.println("attr:" + attr);
        }
    }

}