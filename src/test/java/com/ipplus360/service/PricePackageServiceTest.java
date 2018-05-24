package com.ipplus360.service;

import com.ipplus360.entity.PricePackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class PricePackageServiceTest {

    @Autowired PricePackageService pricePackageService;

    @Test
    public void getPackagesByProduct() throws Exception {
        List<PricePackage> pricePackages = pricePackageService.getPackagesByProduct(2L);
        for (PricePackage pricePackageackage : pricePackages) {
            System.out.println(pricePackageackage);
        }
    }

}