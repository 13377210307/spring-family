package com.cache;

import com.cache.service.bitMap.BitMapService;
import com.cache.service.cachePuncture.CachePunctureService;
import com.cache.vo.LikeVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: w
 * @Date: 2021/2/19 22:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheTest {

    @Autowired
    private CachePunctureService cachePunctureService;

    @Autowired
    private BitMapService bitMapService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {

        for (int i = 1; i <= 2; i++) {
            Thread thread = new Thread(() -> {
                this.cachePunctureService.getOrderDetail(1L);
            });
            thread.start();
        }

    }

    @Test
    public void test2() {
        LikeVo likeVo = new LikeVo();
        likeVo.setContentId(500L);
        likeVo.setUserId(203L);
        likeVo.setType(true);
        this.bitMapService.likeOrUnlike(likeVo);
    }

    @Test
    public void test3() {
        Boolean like = this.bitMapService.isLike(500L, 200L);
        System.out.println(like);
    }

    @Test
    public void test4() {
        Long likeCount = this.bitMapService.likeCount(500L);
        System.out.println(likeCount);
    }



}


