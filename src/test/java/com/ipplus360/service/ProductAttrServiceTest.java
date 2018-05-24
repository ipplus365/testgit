package com.ipplus360.service;

import com.ipplus360.entity.ProductAttr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class ProductAttrServiceTest {

    @Autowired private ProductAttrService productAttrService;

    @Test
    public void getTopAttrs() throws Exception {
        List<ProductAttr> topAttrs = productAttrService.getTopAttrs(3L);
        for (ProductAttr attr : topAttrs) {
            System.out.println(attr);
        }
    }

    @Test
    public void getAttrsByParent() throws Exception {
        List<ProductAttr> childAttr = productAttrService.getAttrsByParent(3);
        for (ProductAttr attr : childAttr) {
            System.out.println(attr);
        }
    }

    @Test
    public void getAttrsByIds() throws Exception {
        Integer[] ids = {1, 3, 5, 6};
        List<ProductAttr> attrs = productAttrService.getAttrsByIds(ids);
        for (ProductAttr attr : attrs) {
            System.out.println(attr);
        }
    }

}