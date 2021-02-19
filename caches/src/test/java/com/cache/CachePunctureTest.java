package com.cache;

import com.cache.service.cachePuncture.CachePunctureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: w
 * @Date: 2021/2/19 22:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CachePunctureTest {

    @Autowired
    private CachePunctureService cachePunctureService;

    @Test
    public void test() {

        for (int i = 1; i <= 2; i++) {
            Thread thread = new Thread(() -> {
                this.cachePunctureService.getOrderDetail(1L);
            });
            thread.start();
        }

    }

}


